# Database Markup Language (DBML) for IntelliJ IDEA

![Build Status](https://img.shields.io/badge/build-passing-brightgreen)
![Version](https://img.shields.io/badge/version-1.0.0-blue)
![Platform](https://img.shields.io/badge/platform-IntelliJ-pink)

A robust IntelliJ IDEA plugin that provides first-class support for **[DBML](https://dbdiagram.io/d)** (Database Markup
Language).

This plugin helps developers design database schemas visually and textually by providing syntax highlighting, code
formatting, smart auto-completion, and navigation features for `.dbml` files.

---

## ✨ Features

### 1. Syntax Highlighting & Parsing

* Full tokenization of DBML keywords (`Table`, `Ref`, `Enum`, `Project`, etc.).
* Distinct coloring for Data Types, Strings, Numbers, Comments, and Settings.
* Support for single-line (`//`) and documentation (`///`) comments.

### 2. Smart Auto-Completion

* **Context-Aware:** Suggests standard SQL types (e.g., `int`, `varchar`, `boolean`) and DBML keywords.
* **Dialect Support:** Automatically detects your project's database type to suggest specific data types (
  see [Configuration](#configuration-database-dialects)).

### 3. Code Formatting

* Reformat code using the standard shortcut (`Ctrl + Alt + L` / `Cmd + Opt + L`).
* Handles indentation for nested blocks (`Table`, `Enum`, `Group`).
* Aligns braces and manages spacing around operators (`<`, `>`, `-`).

### 4. Navigation & References

* **Go to Definition:** `Ctrl + Click` (or `Cmd + Click`) on a table name in a `Ref:` block to jump to the Table
  definition.
* **Partial Injection:** Support for resolving partial references (files included via `~`).

### 5. Editor Enhancements

* **Brace Matching:** Highlights matching `{ }`, `[ ]`, and `( )`.
* **Quote Handling:** Auto-closes quotes for strings.
* **Commenter:** Standard `Ctrl + /` support to toggle comments.

---

## 🛠 Configuration: Database Dialects

This plugin supports specific data type suggestions based on your target database.

To enable **PostgreSQL** specific auto-completion (e.g., `jsonb`, `uuid`, `timestamptz`, `serial`), define the
`database_type` inside a `Project` block in your `.dbml` file:

```dbml
Project my_project_name {
  database_type: 'PostgreSQL'
  Note: 'Description of the project'
}

Table users {
  id integer [pk, increment]
  // 'jsonb' will now appear in auto-complete suggestions
  metadata jsonb 
  created_at timestamptz
}
```

*Supported Dialects:*

* `'PostgreSQL'` / `'Postgres'`
* `'MySQL'` (Generic support)
* *Default:* Standard SQL types

---

## 📥 Installation

### Method 1: Install from Disk (Development)

1. Clone this repository.
2. Run the Gradle build command:
   ```bash
   ./gradlew buildPlugin
   ```
3. Locate the generated ZIP file in `build/distributions/`.
4. Open IntelliJ IDEA settings: `Settings/Preferences` -> `Plugins` -> `⚙️` -> `Install Plugin from Disk...`.
5. Select the ZIP file and restart the IDE.

### Method 2: JetBrains Marketplace

*(Once published)*

1. Open `Settings/Preferences` -> `Plugins`.
2. Search for **"Database Markup Language"**.
3. Click **Install**.

---

## 🤝 Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository.
2. Create a feature branch (`git checkout -b feature/NewFeature`).
3. Commit your changes.
4. Push to the branch.
5. Open a Pull Request.

---

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
