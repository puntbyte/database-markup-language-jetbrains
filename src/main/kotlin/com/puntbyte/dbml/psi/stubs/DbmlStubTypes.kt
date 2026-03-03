package com.puntbyte.dbml.psi.stubs

import com.intellij.psi.tree.IElementType

object DbmlStubTypes {
  val TABLE_DEFINITION = DbmlTableElementType("TABLE_DEFINITION")

  @JvmStatic
  fun getFactory(name: String): IElementType {
    return when (name) {
      "TABLE_DEFINITION" -> TABLE_DEFINITION
      else -> throw IllegalArgumentException("Unknown element type: $name")
    }
  }
}