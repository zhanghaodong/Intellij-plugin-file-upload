package com.rzhang.file.upload.plugin.setting;

import com.intellij.openapi.options.ConfigurableBase;
import org.jetbrains.annotations.NotNull;

import static com.rzhang.file.upload.plugin.model.Constant.*;


/**
 * @author RexZhang
 */
public class FileUploadSettingConfigurable extends ConfigurableBase<FileUploadSettingUI, FileUploadConfig> {

    public FileUploadSettingConfigurable(){
        super(PLUGIN_ID, DISPLAY_NAME, HELP_TOPIC);
    }

    @Override
    protected @NotNull FileUploadConfig getSettings() {
        return FileUploadConfig.getInstance();
    }

    @Override
    protected FileUploadSettingUI createUi() {
        return new FileUploadSettingUI();
    }
}
