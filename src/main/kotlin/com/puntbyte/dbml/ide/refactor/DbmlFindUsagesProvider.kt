package com.puntbyte.dbml.ide.refactor

import com.intellij.lang.cacheBuilder.DefaultWordsScanner
import com.intellij.lang.cacheBuilder.WordsScanner
import com.intellij.lang.findUsages.FindUsagesProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiNamedElement
import com.intellij.psi.tree.TokenSet
import com.puntbyte.dbml.lexer.DbmlLexerAdapter
import com.puntbyte.dbml.psi.DbmlPartialDefinition
import com.puntbyte.dbml.psi.DbmlTableDefinition
import com.puntbyte.dbml.psi.DbmlTypes

class DbmlFindUsagesProvider : FindUsagesProvider {

  override fun getWordsScanner(): WordsScanner {
    // Tells the IDE how to break DBML files into searchable words
    return DefaultWordsScanner(
      DbmlLexerAdapter(),
      TokenSet.create(DbmlTypes.IDENTIFIER),
      TokenSet.create(DbmlTypes.LINE_COMMENT, DbmlTypes.DOC_COMMENT, DbmlTypes.BLOCK_COMMENT),
      TokenSet.create(DbmlTypes.STRING, DbmlTypes.MULTI_STRING)
    )
  }

  override fun canFindUsagesFor(element: PsiElement): Boolean = element is PsiNamedElement

  override fun getHelpId(psiElement: PsiElement): String? = null

  override fun getType(element: PsiElement): String {
    return when (element) {
      is DbmlTableDefinition -> "Table"
      is DbmlPartialDefinition -> "Partial"
      else -> ""
    }
  }

  override fun getDescriptiveName(element: PsiElement): String {
    return (element as? PsiNamedElement)?.name ?: ""
  }

  override fun getNodeText(element: PsiElement, useFullName: Boolean): String {
    return getDescriptiveName(element)
  }
}