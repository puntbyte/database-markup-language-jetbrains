package com.puntbyte.dbml.reference

import com.intellij.openapi.util.TextRange
import com.intellij.patterns.PlatformPatterns
import com.intellij.psi.*
import com.intellij.util.ProcessingContext
import com.puntbyte.dbml.psi.DbmlId
import com.puntbyte.dbml.psi.DbmlNamespaceIdentifier

class DbmlReferenceContributor : PsiReferenceContributor() {
  override fun registerReferenceProviders(registrar: PsiReferenceRegistrar) {

    // CHANGE 1: Target the DbmlId composite element, NOT the raw IDENTIFIER token
    registrar.registerReferenceProvider(
      PlatformPatterns.psiElement(DbmlId::class.java),
      object : PsiReferenceProvider() {
        override fun getReferencesByElement(
          element: PsiElement,
          context: ProcessingContext
        ): Array<PsiReference> {
          val idElement = element as DbmlId

          // CHANGE 2: In 'users.id', 'users' is the table, 'id' is the column.
          // We only want to resolve the FIRST word as a Table!
          val parent = idElement.parent
          if (parent is DbmlNamespaceIdentifier) {
            val firstId = parent.idList.firstOrNull()
            if (firstId != idElement) {
              // This is a column name, not a table. Don't add a table reference here!
              return PsiReference.EMPTY_ARRAY
            }
          }

          val prevSibling = idElement.prevSibling
          if (prevSibling != null && prevSibling.text == "~") {
            return arrayOf(DbmlPartialReference(idElement, TextRange(0, idElement.textLength)))
          }

          // Default Table Reference
          return arrayOf(DbmlTableReference(idElement, TextRange(0, idElement.textLength)))
        }
      }
    )
  }
}