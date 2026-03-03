package com.puntbyte.dbml.ide

import com.intellij.lang.BracePair
import com.intellij.lang.PairedBraceMatcher
import com.intellij.psi.PsiFile
import com.intellij.psi.tree.IElementType
import com.puntbyte.dbml.psi.DbmlTypes

class DbmlBraceMatcher : PairedBraceMatcher {
  private val pairs = arrayOf(
    BracePair(
      DbmlTypes.L_BRACE,
      DbmlTypes.R_BRACE,
      true
    ),
    BracePair(DbmlTypes.L_BRACKET, DbmlTypes.R_BRACKET, false),
    BracePair(DbmlTypes.L_PAREN, DbmlTypes.R_PAREN, false)
  )

  override fun getPairs(): Array<BracePair> = pairs

  override fun isPairedBracesAllowedBeforeType(
    lbraceType: IElementType,
    contextType: IElementType?
  ): Boolean {
    return true
  }

  override fun getCodeConstructStart(file: PsiFile?, openingBraceOffset: Int): Int {
    return openingBraceOffset
  }
}