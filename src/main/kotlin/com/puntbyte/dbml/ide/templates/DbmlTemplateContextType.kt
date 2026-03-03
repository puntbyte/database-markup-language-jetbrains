package com.puntbyte.dbml.ide.templates

import com.intellij.codeInsight.template.TemplateActionContext
import com.intellij.codeInsight.template.TemplateContextType

class DbmlTemplateContextType : TemplateContextType("DBML", "Database Markup Language") {
  override fun isInContext(templateActionContext: TemplateActionContext): Boolean {
    return templateActionContext.file.name.endsWith(".dbml")
  }
}