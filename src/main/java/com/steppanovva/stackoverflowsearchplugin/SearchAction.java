package com.steppanovva.stackoverflowsearchplugin;

import com.intellij.ide.BrowserUtil;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.ui.Messages;
import org.jetbrains.annotations.NotNull;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class SearchAction extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {
        Editor editor = event.getData(PlatformDataKeys.EDITOR);

        assert editor != null;
        String selectedText = editor.getSelectionModel().getSelectedText();

        if (selectedText != null) {
            String encoded = URLEncoder.encode(selectedText, StandardCharsets.UTF_8);
            String url = String.format("https://stackoverflow.com/search?q=%s", encoded);
            Messages.showMessageDialog(url, "StackOverflow Search", Messages.getInformationIcon());
            BrowserUtil.browse(url);
        } else {
            Messages.showMessageDialog("No selected text", "StackOverflow Search",
                    Messages.getInformationIcon());
        }
    }
}
