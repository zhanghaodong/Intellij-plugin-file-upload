package com.rzhang.marklogic.plugin.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.rzhang.marklogic.plugin.net.FileUploader;
import com.rzhang.marklogic.plugin.setting.PersistentConfig;

public class PushAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        // TODO: insert action logic here
        PersistentConfig config = PersistentConfig.getInstance();
        FileUploader.uploadFile("");
    }
}
