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
      // 1. SIMPLE COLUMN REFERENCES
      // e.g. `users.id` or `public.users.id`
      // ==========================================
      override fun visitSimpleColumnReference(o: DbmlSimpleColumnReference) {
        super.visitSimpleColumnReference(o)

        val tableId = o.tableIdentifier ?: return
        val columnId = o.columnIdentifier ?: return

        // SchemaIdentifier includes the DOT (e.g., "public.") based on your new BNF
        val schemaText = o.schemaIdentifier?.text ?: ""
        val fullTableName = schemaText + tableId.text

        checkTableAndColumn(o, tableId, columnId, fullTableName, holder)
      }

      // ==========================================
      // 2. COMPOSITE COLUMN REFERENCES
      // e.g. `users.(id, status)`
      // ==========================================
      override fun visitCompositeColumnReference(o: DbmlCompositeColumnReference) {
        super.visitCompositeColumnReference(o)

        val tableId = o.tableIdentifier ?: return
        val compositeColumn = o.compositeColumn ?: return

        val schemaText = o.schemaIdentifier?.text ?: ""
        val fullTableName = schemaText + tableId.text

        // Loop through every column inside the parentheses (id, status)
        for (columnId in compositeColumn.columnIdentifierList) {
          checkTableAndColumn(o, tableId, columnId, fullTableName, holder)
        }
      }

      // ==========================================
      // 3. ENUMS & CUSTOM TYPES
      // e.g. `status enum_status`
      // ==========================================
      override fun visitDataType(o: DbmlDataType) {
        super.visitDataType(o)
        val typeName = o.text

        val dbType = DbmlUtil.getProjectDatabaseType(o.project)
        val standardTypes = DbmlDialects.getDataTypes(dbType)

        // If it's not a standard DB type, it MUST be an Enum or Custom Type
        if (!standardTypes.contains(typeName.lowercase())) {
          val enums = DbmlUtil.findEnums(o.project)

          val enumExists = enums.any { enumDef ->
            val schema = enumDef.schemaIdentifier?.text ?: ""
            val name = enumDef.enumIdentifier?.text ?: ""
            val fullEnumName = schema + name
            fullEnumName == typeName
          }

          if (!enumExists) {
            holder.registerProblem(
              o, // Red squiggly line on the DataType text
              "Unresolved enum or custom type: '$typeName'",
              ProblemHighlightType.GENERIC_ERROR,
              DbmlCreateEnumQuickFix(typeName)
            )
          }
        }
      }

      // ==========================================
      // 4. PARTIAL REFERENCES
      // e.g. `~ my_partial`
      // ==========================================
      override fun visitPartialReference(o: DbmlPartialReference) {
        super.visitPartialReference(o)

        val partialIdNode = o.partialIdentifier ?: return
        val partialName = partialIdNode.text

        val partials = DbmlUtil.findPartials(o.project)
        val exists = partials.any { it.name == partialName }

        if (!exists) {
          holder.registerProblem(
            partialIdNode, // Red squiggly line on the partial name
            "Unresolved partial: '$partialName'",
            ProblemHighlightType.GENERIC_ERROR,
            DbmlCreatePartialQuickFix(partialName)
          )
        }
      }
    }
  }

  /**
   * Core validation logic for tables and columns
   */
  private fun checkTableAndColumn(
    referenceNode: PsiElement, // The ColumnReference wrapper
    tableNodeToHighlight: DbmlTableIdentifier,
    columnNodeToHighlight: DbmlColumnIdentifier,
    fullTableName: String,
    holder: ProblemsHolder
  ) {
    val tables = DbmlUtil.findTableByName(referenceNode.project, fullTableName)
    val inferredType = inferTypeFromOppositeEndpoint(referenceNode) ?: "int"

    if (tables.isEmpty()) {
      // TABLE IS MISSING -> Red Squiggly on the Table name
      holder.registerProblem(
        tableNodeToHighlight,
        "Unresolved table: '$fullTableName'",
        ProblemHighlightType.GENERIC_ERROR,
        DbmlCreateTableQuickFix(fullTableName, columnNodeToHighlight.text, inferredType)
      )
    } else {
      // TABLE EXISTS. Now check if the COLUMN exists inside it!
      val table = tables.first()
      val columns =
        PsiTreeUtil.getChildrenOfTypeAsList(table.tableBlock, DbmlColumnDefinition::class.java)
      val columnExists = columns.any { it.columnIdentifier.text == columnNodeToHighlight.text }

      if (!columnExists) {
        // COLUMN IS MISSING -> Red Squiggly on the Column name
        holder.registerProblem(
          columnNodeToHighlight,
          "Unresolved column: '${columnNodeToHighlight.text}' in table '$fullTableName'",
          ProblemHighlightType.GENERIC_ERROR,
          DbmlCreateColumnQuickFix(fullTableName, columnNodeToHighlight.text, inferredType)
        )
      }
    }
  }

  /**
   * Analyzes the other side of the `Ref: A > B` to guess the data type for Quick Fix generation.
   */
  private fun inferTypeFromOppositeEndpoint(currentRef: PsiElement): String? {
    // Find the full ReferenceExpression (A > B)
    val refExpr =
      PsiTreeUtil.getParentOfType(currentRef, DbmlReferenceExpression::class.java) ?: return null

    val endpoints = refExpr.columnReferenceList
    if (endpoints.size != 2) return null

    // Determine which side is the "other" side
    val oppositeEndpoint = if (endpoints[0] == currentRef) endpoints[1] else endpoints[0]

    // Only infer from SimpleColumnReferences for simplicity
    val simpleRef = oppositeEndpoint.simpleColumnReference ?: return null

    val schemaText = simpleRef.schemaIdentifier?.text ?: ""
    val fullTableName = schemaText + (simpleRef.tableIdentifier?.text ?: return null)
    val colName = simpleRef.columnIdentifier?.text ?: return null

    // Lookup the table and column type
    val tables = DbmlUtil.findTableByName(currentRef.project, fullTableName)
    val table = tables.firstOrNull() ?: return null
    val columns =
      PsiTreeUtil.getChildrenOfTypeAsList(table.tableBlock, DbmlColumnDefinition::class.java)

    val colDef = columns.find { it.columnIdentifier.text == colName }
    return colDef?.columnType?.text
  }
}