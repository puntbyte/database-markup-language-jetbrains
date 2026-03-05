package com.puntbyte.dbml.ide.fixers

import com.intellij.codeInspection.LocalQuickFix
import com.intellij.codeInspection.ProblemDescriptor
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiDocumentManager
import com.puntbyte.dbml.util.DbmlUtil

class DbmlCreateColumnQuickFix(
  private val tableName: String,
  private val columnName: String,
  private val inferredType: String
) : LocalQuickFix {

  override fun getFamilyName(): String = "Create DBML Column"
  override fun getName(): String = "Create column '$columnName' in '$tableName'"

  override fun applyFix(project: Project, descriptor: ProblemDescriptor) {
    // Find the actual table definition in the project
    val table = DbmlUtil.findTableByName(project, tableName).firstOrNull() ?: return
    val file = table.containingFile
    val document = PsiDocumentManager.getInstance(project).getDocument(file) ?: return

    // We find the closing '}' of the table block
    val block = table.tableBlock
    val insertOffset = block?.textRange?.endOffset?.minus(1) ?: return

    val template = "  $columnName $inferredType\n"

    WriteCommandAction.runWriteCommandAction(project) {
      document.insertString(insertOffset, template)
      PsiDocumentManager.getInstance(project).commitDocument(document)
    }
  }
}