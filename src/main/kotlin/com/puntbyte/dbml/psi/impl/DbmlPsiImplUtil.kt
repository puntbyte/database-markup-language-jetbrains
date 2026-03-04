package com.puntbyte.dbml.psi.impl

import com.intellij.psi.PsiElement
import com.puntbyte.dbml.psi.DbmlPartialDefinition
import com.puntbyte.dbml.psi.DbmlTableDefinition
import com.puntbyte.dbml.psi.DbmlElementFactory

object DbmlPsiImplUtil {

  // --- TABLE DEFINITION ---
  @JvmStatic
  fun getNameIdentifier(element: DbmlTableDefinition): PsiElement? {
    return element.tableIdentifier
  }

  @JvmStatic
  fun getName(element: DbmlTableDefinition): String? {
    // SchemaIdentifier already includes the DOT in your BNF (e.g. "public.")
    val schema = element.schemaIdentifier?.text ?: ""
    val table = element.tableIdentifier?.text ?: ""
    if (table.isEmpty()) return null
    return schema + table
  }

  @JvmStatic
  fun setName(element: DbmlTableDefinition, name: String): PsiElement {
    val identifierNode = element.nameIdentifier?.node ?: return element
    val newIdentifierNode = DbmlElementFactory.createTableIdentifier(element.project, name).node
    element.node.replaceChild(identifierNode, newIdentifierNode)
    return element
  }

  // --- PARTIAL DEFINITION ---
  @JvmStatic
  fun getNameIdentifier(element: DbmlPartialDefinition): PsiElement? {
    return element.partialIdentifier
  }

  @JvmStatic
  fun getName(element: DbmlPartialDefinition): String? {
    return element.partialIdentifier?.text
  }

  @JvmStatic
  fun setName(element: DbmlPartialDefinition, name: String): PsiElement {
    val identifierNode = element.nameIdentifier?.node ?: return element
    val newIdentifierNode = DbmlElementFactory.createPartialIdentifier(element.project, name).node
    element.node.replaceChild(identifierNode, newIdentifierNode)
    return element
  }
}