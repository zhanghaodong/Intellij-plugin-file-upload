package com.rzhang.marklogic.plugin.net;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

public class FileUploader {

    private static final CloseableHttpClient client = HttpClientBuilder.create().build();
    private static String url;
    private static String basicToken;



    public static void uploadFile(String filePath){
//        client.execute()
    }
}
