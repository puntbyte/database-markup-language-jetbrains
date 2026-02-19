package com.puntbyte.dbml.ide.formatter

import com.intellij.formatting.*
import com.intellij.psi.codeStyle.CodeStyleSettings
import com.puntbyte.dbml.core.DbmlLanguage
import com.puntbyte.dbml.psi.DbmlTypes

class DbmlFormattingModelBuilder : FormattingModelBuilder {

  override fun createModel(context: FormattingContext): FormattingModel {
    // Create the block structure
    val rootBlock = DbmlBlock(
      context.node,
      Wrap.createWrap(WrapType.NONE, false),
      /* alignment = */ null,
      createSpacingBuilder(context.codeStyleSettings)
    )

    return FormattingModelProvider
      .createFormattingModelForPsiFile(context.containingFile, rootBlock, context.codeStyleSettings)
  }

  private fun createSpacingBuilder(settings: CodeStyleSettings): SpacingBuilder {
    return SpacingBuilder(settings, DbmlLanguage)
      // Enforce New Line after opening brace (but avoid double newlines for empty blocks).
      .after(DbmlTypes.L_BRACE).lineBreakInCode()  // Table t { <ENTER>
      // NOTE: removed `.before(R_BRACE).lineBreakInCode()` to avoid double blank lines when block is empty

      // Spaces around settings [ ... ]
      .afterInside(DbmlTypes.L_BRACKET, DbmlTypes.SETTING_BLOCK).spaceIf(false)
      .beforeInside(DbmlTypes.R_BRACKET, DbmlTypes.SETTING_BLOCK).spaceIf(false)

      // General Punctuation
      .before(DbmlTypes.L_BRACE).spaceIf(true) // Table t <SPACE> {
      .after(DbmlTypes.COLON).spaceIf(true)    // name: <SPACE> 'val'
      .after(DbmlTypes.COMMA).spaceIf(true)    // col, <SPACE> col

      // Operators
      .around(DbmlTypes.OP_ONE_MANY).spaceIf(true)
      .around(DbmlTypes.OP_MANY_ONE).spaceIf(true)
      .around(DbmlTypes.OP_ONE_ONE).spaceIf(true)
      .around(DbmlTypes.OP_MANY_MANY).spaceIf(true)
  }

}