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

public class DbmlProjectContentImpl extends ASTWrapperPsiElement implements DbmlProjectContent {

  public DbmlProjectContentImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull DbmlVisitor visitor) {
    visitor.visitProjectContent(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof DbmlVisitor) accept((DbmlVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public DbmlMapEntry getMapEntry() {
    return findChildByClass(DbmlMapEntry.class);
  }

  @Override
  @Nullable
  public PsiElement getBlockComment() {
    return findChildByType(BLOCK_COMMENT);
  }

  @Override
  @Nullable
  public PsiElement getDocComment() {
    return findChildByType(DOC_COMMENT);
  }

  @Override
  @Nullable
  public PsiElement getLineComment() {
    return findChildByType(LINE_COMMENT);
  }

}
