package com.puntbyte.dbml.util

import com.intellij.openapi.project.Project
import com.intellij.psi.PsiFile
import com.intellij.psi.PsiManager
import com.intellij.psi.search.FileTypeIndex
import com.intellij.psi.search.GlobalSearchScope
import com.intellij.psi.util.PsiTreeUtil
import com.puntbyte.dbml.core.DbmlFileType
import com.puntbyte.dbml.psi.DbmlFile
import com.puntbyte.dbml.psi.DbmlMapEntry
import com.puntbyte.dbml.psi.DbmlTableDefinition
import com.puntbyte.dbml.psi.DbmlPartialDefinition
import com.puntbyte.dbml.psi.DbmlProjectBlock
import com.puntbyte.dbml.psi.DbmlProjectDefinition

object DbmlUtil {

  // Helper to find all Tables
  fun findTables(project: Project): List<DbmlTableDefinition> {
    val result = ArrayList<DbmlTableDefinition>()
    val virtualFiles = FileTypeIndex.getFiles(DbmlFileType, GlobalSearchScope.allScope(project))

    for (virtualFile in virtualFiles) {
      val dbmlFile = PsiManager.getInstance(project).findFile(virtualFile) as? DbmlFile ?: continue
      val tables = PsiTreeUtil.getChildrenOfType(dbmlFile, DbmlTableDefinition::class.java)
      if (tables != null) {
        result.addAll(tables)
      }
    }
    return result
  }

  // Helper to find all Partials
  fun findPartials(project: Project): List<DbmlPartialDefinition> {
    val result = ArrayList<DbmlPartialDefinition>()
    val virtualFiles = FileTypeIndex.getFiles(DbmlFileType, GlobalSearchScope.allScope(project))

    for (virtualFile in virtualFiles) {
      val dbmlFile = PsiManager.getInstance(project).findFile(virtualFile) as? DbmlFile ?: continue
      val partials = PsiTreeUtil.getChildrenOfType(dbmlFile, DbmlPartialDefinition::class.java)
      if (partials != null) {
        result.addAll(partials)
      }
    }
    return result
  }

  /**
   * Scans the specific file for the Project Definition block and looks for
   * the 'database_type' setting.
   * Example: Project my_project { database_type: 'PostgreSQL' }
   */
  fun getProjectDatabaseType(file: PsiFile): String? {
    // 1. Find the Project Definition Block
    val projectDef = PsiTreeUtil.findChildOfType(file, DbmlProjectDefinition::class.java)
      ?: return null

    // 2. Find the Project Block (content inside braces)
    val projectBlock = PsiTreeUtil.findChildOfType(projectDef, DbmlProjectBlock::class.java)
      ?: return null

    // 3. Iterate over content to find MapEntry
    val mapEntries = PsiTreeUtil.getChildrenOfType(projectBlock, DbmlMapEntry::class.java)
      ?: return null

    for (entry in mapEntries) {
      // Check Key (e.g. "database_type")
      val keyNode = entry.mapKey.text
      if (keyNode == "database_type") {
        // Check Value (e.g. 'PostgreSQL')
        val valueNode = entry.mapValue
        // Clean quotes ('PostgreSQL' -> PostgreSQL)
        return valueNode.text.replace("'", "").replace("\"", "").trim()
      }
    }
    return null
  }
}