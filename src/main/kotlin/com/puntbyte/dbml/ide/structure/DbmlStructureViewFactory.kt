package com.puntbyte.dbml.ide.structure

import com.intellij.ide.structureView.*
import com.intellij.lang.PsiStructureViewFactory
import com.intellij.openapi.editor.Editor
import com.intellij.psi.PsiFile
import com.puntbyte.dbml.psi.DbmlColumnDefinition

class DbmlStructureViewFactory : PsiStructureViewFactory {
  override fun getStructureViewBuilder(psiFile: PsiFile): StructureViewBuilder {
    return object : TreeBasedStructureViewBuilder() {
      override fun createStructureViewModel(editor: Editor?): StructureViewModel {
        return object : StructureViewModelBase(psiFile, editor, DbmlStructureViewElement(psiFile)),
          StructureViewModel.ElementInfoProvider {
          override fun isAlwaysShowsPlus(element: StructureViewTreeElement): Boolean = false
          override fun isAlwaysLeaf(element: StructureViewTreeElement): Boolean {
            return element.value is DbmlColumnDefinition // Columns don't have children
          }
        }
      }
    }
  }
}