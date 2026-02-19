package com.puntbyte.dbml.core

import com.intellij.openapi.fileTypes.LanguageFileType
import javax.swing.Icon

object DbmlFileType : LanguageFileType(DbmlLanguage) {
  override fun getName() = "Database Markup Language"
  override fun getDescription() =
    "Database Markup Language (DBML) is a language for describing database schemas."

  override fun getDefaultExtension() = "dbml"
  override fun getIcon(): Icon = DbmlIcon.File
}