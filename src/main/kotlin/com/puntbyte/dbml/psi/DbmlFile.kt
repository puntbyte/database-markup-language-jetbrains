package com.puntbyte.dbml.psi

import com.intellij.extapi.psi.PsiFileBase
import com.intellij.psi.FileViewProvider
import com.puntbyte.dbml.core.DbmlFileType
import com.puntbyte.dbml.core.DbmlLanguage

class DbmlFile(viewProvider: FileViewProvider) : PsiFileBase(viewProvider, DbmlLanguage) {
  override fun getFileType() = DbmlFileType
  override fun toString() = "Database Markup Language"
}