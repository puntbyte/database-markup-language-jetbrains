package com.puntbyte.dbml.psi

import com.intellij.openapi.project.Project
import com.intellij.psi.PsiFileFactory
import com.intellij.psi.util.PsiTreeUtil
import com.puntbyte.dbml.core.DbmlFileType

object DbmlElementFactory {
  fun createDummyFile(project: Project, text: String): DbmlFile {
    return PsiFileFactory.getInstance(project)
      .createFileFromText("dummy.dbml", DbmlFileType.INSTANCE, text) as DbmlFile
  }

  fun createTableIdentifier(project: Project, name: String): DbmlTableIdentifier {
    val file = createDummyFile(project, "Table $name {}")
    val table = PsiTreeUtil.findChildOfType(file, DbmlTableDefinition::class.java)
    return table!!.tableIdentifier!!
  }

  fun createPartialIdentifier(project: Project, name: String): DbmlPartialIdentifier {
    val file = createDummyFile(project, "TablePartial $name {}")
    val partial = PsiTreeUtil.findChildOfType(file, DbmlPartialDefinition::class.java)
    return partial!!.partialIdentifier!!
  }
}