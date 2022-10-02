package org.chubxu.typehelper.extension;

import com.intellij.codeInsight.editorActions.TypedHandlerDelegate;
import com.intellij.openapi.editor.Caret;
import com.intellij.openapi.editor.CaretModel;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;
import org.chubxu.typehelper.settings.TypeHelperSettings;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class CommaTypeHandler extends TypedHandlerDelegate {
    // 中文标点 -> 英文标点
    private static final Map<Character, Character> chPunToEnPun;
    private TypeHelperSettings typeHelperSettings;

    static {
        chPunToEnPun = new HashMap<>();
        chPunToEnPun.put('，', ',');
        chPunToEnPun.put('。', '.');
    }

    public CommaTypeHandler() {
        typeHelperSettings = TypeHelperSettings.getInstance();
    }

    @Override
    public @NotNull Result beforeCharTyped(char c, @NotNull Project project, @NotNull Editor editor, @NotNull PsiFile file, @NotNull FileType fileType) {
        return Result.CONTINUE;
    }

    @Override
    public @NotNull Result charTyped(char c, @NotNull Project project, @NotNull Editor editor, @NotNull PsiFile file) {
        if (typeHelperSettings.isEnable() && chPunToEnPun.containsKey(c)) {
            Document document = editor.getDocument();
            CaretModel caretModel = editor.getCaretModel();
            Caret primaryCaret = caretModel.getPrimaryCaret();

            int caretOffset = primaryCaret.getOffset();
            document.deleteString(caretOffset-1, caretOffset);
            document.insertString(caretOffset-1, String.valueOf(chPunToEnPun.get(c)));
            primaryCaret.moveToOffset(caretOffset);
        }
        return Result.CONTINUE;
    }
}
