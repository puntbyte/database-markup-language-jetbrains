package com.puntbyte.dbml.highlight

import com.intellij.lexer.Lexer
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.HighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.psi.TokenType
import com.intellij.psi.tree.IElementType
import com.puntbyte.dbml.lexer.DbmlLexerAdapter
import com.puntbyte.dbml.psi.DbmlTypes

class DbmlSyntaxHighlighter : SyntaxHighlighterBase() {

  override fun getHighlightingLexer(): Lexer = DbmlLexerAdapter()

  override fun getTokenHighlights(tokenType: IElementType): Array<TextAttributesKey> {
    return when (tokenType) {
      // --- Keywords ---
      DbmlTypes.KW_TABLE, DbmlTypes.KW_GROUP, DbmlTypes.KW_PROJECT,
      DbmlTypes.KW_REF, DbmlTypes.KW_ENUM, DbmlTypes.KW_PARTIAL,
      DbmlTypes.KW_INDEXES, DbmlTypes.KW_CHECKS, DbmlTypes.KW_AS,
      DbmlTypes.KW_NOTE -> PACK(KEYWORD)

      // --- Data Types ---
      // Highlighting standard types (int, varchar)
      DbmlTypes.DATA_TYPE -> PACK(DATA_TYPE)

      // --- Settings ---
      // Keys like 'pk', 'unique', 'headercolor'
      DbmlTypes.SETTING_KEY -> PACK(METADATA)
      // Values like 'cascade', 'restrict'
      DbmlTypes.SETTING_VAL -> PACK(KEYWORD)

      // --- Values ---
      DbmlTypes.STRING, DbmlTypes.MULTI_STRING -> PACK(STRING)
      DbmlTypes.EXPRESSION -> PACK(EXPRESSION) // Backticks
      DbmlTypes.NUMBER -> PACK(NUMBER)
      DbmlTypes.COLOR -> PACK(NUMBER) // Hex colors look good as numbers
      DbmlTypes.BOOLEAN, DbmlTypes.NULL -> PACK(KEYWORD) // Treat null/true/false like keywords

      // --- Comments ---
      DbmlTypes.LINE_COMMENT -> PACK(LINE_COMMENT)
      DbmlTypes.DOC_COMMENT -> PACK(DOC_COMMENT)
      DbmlTypes.BLOCK_COMMENT -> PACK(BLOCK_COMMENT)

      // --- Structure & Operators ---
      DbmlTypes.L_BRACE, DbmlTypes.R_BRACE -> PACK(BRACES)
      DbmlTypes.L_BRACKET, DbmlTypes.R_BRACKET -> PACK(BRACKETS)
      DbmlTypes.L_PAREN, DbmlTypes.R_PAREN -> PACK(PARENTHESES)

      DbmlTypes.COLON, DbmlTypes.DOT, DbmlTypes.COMMA, DbmlTypes.TILDE,
      DbmlTypes.OP_ONE_MANY, DbmlTypes.OP_MANY_ONE,
      DbmlTypes.OP_ONE_ONE, DbmlTypes.OP_MANY_MANY -> PACK(OPERATOR)

      // --- Standard ---
      DbmlTypes.IDENTIFIER -> PACK(IDENTIFIER)
      TokenType.BAD_CHARACTER -> PACK(BAD_CHARACTER)
      else -> EMPTY_KEYS
    }
  }

  private fun PACK(key: TextAttributesKey): Array<TextAttributesKey> = arrayOf(key)

  companion object {
    val KEYWORD = TextAttributesKey.createTextAttributesKey(
      "DBML_KEYWORD",
      DefaultLanguageHighlighterColors.KEYWORD
    )
    val DATA_TYPE = TextAttributesKey.createTextAttributesKey(
      "DBML_DATA_TYPE",
      DefaultLanguageHighlighterColors.CLASS_NAME
    )
    val METADATA = TextAttributesKey.createTextAttributesKey(
      "DBML_METADATA",
      DefaultLanguageHighlighterColors.METADATA
    )

    val STRING = TextAttributesKey.createTextAttributesKey(
      "DBML_STRING",
      DefaultLanguageHighlighterColors.STRING
    )
    val EXPRESSION = TextAttributesKey.createTextAttributesKey(
      "DBML_EXPRESSION",
      DefaultLanguageHighlighterColors.MARKUP_ENTITY
    )
    val NUMBER = TextAttributesKey.createTextAttributesKey(
      "DBML_NUMBER",
      DefaultLanguageHighlighterColors.NUMBER
    )

    val LINE_COMMENT = TextAttributesKey.createTextAttributesKey(
      "DBML_COMMENT",
      DefaultLanguageHighlighterColors.LINE_COMMENT
    )
    val DOC_COMMENT = TextAttributesKey.createTextAttributesKey(
      "DBML_DOC_COMMENT",
      DefaultLanguageHighlighterColors.DOC_COMMENT
    )

    val BLOCK_COMMENT = TextAttributesKey.createTextAttributesKey(
      "DBML_BLOCK_COMMENT",
      DefaultLanguageHighlighterColors.BLOCK_COMMENT
    )

    val OPERATOR = TextAttributesKey.createTextAttributesKey(
      "DBML_OPERATOR",
      DefaultLanguageHighlighterColors.OPERATION_SIGN
    )
    val BRACES = TextAttributesKey.createTextAttributesKey(
      "DBML_BRACES",
      DefaultLanguageHighlighterColors.BRACES
    )
    val BRACKETS = TextAttributesKey.createTextAttributesKey(
      "DBML_BRACKETS",
      DefaultLanguageHighlighterColors.BRACKETS
    )
    val PARENTHESES = TextAttributesKey.createTextAttributesKey(
      "DBML_PARENTHESES",
      DefaultLanguageHighlighterColors.PARENTHESES
    )

    val IDENTIFIER = TextAttributesKey.createTextAttributesKey(
      "DBML_IDENTIFIER",
      DefaultLanguageHighlighterColors.IDENTIFIER
    )
    val BAD_CHARACTER = TextAttributesKey.createTextAttributesKey(
      "DBML_BAD_CHARACTER",
      HighlighterColors.BAD_CHARACTER
    )

    private val EMPTY_KEYS = emptyArray<TextAttributesKey>()
  }
}