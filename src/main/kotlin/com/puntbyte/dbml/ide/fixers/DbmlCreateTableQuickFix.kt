package com.puntbyte.dbml.ide.fixers

import com.intellij.codeInspection.LocalQuickFix
import com.intellij.codeInspection.ProblemDescriptor
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiDocumentManager

class DbmlCreateTableQuickFix(
  private val tableName: String,
  private val columnName: String,
  private val inferredType: String
) : LocalQuickFix {

  override fun getFamilyName(): String = "Create DBML Table"
  override fun getName(): String = "Create table '$tableName'"

  override fun applyFix(project: Project, descriptor: ProblemDescriptor) {
    val file = descriptor.psiElement.containingFile
    val document = PsiDocumentManager.getInstance(project).getDocument(file) ?: return

    // Creates the Table AND the required column with the inferred type!
    val template = "\n\nTable $tableName {\n  $columnName $inferredType\n}\n"

    WriteCommandAction.runWriteCommandAction(project) {
      document.insertString(document.textLength, template)
      PsiDocumentManager.getInstance(project).commitDocument(document)
    }
  }
}