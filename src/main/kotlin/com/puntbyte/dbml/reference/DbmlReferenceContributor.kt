package com.puntbyte.dbml.reference

import com.intellij.openapi.util.TextRange
import com.intellij.patterns.PlatformPatterns
import com.intellij.psi.*
import com.intellij.util.ProcessingContext
import com.puntbyte.dbml.psi.DbmlPartialIdentifier
import com.puntbyte.dbml.psi.DbmlTableIdentifier

class DbmlReferenceContributor : PsiReferenceContributor() {
  override fun registerReferenceProviders(registrar: PsiReferenceRegistrar) {

    // 1. Target ONLY Table Identifiers!
    registrar.registerReferenceProvider(
      PlatformPatterns.psiElement(DbmlTableIdentifier::class.java),
      object : PsiReferenceProvider() {
        override fun getReferencesByElement(
          element: PsiElement,
          context: ProcessingContext
        ): Array<PsiReference> {
          return arrayOf(DbmlTableReference(element, TextRange(0, element.textLength)))
        }
      }
    )

    // 2. Target ONLY Partial Identifiers!
    registrar.registerReferenceProvider(
      PlatformPatterns.psiElement(DbmlPartialIdentifier::class.java),
      object : PsiReferenceProvider() {
        override fun getReferencesByElement(
          element: PsiElement,
          context: ProcessingContext
        ): Array<PsiReference> {
          return arrayOf(DbmlPartialReference(element, TextRange(0, element.textLength)))
        }
      }
    )
  }
}