// This is a generated file. Not intended for manual editing.
package com.puntbyte.dbml.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface DbmlTableBlock extends PsiElement {

  @NotNull
  List<DbmlCheckDefinition> getCheckDefinitionList();

  @NotNull
  List<DbmlColumnDefinition> getColumnDefinitionList();

  @NotNull
  List<DbmlIndexDefinition> getIndexDefinitionList();

  @NotNull
  List<DbmlMapEntry> getMapEntryList();

  @NotNull
  List<DbmlNoteDefinition> getNoteDefinitionList();

  @NotNull
  List<DbmlPartialInjection> getPartialInjectionList();

}
