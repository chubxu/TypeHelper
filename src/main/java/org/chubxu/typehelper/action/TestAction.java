package org.chubxu.typehelper.action;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.editor.markup.*;
import com.intellij.openapi.project.DumbAwareAction;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.ui.Gray;
import com.intellij.ui.JBColor;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class TestAction extends DumbAwareAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        Project project = e.getProject();
        Editor editor = e.getRequiredData(CommonDataKeys.EDITOR);
        String selectedText = editor.getSelectionModel().getSelectedText();
        TextRange textRange = new TextRange(
                editor.getSelectionModel().getSelectionStart(),
                editor.getSelectionModel().getSelectionEnd()
        );

        TextAttributes textAttributes = new TextAttributes(JBColor.YELLOW, JBColor.BLUE, JBColor.GRAY, EffectType.ROUNDED_BOX, Font.BOLD);

        MarkupModel markupModel = editor.getMarkupModel();
        markupModel.addRangeHighlighter(
                textRange.getStartOffset(),
                textRange.getEndOffset(),
                HighlighterLayer.SELECTION - 1,
                textAttributes,
                HighlighterTargetArea.EXACT_RANGE
        );
    }
}
