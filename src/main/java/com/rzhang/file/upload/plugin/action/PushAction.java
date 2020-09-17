package com.rzhang.file.upload.plugin.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.rzhang.file.upload.plugin.net.FileUploader;
import com.rzhang.file.upload.plugin.util.MessageUtils;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;


public class PushAction extends AnAction {

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        try {
            VirtualFile virtualFile = e.getData(PlatformDataKeys.VIRTUAL_FILE);
            Project project = e.getProject();
            assert project != null;
            FileUploader.getInstance(project).uploadFile(Objects.requireNonNull(virtualFile));
        } catch (Exception exception) {
            MessageUtils.showAllWarnMsg("error", String.format("Uploaded to server failed, caused by %s", exception.getMessage()));
        }
    }
}
