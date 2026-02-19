package com.puntbyte.dbml.core

import com.intellij.lang.Language

object DbmlLanguage : Language("DBML") {
  private fun readResolve(): Any = DbmlLanguage
}