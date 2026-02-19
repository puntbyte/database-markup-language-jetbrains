package com.puntbyte.dbml.ide

import com.intellij.codeInsight.editorActions.TypedHandlerDelegate
import com.intellij.openapi.editor.Editor
import com.intellij.psi.PsiFile
import com.intellij.psi.TokenType
import com.intellij.psi.tree.IElementType
import com.puntbyte.dbml.psi.DbmlTypes
import com.intellij.codeInsight.lookup.LookupManager
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.psi.PsiDocumentManager
import com.intellij.openapi.util.TextRange
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.CaretModel

class DbmlTypedHandler : TypedHandlerDelegate() {

  override fun charTyped(c: Char, project: com.intellij.openapi.project.Project, editor: Editor, file: PsiFile): Result {
    // only care about single quote
    if (c != '\'') return Result.CONTINUE

    // If completion popup is active, skip auto insert
    if (LookupManager.getActiveLookup(editor) != null) return Result.CONTINUE

    val caretModel = editor.caretModel
    val offset = caretModel.offset
    val document = editor.document

    // avoid out-of-bounds
    if (offset < 0) return Result.CONTINUE

    // If next char already is a closing quote, just move caret forward
    val nextChar = if (offset < document.textLength) document.charsSequence[offset] else null
    if (nextChar == '\'') {
      // just let the default handler move caret (no insertion)
      return Result.CONTINUE
    }

    // Insert a closing single-quote
    WriteCommandAction.runWriteCommandAction(project) {
      document.insertString(offset, "'")
      // leave caret between the quotes
      caretModel.moveToOffset(offset)
      PsiDocumentManager.getInstance(project).commitDocument(document)
    }

    return Result.STOP
  }
}
