package org.chubxu.typehelper;

import com.intellij.codeInsight.editorActions.TypedHandlerDelegate;
import com.intellij.openapi.editor.Caret;
import com.intellij.openapi.editor.CaretModel;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;

public class CommaTypeHandler extends TypedHandlerDelegate {
    @Override
    public @NotNull Result beforeCharTyped(char c, @NotNull Project project, @NotNull Editor editor, @NotNull PsiFile file, @NotNull FileType fileType) {
        return Result.CONTINUE;
    }

    @Override
    public @NotNull Result charTyped(char c, @NotNull Project project, @NotNull Editor editor, @NotNull PsiFile file) {
        String commaChinese = String.valueOf(c);
        if ("，".equals(commaChinese)) {
            Document document = editor.getDocument();
            CaretModel caretModel = editor.getCaretModel();
            Caret primaryCaret = caretModel.getPrimaryCaret();
            int caretOffset = primaryCaret.getOffset();
            document.deleteString(caretOffset-1, caretOffset);
            document.insertString(caretOffset-1, ",");
            primaryCaret.moveToOffset(caretOffset);
        }
        return Result.CONTINUE;
    }
}
