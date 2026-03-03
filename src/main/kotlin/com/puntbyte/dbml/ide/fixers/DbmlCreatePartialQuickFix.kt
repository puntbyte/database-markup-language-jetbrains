package com.puntbyte.dbml.ide.fixers

import com.intellij.codeInspection.LocalQuickFix
import com.intellij.codeInspection.ProblemDescriptor
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiDocumentManager

class DbmlCreatePartialQuickFix(private val partialName: String) : LocalQuickFix {
  override fun getFamilyName(): String = "Create DBML Partial"
  override fun getName(): String = "Create Partial '$partialName'"

  override fun applyFix(project: Project, descriptor: ProblemDescriptor) {
    val file = descriptor.psiElement.containingFile
    val document = PsiDocumentManager.getInstance(project).getDocument(file) ?: return
    val template = "\n\nTablePartial $partialName {\n  \n}\n"

    WriteCommandAction.runWriteCommandAction(project) {
      document.insertString(document.textLength, template)
      PsiDocumentManager.getInstance(project).commitDocument(document)
    }
  }
}