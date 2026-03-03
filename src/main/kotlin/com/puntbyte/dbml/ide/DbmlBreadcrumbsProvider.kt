package com.puntbyte.dbml.ide

import com.intellij.lang.Language
import com.intellij.psi.PsiElement
import com.intellij.ui.breadcrumbs.BreadcrumbsProvider
import com.puntbyte.dbml.core.DbmlLanguage
import com.puntbyte.dbml.psi.*

class DbmlBreadcrumbsProvider : BreadcrumbsProvider {
  override fun getLanguages(): Array<Language> = arrayOf(DbmlLanguage)

  // Tell JetBrains which elements should create a breadcrumb
  override fun acceptElement(element: PsiElement): Boolean {
    return element is DbmlTableDefinition ||
        element is DbmlColumnDefinition ||
        element is DbmlEnumDefinition ||
        element is DbmlProjectDefinition
  }

  // What text should the breadcrumb display?
  override fun getElementInfo(element: PsiElement): String {
    return when (element) {
      is DbmlTableDefinition -> element.name ?: "Unnamed Table"
      is DbmlColumnDefinition -> element.columnIdentifier.text
      is DbmlEnumDefinition -> "Enum" // Assuming Enum doesn't implement PsiNameIdentifierOwner yet
      is DbmlProjectDefinition -> "Project"
      else -> ""
    }
  }
}