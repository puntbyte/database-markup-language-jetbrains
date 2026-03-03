package com.puntbyte.dbml.index

import com.intellij.psi.stubs.StringStubIndexExtension
import com.intellij.psi.stubs.StubIndexKey
import com.puntbyte.dbml.psi.DbmlTableDefinition

class DbmlTableIndex : StringStubIndexExtension<DbmlTableDefinition>() {
  override fun getKey(): StubIndexKey<String, DbmlTableDefinition> = KEY

  companion object {
    val KEY: StubIndexKey<String, DbmlTableDefinition> =
      StubIndexKey.createIndexKey("dbml.table.index")
  }
}