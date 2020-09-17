package com.rzhang.file.upload.plugin.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.application.ApplicationManager;
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
            VirtualFile[] virtualFiles = e.getData(PlatformDataKeys.VIRTUAL_FILE_ARRAY);
            Project project = e.getProject();
            assert project != null;
            assert virtualFiles != null;
            for (VirtualFile file:virtualFiles) {
                ApplicationManager.getApplication().executeOnPooledThread(() -> ApplicationManager.getApplication().runReadAction(()
                        -> FileUploader.getInstance(project).uploadFile(Objects.requireNonNull(file))));
            }
        } catch (Exception exception) {
            MessageUtils.showAllWarnMsg("error", String.format("Uploaded to server failed, caused by %s", exception.getMessage()));
        }
    }
}
