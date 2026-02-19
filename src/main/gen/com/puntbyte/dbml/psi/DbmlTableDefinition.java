// This is a generated file. Not intended for manual editing.
package com.puntbyte.dbml.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNameIdentifierOwner;

public interface DbmlTableDefinition extends PsiNameIdentifierOwner {

  @Nullable
  DbmlAliasClause getAliasClause();

  @Nullable
  DbmlSettingBlock getSettingBlock();

  @NotNull
  DbmlTableBlock getTableBlock();

  @NotNull
  DbmlTableIdentifier getTableIdentifier();

  @NotNull
  PsiElement getKwTable();

  @Nullable String getName();

  @NotNull PsiElement setName(@NotNull String newName);

  @Nullable PsiElement getNameIdentifier();

}
