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

        val sqlLanguage = Language.findLanguageByID("PostgreSQL")
          ?: Language.findLanguageByID("SQL")
          ?: return

        val innerText = text.substring(1, text.length - 1).trim()
        val upperText = innerText.uppercase()

        // Smart Prefixing:
        // If the user wrote a full statement (e.g. `SELECT * FROM users`), don't add prefix.
        // If it's just a function or expression (e.g. `now()`), wrap it in SELECT.
        val isFullStatement = upperText.startsWith("SELECT") || upperText.startsWith("WITH")

        val prefix = if (isFullStatement) null else "SELECT "
        val suffix = if (isFullStatement) null else ";"

        registrar.startInjecting(sqlLanguage)

        // Add the prefix and suffix so the SQL parser evaluates it correctly!
        registrar.addPlace(
          prefix,
          suffix,
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