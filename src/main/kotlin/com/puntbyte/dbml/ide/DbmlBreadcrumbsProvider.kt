package com.puntbyte.dbml.ide

import com.intellij.lang.Language
import com.intellij.psi.PsiElement
import com.intellij.ui.breadcrumbs.BreadcrumbsProvider
import com.puntbyte.dbml.core.DbmlLanguage
import com.puntbyte.dbml.psi.*

class DbmlBreadcrumbsProvider : BreadcrumbsProvider {
  override fun getLanguages(): Array<Language> = arrayOf(DbmlLanguage)

  override fun acceptElement(element: PsiElement): Boolean {
    return element is DbmlTableDefinition ||
        element is DbmlPartialDefinition ||
        element is DbmlColumnDefinition ||
        element is DbmlEnumDefinition ||
        element is DbmlProjectDefinition ||
        element is DbmlGroupDefinition
  }

  override fun getElementInfo(element: PsiElement): String {
    return when (element) {
      is DbmlTableDefinition -> element.name ?: "Unnamed Table"
      is DbmlPartialDefinition -> element.name ?: "Unnamed Partial"
      is DbmlColumnDefinition -> element.identifier.text ?: "Column"
      is DbmlEnumDefinition -> element.enumIdentifier?.text ?: "Enum"
      is DbmlGroupDefinition -> element.identifier?.text ?: "Group"
      is DbmlProjectDefinition -> "Project"
      else -> ""
    }
  }
}