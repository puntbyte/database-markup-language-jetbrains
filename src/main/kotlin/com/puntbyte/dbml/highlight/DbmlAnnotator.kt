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

    // 1. Highlight standard definitions (Tables, Partials, Enums, Projects)
    if (element is DbmlTableIdentifier || element is DbmlEnumIdentifier ||
      element is DbmlPartialIdentifier || element is DbmlProjectIdentifier ||
      element is DbmlGroupIdentifier
    ) {
      applyColor(holder, element, DefaultLanguageHighlighterColors.CLASS_NAME)
    }

    // 2. Highlight Columns
    if (element is DbmlColumnIdentifier) {
      applyColor(holder, element, DefaultLanguageHighlighterColors.INSTANCE_FIELD)
    }

    // 3. Highlight DataTypes inside Column Definitions
    if (element is DbmlDataType) {
      applyColor(holder, element, DefaultLanguageHighlighterColors.FUNCTION_CALL)
    }

    // 4. Highlight Settings Map Keys
    if (element is DbmlMapKey) {
      applyColor(holder, element, DefaultLanguageHighlighterColors.METADATA)
    }

    // 5. Schema Identifier
    if (element is DbmlSchemaIdentifier) {
      applyColor(
        holder,
        element,
        DefaultLanguageHighlighterColors.KEYWORD
      ) // Or however you want Schemas styled
    }
  }

  private fun applyColor(holder: AnnotationHolder, element: PsiElement, color: TextAttributesKey) {
    holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
      .range(element.textRange)
      .textAttributes(color)
      .create()
  }
}