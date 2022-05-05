package com.burak.barman;

import com.burak.barman.utils.CheckPassword;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;

import static com.burak.barman.DBUtils.changeScene;
import static com.burak.barman.DBUtils.checkPassword;
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

    private void clearFields() {
        newPassword.clear();
        confirmNewPassword.clear();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        buttonBack.setOnAction(event -> changeScene(event, "mainStage.fxml", 0, null));

        buttonSafe.setOnAction(event -> {
            if (!CheckPassword.checkInput(newPassword.getText(), confirmNewPassword.getText(), null, labelWrong)) {
                clearFields();
            } else {
                try {
                    checkPassword(String.valueOf(getHash(oldPassword.getText())), String.valueOf(getHash(newPassword.getText())), labelWrong);
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
            }

        });

    }
}
