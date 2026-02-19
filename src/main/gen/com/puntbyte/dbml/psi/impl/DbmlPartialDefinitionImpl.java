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

public class DbmlPartialDefinitionImpl extends ASTWrapperPsiElement implements DbmlPartialDefinition {

  public DbmlPartialDefinitionImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull DbmlVisitor visitor) {
    visitor.visitPartialDefinition(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof DbmlVisitor) accept((DbmlVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public DbmlPartialBlock getPartialBlock() {
    return findNotNullChildByClass(DbmlPartialBlock.class);
  }

  @Override
  @Nullable
  public DbmlSettingBlock getSettingBlock() {
    return findChildByClass(DbmlSettingBlock.class);
  }

  @Override
  @NotNull
  public DbmlId getId() {
    return findNotNullChildByClass(DbmlId.class);
  }

  @Override
  @NotNull
  public PsiElement getKwPartial() {
    return findNotNullChildByType(KW_PARTIAL);
  }

  @Override
  public @Nullable String getName() {
    return DbmlPsiImplUtil.getName(this);
  }

  @Override
  public @NotNull PsiElement setName(@NotNull String newName) {
    return DbmlPsiImplUtil.setName(this, newName);
  }

  @Override
  public @Nullable PsiElement getNameIdentifier() {
    return DbmlPsiImplUtil.getNameIdentifier(this);
  }

}
