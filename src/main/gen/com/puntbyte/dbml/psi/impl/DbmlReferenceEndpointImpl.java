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

public class DbmlReferenceEndpointImpl extends ASTWrapperPsiElement implements DbmlReferenceEndpoint {

  public DbmlReferenceEndpointImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull DbmlVisitor visitor) {
    visitor.visitReferenceEndpoint(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof DbmlVisitor) accept((DbmlVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public DbmlCompositeReference getCompositeReference() {
    return findChildByClass(DbmlCompositeReference.class);
  }

  @Override
  @Nullable
  public DbmlNamespaceIdentifier getNamespaceIdentifier() {
    return findChildByClass(DbmlNamespaceIdentifier.class);
  }

}
