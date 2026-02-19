package com.puntbyte.dbml.reference

import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiElementResolveResult
import com.intellij.psi.PsiPolyVariantReference
import com.intellij.psi.PsiReferenceBase
import com.intellij.psi.ResolveResult
import com.puntbyte.dbml.core.DbmlIcon
import com.puntbyte.dbml.util.DbmlUtil


class DbmlPartialReference(element: PsiElement, textRange: TextRange) :
  PsiReferenceBase<PsiElement>(element, textRange), PsiPolyVariantReference {

  // Clean up key (remove quotes)
  private val key: String = element.text.substring(textRange.startOffset, textRange.endOffset)
    .replace("\"", "")
    .replace("'", "")

  override fun resolve(): PsiElement? {
    val resolveResults = multiResolve(false)
    return if (resolveResults.size == 1) resolveResults[0].element else null
  }

  override fun multiResolve(incompleteCode: Boolean): Array<ResolveResult> {
    val project = myElement.project
    val partials = DbmlUtil.findPartials(project)

    // Filter: Find partials matching the key
    val result = partials.filter { it.name == key }

    // Map to ResolveResult
    return result.map { PsiElementResolveResult(it) }.toTypedArray()
  }

  override fun getVariants(): Array<Any> {
    val project = myElement.project

    // Auto-complete list
    return DbmlUtil.findPartials(project).mapNotNull { partial ->
      // Safely access .name (provided by DbmlPsiImplUtil via generated interface)
      partial.name?.let { name ->
        LookupElementBuilder.create(name)
          .withIcon(DbmlIcon.File)
          .withTypeText("Partial")
      }
    }.toTypedArray()
  }
}