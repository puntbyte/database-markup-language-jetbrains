package com.puntbyte.dbml.highlight

import com.intellij.openapi.fileTypes.SyntaxHighlighter
import com.intellij.openapi.fileTypes.SyntaxHighlighterFactory
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile

class DbmlSyntaxHighlighterFactory : SyntaxHighlighterFactory() {
  override fun getSyntaxHighlighter(
    project: Project?,
    virtualFile: VirtualFile?
  ): SyntaxHighlighter {
    return DbmlSyntaxHighlighter()
  }
}