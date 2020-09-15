package com.rzhang.marklogic.plugin.setting;

import com.intellij.openapi.components.*;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/**
 * @author Rex Zhang
 */
@State(name = "PersistentConfig", storages = {@Storage(value = "marklogic-config.xml", roamingType = RoamingType.DISABLED)})
public class PersistentConfig implements PersistentStateComponent<PersistentConfig> {

    private String userName;
    private String password;
    private String uri;

    public static PersistentConfig getInstance() {
        return ServiceManager.getService(PersistentConfig.class);
    }
    @Nullable
    @Override
    public PersistentConfig getState() {
        return this;
    }

    @Override
    public void loadState(@NotNull PersistentConfig state) {
        XmlSerializerUtil.copyBean(state, this);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}