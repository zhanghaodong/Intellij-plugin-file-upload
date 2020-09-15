package com.rzhang.file.upload.plugin.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.vfs.VirtualFile;
import com.rzhang.file.upload.plugin.net.FileUploader;
import com.rzhang.file.upload.plugin.setting.PersistentConfig;

import java.io.IOException;
import java.util.Objects;

public class PushAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        try {
            VirtualFile virtualFile = e.getData(PlatformDataKeys.VIRTUAL_FILE);
            FileUploader.getInstance().uploadFile(Objects.requireNonNull(virtualFile));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
