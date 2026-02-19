package com.puntbyte.dbml.ide.formatter

import com.intellij.formatting.*
import com.intellij.lang.ASTNode
import com.intellij.psi.TokenType
import com.intellij.psi.formatter.common.AbstractBlock
import com.intellij.psi.tree.IElementType
import com.puntbyte.dbml.psi.DbmlTypes

class DbmlBlock(
  node: ASTNode,
  wrap: Wrap?,
  alignment: Alignment?,
  private val spacingBuilder: SpacingBuilder
) : AbstractBlock(node, wrap, alignment) {

  override fun buildChildren(): List<Block> {
    val blocks = ArrayList<Block>()
    var child = myNode.firstChildNode
    while (child != null) {
      if (child.elementType !== TokenType.WHITE_SPACE) {
        val block = DbmlBlock(
          child,
          Wrap.createWrap(WrapType.NONE, false),
          null,
          spacingBuilder
        )
        blocks.add(block)
      }
      child = child.treeNext
    }
    return blocks
  }

  override fun getIndent(): Indent? {
    val type = myNode.elementType

    // Opening and closing braces/brackets/parens should NOT have additional indent
    // relative to their parent block. The parent element's indent handles nesting.
    if (type == DbmlTypes.R_BRACE || type == DbmlTypes.R_BRACKET || type == DbmlTypes.R_PAREN ||
      type == DbmlTypes.L_BRACE || type == DbmlTypes.L_BRACKET || type == DbmlTypes.L_PAREN
    ) {
      return Indent.getNoneIndent()
    }

    // Content inside block containers should be indented one level
    val parentType = myNode.treeParent?.elementType
    if (isBlockContainer(parentType)) {
      return Indent.getNormalIndent()
    }

    return Indent.getNoneIndent()
  }

  override fun getChildAttributes(newChildIndex: Int): ChildAttributes {
    if (isBlockContainer(myNode.elementType)) {
      // New content inside block containers should be indented
      return ChildAttributes(Indent.getNormalIndent(), null)
    }
    return ChildAttributes(Indent.getNoneIndent(), null)
  }

  override fun getSpacing(child1: Block?, child2: Block): Spacing? {
    return spacingBuilder.getSpacing(this, child1, child2)
  }

  override fun isLeaf(): Boolean = myNode.firstChildNode == null

  private fun isBlockContainer(type: IElementType?): Boolean {
    return type == DbmlTypes.TABLE_BLOCK ||
        type == DbmlTypes.PROJECT_BLOCK ||
        type == DbmlTypes.ENUM_BLOCK ||
        type == DbmlTypes.PARTIAL_BLOCK ||
        type == DbmlTypes.GROUP_BLOCK ||
        type == DbmlTypes.INDEX_BLOCK ||
        type == DbmlTypes.CHECK_BLOCK ||
        type == DbmlTypes.NOTE_BLOCK ||
        type == DbmlTypes.REFERENCE_BLOCK
  }
}