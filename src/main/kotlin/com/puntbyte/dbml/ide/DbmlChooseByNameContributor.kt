package com.puntbyte.dbml.ide

import com.intellij.navigation.ChooseByNameContributor
import com.intellij.navigation.NavigationItem
import com.intellij.openapi.project.Project
import com.intellij.psi.stubs.StubIndex
import com.puntbyte.dbml.index.DbmlTableIndex
import com.puntbyte.dbml.util.DbmlUtil

class DbmlChooseByNameContributor : ChooseByNameContributor {

  // 1. Return all indexed names (what the user can type in the search bar)
  override fun getNames(project: Project, includeNonProjectItems: Boolean): Array<String> {
    return StubIndex.getInstance().getAllKeys(DbmlTableIndex.KEY, project).toTypedArray()
  }

  // 2. Return the actual elements when the user clicks a search result
  override fun getItemsByName(name: String, pattern: String, project: Project, includeNonProjectItems: Boolean): Array<NavigationItem> {
    val tables = DbmlUtil.findTableByName(project, name)
    return tables.filterIsInstance<NavigationItem>().toTypedArray()
  }
}