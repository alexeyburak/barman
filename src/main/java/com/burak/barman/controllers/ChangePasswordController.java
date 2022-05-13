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
        newPassword.clear();
        confirmNewPassword.clear();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        buttonBack.setOnAction(event -> changeScene(event, "mainStage.fxml"));

        buttonSafe.setOnAction(event -> {
            if (!CheckPassword.checkInput(newPassword.getText(), confirmNewPassword.getText(), null, labelWrong)) {
                clearFields();
            } else {
                try {
                    usersDao.checkPassword(String.valueOf(getHash(oldPassword.getText())), String.valueOf(getHash(newPassword.getText())), labelWrong);
                    labelWrong.setText("password has changed");
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
            }

        });

    }
}
