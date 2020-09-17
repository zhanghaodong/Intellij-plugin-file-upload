package com.rzhang.file.upload.plugin.setting;


import com.intellij.openapi.components.*;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author Rex Zhang
 */
@State(name = "PersistentConfig", storages = {@Storage(value = "file-upload-config.xml", roamingType = RoamingType.DISABLED)})
public class FileUploadConfig implements PersistentStateComponent<FileUploadConfig> {

    private String userName;
    private String passWord;
    private String url;
    private String fileExtension;

    public static FileUploadConfig getInstance() {
        return ServiceManager.getService(FileUploadConfig.class);
    }

    @Nullable
    @Override
    public FileUploadConfig getState() {
        return this;
    }

    @Override
    public void loadState(@NotNull FileUploadConfig fileUploadConfig) {
        XmlSerializerUtil.copyBean(fileUploadConfig, fileUploadConfig);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }
}