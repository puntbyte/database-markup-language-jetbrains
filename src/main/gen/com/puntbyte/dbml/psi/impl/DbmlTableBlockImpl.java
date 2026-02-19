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

public class DbmlTableBlockImpl extends ASTWrapperPsiElement implements DbmlTableBlock {

  public DbmlTableBlockImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull DbmlVisitor visitor) {
    visitor.visitTableBlock(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof DbmlVisitor) accept((DbmlVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<DbmlCheckDefinition> getCheckDefinitionList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, DbmlCheckDefinition.class);
  }

  @Override
  @NotNull
  public List<DbmlColumnDefinition> getColumnDefinitionList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, DbmlColumnDefinition.class);
  }

  @Override
  @NotNull
  public List<DbmlIndexDefinition> getIndexDefinitionList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, DbmlIndexDefinition.class);
  }

  @Override
  @NotNull
  public List<DbmlMapEntry> getMapEntryList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, DbmlMapEntry.class);
  }

  @Override
  @NotNull
  public List<DbmlNoteDefinition> getNoteDefinitionList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, DbmlNoteDefinition.class);
  }

  @Override
  @NotNull
  public List<DbmlPartialInjection> getPartialInjectionList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, DbmlPartialInjection.class);
  }

}
