package com.puntbyte.dbml.completion

object DbmlDialects {

  // Types common to almost all SQL flavors
  private val STANDARD_TYPES = listOf(
    "int", "integer", "varchar", "char", "text", "boolean", "bool",
    "date", "datetime", "timestamp", "decimal", "float", "double", "blob"
  )

  // PostgreSQL specific extensions
  private val POSTGRES_TYPES = STANDARD_TYPES + listOf(
    "uuid", "jsonb", "json", "xml", "timestamptz", "serial", "bigserial",
    "inet", "cidr", "macaddr", "tsvector", "tsquery", "money", "bytea"
  )

  // MySQL specific extensions (Example for future use)
  private val MYSQL_TYPES = STANDARD_TYPES + listOf(
    "tinyint", "mediumint", "bigint", "year", "longtext", "mediumtext", "tinytext"
  )

  /**
   * Returns the list of types based on the project database_type string.
   * Case-insensitive comparison.
   */
  fun getDataTypes(databaseType: String?): List<String> {
    return when (databaseType?.lowercase()) {
      "postgresql", "postgres" -> POSTGRES_TYPES
      "mysql" -> MYSQL_TYPES
      else -> STANDARD_TYPES
    }
  }
}