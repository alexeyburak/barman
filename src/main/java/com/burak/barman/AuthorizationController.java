package com.burak.barman;

import com.burak.barman.utils.GetHash;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;

public class AuthorizationController implements Initializable {

    @FXML private TextField authorizationTextUsername;
    @FXML private PasswordField authorizationTextPassword;
    @FXML private Button authorizationButtonAccept;
    @FXML private Button authorizationButtonNewUser;
    @FXML private Button authorizationButtonBack;
    @FXML private Label authorizationLabelWrong;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)  {

        authorizationButtonAccept.setOnAction(event -> {
            if (!authorizationTextUsername.getText().isEmpty() && !authorizationTextPassword.getText().isEmpty()) {
                try {
                    DBUtils.logInUser(event, authorizationTextUsername.getText(), String.valueOf(GetHash.getHash(authorizationTextPassword.getText())), authorizationLabelWrong, authorizationTextPassword);
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
            } else {
                authorizationLabelWrong.setText("Fill ALL!");
            }

        });

        authorizationButtonBack.setOnAction(event -> DBUtils.changeScene(event, "mainStage.fxml"));

        authorizationButtonNewUser.setOnAction(event -> DBUtils.changeScene(event, "registration.fxml"));

    }

}
