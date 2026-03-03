package com.puntbyte.dbml.psi

import com.intellij.openapi.project.Project
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFileFactory
import com.intellij.psi.util.PsiTreeUtil
import com.puntbyte.dbml.core.DbmlFileType

object DbmlElementFactory {

  fun createDummyFile(project: Project, text: String): DbmlFile {
    val name = "dummy.dbml"
    return PsiFileFactory.getInstance(project)
      .createFileFromText(name, DbmlFileType.INSTANCE, text) as DbmlFile
  }

  // Creates an isolated identifier node to swap during refactoring
  fun createIdentifier(project: Project, name: String): PsiElement {
    val file = createDummyFile(project, "Table $name {}")
    val table = PsiTreeUtil.findChildOfType(file, DbmlTableDefinition::class.java)
    return table!!.nameIdentifier!!
  }
}