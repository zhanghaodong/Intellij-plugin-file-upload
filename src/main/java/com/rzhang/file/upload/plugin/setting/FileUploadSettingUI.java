package com.rzhang.file.upload.plugin.setting;

import com.intellij.openapi.options.ConfigurableUi;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.util.Comparing;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.ui.components.JBTextField;
import com.intellij.util.ui.FormBuilder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * @author Rex Zhang
 */
public class FileUploadSettingUI implements ConfigurableUi<FileUploadConfig> {

    private JPanel myMainPanel;
    private final JTextField myLoginTextField = new JBTextField();
    private final JPasswordField myPasswordTextField = new JPasswordField();
    private final JTextField myUrlTextField = new JTextField();

    public FileUploadSettingUI(){
        super();
        init();
    }

    @Override
    public void reset(@NotNull FileUploadConfig fileUploadConfig) {
        fileUploadConfig.setUserName(fileUploadConfig.getUserName());
        fileUploadConfig.setPassWord(fileUploadConfig.getPassWord());
        fileUploadConfig.setUrl(fileUploadConfig.getUrl());
    }

    @Override
    public boolean isModified(@NotNull FileUploadConfig fileUploadConfig) {
        return  !Comparing.strEqual(myLoginTextField.getText(), fileUploadConfig.getUserName())
                || !Comparing.strEqual(String.valueOf(myPasswordTextField.getPassword()), fileUploadConfig.getPassWord())
                || !Comparing.strEqual(myUrlTextField.getText(), fileUploadConfig.getUrl());

    }

    @Override
    public void apply(@NotNull FileUploadConfig fileUploadConfig) throws ConfigurationException {
            if (this.isModified(fileUploadConfig)) {
                fileUploadConfig.AUTHENTICATION_CANCELLED = false;
            }
            fileUploadConfig.setUserName(getText(myLoginTextField));
            fileUploadConfig.setPassWord(String.valueOf(myPasswordTextField.getPassword()));
            fileUploadConfig.setUrl(getText(myUrlTextField));
    }

    @Override
    public @NotNull JComponent getComponent() {
        if (this.myMainPanel == null) {
            init();
        }
        return myMainPanel;
    }

    private void init() {
        this.myMainPanel = FormBuilder.createFormBuilder().addLabeledComponent("User name", this.myLoginTextField)
                .addLabeledComponent("Password", myPasswordTextField)
                .addLabeledComponent("Server url", myUrlTextField).getPanel();
    }


    @Nullable
    private static String getText(@NotNull JTextField field) {
        return StringUtil.nullize(field.getText(), true);
    }
}