package org.chubxu.typehelper.action;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.markup.HighlighterLayer;
import com.intellij.openapi.editor.markup.HighlighterTargetArea;
import com.intellij.openapi.editor.markup.MarkupModel;
import com.intellij.openapi.editor.markup.TextAttributes;
import com.intellij.openapi.project.DumbAwareAction;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.ui.Gray;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class TestAction extends DumbAwareAction {

    private final TextAttributes textAttributes;

    public TestAction() {
        super();
        textAttributes = new TextAttributes();
        textAttributes.setBackgroundColor(Gray._51);
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        Editor editor = e.getRequiredData(CommonDataKeys.EDITOR);
        MarkupModel markupModel = editor.getMarkupModel();
        PsiFile psiFile = e.getRequiredData(CommonDataKeys.PSI_FILE);

        PsiMethod psiMethod = PsiTreeUtil.findElementOfClassAtOffset(psiFile, editor.getCaretModel().getOffset(),
                PsiMethod.class, false);

        if (Objects.isNull(psiMethod)) {
            markupModel.removeAllHighlighters();
            return ;
        }
        markupModel.removeAllHighlighters();
        TextRange textRange = psiMethod.getTextRange();
        markupModel.addRangeHighlighter(0, getLineStartOffsetByMethodStartOffset(editor, textRange.getStartOffset()),
                HighlighterLayer.FIRST, textAttributes, HighlighterTargetArea.EXACT_RANGE);
        markupModel.addRangeHighlighter(getLineEndOffsetByMethodEndOffset(editor, textRange.getEndOffset()),
                editor.getDocument().getTextLength(), HighlighterLayer.SELECTION - 1, textAttributes,
                HighlighterTargetArea.EXACT_RANGE);
    }

    private int getLineStartOffsetByMethodStartOffset(Editor editor, int startOffset) {
        int lineNumber = editor.getDocument().getLineNumber(startOffset);
        return editor.getDocument().getLineStartOffset(lineNumber);
    }

    private int getLineEndOffsetByMethodEndOffset(Editor editor, int endOffset) {
        int lineNumber = editor.getDocument().getLineNumber(endOffset);
        return editor.getDocument().getLineStartOffset(lineNumber+1);
    }
}
