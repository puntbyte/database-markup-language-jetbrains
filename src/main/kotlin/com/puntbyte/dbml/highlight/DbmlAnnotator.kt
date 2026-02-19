package com.puntbyte.dbml.highlight

import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.psi.PsiElement
import com.puntbyte.dbml.psi.*

class DbmlAnnotator : Annotator {
  override fun annotate(element: PsiElement, holder: AnnotationHolder) {

    // 1. Highlight Table Names (Definitions)
    // If 'Table' or 'Project' keywords are used as names, this recolors them properly.
    if (element.parent is DbmlTableIdentifier) {
      applyColor(holder, element, DefaultLanguageHighlighterColors.CLASS_NAME)
      return
    }

    // 2. Highlight Column Names
    if (element.parent is DbmlColumnIdentifier) {
      // This is CRITICAL: If the column is named "int" or "Table",
      // the Lexer colored it as Keyword/Type. We force it to look like a Field.
      applyColor(holder, element, DefaultLanguageHighlighterColors.INSTANCE_FIELD)
      return
    }

    // 3. Highlight Column Types (Strictly the ID part)
    // We check if the current element is an 'ID' node, AND its parent is 'ColumnType'
    if (element is DbmlId && element.parent is DbmlColumnType) {
      applyColor(holder, element, DefaultLanguageHighlighterColors.FUNCTION_CALL)
      return
    }

    // 4. Highlight Settings Map Keys (e.g., 'pk', 'note' inside [])
    // Covers: [pk] AND [note: 'value']
    if (element.parent is DbmlMapKey) {
      applyColor(holder, element, DefaultLanguageHighlighterColors.METADATA)
      return
    }

    // 5. Highlight Refs (Endpoints)
    // e.g. users.id in `Ref: users.id < posts.id`
    if (element.parent is DbmlNamespaceIdentifier && element.parent.parent is DbmlReferenceEndpoint) {
      applyColor(holder, element, DefaultLanguageHighlighterColors.FUNCTION_DECLARATION)
      return
    }
  }

  private fun applyColor(holder: AnnotationHolder, element: PsiElement, color: TextAttributesKey) {
    holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
      .range(element.textRange)
      .textAttributes(color)
      .create()
  }
}