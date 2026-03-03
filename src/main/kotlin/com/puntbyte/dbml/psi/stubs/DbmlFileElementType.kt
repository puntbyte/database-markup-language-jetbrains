package com.puntbyte.dbml.psi.stubs

import com.intellij.psi.stubs.PsiFileStub
import com.intellij.psi.tree.IStubFileElementType
import com.puntbyte.dbml.core.DbmlLanguage

class DbmlFileElementType : IStubFileElementType<PsiFileStub<*>>("DBML_FILE", DbmlLanguage) {
  companion object {
    val INSTANCE = DbmlFileElementType()
  }
}