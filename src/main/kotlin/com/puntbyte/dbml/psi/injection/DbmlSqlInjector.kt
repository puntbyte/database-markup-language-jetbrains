package com.puntbyte.dbml.ide.injection

import com.intellij.lang.Language
import com.intellij.lang.injection.MultiHostInjector
import com.intellij.lang.injection.MultiHostRegistrar
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiLanguageInjectionHost
import com.puntbyte.dbml.psi.DbmlExpressionWrapper

class DbmlSqlInjector : MultiHostInjector {

  override fun getLanguagesToInject(registrar: MultiHostRegistrar, context: PsiElement) {
    if (context is DbmlExpressionWrapper) {
      val text = context.text
      // Ensure it's wrapped in backticks
      if (text.length >= 2 && text.startsWith("`") && text.endsWith("`")) {

        // Try to find the SQL language (fallback to PostgreSQL if available)
        val sqlLanguage = Language.findLanguageByID("PostgreSQL")
          ?: Language.findLanguageByID("SQL")
          ?: return

        registrar.startInjecting(sqlLanguage)
        // Inject everything EXCEPT the two backticks (TextRange from 1 to length - 1)
        registrar.addPlace(
          null,
          null,
          context as PsiLanguageInjectionHost,
          TextRange(1, text.length - 1)
        )
        registrar.doneInjecting()
      }
    }
  }

  override fun elementsToInjectIn(): List<Class<out PsiElement>> {
    return listOf(DbmlExpressionWrapper::class.java)
  }
}