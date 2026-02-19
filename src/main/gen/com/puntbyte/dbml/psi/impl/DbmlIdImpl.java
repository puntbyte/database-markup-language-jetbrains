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

public class DbmlIdImpl extends ASTWrapperPsiElement implements DbmlId {

  public DbmlIdImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull DbmlVisitor visitor) {
    visitor.visitId(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof DbmlVisitor) accept((DbmlVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public PsiElement getBoolean() {
    return findChildByType(BOOLEAN);
  }

  @Override
  @Nullable
  public PsiElement getDataType() {
    return findChildByType(DATA_TYPE);
  }

  @Override
  @Nullable
  public PsiElement getIdentifier() {
    return findChildByType(IDENTIFIER);
  }

  @Override
  @Nullable
  public PsiElement getKwAs() {
    return findChildByType(KW_AS);
  }

  @Override
  @Nullable
  public PsiElement getKwChecks() {
    return findChildByType(KW_CHECKS);
  }

  @Override
  @Nullable
  public PsiElement getKwEnum() {
    return findChildByType(KW_ENUM);
  }

  @Override
  @Nullable
  public PsiElement getKwGroup() {
    return findChildByType(KW_GROUP);
  }

  @Override
  @Nullable
  public PsiElement getKwIndexes() {
    return findChildByType(KW_INDEXES);
  }

  @Override
  @Nullable
  public PsiElement getKwNote() {
    return findChildByType(KW_NOTE);
  }

  @Override
  @Nullable
  public PsiElement getKwPartial() {
    return findChildByType(KW_PARTIAL);
  }

  @Override
  @Nullable
  public PsiElement getKwProject() {
    return findChildByType(KW_PROJECT);
  }

  @Override
  @Nullable
  public PsiElement getKwRef() {
    return findChildByType(KW_REF);
  }

  @Override
  @Nullable
  public PsiElement getKwTable() {
    return findChildByType(KW_TABLE);
  }

  @Override
  @Nullable
  public PsiElement getNull() {
    return findChildByType(NULL);
  }

  @Override
  @Nullable
  public PsiElement getSettingKey() {
    return findChildByType(SETTING_KEY);
  }

  @Override
  @Nullable
  public PsiElement getSettingVal() {
    return findChildByType(SETTING_VAL);
  }

}
