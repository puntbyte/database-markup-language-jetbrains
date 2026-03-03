package com.puntbyte.dbml.ide.structure

import com.intellij.ide.structureView.StructureViewTreeElement
import com.intellij.ide.util.treeView.smartTree.SortableTreeElement
import com.intellij.ide.util.treeView.smartTree.TreeElement
import com.intellij.navigation.ItemPresentation
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.util.PsiTreeUtil
import com.puntbyte.dbml.core.DbmlIcon
import com.puntbyte.dbml.psi.*
import javax.swing.Icon

class DbmlStructureViewElement(private val element: PsiElement) : StructureViewTreeElement,
  SortableTreeElement {

  override fun getValue(): Any = element

  override fun getPresentation(): ItemPresentation {
    return object : ItemPresentation {
      override fun getPresentableText(): String? {
        return when (element) {
          is PsiFile -> element.name
          is DbmlTableDefinition -> element.name
          is DbmlColumnDefinition -> element.columnIdentifier.text + " " + element.columnType.text
          else -> element.text
        }
      }

      override fun getIcon(unused: Boolean): Icon =
        DbmlIcon.File // Use specific icons if you have them!

      override fun getLocationString(): String? = null
    }
  }

  override fun getChildren(): Array<TreeElement> {
    val childrenElements = mutableListOf<PsiElement>()

    when (element) {
      is DbmlFile -> {
        // Root file: show tables, enums, etc.
        childrenElements.addAll(
          PsiTreeUtil.getChildrenOfTypeAsList(
            element,
            DbmlTableDefinition::class.java
          )
        )
        childrenElements.addAll(
          PsiTreeUtil.getChildrenOfTypeAsList(
            element,
            DbmlEnumDefinition::class.java
          )
        )
      }

      is DbmlTableDefinition -> {
        // Inside a table: show columns
        val block = element.tableBlock
        childrenElements.addAll(
          PsiTreeUtil.getChildrenOfTypeAsList(
            block,
            DbmlColumnDefinition::class.java
          )
        )
      }
    }

    return childrenElements.map { DbmlStructureViewElement(it) }.toTypedArray()
  }

  override fun navigate(requestFocus: Boolean) {
    (element as? com.intellij.pom.Navigatable)?.navigate(requestFocus)
  }

  override fun canNavigate(): Boolean =
    (element as? com.intellij.pom.Navigatable)?.canNavigate() == true

  override fun canNavigateToSource(): Boolean =
    (element as? com.intellij.pom.Navigatable)?.canNavigateToSource() == true

  override fun getAlphaSortKey(): String = presentation.presentableText ?: ""
}