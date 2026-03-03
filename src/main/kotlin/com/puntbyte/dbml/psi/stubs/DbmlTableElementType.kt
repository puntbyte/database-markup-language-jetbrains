package com.puntbyte.dbml.psi.stubs

import com.intellij.psi.PsiElement
import com.intellij.psi.stubs.*
import com.puntbyte.dbml.core.DbmlLanguage
import com.puntbyte.dbml.index.DbmlTableIndex
import com.puntbyte.dbml.psi.DbmlTableDefinition
import com.puntbyte.dbml.psi.impl.DbmlTableDefinitionImpl

class DbmlTableElementType(debugName: String) :
  IStubElementType<DbmlTableStub, DbmlTableDefinition>(debugName, DbmlLanguage) {

  override fun createPsi(stub: DbmlTableStub): DbmlTableDefinition {
    return DbmlTableDefinitionImpl(stub, this)
  }

  override fun createStub(psi: DbmlTableDefinition, parentStub: StubElement<out PsiElement>?): DbmlTableStub {
    return Impl(parentStub, this, psi.name)
  }

  override fun getExternalId(): String = "dbml.table"

  override fun serialize(stub: DbmlTableStub, dataStream: StubOutputStream) {
    dataStream.writeName(stub.name)
  }

  override fun deserialize(dataStream: StubInputStream, parentStub: StubElement<*>?): DbmlTableStub {
    return Impl(parentStub, this, dataStream.readNameString())
  }

  // THIS is where we register the table's name into the IDE's background index
  override fun indexStub(stub: DbmlTableStub, sink: IndexSink) {
    val name = stub.name
    if (name != null) {
      sink.occurrence(DbmlTableIndex.KEY, name)
    }
  }

  class Impl(
    parent: StubElement<*>?,
    elementType: DbmlTableElementType,
    private val myName: String?
  ) : StubBase<DbmlTableDefinition>(parent, elementType), DbmlTableStub {
    override fun getName(): String? = myName
  }
}