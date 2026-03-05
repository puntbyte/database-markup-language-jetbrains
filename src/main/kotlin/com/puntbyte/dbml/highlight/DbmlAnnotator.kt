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

    // 1. Highlight standard wrapper definitions (Tables, Partials, Enums)
    if (element is DbmlTableIdentifier || element is DbmlEnumIdentifier || element is DbmlPartialIdentifier) {
      applyColor(holder, element, DefaultLanguageHighlighterColors.CLASS_NAME)
      return
    }

    // 2. Highlight standalone Identifiers (Columns, Projects, Groups)
    if (element is DbmlIdentifier) {
      val parent = element.parent

      // If it's the name of a Project or a Group
      if (parent is DbmlProjectDefinition || parent is DbmlGroupDefinition) {
        applyColor(holder, element, DefaultLanguageHighlighterColors.CLASS_NAME)
        return
      }

      // If it's the name of a Column (first identifier in a ColumnDefinition)
      if (parent is DbmlColumnDefinition && element == parent.identifier) {
        applyColor(holder, element, DefaultLanguageHighlighterColors.INSTANCE_FIELD)
        return
      }
    }

    // 3. Highlight DataTypes inside Column Definitions
    if (element is DbmlDataType) {
      applyColor(holder, element, DefaultLanguageHighlighterColors.FUNCTION_CALL)
      return
    }

    // 4. Highlight Settings Map Keys
    if (element is DbmlMapKey) {
      applyColor(holder, element, DefaultLanguageHighlighterColors.METADATA)
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