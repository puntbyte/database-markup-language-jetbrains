package com.puntbyte.dbml.ide.refactor

import com.intellij.lang.refactoring.RefactoringSupportProvider
import com.intellij.psi.PsiElement
import com.puntbyte.dbml.psi.DbmlPartialDefinition
import com.puntbyte.dbml.psi.DbmlTableDefinition

class DbmlRefactoringSupportProvider : RefactoringSupportProvider() {
  override fun isMemberInplaceRenameAvailable(element: PsiElement, context: PsiElement?): Boolean {
    // Allow renaming Tables and Partials!
    return element is DbmlTableDefinition || element is DbmlPartialDefinition
  }
}