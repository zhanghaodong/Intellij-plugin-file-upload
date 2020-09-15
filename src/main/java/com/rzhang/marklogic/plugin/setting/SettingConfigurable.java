package com.rzhang.marklogic.plugin.setting;

import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SearchableConfigurable;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * @author RexZhang
 */
public class SettingConfigurable implements SearchableConfigurable {

    public static final String DISPLAY_NAME = "MarkLogic Plugin";
    private final PersistentConfig config = PersistentConfig.getInstance();
    private SettingUI settingUI;

    @Override
    public @NotNull String getId() {
        return "marklogic.id";
    }

    @Nls(capitalization = Nls.Capitalization.Title)
    @Override
    public String getDisplayName() {
        return DISPLAY_NAME;
    }

    @Override
    public @Nullable JComponent createComponent() {
        settingUI = new SettingUI();
        return settingUI.getjPanel();
    }

    @Override
    public boolean isModified() {
        boolean modified = !settingUI.getUserName().equals(config.getUserName());
        modified |= settingUI.getUrl().equals(config.getUri());
        modified |= String.valueOf(settingUI.getPassword()).equals(config.getPassword());
        return modified;
    }

    @Override
    public void apply() throws ConfigurationException {
        config.setUserName(settingUI.getUserName());
        config.setPassword(String.valueOf(settingUI.getPassword()));
        config.setUri(settingUI.getUrl());
    }

    @Override
    public void reset() {
        settingUI.setUserName(config.getUserName());
        settingUI.setPassword(config.getPassword());
        settingUI.setUrl(config.getUri());
    }

    @Override
    public void disposeUIResources() {
        settingUI = null;
    }
}
