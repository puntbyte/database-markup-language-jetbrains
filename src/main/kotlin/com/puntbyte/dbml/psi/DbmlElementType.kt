package com.puntbyte.dbml.psi

import com.intellij.psi.tree.IElementType
import com.puntbyte.dbml.core.DbmlLanguage
import org.jetbrains.annotations.NonNls

class DbmlElementType(@NonNls debugName: String) : IElementType(debugName, DbmlLanguage) {
  override fun toString(): String = "DbmlTokenType.${super.toString()}"
}