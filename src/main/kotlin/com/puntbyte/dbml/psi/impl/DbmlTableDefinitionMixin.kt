package com.puntbyte.dbml.psi.impl

import com.intellij.extapi.psi.StubBasedPsiElementBase
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import com.intellij.psi.stubs.IStubElementType
import com.puntbyte.dbml.psi.DbmlTableDefinition
import com.puntbyte.dbml.psi.stubs.DbmlTableStub

abstract class DbmlTableDefinitionMixin : StubBasedPsiElementBase<DbmlTableStub>,
  DbmlTableDefinition {

  constructor(stub: DbmlTableStub, nodeType: IStubElementType<*, *>) : super(stub, nodeType)
  constructor(node: ASTNode) : super(node)

  override fun getName(): String? {
    // FAST PATH: Read from background index if available!
    val stub = greenStub
    if (stub != null) return stub.name

    // SLOW PATH: Parse AST if not indexed yet
    return DbmlPsiImplUtil.getName(this)
  }

  override fun setName(name: String): PsiElement {
    return DbmlPsiImplUtil.setName(this, name)
  }

  override fun getNameIdentifier(): PsiElement? {
    return DbmlPsiImplUtil.getNameIdentifier(this)
  }
}