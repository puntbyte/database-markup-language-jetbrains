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

public class DbmlTableDefinitionImpl extends ASTWrapperPsiElement implements DbmlTableDefinition {

  public DbmlTableDefinitionImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull DbmlVisitor visitor) {
    visitor.visitTableDefinition(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof DbmlVisitor) accept((DbmlVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public DbmlAliasClause getAliasClause() {
    return findChildByClass(DbmlAliasClause.class);
  }

  @Override
  @Nullable
  public DbmlSettingBlock getSettingBlock() {
    return findChildByClass(DbmlSettingBlock.class);
  }

  @Override
  @NotNull
  public DbmlTableBlock getTableBlock() {
    return findNotNullChildByClass(DbmlTableBlock.class);
  }

  @Override
  @NotNull
  public DbmlTableIdentifier getTableIdentifier() {
    return findNotNullChildByClass(DbmlTableIdentifier.class);
  }

  @Override
  @NotNull
  public PsiElement getKwTable() {
    return findNotNullChildByType(KW_TABLE);
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
