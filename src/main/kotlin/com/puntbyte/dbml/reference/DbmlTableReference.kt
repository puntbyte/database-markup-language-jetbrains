package com.puntbyte.dbml.reference

import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiElementResolveResult
import com.intellij.psi.PsiPolyVariantReference
import com.intellij.psi.PsiReferenceBase
import com.intellij.psi.ResolveResult
import com.puntbyte.dbml.core.DbmlIcon
import com.puntbyte.dbml.psi.DbmlElementFactory
import com.puntbyte.dbml.psi.DbmlTableIdentifier
import com.puntbyte.dbml.util.DbmlUtil

class DbmlTableReference(element: PsiElement, textRange: TextRange) :
  PsiReferenceBase<PsiElement>(element, textRange), PsiPolyVariantReference {

  // Clean the key: remove quotes if the identifier is quoted (e.g., "users" -> users)
  private val key: String = element.text.substring(textRange.startOffset, textRange.endOffset)
    .replace("\"", "")
    .replace("'", "")

  // Resolves to the single best match (if any)
  override fun resolve(): PsiElement? {
    val resolveResults = multiResolve(false)
    return if (resolveResults.size == 1) resolveResults[0].element else null
  }

  // Resolves to an array of results (handles cases where a table might be defined twice by mistake)
  override fun multiResolve(incompleteCode: Boolean): Array<ResolveResult> {
    val project = myElement.project

    // O(1) Instant Lookup instead of parsing the whole project!
    val matches = DbmlUtil.findTableByName(project, key)

    return matches.map { PsiElementResolveResult(it) }.toTypedArray()
  }

  // Provides Auto-Complete suggestions
  override fun getVariants(): Array<Any> {
    val project = myElement.project
    val tables = DbmlUtil.findTables(project)

    return tables.mapNotNull { table ->
      val name = table.name
      if (name != null) {
        LookupElementBuilder.create(table) // Link the lookup item to the PSI element
          .withLookupString(name)        // The text to insert
          .withIcon(DbmlIcon.File)       // Your custom icon
          .withTypeText("Table")         // Hint text on the right
          .withBoldness(true)
      } else {
        null
      }
    }.toTypedArray()
  }

  override fun handleElementRename(newElementName: String): PsiElement {
    val idElement = myElement as? DbmlTableIdentifier ?: return myElement
    val newId = DbmlElementFactory.createTableIdentifier(idElement.project, newElementName)
    idElement.replace(newId)
    return myElement
  }
}