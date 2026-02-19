// This is a generated file. Not intended for manual editing.
package com.puntbyte.dbml.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface DbmlReferenceExpression extends PsiElement {

  @NotNull
  List<DbmlReferenceEndpoint> getReferenceEndpointList();

  @NotNull
  DbmlRelationOperator getRelationOperator();

  @Nullable
  DbmlSettingBlock getSettingBlock();

}
