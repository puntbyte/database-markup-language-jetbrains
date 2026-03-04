package com.puntbyte.dbml.psi.impl

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.psi.LiteralTextEscaper
import com.intellij.psi.PsiLanguageInjectionHost
import com.intellij.psi.util.PsiTreeUtil
import com.puntbyte.dbml.psi.DbmlElementFactory
import com.puntbyte.dbml.psi.DbmlExpressionWrapper

abstract class DbmlExpressionMixin(node: ASTNode) : ASTWrapperPsiElement(node),
  PsiLanguageInjectionHost {

  override fun isValidHost(): Boolean = true

  override fun updateText(text: String): PsiLanguageInjectionHost {
    // Create a dummy file to parse the updated expression
    val file = DbmlElementFactory.createDummyFile(project, "Table t { c int [default: $text] }")
    val newExpr = PsiTreeUtil.findChildOfType(file, DbmlExpressionWrapper::class.java)

    if (newExpr != null) {
      node.replaceChild(node.firstChildNode, newExpr.node.firstChildNode)
    }
    return this
  }

  override fun createLiteralTextEscaper(): LiteralTextEscaper<out PsiLanguageInjectionHost> {
    return LiteralTextEscaper.createSimple(this)
  }
}