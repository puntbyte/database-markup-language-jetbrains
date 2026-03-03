package com.puntbyte.dbml.util

import com.intellij.openapi.project.Project
import com.intellij.psi.PsiFile
import com.intellij.psi.PsiManager
import com.intellij.psi.search.FileTypeIndex
import com.intellij.psi.search.GlobalSearchScope
import com.intellij.psi.stubs.StubIndex
import com.intellij.psi.util.PsiTreeUtil
import com.puntbyte.dbml.core.DbmlFileType
import com.puntbyte.dbml.index.DbmlTableIndex
import com.puntbyte.dbml.psi.DbmlEnumDefinition
import com.puntbyte.dbml.psi.DbmlFile
import com.puntbyte.dbml.psi.DbmlMapEntry
import com.puntbyte.dbml.psi.DbmlTableDefinition
import com.puntbyte.dbml.psi.DbmlPartialDefinition
import com.puntbyte.dbml.psi.DbmlProjectBlock
import com.puntbyte.dbml.psi.DbmlProjectDefinition

object DbmlUtil {

  // FAST: Get a table directly by its name without parsing files!
  fun findTableByName(project: Project, name: String): List<DbmlTableDefinition> {
    val scope = GlobalSearchScope.allScope(project)
    return StubIndex.getElements(
      DbmlTableIndex.KEY,
      name,
      project,
      scope,
      DbmlTableDefinition::class.java
    ).toList()
  }


  // FAST: Get all tables by grabbing all keys from the index
  fun findTables(project: Project): List<DbmlTableDefinition> {
    val result = mutableListOf<DbmlTableDefinition>()
    val index = StubIndex.getInstance()
    val scope = GlobalSearchScope.allScope(project)

    // Get all indexed table names
    val allNames = index.getAllKeys(DbmlTableIndex.KEY, project)

    for (name in allNames) {
      // FIX: Use the static method StubIndex.getElements(...) here
      result.addAll(
        StubIndex.getElements(
          DbmlTableIndex.KEY,
          name,
          project,
          scope,
          DbmlTableDefinition::class.java
        )
      )
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

  // Helper to find all Enums
  fun findEnums(project: Project): List<DbmlEnumDefinition> {
    val result = ArrayList<DbmlEnumDefinition>()
    val virtualFiles = FileTypeIndex.getFiles(DbmlFileType, GlobalSearchScope.allScope(project))

    for (virtualFile in virtualFiles) {
      val dbmlFile = PsiManager.getInstance(project).findFile(virtualFile) as? DbmlFile ?: continue
      val enums = PsiTreeUtil.getChildrenOfType(dbmlFile, DbmlEnumDefinition::class.java)
      if (enums != null) {
        result.addAll(enums)
      }
    }
    return result
  }

  /**
   * Scans the specific file for the Project Definition block and looks for
   * the 'database_type' setting.
   * Example: Project my_project { database_type: 'PostgreSQL' }
   */
  fun getProjectDatabaseType(project: Project): String? {
    val virtualFiles = FileTypeIndex.getFiles(DbmlFileType, GlobalSearchScope.allScope(project))
    for (virtualFile in virtualFiles) {
      val file = PsiManager.getInstance(project).findFile(virtualFile) as? DbmlFile ?: continue
      val projectDef = PsiTreeUtil.findChildOfType(file, DbmlProjectDefinition::class.java) ?: continue
      val projectBlock = PsiTreeUtil.findChildOfType(projectDef, DbmlProjectBlock::class.java) ?: continue

      // FIX: Use collectElementsOfType to search deeply into the block
      val mapEntries = PsiTreeUtil.collectElementsOfType(projectBlock, DbmlMapEntry::class.java)

      for (entry in mapEntries) {
        if (entry.mapKey.text == "database_type") {
          return entry.mapValue.text.replace("'", "").replace("\"", "").trim()
        }
      }
    }
    return null
  }


  /*fun getProjectDatabaseType(file: PsiFile): String? {
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
  }*/
}