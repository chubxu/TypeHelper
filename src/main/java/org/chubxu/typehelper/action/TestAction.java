package org.chubxu.typehelper.action;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.editor.markup.*;
import com.intellij.openapi.project.DumbAwareAction;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.ui.Gray;
import com.intellij.ui.JBColor;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class TestAction extends DumbAwareAction {

    private TextAttributes textAttributes;

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        Project project = e.getProject();
        Editor editor = e.getRequiredData(CommonDataKeys.EDITOR);
        PsiFile psiFile = e.getRequiredData(CommonDataKeys.PSI_FILE);
        String selectedText = editor.getSelectionModel().getSelectedText();
        textAttributes = new TextAttributes(JBColor.YELLOW, JBColor.BLUE, JBColor.GRAY, EffectType.ROUNDED_BOX, Font.BOLD);

        PsiElement psiElement = psiFile.findElementAt(editor.getCaretModel().getOffset());
        PsiElement elementParent = psiElement.getParent();


        PsiMethod psiMethod = PsiTreeUtil.findElementOfClassAtOffset(
                psiFile,
                editor.getCaretModel().getOffset(),
                PsiMethod.class,
                false
        );
        TextRange textRange = psiMethod.getTextRange();


//        TextRange textRange = new TextRange(
//                editor.getSelectionModel().getSelectionStart(),
//                editor.getSelectionModel().getSelectionEnd()
//        );


        MarkupModel markupModel = editor.getMarkupModel();

        markupModel.removeAllHighlighters();

        markupModel.addRangeHighlighter(
                textRange.getStartOffset(),
                textRange.getEndOffset(),
                HighlighterLayer.SELECTION - 1,
                textAttributes,
                HighlighterTargetArea.EXACT_RANGE
        );
    }
}
