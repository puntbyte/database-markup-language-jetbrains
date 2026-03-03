package com.puntbyte.dbml.ide

import com.intellij.lang.documentation.AbstractDocumentationProvider
import com.intellij.lang.documentation.DocumentationMarkup
import com.intellij.psi.PsiElement
import com.puntbyte.dbml.psi.DbmlTableDefinition

class DbmlDocumentationProvider : AbstractDocumentationProvider() {

  // This is called when the user hovers over a resolved reference or a definition
  override fun generateDoc(element: PsiElement, originalElement: PsiElement?): String? {
    if (element is DbmlTableDefinition) {
      val tableName = element.name ?: "Unknown"
      val alias = element.aliasClause?.text?.let { " $it" } ?: ""

      // Build a JetBrains-styled HTML documentation popup
      return buildString {
        append(DocumentationMarkup.DEFINITION_START)
        append("Table: <b>").append(tableName).append("</b>").append(alias)
        append(DocumentationMarkup.DEFINITION_END)

        append(DocumentationMarkup.CONTENT_START)
        append("Database Markup Language Table Definition")
        append(DocumentationMarkup.CONTENT_END)
      }
    }
    return null
  }
}