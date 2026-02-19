package com.puntbyte.dbml.psi.impl

import com.intellij.psi.PsiElement
import com.intellij.psi.util.PsiTreeUtil
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
    // Find the 'id' wrapper node
    val idNode = PsiTreeUtil.findChildOfType(element, DbmlId::class.java)
    // Return the actual leaf identifier inside it
    return idNode?.firstChild
  }

  @JvmStatic
  fun getName(element: DbmlTableDefinition): String? {
    return getNameIdentifier(element)?.text
  }

  @JvmStatic
  fun setName(element: DbmlTableDefinition, newName: String): PsiElement {
    // Rename logic (omitted for brevity, returns element as is)
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
  fun setName(element: DbmlPartialDefinition, newName: String): PsiElement {
    return element
  }
}