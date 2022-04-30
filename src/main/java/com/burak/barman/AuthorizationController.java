package com.burak.barman;

import com.burak.barman.utils.GetHash;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

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
    @FXML private TextField authorizationShowPassword;
    @FXML private CheckBox authorizationCheckBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)  {

        // Log in users
        authorizationButtonAccept.setOnAction(event -> {
            if (!authorizationTextUsername.getText().isEmpty() && !authorizationTextPassword.getText().isEmpty()) {
                try {
                    if (authorizationCheckBox.isSelected()) {
                        DBUtils.logInUser(event, authorizationTextUsername.getText(), String.valueOf(GetHash.getHash(authorizationShowPassword.getText())), authorizationLabelWrong, authorizationTextPassword);
                    } else {
                        DBUtils.logInUser(event, authorizationTextUsername.getText(), String.valueOf(GetHash.getHash(authorizationTextPassword.getText())), authorizationLabelWrong, authorizationTextPassword);
                    }
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
            } else {
                authorizationLabelWrong.setText("Fill ALL!");
            }

        });

        // Go back to mainStage
        authorizationButtonBack.setOnAction(event -> DBUtils.changeScene(event, "mainStage.fxml"));

        // Go to registration
        authorizationButtonNewUser.setOnAction(event -> DBUtils.changeScene(event, "registration.fxml"));

        // Show password if CheckBox is selected
        authorizationCheckBox.setOnAction(event -> DBUtils.showPassword(authorizationCheckBox, authorizationShowPassword, authorizationTextPassword));

    }

}
