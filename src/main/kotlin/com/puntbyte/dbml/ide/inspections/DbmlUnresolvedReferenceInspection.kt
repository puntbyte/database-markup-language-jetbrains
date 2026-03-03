package com.puntbyte.dbml.ide.inspections

import com.intellij.codeInspection.LocalInspectionTool
import com.intellij.codeInspection.ProblemHighlightType
import com.intellij.codeInspection.ProblemsHolder
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

      // 1. Check Tables and Columns inside Ref Blocks
      override fun visitReferenceExpression(o: DbmlReferenceExpression) {
        super.visitReferenceExpression(o)
        val endpoints = o.referenceEndpointList
        if (endpoints.size == 2) {
          checkEndpoint(endpoints[0], endpoints[1], holder) // Check Left
          checkEndpoint(endpoints[1], endpoints[0], holder) // Check Right
        }
      }

      // 2. Check Custom Types (Enums)
      override fun visitColumnType(o: DbmlColumnType) {
        super.visitColumnType(o)
        val typeName = o.id.text
        val dbType = DbmlUtil.getProjectDatabaseType(o.containingFile)
        val standardTypes = DbmlDialects.getDataTypes(dbType)

        // If it's not a standard DB type like 'int' or 'varchar', it's an Enum reference!
        if (!standardTypes.contains(typeName.lowercase())) {
          val enums = DbmlUtil.findEnums(o.project)

          // Match the Enum name (handles schema.enum_name perfectly)
          val enumExists = enums.any { it.namespaceIdentifier?.text == typeName }

          if (!enumExists) {
            holder.registerProblem(
              o.id,
              "Unresolved enum or custom type: '$typeName'",
              ProblemHighlightType.GENERIC_ERROR, // RED SQUIGGLY!
              DbmlCreateEnumQuickFix(typeName)
            )
          }
        }
      }

      // 3. Check Partials
      override fun visitPartialInjection(o: DbmlPartialInjection) {
        super.visitPartialInjection(o)
        val partialName = o.id?.text ?: return
        val partials = DbmlUtil.findPartials(o.project)

        val exists = partials.any { it.name == partialName }
        if (!exists) {
          holder.registerProblem(
            o.id!!,
            "Unresolved partial: '$partialName'",
            ProblemHighlightType.GENERIC_ERROR,
            DbmlCreatePartialQuickFix(partialName)
          )
        }
      }
    }
  }

  private fun checkEndpoint(target: DbmlReferenceEndpoint, source: DbmlReferenceEndpoint, holder: ProblemsHolder) {
    val targetNs = target.namespaceIdentifier ?: return
    val idList = targetNs.idList
    if (idList.size < 2) return // Expects at least table.column

    val columnId = idList.last()
    val tableIds = idList.dropLast(1)

    // This handles "users" AND "schema.users" perfectly
    val tableName = tableIds.joinToString(".") { it.text }

    val tables = DbmlUtil.findTableByName(target.project, tableName)
    val inferredType = getInferredType(source)

    if (tables.isEmpty()) {
      // Table doesn't exist -> Red Squiggly on Table name
      val tableNodeToHighlight = tableIds.last()
      holder.registerProblem(
        tableNodeToHighlight,
        "Unresolved table: '$tableName'",
        ProblemHighlightType.GENERIC_ERROR,
        DbmlCreateTableQuickFix(tableName, columnId.text, inferredType)
      )
    } else {
      // Table exists. Check if column exists!
      val table = tables.first()
      val columns = PsiTreeUtil.getChildrenOfTypeAsList(table.tableBlock, DbmlColumnDefinition::class.java)
      val columnExists = columns.any { it.columnIdentifier.text == columnId.text }

      if (!columnExists) {
        // Red Squiggly on Column name
        holder.registerProblem(
          columnId,
          "Unresolved column: '${columnId.text}' in table '$tableName'",
          ProblemHighlightType.GENERIC_ERROR,
          DbmlCreateColumnQuickFix(tableName, columnId.text, inferredType)
        )
      }
    }
  }

  private fun getInferredType(source: DbmlReferenceEndpoint): String {
    val ns = source.namespaceIdentifier ?: return "int"
    val idList = ns.idList
    if (idList.size < 2) return "int" // Default fallback

    val columnText = idList.last().text
    val tableName = idList.dropLast(1).joinToString(".") { it.text }

    val tables = DbmlUtil.findTableByName(source.project, tableName)
    val table = tables.firstOrNull() ?: return "int"

    // Search the source table for its column type
    val columns = PsiTreeUtil.getChildrenOfTypeAsList(table.tableBlock, DbmlColumnDefinition::class.java)
    val col = columns.find { it.columnIdentifier.text == columnText }

    // Return the exact type of the source column, or fallback to int
    return col?.columnType?.text ?: "int"
  }
}