package com.puntbyte.dbml.ide.folding

import com.intellij.lang.ASTNode
import com.intellij.lang.folding.FoldingBuilderEx
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.psi.PsiElement
import com.intellij.psi.util.PsiTreeUtil
import com.puntbyte.dbml.psi.DbmlTypes

class DbmlFoldingBuilder : FoldingBuilderEx() {

  override fun buildFoldRegions(
    root: PsiElement,
    document: Document,
    quick: Boolean
  ): Array<FoldingDescriptor> {
    val descriptors = mutableListOf<FoldingDescriptor>()

    // Find all blocks to fold
    PsiTreeUtil.processElements(root) { element ->
      val type = element.node.elementType
      if (type == DbmlTypes.TABLE_BLOCK ||
        type == DbmlTypes.ENUM_BLOCK ||
        type == DbmlTypes.PROJECT_BLOCK ||
        type == DbmlTypes.NOTE_BLOCK ||
        type == DbmlTypes.GROUP_BLOCK ||
        type == DbmlTypes.INDEX_BLOCK ||   // <-- ADD THIS
        type == DbmlTypes.CHECK_BLOCK
      ) {   // <-- ADD THIS

        val range = element.textRange
        if (range.length > 2) {
          descriptors.add(FoldingDescriptor(element.node, range))
        }
      }

      true // Continue scanning
    }

    return descriptors.toTypedArray()
  }

  override fun getPlaceholderText(node: ASTNode): String = "{...}"

  override fun isCollapsedByDefault(node: ASTNode): Boolean = false
}