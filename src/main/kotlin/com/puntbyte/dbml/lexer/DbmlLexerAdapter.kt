package com.puntbyte.dbml.lexer

import com.intellij.lexer.FlexAdapter

class DbmlLexerAdapter : FlexAdapter(DbmlLexer(null))