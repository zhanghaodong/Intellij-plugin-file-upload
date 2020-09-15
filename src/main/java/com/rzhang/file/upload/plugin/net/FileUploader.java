package com.rzhang.file.upload.plugin.net;

import com.intellij.openapi.vfs.VirtualFile;
import com.rzhang.file.upload.plugin.setting.PersistentConfig;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.*;

import java.io.IOException;
import java.util.Objects;

public class FileUploader {

    private final static CloseableHttpClient client = HttpClients.createDefault();
    private final PersistentConfig persistentConfig = PersistentConfig.getInstance();
    private final HttpClientContext context;
    private volatile static FileUploader fileUploader;

    private FileUploader(){
        context = HttpClientContext.create();
        CredentialsProvider credentials = new BasicCredentialsProvider();
        credentials.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(persistentConfig.getUserName(), persistentConfig.getPassword()));
        context.setCredentialsProvider(credentials);
    }

    public void uploadFile(VirtualFile file) throws IOException {
        HttpPut httpPut = new HttpPut(persistentConfig.getUri());
        httpPut.setEntity(new StringEntity(file.getUserDataString()));
        try {
            CloseableHttpResponse response = client.execute(httpPut, context);
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }

    public static FileUploader getInstance() {
        if (Objects.isNull(fileUploader)){
            synchronized (FileUploader.class){
                fileUploader = new FileUploader();
            }
        }
        return fileUploader;
    }
}
