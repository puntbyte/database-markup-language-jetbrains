// This is a generated file. Not intended for manual editing.
package com.puntbyte.dbml.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface DbmlPartialBlock extends PsiElement {

  @NotNull
  List<DbmlColumnDefinition> getColumnDefinitionList();

  @NotNull
  List<DbmlIndexDefinition> getIndexDefinitionList();

  @NotNull
  List<DbmlNoteDefinition> getNoteDefinitionList();

}
