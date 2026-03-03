package com.puntbyte.dbml.ide.templates

import com.intellij.codeInsight.template.impl.DefaultLiveTemplatesProvider

class DbmlLiveTemplatesProvider : DefaultLiveTemplatesProvider {
  // Points to XML files inside resources/liveTemplates/
  override fun getDefaultLiveTemplateFiles(): Array<String> = arrayOf("liveTemplates/Dbml")
  override fun getHiddenLiveTemplateFiles(): Array<String>? = null
}