// This is a generated file. Not intended for manual editing.
package com.puntbyte.dbml.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface DbmlProjectDefinition extends PsiElement {

  @NotNull
  DbmlProjectBlock getProjectBlock();

  @Nullable
  DbmlId getId();

  @NotNull
  PsiElement getKwProject();

}
