package com.puntbyte.dbml.completion

import com.intellij.codeInsight.completion.*
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.patterns.PlatformPatterns
import com.intellij.util.ProcessingContext
import com.intellij.icons.AllIcons
import com.puntbyte.dbml.psi.DbmlTypes
import com.puntbyte.dbml.util.DbmlUtil

class DbmlCompletionContributor : CompletionContributor() {
  init {
    extend(
      CompletionType.BASIC,
      PlatformPatterns.psiElement(DbmlTypes.IDENTIFIER),
      object : CompletionProvider<CompletionParameters>() {
        override fun addCompletions(
          parameters: CompletionParameters,
          context: ProcessingContext,
          resultSet: CompletionResultSet
        ) {
          val file = parameters.originalFile

          // 1. Detect Database Type from Project Settings
          val dbType = DbmlUtil.getProjectDatabaseType(file.project)

          // 2. Get contextual types
          val typesToSuggest = DbmlDialects.getDataTypes(dbType)

          // 3. Add Data Types to Auto-complete
          for (type in typesToSuggest) {
            var builder = LookupElementBuilder.create(type)
              .withBoldness(true)
              .withTypeText("Type")

            // Add a specific icon/hint if it's a specific DB type
            if (dbType != null && isDialectSpecific(type, dbType)) {
              builder = builder
                .withIcon(AllIcons.Nodes.DataTables) // Or custom icon
                .withTypeText(dbType, true)
            }

            resultSet.addElement(builder)
          }

          // 4. Add Keywords (Standard DBML structure)
          val keywords = listOf(
            "Project", "Table", "Enum", "Ref", "Reference",
            "TableGroup", "Group", "TablePartial", "Partial",
            "Indexes", "Checks", "Note"
          )

          for (keyword in keywords) {
            resultSet.addElement(LookupElementBuilder.create(keyword).withBoldness(false))
          }
        }
      }
    )
  }

  /**
   * Optional helper to visually distinguish specific types in the dropdown
   */
  private fun isDialectSpecific(type: String, dbType: String): Boolean {
    // Simple logic: if the user explicitly set Postgres, highlight 'jsonb' etc.
    if (dbType.equals("PostgreSQL", ignoreCase = true)) {
      return type in listOf("jsonb", "uuid", "timestamptz", "serial", "bigserial")
    }
    return false
  }
}