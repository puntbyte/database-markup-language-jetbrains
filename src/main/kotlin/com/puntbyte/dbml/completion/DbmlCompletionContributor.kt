package com.puntbyte.dbml.completion

import com.intellij.codeInsight.completion.*
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.patterns.PlatformPatterns.psiElement
import com.intellij.util.ProcessingContext
import com.puntbyte.dbml.psi.*
import com.puntbyte.dbml.util.DbmlUtil

class DbmlCompletionContributor : CompletionContributor() {
  init {
    // ==========================================
    // 1. TOP-LEVEL KEYWORDS (File level)
    // Suggest Table, Enum, Project, etc.
    // ==========================================
    extend(
      CompletionType.BASIC,
      psiElement().withParent(DbmlFile::class.java),
      object : CompletionProvider<CompletionParameters>() {
        override fun addCompletions(
          parameters: CompletionParameters,
          context: ProcessingContext,
          resultSet: CompletionResultSet
        ) {
          val keywords = listOf("Table", "Enum", "Project", "Ref", "TableGroup", "TablePartial")
          for (keyword in keywords) {
            resultSet.addElement(LookupElementBuilder.create(keyword).withBoldness(true))
          }
        }
      }
    )

    // ==========================================
    // 2. INSIDE TABLE BLOCKS (Data Types & Table Keywords)
    // ==========================================
    extend(
      CompletionType.BASIC,
      psiElement().inside(DbmlTableBlock::class.java)
        .andNot(psiElement().inside(DbmlSettingBlock::class.java)),
      object : CompletionProvider<CompletionParameters>() {
        override fun addCompletions(
          parameters: CompletionParameters,
          context: ProcessingContext,
          resultSet: CompletionResultSet
        ) {

          // Suggest internal table blocks
          listOf("Note", "Ref", "Indexes", "Checks").forEach {
            resultSet.addElement(LookupElementBuilder.create(it).withBoldness(true))
          }

          // Suggest Data Types based on the project DB type!
          val dbType = DbmlUtil.getProjectDatabaseType(parameters.originalFile.project)
          val typesToSuggest = DbmlDialects.getDataTypes(dbType)

          for (type in typesToSuggest) {
            resultSet.addElement(
              LookupElementBuilder.create(type).withTypeText("Type", true)
            )
          }

          // Suggest Custom Enums too!
          val enums = DbmlUtil.findEnums(parameters.originalFile.project)
          for (enumDef in enums) {
            val enumName = enumDef.enumIdentifier?.text
            if (enumName != null) {
              resultSet.addElement(LookupElementBuilder.create(enumName).withTypeText("Enum"))
            }
          }
        }
      }
    )

    // ==========================================
    // 3. INSIDE SETTING BLOCKS [ ... ]
    // ==========================================
    extend(
      CompletionType.BASIC,
      psiElement().inside(DbmlSettingBlock::class.java),
      object : CompletionProvider<CompletionParameters>() {
        override fun addCompletions(
          parameters: CompletionParameters,
          context: ProcessingContext,
          resultSet: CompletionResultSet
        ) {
          val settings = listOf(
            "pk", "primary key", "not null", "null", "unique",
            "increment", "default:", "note:", "ref:", "update:", "delete:"
          )

          for (setting in settings) {
            resultSet.addElement(LookupElementBuilder.create(setting).withTypeText("Setting"))
          }

          // Provide cascade/restrict if typing after 'delete:' or 'update:'
          val cascadeOptions = listOf("cascade", "restrict", "set null", "set default", "no action")
          for (opt in cascadeOptions) {
            resultSet.addElement(LookupElementBuilder.create(opt).withTypeText("Action"))
          }
        }
      }
    )

    // ==========================================
    // 4. INSIDE REFERENCE EXPRESSIONS
    // ==========================================
    extend(
      CompletionType.BASIC,
      psiElement().inside(DbmlReferenceExpression::class.java),
      object : CompletionProvider<CompletionParameters>() {
        override fun addCompletions(
          parameters: CompletionParameters,
          context: ProcessingContext,
          resultSet: CompletionResultSet
        ) {
          val operators = listOf("<", ">", "-", "<>")
          for (op in operators) {
            resultSet.addElement(LookupElementBuilder.create(op).withTypeText("Relation"))
          }
        }
      }
    )
  }
}