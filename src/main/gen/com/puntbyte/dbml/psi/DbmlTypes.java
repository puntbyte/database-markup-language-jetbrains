// This is a generated file. Not intended for manual editing.
package com.puntbyte.dbml.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import com.puntbyte.dbml.psi.impl.*;

public interface DbmlTypes {

  IElementType ALIAS_CLAUSE = new DbmlElementType("ALIAS_CLAUSE");
  IElementType CHECK_BLOCK = new DbmlElementType("CHECK_BLOCK");
  IElementType CHECK_DEFINITION = new DbmlElementType("CHECK_DEFINITION");
  IElementType CHECK_ITEM = new DbmlElementType("CHECK_ITEM");
  IElementType COLUMN_DEFINITION = new DbmlElementType("COLUMN_DEFINITION");
  IElementType COLUMN_IDENTIFIER = new DbmlElementType("COLUMN_IDENTIFIER");
  IElementType COLUMN_TYPE = new DbmlElementType("COLUMN_TYPE");
  IElementType COMPOSITE_REFERENCE = new DbmlElementType("COMPOSITE_REFERENCE");
  IElementType DATA_LIMIT = new DbmlElementType("DATA_LIMIT");
  IElementType ENUM_BLOCK = new DbmlElementType("ENUM_BLOCK");
  IElementType ENUM_DEFINITION = new DbmlElementType("ENUM_DEFINITION");
  IElementType ENUM_VALUE = new DbmlElementType("ENUM_VALUE");
  IElementType GROUP_BLOCK = new DbmlElementType("GROUP_BLOCK");
  IElementType GROUP_CONTENT = new DbmlElementType("GROUP_CONTENT");
  IElementType GROUP_DEFINITION = new DbmlElementType("GROUP_DEFINITION");
  IElementType ID = new DbmlElementType("ID");
  IElementType INDEX_BLOCK = new DbmlElementType("INDEX_BLOCK");
  IElementType INDEX_DEFINITION = new DbmlElementType("INDEX_DEFINITION");
  IElementType INDEX_EXPRESSION = new DbmlElementType("INDEX_EXPRESSION");
  IElementType INDEX_ITEM = new DbmlElementType("INDEX_ITEM");
  IElementType INLINE_REF = new DbmlElementType("INLINE_REF");
  IElementType MAP_ENTRY = new DbmlElementType("MAP_ENTRY");
  IElementType MAP_KEY = new DbmlElementType("MAP_KEY");
  IElementType MAP_VALUE = new DbmlElementType("MAP_VALUE");
  IElementType NAMESPACE_IDENTIFIER = new DbmlElementType("NAMESPACE_IDENTIFIER");
  IElementType NOTE_BLOCK = new DbmlElementType("NOTE_BLOCK");
  IElementType NOTE_DEFINITION = new DbmlElementType("NOTE_DEFINITION");
  IElementType PARTIAL_BLOCK = new DbmlElementType("PARTIAL_BLOCK");
  IElementType PARTIAL_DEFINITION = new DbmlElementType("PARTIAL_DEFINITION");
  IElementType PARTIAL_INJECTION = new DbmlElementType("PARTIAL_INJECTION");
  IElementType PROJECT_BLOCK = new DbmlElementType("PROJECT_BLOCK");
  IElementType PROJECT_CONTENT = new DbmlElementType("PROJECT_CONTENT");
  IElementType PROJECT_DEFINITION = new DbmlElementType("PROJECT_DEFINITION");
  IElementType REFERENCE_BLOCK = new DbmlElementType("REFERENCE_BLOCK");
  IElementType REFERENCE_DEFINITION = new DbmlElementType("REFERENCE_DEFINITION");
  IElementType REFERENCE_ENDPOINT = new DbmlElementType("REFERENCE_ENDPOINT");
  IElementType REFERENCE_EXPRESSION = new DbmlElementType("REFERENCE_EXPRESSION");
  IElementType RELATION_OPERATOR = new DbmlElementType("RELATION_OPERATOR");
  IElementType SETTING_BLOCK = new DbmlElementType("SETTING_BLOCK");
  IElementType SETTING_CONTENT = new DbmlElementType("SETTING_CONTENT");
  IElementType SETTING_LIST = new DbmlElementType("SETTING_LIST");
  IElementType STICKY_NOTE_DEFINITION = new DbmlElementType("STICKY_NOTE_DEFINITION");
  IElementType TABLE_BLOCK = new DbmlElementType("TABLE_BLOCK");
  IElementType TABLE_DEFINITION = new DbmlElementType("TABLE_DEFINITION");
  IElementType TABLE_IDENTIFIER = new DbmlElementType("TABLE_IDENTIFIER");

  IElementType BLOCK_COMMENT = new DbmlElementType("BLOCK_COMMENT");
  IElementType BOOLEAN = new DbmlElementType("BOOLEAN");
  IElementType COLON = new DbmlElementType(":");
  IElementType COLOR = new DbmlElementType("COLOR");
  IElementType COMMA = new DbmlElementType(",");
  IElementType DATA_TYPE = new DbmlElementType("DATA_TYPE");
  IElementType DOC_COMMENT = new DbmlElementType("DOC_COMMENT");
  IElementType DOT = new DbmlElementType(".");
  IElementType EXPRESSION = new DbmlElementType("EXPRESSION");
  IElementType IDENTIFIER = new DbmlElementType("IDENTIFIER");
  IElementType KW_AS = new DbmlElementType("KW_AS");
  IElementType KW_CHECKS = new DbmlElementType("KW_CHECKS");
  IElementType KW_ENUM = new DbmlElementType("KW_ENUM");
  IElementType KW_GROUP = new DbmlElementType("KW_GROUP");
  IElementType KW_INDEXES = new DbmlElementType("KW_INDEXES");
  IElementType KW_NOTE = new DbmlElementType("KW_NOTE");
  IElementType KW_PARTIAL = new DbmlElementType("KW_PARTIAL");
  IElementType KW_PROJECT = new DbmlElementType("KW_PROJECT");
  IElementType KW_REF = new DbmlElementType("KW_REF");
  IElementType KW_TABLE = new DbmlElementType("KW_TABLE");
  IElementType LINE_COMMENT = new DbmlElementType("LINE_COMMENT");
  IElementType L_BRACE = new DbmlElementType("{");
  IElementType L_BRACKET = new DbmlElementType("[");
  IElementType L_PAREN = new DbmlElementType("(");
  IElementType MULTI_STRING = new DbmlElementType("MULTI_STRING");
  IElementType NULL = new DbmlElementType("NULL");
  IElementType NUMBER = new DbmlElementType("NUMBER");
  IElementType OP_MANY_MANY = new DbmlElementType("<>");
  IElementType OP_MANY_ONE = new DbmlElementType(">");
  IElementType OP_ONE_MANY = new DbmlElementType("<");
  IElementType OP_ONE_ONE = new DbmlElementType("-");
  IElementType R_BRACE = new DbmlElementType("}");
  IElementType R_BRACKET = new DbmlElementType("]");
  IElementType R_PAREN = new DbmlElementType(")");
  IElementType SETTING_KEY = new DbmlElementType("SETTING_KEY");
  IElementType SETTING_VAL = new DbmlElementType("SETTING_VAL");
  IElementType STRING = new DbmlElementType("STRING");
  IElementType TILDE = new DbmlElementType("~");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == ALIAS_CLAUSE) {
        return new DbmlAliasClauseImpl(node);
      }
      else if (type == CHECK_BLOCK) {
        return new DbmlCheckBlockImpl(node);
      }
      else if (type == CHECK_DEFINITION) {
        return new DbmlCheckDefinitionImpl(node);
      }
      else if (type == CHECK_ITEM) {
        return new DbmlCheckItemImpl(node);
      }
      else if (type == COLUMN_DEFINITION) {
        return new DbmlColumnDefinitionImpl(node);
      }
      else if (type == COLUMN_IDENTIFIER) {
        return new DbmlColumnIdentifierImpl(node);
      }
      else if (type == COLUMN_TYPE) {
        return new DbmlColumnTypeImpl(node);
      }
      else if (type == COMPOSITE_REFERENCE) {
        return new DbmlCompositeReferenceImpl(node);
      }
      else if (type == DATA_LIMIT) {
        return new DbmlDataLimitImpl(node);
      }
      else if (type == ENUM_BLOCK) {
        return new DbmlEnumBlockImpl(node);
      }
      else if (type == ENUM_DEFINITION) {
        return new DbmlEnumDefinitionImpl(node);
      }
      else if (type == ENUM_VALUE) {
        return new DbmlEnumValueImpl(node);
      }
      else if (type == GROUP_BLOCK) {
        return new DbmlGroupBlockImpl(node);
      }
      else if (type == GROUP_CONTENT) {
        return new DbmlGroupContentImpl(node);
      }
      else if (type == GROUP_DEFINITION) {
        return new DbmlGroupDefinitionImpl(node);
      }
      else if (type == ID) {
        return new DbmlIdImpl(node);
      }
      else if (type == INDEX_BLOCK) {
        return new DbmlIndexBlockImpl(node);
      }
      else if (type == INDEX_DEFINITION) {
        return new DbmlIndexDefinitionImpl(node);
      }
      else if (type == INDEX_EXPRESSION) {
        return new DbmlIndexExpressionImpl(node);
      }
      else if (type == INDEX_ITEM) {
        return new DbmlIndexItemImpl(node);
      }
      else if (type == INLINE_REF) {
        return new DbmlInlineRefImpl(node);
      }
      else if (type == MAP_ENTRY) {
        return new DbmlMapEntryImpl(node);
      }
      else if (type == MAP_KEY) {
        return new DbmlMapKeyImpl(node);
      }
      else if (type == MAP_VALUE) {
        return new DbmlMapValueImpl(node);
      }
      else if (type == NAMESPACE_IDENTIFIER) {
        return new DbmlNamespaceIdentifierImpl(node);
      }
      else if (type == NOTE_BLOCK) {
        return new DbmlNoteBlockImpl(node);
      }
      else if (type == NOTE_DEFINITION) {
        return new DbmlNoteDefinitionImpl(node);
      }
      else if (type == PARTIAL_BLOCK) {
        return new DbmlPartialBlockImpl(node);
      }
      else if (type == PARTIAL_DEFINITION) {
        return new DbmlPartialDefinitionImpl(node);
      }
      else if (type == PARTIAL_INJECTION) {
        return new DbmlPartialInjectionImpl(node);
      }
      else if (type == PROJECT_BLOCK) {
        return new DbmlProjectBlockImpl(node);
      }
      else if (type == PROJECT_CONTENT) {
        return new DbmlProjectContentImpl(node);
      }
      else if (type == PROJECT_DEFINITION) {
        return new DbmlProjectDefinitionImpl(node);
      }
      else if (type == REFERENCE_BLOCK) {
        return new DbmlReferenceBlockImpl(node);
      }
      else if (type == REFERENCE_DEFINITION) {
        return new DbmlReferenceDefinitionImpl(node);
      }
      else if (type == REFERENCE_ENDPOINT) {
        return new DbmlReferenceEndpointImpl(node);
      }
      else if (type == REFERENCE_EXPRESSION) {
        return new DbmlReferenceExpressionImpl(node);
      }
      else if (type == RELATION_OPERATOR) {
        return new DbmlRelationOperatorImpl(node);
      }
      else if (type == SETTING_BLOCK) {
        return new DbmlSettingBlockImpl(node);
      }
      else if (type == SETTING_CONTENT) {
        return new DbmlSettingContentImpl(node);
      }
      else if (type == SETTING_LIST) {
        return new DbmlSettingListImpl(node);
      }
      else if (type == STICKY_NOTE_DEFINITION) {
        return new DbmlStickyNoteDefinitionImpl(node);
      }
      else if (type == TABLE_BLOCK) {
        return new DbmlTableBlockImpl(node);
      }
      else if (type == TABLE_DEFINITION) {
        return new DbmlTableDefinitionImpl(node);
      }
      else if (type == TABLE_IDENTIFIER) {
        return new DbmlTableIdentifierImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
