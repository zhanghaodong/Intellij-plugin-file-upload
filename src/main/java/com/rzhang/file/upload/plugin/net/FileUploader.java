package com.rzhang.file.upload.plugin.net;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VfsUtilCore;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileVisitor;
import com.rzhang.file.upload.plugin.setting.FileUploadConfig;
import com.rzhang.file.upload.plugin.util.MessageUtils;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.*;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Objects;

public class FileUploader {

    private final static CloseableHttpClient client = HttpClients.createDefault();
    private final FileUploadConfig persistentConfig = FileUploadConfig.getInstance();
    private final HttpClientContext context;
    private volatile static FileUploader fileUploader;
    private static Project currentProject;

    private FileUploader(Project project) {
        context = HttpClientContext.create();
        CredentialsProvider credentials = new BasicCredentialsProvider();
        credentials.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(persistentConfig.getUserName(), persistentConfig.getPassWord()));
        context.setCredentialsProvider(credentials);
        currentProject = project;
    }

    public void uploadFile(VirtualFile file) {
        String[] fileExtensions = persistentConfig.getFileExtension().split(";");
        VfsUtilCore.visitChildrenRecursively(file, new VirtualFileVisitor() {
            @Override
            public boolean visitFile(@NotNull VirtualFile file) {
                if (Arrays.asList(fileExtensions).contains(file.getExtension())) {
                    execute(file);
                }
                return super.visitFile(file);
            }
        });
    }

    private void execute(VirtualFile file) {
        String prefixUrl = getRelativePath(file);
        String[] urls = persistentConfig.getUrl().split(";");
        for (String url : urls) {
            url = persistentConfig.getUrl() + prefixUrl;
            HttpPut httpPut = new HttpPut(url);
            try {
                httpPut.setEntity(new StringEntity(VfsUtil.loadText(file)));
                CloseableHttpResponse response = client.execute(httpPut, context);
                MessageUtils.showAllWarnMsg("info", String.format("Upload file %s successfully, response code: %s",
                        file.getCanonicalPath(), response.getStatusLine().getStatusCode()));
            } catch (Exception e) {
                MessageUtils.showAllWarnMsg("error", "Uploaded to servers failed");
            }
        }
    }

    private String getRelativePath(VirtualFile file) {
        String basePath = currentProject.getBasePath();
        String filePath = file.getPath();
        assert basePath != null;
        return filePath.replace(basePath, "");
    }

    public static FileUploader getInstance(Project project) {
        if (Objects.isNull(fileUploader) || !currentProject.getName().equals(project.getName())) {
            synchronized (FileUploader.class) {
                fileUploader = new FileUploader(project);
            }
        }
        return fileUploader;
    }
}
