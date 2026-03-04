package com.puntbyte.dbml.ide.formatter

import com.intellij.application.options.IndentOptionsEditor
import com.intellij.application.options.SmartIndentOptionsEditor
import com.intellij.psi.codeStyle.CodeStyleSettingsCustomizable
import com.intellij.psi.codeStyle.LanguageCodeStyleSettingsProvider
import com.puntbyte.dbml.core.DbmlLanguage

class DbmlLanguageCodeStyleSettingsProvider : LanguageCodeStyleSettingsProvider() {

  override fun getLanguage(): com.intellij.lang.Language = DbmlLanguage

  // What the preview window looks like in Settings
  override fun getCodeSample(settingsType: SettingsType): String {
    return """
            Project my_project {
              database_type: 'PostgreSQL'
            }
            
            Table users {
              id int[pk, increment]
              username varchar [not null, unique]
              created_at timestamp[default: `now()`]
            }
        """.trimIndent()
  }

  override fun getIndentOptionsEditor(): IndentOptionsEditor {
    return SmartIndentOptionsEditor()
  }

  override fun customizeSettings(consumer: CodeStyleSettingsCustomizable, settingsType: SettingsType) {
    if (settingsType == SettingsType.SPACING_SETTINGS) {
      consumer.showStandardOptions("SPACE_AROUND_ASSIGNMENT_OPERATORS")
    } else if (settingsType == SettingsType.BLANK_LINES_SETTINGS) {
      consumer.showStandardOptions("KEEP_BLANK_LINES_IN_CODE")
    }
  }
}