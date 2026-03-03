package com.puntbyte.dbml.psi.impl

import com.intellij.psi.PsiElement
import com.intellij.psi.PsiManager
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.testFramework.LightVirtualFile
import com.puntbyte.dbml.core.DbmlFileType
import com.puntbyte.dbml.psi.DbmlElementFactory
import com.puntbyte.dbml.psi.DbmlFile
import com.puntbyte.dbml.psi.DbmlId
import com.puntbyte.dbml.psi.DbmlPartialDefinition
import com.puntbyte.dbml.psi.DbmlTableDefinition
import com.puntbyte.dbml.psi.DbmlTypes

object DbmlPsiImplUtil {

  // ==========================================
  //  TABLE DEFINITION IMPLEMENTATION
  // ==========================================

  @JvmStatic
  fun getNameIdentifier(element: DbmlTableDefinition): PsiElement? {
    // Return the entire TableIdentifier (which includes the schema and the dot)
    return element.tableIdentifier
  }

  @JvmStatic
  fun getName(element: DbmlTableDefinition): String? {
    // Return the full text: "blog.posts"
    return element.tableIdentifier.text
  }

  @JvmStatic
  fun setName(element: DbmlTableDefinition, name: String): PsiElement {
    val identifierNode = element.nameIdentifier ?: return element
    val newIdentifierNode = DbmlElementFactory.createIdentifier(element.project, name)
    identifierNode.replace(newIdentifierNode)
    return element
  }

  // ==========================================
  //  PARTIAL DEFINITION IMPLEMENTATION (ADD THIS)
  // ==========================================

  @JvmStatic
  fun getNameIdentifier(element: DbmlPartialDefinition): PsiElement? {
    // Logic: PartialDefinition -> Id -> (Identifier/Keyword)
    val idNode = PsiTreeUtil.findChildOfType(element, DbmlId::class.java)
    return idNode?.firstChild
  }

  @JvmStatic
  fun getName(element: DbmlPartialDefinition): String? {
    return getNameIdentifier(element)?.text
  }

  @JvmStatic
  fun setName(element: DbmlPartialDefinition, name: String): PsiElement {
    val identifierNode = element.nameIdentifier ?: return element
    val newIdentifierNode = DbmlElementFactory.createIdentifier(element.project, name)
    identifierNode.replace(newIdentifierNode)
    return element
  }
}