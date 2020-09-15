package com.rzhang.file.upload.plugin.setting;

import com.intellij.ui.components.JBPasswordField;
import com.intellij.ui.components.JBTextField;
import com.intellij.util.ui.FormBuilder;

import javax.swing.*;

/**
 * @author Rex Zhang
 */
public class SettingUI {
    private JPanel jPanel;
    private final JBTextField userName = new JBTextField();
    private final JBPasswordField password = new JBPasswordField();
    private final JBTextField url = new JBTextField();

    public SettingUI(){
        jPanel = FormBuilder.createFormBuilder()
                .addLabeledComponent("User name", userName, 1, false)
                .addLabeledComponent("Password", password)
                .addLabeledComponent("MarkLogic server", url)
                .getPanel();
    }

    public JPanel getjPanel() {
        return jPanel;
    }

    public void setjPanel(JPanel jPanel) {
        this.jPanel = jPanel;
    }

    public String getUserName() {
        return userName.getText();
    }

    public void setUserName(String userName) {
        this.userName.setText(userName);
    }

    public char[] getPassword() {
        return password.getPassword();
    }

    public void setPassword(String password) {
        this.password.setText(password);
    }

    public String getUrl() {
        return url.getText();
    }

    public void setUrl(String url) {
        this.url.setText(url);
    }
}