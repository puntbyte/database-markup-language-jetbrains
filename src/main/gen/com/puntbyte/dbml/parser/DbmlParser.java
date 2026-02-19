// This is a generated file. Not intended for manual editing.
package com.puntbyte.dbml.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import static com.puntbyte.dbml.psi.DbmlTypes.*;
import static com.intellij.lang.parser.GeneratedParserUtilBase.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;
import com.intellij.lang.LightPsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class DbmlParser implements PsiParser, LightPsiParser {

  public ASTNode parse(IElementType t, PsiBuilder b) {
    parseLight(t, b);
    return b.getTreeBuilt();
  }

  public void parseLight(IElementType t, PsiBuilder b) {
    boolean r;
    b = adapt_builder_(t, b, this, null);
    Marker m = enter_section_(b, 0, _COLLAPSE_, null);
    r = parse_root_(t, b);
    exit_section_(b, 0, m, t, r, true, TRUE_CONDITION);
  }

  protected boolean parse_root_(IElementType t, PsiBuilder b) {
    return parse_root_(t, b, 0);
  }

  static boolean parse_root_(IElementType t, PsiBuilder b, int l) {
    return DbmlFile(b, l + 1);
  }

  /* ********************************************************** */
  // KW_AS id
  public static boolean AliasClause(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AliasClause")) return false;
    if (!nextTokenIs(b, KW_AS)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, KW_AS);
    r = r && id(b, l + 1);
    exit_section_(b, m, ALIAS_CLAUSE, r);
    return r;
  }

  /* ********************************************************** */
  // L_BRACE CheckItem* R_BRACE
  public static boolean CheckBlock(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CheckBlock")) return false;
    if (!nextTokenIs(b, L_BRACE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, L_BRACE);
    r = r && CheckBlock_1(b, l + 1);
    r = r && consumeToken(b, R_BRACE);
    exit_section_(b, m, CHECK_BLOCK, r);
    return r;
  }

  // CheckItem*
  private static boolean CheckBlock_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CheckBlock_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!CheckItem(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "CheckBlock_1", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // KW_CHECKS CheckBlock
  public static boolean CheckDefinition(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CheckDefinition")) return false;
    if (!nextTokenIs(b, KW_CHECKS)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, KW_CHECKS);
    r = r && CheckBlock(b, l + 1);
    exit_section_(b, m, CHECK_DEFINITION, r);
    return r;
  }

  /* ********************************************************** */
  // EXPRESSION SettingBlock?
  public static boolean CheckItem(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CheckItem")) return false;
    if (!nextTokenIs(b, EXPRESSION)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, EXPRESSION);
    r = r && CheckItem_1(b, l + 1);
    exit_section_(b, m, CHECK_ITEM, r);
    return r;
  }

  // SettingBlock?
  private static boolean CheckItem_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CheckItem_1")) return false;
    SettingBlock(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // ColumnIdentifier ColumnType SettingBlock?
  public static boolean ColumnDefinition(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ColumnDefinition")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, COLUMN_DEFINITION, "<column definition>");
    r = ColumnIdentifier(b, l + 1);
    r = r && ColumnType(b, l + 1);
    r = r && ColumnDefinition_2(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // SettingBlock?
  private static boolean ColumnDefinition_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ColumnDefinition_2")) return false;
    SettingBlock(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // id
  public static boolean ColumnIdentifier(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ColumnIdentifier")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, COLUMN_IDENTIFIER, "<column identifier>");
    r = id(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // id (L_PAREN DataLimit R_PAREN)?
  public static boolean ColumnType(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ColumnType")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, COLUMN_TYPE, "<column type>");
    r = id(b, l + 1);
    r = r && ColumnType_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (L_PAREN DataLimit R_PAREN)?
  private static boolean ColumnType_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ColumnType_1")) return false;
    ColumnType_1_0(b, l + 1);
    return true;
  }

  // L_PAREN DataLimit R_PAREN
  private static boolean ColumnType_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ColumnType_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, L_PAREN);
    r = r && DataLimit(b, l + 1);
    r = r && consumeToken(b, R_PAREN);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // LINE_COMMENT | DOC_COMMENT | BLOCK_COMMENT
  static boolean Comment(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Comment")) return false;
    boolean r;
    r = consumeToken(b, LINE_COMMENT);
    if (!r) r = consumeToken(b, DOC_COMMENT);
    if (!r) r = consumeToken(b, BLOCK_COMMENT);
    return r;
  }

  /* ********************************************************** */
  // L_PAREN NamespaceIdentifier (COMMA NamespaceIdentifier)* R_PAREN
  public static boolean CompositeReference(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CompositeReference")) return false;
    if (!nextTokenIs(b, L_PAREN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, L_PAREN);
    r = r && NamespaceIdentifier(b, l + 1);
    r = r && CompositeReference_2(b, l + 1);
    r = r && consumeToken(b, R_PAREN);
    exit_section_(b, m, COMPOSITE_REFERENCE, r);
    return r;
  }

  // (COMMA NamespaceIdentifier)*
  private static boolean CompositeReference_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CompositeReference_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!CompositeReference_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "CompositeReference_2", c)) break;
    }
    return true;
  }

  // COMMA NamespaceIdentifier
  private static boolean CompositeReference_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CompositeReference_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && NamespaceIdentifier(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // NUMBER (COMMA NUMBER)?
  public static boolean DataLimit(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DataLimit")) return false;
    if (!nextTokenIs(b, NUMBER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, NUMBER);
    r = r && DataLimit_1(b, l + 1);
    exit_section_(b, m, DATA_LIMIT, r);
    return r;
  }

  // (COMMA NUMBER)?
  private static boolean DataLimit_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DataLimit_1")) return false;
    DataLimit_1_0(b, l + 1);
    return true;
  }

  // COMMA NUMBER
  private static boolean DataLimit_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DataLimit_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, COMMA, NUMBER);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // TopLevelItem*
  static boolean DbmlFile(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DbmlFile")) return false;
    while (true) {
      int c = current_position_(b);
      if (!TopLevelItem(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "DbmlFile", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // L_BRACE EnumValue* R_BRACE
  public static boolean EnumBlock(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "EnumBlock")) return false;
    if (!nextTokenIs(b, L_BRACE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, L_BRACE);
    r = r && EnumBlock_1(b, l + 1);
    r = r && consumeToken(b, R_BRACE);
    exit_section_(b, m, ENUM_BLOCK, r);
    return r;
  }

  // EnumValue*
  private static boolean EnumBlock_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "EnumBlock_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!EnumValue(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "EnumBlock_1", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // KW_ENUM NamespaceIdentifier EnumBlock
  public static boolean EnumDefinition(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "EnumDefinition")) return false;
    if (!nextTokenIs(b, KW_ENUM)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, KW_ENUM);
    r = r && NamespaceIdentifier(b, l + 1);
    r = r && EnumBlock(b, l + 1);
    exit_section_(b, m, ENUM_DEFINITION, r);
    return r;
  }

  /* ********************************************************** */
  // (id | STRING) SettingBlock?
  public static boolean EnumValue(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "EnumValue")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ENUM_VALUE, "<enum value>");
    r = EnumValue_0(b, l + 1);
    r = r && EnumValue_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // id | STRING
  private static boolean EnumValue_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "EnumValue_0")) return false;
    boolean r;
    r = id(b, l + 1);
    if (!r) r = consumeToken(b, STRING);
    return r;
  }

  // SettingBlock?
  private static boolean EnumValue_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "EnumValue_1")) return false;
    SettingBlock(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // L_BRACE GroupContent* R_BRACE
  public static boolean GroupBlock(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "GroupBlock")) return false;
    if (!nextTokenIs(b, L_BRACE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, L_BRACE);
    r = r && GroupBlock_1(b, l + 1);
    r = r && consumeToken(b, R_BRACE);
    exit_section_(b, m, GROUP_BLOCK, r);
    return r;
  }

  // GroupContent*
  private static boolean GroupBlock_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "GroupBlock_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!GroupContent(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "GroupBlock_1", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // id | NoteDefinition | Comment
  public static boolean GroupContent(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "GroupContent")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, GROUP_CONTENT, "<group content>");
    r = id(b, l + 1);
    if (!r) r = NoteDefinition(b, l + 1);
    if (!r) r = Comment(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // KW_GROUP id SettingBlock? GroupBlock
  public static boolean GroupDefinition(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "GroupDefinition")) return false;
    if (!nextTokenIs(b, KW_GROUP)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, KW_GROUP);
    r = r && id(b, l + 1);
    r = r && GroupDefinition_2(b, l + 1);
    r = r && GroupBlock(b, l + 1);
    exit_section_(b, m, GROUP_DEFINITION, r);
    return r;
  }

  // SettingBlock?
  private static boolean GroupDefinition_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "GroupDefinition_2")) return false;
    SettingBlock(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // L_BRACE IndexItem* R_BRACE
  public static boolean IndexBlock(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "IndexBlock")) return false;
    if (!nextTokenIs(b, L_BRACE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, L_BRACE);
    r = r && IndexBlock_1(b, l + 1);
    r = r && consumeToken(b, R_BRACE);
    exit_section_(b, m, INDEX_BLOCK, r);
    return r;
  }

  // IndexItem*
  private static boolean IndexBlock_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "IndexBlock_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!IndexItem(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "IndexBlock_1", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // KW_INDEXES IndexBlock
  public static boolean IndexDefinition(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "IndexDefinition")) return false;
    if (!nextTokenIs(b, KW_INDEXES)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, KW_INDEXES);
    r = r && IndexBlock(b, l + 1);
    exit_section_(b, m, INDEX_DEFINITION, r);
    return r;
  }

  /* ********************************************************** */
  // NamespaceIdentifier
  //   | L_PAREN NamespaceIdentifier (COMMA NamespaceIdentifier)* R_PAREN
  //   | EXPRESSION
  public static boolean IndexExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "IndexExpression")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, INDEX_EXPRESSION, "<index expression>");
    r = NamespaceIdentifier(b, l + 1);
    if (!r) r = IndexExpression_1(b, l + 1);
    if (!r) r = consumeToken(b, EXPRESSION);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // L_PAREN NamespaceIdentifier (COMMA NamespaceIdentifier)* R_PAREN
  private static boolean IndexExpression_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "IndexExpression_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, L_PAREN);
    r = r && NamespaceIdentifier(b, l + 1);
    r = r && IndexExpression_1_2(b, l + 1);
    r = r && consumeToken(b, R_PAREN);
    exit_section_(b, m, null, r);
    return r;
  }

  // (COMMA NamespaceIdentifier)*
  private static boolean IndexExpression_1_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "IndexExpression_1_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!IndexExpression_1_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "IndexExpression_1_2", c)) break;
    }
    return true;
  }

  // COMMA NamespaceIdentifier
  private static boolean IndexExpression_1_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "IndexExpression_1_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && NamespaceIdentifier(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // IndexExpression SettingBlock?
  public static boolean IndexItem(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "IndexItem")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, INDEX_ITEM, "<index item>");
    r = IndexExpression(b, l + 1);
    r = r && IndexItem_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // SettingBlock?
  private static boolean IndexItem_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "IndexItem_1")) return false;
    SettingBlock(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // RelationOperator NamespaceIdentifier
  public static boolean InlineRef(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "InlineRef")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, INLINE_REF, "<inline ref>");
    r = RelationOperator(b, l + 1);
    r = r && NamespaceIdentifier(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // KW_REF id? ReferenceBlock
  static boolean LongReference(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LongReference")) return false;
    if (!nextTokenIs(b, KW_REF)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, KW_REF);
    r = r && LongReference_1(b, l + 1);
    r = r && ReferenceBlock(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // id?
  private static boolean LongReference_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LongReference_1")) return false;
    id(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // MapKey COLON MapValue
  public static boolean MapEntry(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MapEntry")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, MAP_ENTRY, "<map entry>");
    r = MapKey(b, l + 1);
    r = r && consumeToken(b, COLON);
    r = r && MapValue(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // id
  public static boolean MapKey(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MapKey")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, MAP_KEY, "<map key>");
    r = id(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // BOOLEAN | NUMBER | STRING | COLOR | EXPRESSION | InlineRef | NULL | id
  public static boolean MapValue(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MapValue")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, MAP_VALUE, "<map value>");
    r = consumeToken(b, BOOLEAN);
    if (!r) r = consumeToken(b, NUMBER);
    if (!r) r = consumeToken(b, STRING);
    if (!r) r = consumeToken(b, COLOR);
    if (!r) r = consumeToken(b, EXPRESSION);
    if (!r) r = InlineRef(b, l + 1);
    if (!r) r = consumeToken(b, NULL);
    if (!r) r = id(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // id (DOT id)*
  public static boolean NamespaceIdentifier(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "NamespaceIdentifier")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, NAMESPACE_IDENTIFIER, "<namespace identifier>");
    r = id(b, l + 1);
    r = r && NamespaceIdentifier_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (DOT id)*
  private static boolean NamespaceIdentifier_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "NamespaceIdentifier_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!NamespaceIdentifier_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "NamespaceIdentifier_1", c)) break;
    }
    return true;
  }

  // DOT id
  private static boolean NamespaceIdentifier_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "NamespaceIdentifier_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, DOT);
    r = r && id(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // L_BRACE (STRING | MULTI_STRING) R_BRACE
  public static boolean NoteBlock(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "NoteBlock")) return false;
    if (!nextTokenIs(b, L_BRACE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, L_BRACE);
    r = r && NoteBlock_1(b, l + 1);
    r = r && consumeToken(b, R_BRACE);
    exit_section_(b, m, NOTE_BLOCK, r);
    return r;
  }

  // STRING | MULTI_STRING
  private static boolean NoteBlock_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "NoteBlock_1")) return false;
    boolean r;
    r = consumeToken(b, STRING);
    if (!r) r = consumeToken(b, MULTI_STRING);
    return r;
  }

  /* ********************************************************** */
  // KW_NOTE NoteBlock
  public static boolean NoteDefinition(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "NoteDefinition")) return false;
    if (!nextTokenIs(b, KW_NOTE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, KW_NOTE);
    r = r && NoteBlock(b, l + 1);
    exit_section_(b, m, NOTE_DEFINITION, r);
    return r;
  }

  /* ********************************************************** */
  // L_BRACE PartialContent* R_BRACE
  public static boolean PartialBlock(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PartialBlock")) return false;
    if (!nextTokenIs(b, L_BRACE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, L_BRACE);
    r = r && PartialBlock_1(b, l + 1);
    r = r && consumeToken(b, R_BRACE);
    exit_section_(b, m, PARTIAL_BLOCK, r);
    return r;
  }

  // PartialContent*
  private static boolean PartialBlock_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PartialBlock_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!PartialContent(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "PartialBlock_1", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // ColumnDefinition | IndexDefinition | NoteDefinition | Comment
  static boolean PartialContent(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PartialContent")) return false;
    boolean r;
    r = ColumnDefinition(b, l + 1);
    if (!r) r = IndexDefinition(b, l + 1);
    if (!r) r = NoteDefinition(b, l + 1);
    if (!r) r = Comment(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // KW_PARTIAL id SettingBlock? PartialBlock
  public static boolean PartialDefinition(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PartialDefinition")) return false;
    if (!nextTokenIs(b, KW_PARTIAL)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, KW_PARTIAL);
    r = r && id(b, l + 1);
    r = r && PartialDefinition_2(b, l + 1);
    r = r && PartialBlock(b, l + 1);
    exit_section_(b, m, PARTIAL_DEFINITION, r);
    return r;
  }

  // SettingBlock?
  private static boolean PartialDefinition_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PartialDefinition_2")) return false;
    SettingBlock(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // TILDE id
  public static boolean PartialInjection(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PartialInjection")) return false;
    if (!nextTokenIs(b, TILDE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, TILDE);
    r = r && id(b, l + 1);
    exit_section_(b, m, PARTIAL_INJECTION, r);
    return r;
  }

  /* ********************************************************** */
  // L_BRACE ProjectContent* (NoteDefinition)? R_BRACE
  public static boolean ProjectBlock(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ProjectBlock")) return false;
    if (!nextTokenIs(b, L_BRACE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, L_BRACE);
    r = r && ProjectBlock_1(b, l + 1);
    r = r && ProjectBlock_2(b, l + 1);
    r = r && consumeToken(b, R_BRACE);
    exit_section_(b, m, PROJECT_BLOCK, r);
    return r;
  }

  // ProjectContent*
  private static boolean ProjectBlock_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ProjectBlock_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!ProjectContent(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "ProjectBlock_1", c)) break;
    }
    return true;
  }

  // (NoteDefinition)?
  private static boolean ProjectBlock_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ProjectBlock_2")) return false;
    ProjectBlock_2_0(b, l + 1);
    return true;
  }

  // (NoteDefinition)
  private static boolean ProjectBlock_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ProjectBlock_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = NoteDefinition(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // MapEntry | Comment
  public static boolean ProjectContent(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ProjectContent")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, PROJECT_CONTENT, "<project content>");
    r = MapEntry(b, l + 1);
    if (!r) r = Comment(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // KW_PROJECT id? ProjectBlock
  public static boolean ProjectDefinition(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ProjectDefinition")) return false;
    if (!nextTokenIs(b, KW_PROJECT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, KW_PROJECT);
    r = r && ProjectDefinition_1(b, l + 1);
    r = r && ProjectBlock(b, l + 1);
    exit_section_(b, m, PROJECT_DEFINITION, r);
    return r;
  }

  // id?
  private static boolean ProjectDefinition_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ProjectDefinition_1")) return false;
    id(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // L_BRACE ReferenceExpression R_BRACE
  public static boolean ReferenceBlock(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ReferenceBlock")) return false;
    if (!nextTokenIs(b, L_BRACE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, L_BRACE);
    r = r && ReferenceExpression(b, l + 1);
    r = r && consumeToken(b, R_BRACE);
    exit_section_(b, m, REFERENCE_BLOCK, r);
    return r;
  }

  /* ********************************************************** */
  // LongReference | ShortReference
  public static boolean ReferenceDefinition(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ReferenceDefinition")) return false;
    if (!nextTokenIs(b, KW_REF)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = LongReference(b, l + 1);
    if (!r) r = ShortReference(b, l + 1);
    exit_section_(b, m, REFERENCE_DEFINITION, r);
    return r;
  }

  /* ********************************************************** */
  // NamespaceIdentifier | CompositeReference
  public static boolean ReferenceEndpoint(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ReferenceEndpoint")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, REFERENCE_ENDPOINT, "<reference endpoint>");
    r = NamespaceIdentifier(b, l + 1);
    if (!r) r = CompositeReference(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // ReferenceEndpoint RelationOperator ReferenceEndpoint SettingBlock?
  public static boolean ReferenceExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ReferenceExpression")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, REFERENCE_EXPRESSION, "<reference expression>");
    r = ReferenceEndpoint(b, l + 1);
    r = r && RelationOperator(b, l + 1);
    r = r && ReferenceEndpoint(b, l + 1);
    r = r && ReferenceExpression_3(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // SettingBlock?
  private static boolean ReferenceExpression_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ReferenceExpression_3")) return false;
    SettingBlock(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // OP_ONE_MANY | OP_MANY_ONE | OP_ONE_ONE | OP_MANY_MANY
  public static boolean RelationOperator(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "RelationOperator")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, RELATION_OPERATOR, "<relation operator>");
    r = consumeToken(b, OP_ONE_MANY);
    if (!r) r = consumeToken(b, OP_MANY_ONE);
    if (!r) r = consumeToken(b, OP_ONE_ONE);
    if (!r) r = consumeToken(b, OP_MANY_MANY);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // L_BRACKET SettingList R_BRACKET
  public static boolean SettingBlock(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SettingBlock")) return false;
    if (!nextTokenIs(b, L_BRACKET)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, L_BRACKET);
    r = r && SettingList(b, l + 1);
    r = r && consumeToken(b, R_BRACKET);
    exit_section_(b, m, SETTING_BLOCK, r);
    return r;
  }

  /* ********************************************************** */
  // MapEntry | id | Comment
  public static boolean SettingContent(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SettingContent")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, SETTING_CONTENT, "<setting content>");
    r = MapEntry(b, l + 1);
    if (!r) r = id(b, l + 1);
    if (!r) r = Comment(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // SettingContent (COMMA SettingContent)*
  public static boolean SettingList(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SettingList")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, SETTING_LIST, "<setting list>");
    r = SettingContent(b, l + 1);
    r = r && SettingList_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (COMMA SettingContent)*
  private static boolean SettingList_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SettingList_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!SettingList_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "SettingList_1", c)) break;
    }
    return true;
  }

  // COMMA SettingContent
  private static boolean SettingList_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SettingList_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && SettingContent(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // KW_REF id? COLON ReferenceExpression
  static boolean ShortReference(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ShortReference")) return false;
    if (!nextTokenIs(b, KW_REF)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, KW_REF);
    r = r && ShortReference_1(b, l + 1);
    r = r && consumeToken(b, COLON);
    r = r && ReferenceExpression(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // id?
  private static boolean ShortReference_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ShortReference_1")) return false;
    id(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // KW_NOTE id? NoteBlock
  public static boolean StickyNoteDefinition(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StickyNoteDefinition")) return false;
    if (!nextTokenIs(b, KW_NOTE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, KW_NOTE);
    r = r && StickyNoteDefinition_1(b, l + 1);
    r = r && NoteBlock(b, l + 1);
    exit_section_(b, m, STICKY_NOTE_DEFINITION, r);
    return r;
  }

  // id?
  private static boolean StickyNoteDefinition_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StickyNoteDefinition_1")) return false;
    id(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // L_BRACE TableContent* R_BRACE
  public static boolean TableBlock(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TableBlock")) return false;
    if (!nextTokenIs(b, L_BRACE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, L_BRACE);
    r = r && TableBlock_1(b, l + 1);
    r = r && consumeToken(b, R_BRACE);
    exit_section_(b, m, TABLE_BLOCK, r);
    return r;
  }

  // TableContent*
  private static boolean TableBlock_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TableBlock_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!TableContent(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "TableBlock_1", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // ColumnDefinition
  //   | PartialInjection
  //   | IndexDefinition
  //   | CheckDefinition
  //   | NoteDefinition
  //   | MapEntry
  //   | Comment
  static boolean TableContent(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TableContent")) return false;
    boolean r;
    r = ColumnDefinition(b, l + 1);
    if (!r) r = PartialInjection(b, l + 1);
    if (!r) r = IndexDefinition(b, l + 1);
    if (!r) r = CheckDefinition(b, l + 1);
    if (!r) r = NoteDefinition(b, l + 1);
    if (!r) r = MapEntry(b, l + 1);
    if (!r) r = Comment(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // KW_TABLE TableIdentifier AliasClause? SettingBlock? TableBlock
  public static boolean TableDefinition(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TableDefinition")) return false;
    if (!nextTokenIs(b, KW_TABLE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, KW_TABLE);
    r = r && TableIdentifier(b, l + 1);
    r = r && TableDefinition_2(b, l + 1);
    r = r && TableDefinition_3(b, l + 1);
    r = r && TableBlock(b, l + 1);
    exit_section_(b, m, TABLE_DEFINITION, r);
    return r;
  }

  // AliasClause?
  private static boolean TableDefinition_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TableDefinition_2")) return false;
    AliasClause(b, l + 1);
    return true;
  }

  // SettingBlock?
  private static boolean TableDefinition_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TableDefinition_3")) return false;
    SettingBlock(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // NamespaceIdentifier
  public static boolean TableIdentifier(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TableIdentifier")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, TABLE_IDENTIFIER, "<table identifier>");
    r = NamespaceIdentifier(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // ProjectDefinition
  //   | EnumDefinition
  //   | TableDefinition
  //   | ReferenceDefinition
  //   | GroupDefinition
  //   | PartialDefinition
  //   | StickyNoteDefinition
  //   | Comment
  static boolean TopLevelItem(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TopLevelItem")) return false;
    boolean r;
    r = ProjectDefinition(b, l + 1);
    if (!r) r = EnumDefinition(b, l + 1);
    if (!r) r = TableDefinition(b, l + 1);
    if (!r) r = ReferenceDefinition(b, l + 1);
    if (!r) r = GroupDefinition(b, l + 1);
    if (!r) r = PartialDefinition(b, l + 1);
    if (!r) r = StickyNoteDefinition(b, l + 1);
    if (!r) r = Comment(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // IDENTIFIER
  //   | DATA_TYPE
  //   | SETTING_KEY
  //   | SETTING_VAL
  //   | KW_TABLE | KW_GROUP | KW_PARTIAL | KW_PROJECT | KW_REF | KW_ENUM
  //   | KW_NOTE | KW_INDEXES | KW_CHECKS | KW_AS
  //   | BOOLEAN | NULL
  public static boolean id(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "id")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ID, "<id>");
    r = consumeToken(b, IDENTIFIER);
    if (!r) r = consumeToken(b, DATA_TYPE);
    if (!r) r = consumeToken(b, SETTING_KEY);
    if (!r) r = consumeToken(b, SETTING_VAL);
    if (!r) r = consumeToken(b, KW_TABLE);
    if (!r) r = consumeToken(b, KW_GROUP);
    if (!r) r = consumeToken(b, KW_PARTIAL);
    if (!r) r = consumeToken(b, KW_PROJECT);
    if (!r) r = consumeToken(b, KW_REF);
    if (!r) r = consumeToken(b, KW_ENUM);
    if (!r) r = consumeToken(b, KW_NOTE);
    if (!r) r = consumeToken(b, KW_INDEXES);
    if (!r) r = consumeToken(b, KW_CHECKS);
    if (!r) r = consumeToken(b, KW_AS);
    if (!r) r = consumeToken(b, BOOLEAN);
    if (!r) r = consumeToken(b, NULL);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

}
