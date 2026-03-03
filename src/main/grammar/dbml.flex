package com.puntbyte.dbml.lexer;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.TokenType;
import com.puntbyte.dbml.psi.DbmlTypes;

%%

%class DbmlLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType
%ignorecase
%eof{  return;
%eof}

CRLF=\R
WHITE_SPACE=[\ \n\t\f]

// Values
BOOLEAN=(true|false)
NUMBER=\-?[0-9]+(\.[0-9]+)?
STRING='([^'\\]|\\.)*'
MULTI_STRING=\'\'\'([^\']|\'([^\']|\'[^\']))*\'\'\'
COLOR="#"[a-fA-F0-9]{3,6}

// Identifiers
IDENTIFIER=[a-zA-Z_][a-zA-Z0-9_]*
QUOTED_ID=\"([^\"\\]|\\.)*\"
EXPRESSION=`([^`\\]|\\.)*`

// Comments
LINE_COMMENT="//"[^\r\n]*
DOC_COMMENT="///"[^\r\n]*
BLOCK_COMMENT="/*"([^*]|\*+[^*/])*(\*+"/")?

%%

<YYINITIAL> {
  {LINE_COMMENT}             { return DbmlTypes.LINE_COMMENT; }
  {DOC_COMMENT}              { return DbmlTypes.DOC_COMMENT; }
  {BLOCK_COMMENT}            { return DbmlTypes.BLOCK_COMMENT; }

  // Structure
  "{"                        { return DbmlTypes.L_BRACE; }
  "}"                        { return DbmlTypes.R_BRACE; }
  "["                        { return DbmlTypes.L_BRACKET; }
  "]"                        { return DbmlTypes.R_BRACKET; }
  "("                        { return DbmlTypes.L_PAREN; }
  ")"                        { return DbmlTypes.R_PAREN; }

  // Operators
  ":"                        { return DbmlTypes.COLON; }
  "."                        { return DbmlTypes.DOT; }
  ","                        { return DbmlTypes.COMMA; }
  "~"                        { return DbmlTypes.TILDE; }

  // Relations
  "<>"                       { return DbmlTypes.OP_MANY_MANY; }
  "<"                        { return DbmlTypes.OP_ONE_MANY; }
  ">"                        { return DbmlTypes.OP_MANY_ONE; }
  "-"                        { return DbmlTypes.OP_ONE_ONE; }

  // Values
  {BOOLEAN}                  { return DbmlTypes.BOOLEAN; }
  {NUMBER}                   { return DbmlTypes.NUMBER; }
  {STRING}                   { return DbmlTypes.STRING; }
  {MULTI_STRING}             { return DbmlTypes.MULTI_STRING; }
  {COLOR}                    { return DbmlTypes.COLOR; }
  "null"                     { return DbmlTypes.NULL; }
  {EXPRESSION}               { return DbmlTypes.EXPRESSION; }

  // Keywords (Case Insensitive logic handled here)
  "Table" | "table" | "TABLE"                       { return DbmlTypes.KW_TABLE; }

  "TableGroup" | "tablegroup" | "TABLEGROUP" |
  "Group" | "group" | "GROUP"                       { return DbmlTypes.KW_GROUP; }

  "TablePartial" | "tablepartial" | "TABLEPARTIAL" |
  "Partial" | "partial" | "PARTIAL"                 { return DbmlTypes.KW_PARTIAL; }

  "Project" | "project" | "PROJECT"                 { return DbmlTypes.KW_PROJECT; }

  "Ref" | "ref" | "REF" |
  "Reference" | "reference" | "REFERENCE"           { return DbmlTypes.KW_REF; }

  "Enum" | "enum" | "ENUM"                          { return DbmlTypes.KW_ENUM; }
  "Note" | "note" | "NOTE"                          { return DbmlTypes.KW_NOTE; }
  "Indexes" | "indexes" | "INDEXES"                 { return DbmlTypes.KW_INDEXES; }
  "Checks" | "checks" | "CHECKS"                    { return DbmlTypes.KW_CHECKS; }
  "As" | "as" | "AS"                                { return DbmlTypes.KW_AS; }

  // Data Types (For highlighting mainly, but Parser accepts ANY identifier as type)
  "int" | "integer" | "tinyint" | "smallint" | "bigint" |
  "serial" | "bigserial" |
  "varchar" | "char" | "text" | "string" | "longtext" |
  "bool" | "boolean" | "bit" |
  "timestamp" | "timestamptz" | "date" | "datetime" | "time" |
  "decimal" | "numeric" | "float" | "double" | "real" |
  "json" | "jsonb" | "xml" |
  "blob" | "binary" | "varbinary" | "uuid"          { return DbmlTypes.DATA_TYPE; }

  // Settings
  "pk" | "primary key" |
  "not null" |
  "unique" |
  "increment" |
  "default" |
  "headercolor" | "headerColor" |
  "name" |
  "type" |
  "delete" | "update"        { return DbmlTypes.SETTING_KEY; }

  "cascade" | "restrict" | "set null" | "set default" | "no action"     { return DbmlTypes.SETTING_VAL; }

  {IDENTIFIER}               { return DbmlTypes.IDENTIFIER; }
  {QUOTED_ID}                { return DbmlTypes.IDENTIFIER; }

  {WHITE_SPACE}              { return TokenType.WHITE_SPACE; }
}

[^] { return TokenType.BAD_CHARACTER; }