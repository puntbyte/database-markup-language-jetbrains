// This is a generated file. Not intended for manual editing.
package com.puntbyte.dbml.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNameIdentifierOwner;

public interface DbmlPartialDefinition extends PsiNameIdentifierOwner {

  @NotNull
  DbmlPartialBlock getPartialBlock();

  @Nullable
  DbmlSettingBlock getSettingBlock();

  @NotNull
  DbmlId getId();

  @NotNull
  PsiElement getKwPartial();

  @Nullable String getName();

  @NotNull PsiElement setName(@NotNull String newName);

  @Nullable PsiElement getNameIdentifier();

}
