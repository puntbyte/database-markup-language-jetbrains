package com.puntbyte.dbml.ide.fixers

import com.intellij.codeInspection.LocalQuickFix
import com.intellij.codeInspection.ProblemDescriptor
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiDocumentManager

class DbmlCreateEnumQuickFix(private val enumName: String) : LocalQuickFix {
  override fun getFamilyName(): String = "Create DBML Enum"
  override fun getName(): String = "Create Enum '$enumName'"

  override fun applyFix(project: Project, descriptor: ProblemDescriptor) {
    val file = descriptor.psiElement.containingFile
    val document = PsiDocumentManager.getInstance(project).getDocument(file) ?: return
    val template = "\n\nEnum $enumName {\n  value1\n  value2\n}\n"

    WriteCommandAction.runWriteCommandAction(project) {
      document.insertString(document.textLength, template)
      PsiDocumentManager.getInstance(project).commitDocument(document)
    }
  }
}