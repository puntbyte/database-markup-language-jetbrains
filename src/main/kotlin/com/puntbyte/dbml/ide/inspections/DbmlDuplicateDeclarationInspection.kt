package com.puntbyte.dbml.ide.inspections

import com.intellij.codeInspection.LocalInspectionTool
import com.intellij.codeInspection.ProblemHighlightType
import com.intellij.codeInspection.ProblemsHolder
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiElementVisitor
import com.intellij.psi.util.PsiTreeUtil
import com.puntbyte.dbml.psi.*
import com.puntbyte.dbml.util.DbmlUtil

class DbmlDuplicateDeclarationInspection : LocalInspectionTool() {

  override fun buildVisitor(holder: ProblemsHolder, isOnTheFly: Boolean): PsiElementVisitor {
    return object : DbmlVisitor() {

      // --- 1. PROJECT (Only 1 per file) ---
      override fun visitProjectDefinition(o: DbmlProjectDefinition) {
        val file = o.containingFile
        val projects = PsiTreeUtil.getChildrenOfTypeAsList(file, DbmlProjectDefinition::class.java)

        if (projects.size > 1) {
          // Highlight the word "Project" or its ID
          val target = o.id ?: o.firstChild
          holder.registerProblem(
            target,
            "Only one Project definition is allowed per file",
            ProblemHighlightType.GENERIC_ERROR
          )
        }
      }

      // --- 2. TABLES (Project-wide via Stub Index) ---
      override fun visitTableDefinition(o: DbmlTableDefinition) {
        val tableName = o.name ?: return
        val matches = DbmlUtil.findTableByName(o.project, tableName)

        if (matches.size > 1) {
          holder.registerProblem(
            o.nameIdentifier ?: o,
            "Duplicate table definition: '$tableName'",
            ProblemHighlightType.GENERIC_ERROR
          )
        }
      }

      // --- 3. ENUMS, GROUPS, PARTIALS, NOTES (File-wide checks) ---
      override fun visitEnumDefinition(o: DbmlEnumDefinition) {
        checkDuplicateInFile(o, o.namespaceIdentifier?.text, DbmlEnumDefinition::class.java, "Enum", holder)
      }

      override fun visitGroupDefinition(o: DbmlGroupDefinition) {
        checkDuplicateInFile(o, o.id?.text, DbmlGroupDefinition::class.java, "Group", holder)
      }

      override fun visitPartialDefinition(o: DbmlPartialDefinition) {
        checkDuplicateInFile(o, o.id?.text, DbmlPartialDefinition::class.java, "Partial", holder)
      }

      override fun visitStickyNoteDefinition(o: DbmlStickyNoteDefinition) {
        val noteName = o.id?.text ?: return // Unnamed notes are perfectly fine
        checkDuplicateInFile(o, noteName, DbmlStickyNoteDefinition::class.java, "Note", holder)
      }
    }
  }

  /**
   * Helper function to scan the current file for duplicate elements of the same type and name.
   */
  private fun <T : PsiElement> checkDuplicateInFile(
    element: T,
    name: String?,
    clazz: Class<T>,
    typeName: String,
    holder: ProblemsHolder
  ) {
    if (name == null) return

    val file = element.containingFile
    val elements = PsiTreeUtil.getChildrenOfTypeAsList(file, clazz)

    // Count how many elements of this type have the exact same name
    val count = elements.count { getElementName(it) == name }

    if (count > 1) {
      val targetToHighlight = getNameNode(element) ?: element
      holder.registerProblem(
        targetToHighlight,
        "Duplicate $typeName definition: '$name'",
        ProblemHighlightType.GENERIC_ERROR // Red squiggly line
      )
    }
  }

  private fun getElementName(element: PsiElement): String? {
    return when (element) {
      is DbmlEnumDefinition -> element.namespaceIdentifier?.text
      is DbmlGroupDefinition -> element.id?.text
      is DbmlPartialDefinition -> element.id?.text
      is DbmlStickyNoteDefinition -> element.id?.text
      else -> null
    }
  }

  private fun getNameNode(element: PsiElement): PsiElement? {
    return when (element) {
      is DbmlEnumDefinition -> element.namespaceIdentifier
      is DbmlGroupDefinition -> element.id
      is DbmlPartialDefinition -> element.id
      is DbmlStickyNoteDefinition -> element.id
      else -> null
    }
  }
}