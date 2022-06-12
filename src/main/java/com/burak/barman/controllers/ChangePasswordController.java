package com.burak.barman.controllers;

import com.burak.barman.daoImpl.UsersDaoImpl;
import com.burak.barman.utils.CheckPassword;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;

import static com.burak.barman.ChangeScene.changeScene;
import static com.burak.barman.utils.GetHash.getHash;


/**
 * Barman
 * Created by Alexey Burak
 */

public class ChangePasswordController implements Initializable {

    @FXML private Button buttonBack;
    @FXML private Button buttonSafe;
    @FXML private PasswordField oldPassword;
    @FXML private PasswordField newPassword;
    @FXML private PasswordField confirmNewPassword;
    @FXML private Label labelWrong;
    private static final UsersDaoImpl usersDao;
    static {
        usersDao = new UsersDaoImpl();
    }

    private void clearFields() {
        oldPassword.clear();
        newPassword.clear();
        confirmNewPassword.clear();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // If Enter is clicked and old password is focused
        oldPassword.setOnKeyPressed(event -> {
            if (event.getCode().equals(javafx.scene.input.KeyCode.ENTER) && oldPassword.isFocused()) {
                newPassword.requestFocus();
            }
        });

        // If Enter is clicked and new password is focused
        newPassword.setOnKeyPressed(event -> {
            if (event.getCode().equals(javafx.scene.input.KeyCode.ENTER) && newPassword.isFocused()) {
                confirmNewPassword.requestFocus();
            }
        });

        // If Enter is clicked
        confirmNewPassword.setOnKeyPressed(event -> {
            if (event.getCode().equals(javafx.scene.input.KeyCode.ENTER)) {
                buttonSafe.fire();
            }
        });

        // Go to main menu
        buttonBack.setOnAction(event -> changeScene(event, "mainStage.fxml"));

        // Change password if the old password is correct
        buttonSafe.setOnAction(event -> {
            if (!CheckPassword.checkInput(newPassword.getText(), confirmNewPassword.getText(), null, labelWrong)) {
                clearFields();
            } else {
                try {
                    usersDao.checkPassword(String.valueOf(getHash(oldPassword.getText())), String.valueOf(getHash(newPassword.getText())), labelWrong);
                    clearFields();
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
