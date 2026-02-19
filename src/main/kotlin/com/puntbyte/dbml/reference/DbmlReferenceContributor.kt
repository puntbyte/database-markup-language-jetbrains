package com.puntbyte.dbml.reference

import com.intellij.openapi.util.TextRange
import com.intellij.patterns.PlatformPatterns
import com.intellij.psi.*
import com.intellij.util.ProcessingContext
import com.puntbyte.dbml.psi.DbmlTypes

class DbmlReferenceContributor : PsiReferenceContributor() {
  override fun registerReferenceProviders(registrar: PsiReferenceRegistrar) {
    // Apply this reference logic to any IDENTIFIER
    // In a real scenario, you should make the pattern more specific
    // (e.g., only identifiers inside a RefExpression)
    // Inside registerReferenceProviders...
    registrar.registerReferenceProvider(
      PlatformPatterns.psiElement(DbmlTypes.IDENTIFIER),
      object : PsiReferenceProvider() {
        override fun getReferencesByElement(
          element: PsiElement,
          context: ProcessingContext
        ): Array<PsiReference> {
          val text = element.text
          val prevSibling = element.prevSibling

          // Check if previous token is '~' (Partial Injection)
          if (prevSibling != null && prevSibling.text == "~") {
            return arrayOf(DbmlPartialReference(element, TextRange(0, element.textLength)))
          }

          // Default Table Reference
          return arrayOf(DbmlTableReference(element, TextRange(0, element.textLength)))
        }
      }
    )
  }
}