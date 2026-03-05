package com.puntbyte.dbml.ide.inspections

import com.intellij.codeInspection.LocalInspectionTool
import com.intellij.codeInspection.ProblemHighlightType
import com.intellij.codeInspection.ProblemsHolder
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiElementVisitor
import com.intellij.psi.util.PsiTreeUtil
import com.puntbyte.dbml.completion.DbmlDialects
import com.puntbyte.dbml.ide.fixers.DbmlCreateColumnQuickFix
import com.puntbyte.dbml.ide.fixers.DbmlCreateEnumQuickFix
import com.puntbyte.dbml.ide.fixers.DbmlCreatePartialQuickFix
import com.puntbyte.dbml.ide.fixers.DbmlCreateTableQuickFix
import com.puntbyte.dbml.psi.*
import com.puntbyte.dbml.util.DbmlUtil

class DbmlUnresolvedReferenceInspection : LocalInspectionTool() {

  override fun buildVisitor(holder: ProblemsHolder, isOnTheFly: Boolean): PsiElementVisitor {
    return object : DbmlVisitor() {

      // ==========================================
      // 1. SIMPLE COLUMN REFERENCES (`users.id` or `public.users.id`)
      // ==========================================
      override fun visitSimpleColumnReference(o: DbmlSimpleColumnReference) {
        super.visitSimpleColumnReference(o)

        val ids = o.identifierList
        if (ids.size < 2) return

        val fullTableName = if (ids.size == 3) "${ids[0].text}.${ids[1].text}" else ids[0].text
        val columnId = ids.last()
        val tableNodeToHighlight = if (ids.size == 3) ids[1] else ids[0]

        checkTableAndColumn(o, tableNodeToHighlight, columnId, fullTableName, holder)
      }

      // ==========================================
      // 2. COMPOSITE COLUMN REFERENCES (`users.(id, status)`)
      // ==========================================
      override fun visitCompositeColumnReference(o: DbmlCompositeColumnReference) {
        super.visitCompositeColumnReference(o)

        val ids = o.identifierList
        if (ids.isEmpty()) return

        val fullTableName = if (ids.size == 2) "${ids[0].text}.${ids[1].text}" else ids[0].text
        val tableNodeToHighlight = if (ids.size == 2) ids[1] else ids[0]

        val compositeColumn = o.compositeColumn ?: return

        for (columnId in compositeColumn.identifierList) {
          checkTableAndColumn(o, tableNodeToHighlight, columnId, fullTableName, holder)
        }
      }

      // ==========================================
      // 3. ENUMS & CUSTOM TYPES
      // ==========================================
      override fun visitDataType(o: DbmlDataType) {
        super.visitDataType(o)
        val typeName = o.text
        val dbType = DbmlUtil.getProjectDatabaseType(o.project)
        val standardTypes = DbmlDialects.getDataTypes(dbType)

        if (!standardTypes.contains(typeName.lowercase())) {
          val enums = DbmlUtil.findEnums(o.project)
          val enumExists = enums.any { it.enumIdentifier?.text == typeName }

          if (!enumExists) {
            holder.registerProblem(
              o,
              "Unresolved enum or custom type: '$typeName'",
              ProblemHighlightType.GENERIC_ERROR,
              DbmlCreateEnumQuickFix(typeName)
            )
          }
        }
      }

      // ==========================================
      // 4. PARTIAL REFERENCES
      // ==========================================
      override fun visitPartialReference(o: DbmlPartialReference) {
        super.visitPartialReference(o)
        val partialIdNode = o.partialIdentifier ?: return
        val partialName = partialIdNode.text

        val partials = DbmlUtil.findPartials(o.project)
        if (partials.none { it.name == partialName }) {
          holder.registerProblem(
            partialIdNode,
            "Unresolved partial: '$partialName'",
            ProblemHighlightType.GENERIC_ERROR,
            DbmlCreatePartialQuickFix(partialName)
          )
        }
      }
    }
  }

  private fun checkTableAndColumn(
    referenceNode: PsiElement,
    tableNodeToHighlight: DbmlIdentifier,
    columnNodeToHighlight: DbmlIdentifier,
    fullTableName: String,
    holder: ProblemsHolder
  ) {
    val tables = DbmlUtil.findTableByName(referenceNode.project, fullTableName)
    val inferredType = inferTypeFromOppositeEndpoint(referenceNode) ?: "int"

    if (tables.isEmpty()) {
      holder.registerProblem(
        tableNodeToHighlight,
        "Unresolved table: '$fullTableName'",
        ProblemHighlightType.GENERIC_ERROR,
        DbmlCreateTableQuickFix(fullTableName, columnNodeToHighlight.text, inferredType)
      )
    } else {
      val table = tables.first()
      val columns =
        PsiTreeUtil.getChildrenOfTypeAsList(table.tableBlock, DbmlColumnDefinition::class.java)

      // Check if ANY column has the matching name
      val columnExists = columns.any { it.identifier.text == columnNodeToHighlight.text }


      if (!columnExists) {
        holder.registerProblem(
          columnNodeToHighlight,
          "Unresolved column: '${columnNodeToHighlight.text}' in table '$fullTableName'",
          ProblemHighlightType.GENERIC_ERROR,
          DbmlCreateColumnQuickFix(fullTableName, columnNodeToHighlight.text, inferredType)
        )
      }
    }
  }

  private fun inferTypeFromOppositeEndpoint(currentRef: PsiElement): String? {
    val refExpr =
      PsiTreeUtil.getParentOfType(currentRef, DbmlReferenceExpression::class.java) ?: return null
    val endpoints = refExpr.columnReferenceList
    if (endpoints.size != 2) return null

    val oppositeEndpoint = if (endpoints[0] == currentRef) endpoints[1] else endpoints[0]
    val simpleRef = oppositeEndpoint.simpleColumnReference ?: return null

    val ids = simpleRef.identifierList
    if (ids.size < 2) return null

    val fullTableName = if (ids.size == 3) "${ids[0].text}.${ids[1].text}" else ids[0].text
    val colName = ids.last().text

    val tables = DbmlUtil.findTableByName(currentRef.project, fullTableName)
    val table = tables.firstOrNull() ?: return null
    val columns =
      PsiTreeUtil.getChildrenOfTypeAsList(table.tableBlock, DbmlColumnDefinition::class.java)

    val colDef = columns.find { it.identifier.text == colName }
    return colDef?.columnType?.text
  }
}