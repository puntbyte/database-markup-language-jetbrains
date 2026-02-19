package com.puntbyte.dbml.ide

import com.intellij.codeInsight.editorActions.SimpleTokenSetQuoteHandler
import com.puntbyte.dbml.psi.DbmlTypes

class DbmlQuoteHandler : SimpleTokenSetQuoteHandler(
  DbmlTypes.STRING,
  DbmlTypes.MULTI_STRING // For ''' ... '''
)