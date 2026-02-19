// This is a generated file. Not intended for manual editing.
package com.puntbyte.dbml.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.puntbyte.dbml.psi.DbmlTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.puntbyte.dbml.psi.*;

public class DbmlTableIdentifierImpl extends ASTWrapperPsiElement implements DbmlTableIdentifier {

  public DbmlTableIdentifierImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull DbmlVisitor visitor) {
    visitor.visitTableIdentifier(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof DbmlVisitor) accept((DbmlVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public DbmlNamespaceIdentifier getNamespaceIdentifier() {
    return findNotNullChildByClass(DbmlNamespaceIdentifier.class);
  }

}
