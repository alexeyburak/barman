package com.burak.barman.controllers;

/*
 * Barman
 * Created by Alexey Burak
 */

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;

import static com.burak.barman.ChangeScene.changeScene;
import static com.burak.barman.daoImpl.UsersDaoImpl.logInUser;
import static com.burak.barman.utils.GetHash.getHash;
import static com.burak.barman.utils.Tools.showPassword;
import static com.burak.barman.utils.Tools.showAlertConfirmation;

public class AuthorizationController implements Initializable {

    @FXML private TextField authorizationTextUsername;
    @FXML private PasswordField authorizationTextPassword;
    @FXML private Button authorizationButtonAccept;
    @FXML private Button authorizationButtonNewUser;
    @FXML private Label authorizationLabelWrong;
    @FXML private TextField authorizationShowPassword;
    @FXML private CheckBox authorizationCheckBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)  {

        // Log in users
        authorizationButtonAccept.setOnAction(event -> {
            if (!authorizationTextUsername.getText().isEmpty() && !authorizationTextPassword.getText().isEmpty()) {
                try {
                    // If user sees password being entered
                    if (authorizationCheckBox.isSelected()) {
                        logInUser(event, authorizationTextUsername.getText(), String.valueOf(getHash(authorizationShowPassword.getText())), authorizationLabelWrong, authorizationTextPassword);
                    } else {
                        // If user does not see password being entered
                        logInUser(event, authorizationTextUsername.getText(), String.valueOf(getHash(authorizationTextPassword.getText())), authorizationLabelWrong, authorizationTextPassword);
                    }
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
            } else {
                authorizationLabelWrong.setText("Fill ALL!");
                authorizationShowPassword.clear();
                authorizationTextPassword.clear();
            }

        });

        // Go to registration
        authorizationButtonNewUser.setOnAction(event -> {
            String title = "Confirm your age";
            String headerText = "Barman needs to be over the age of 18";
            String contextText = "By clicking OK, you confirm that your age is more than 18 years";
            if (showAlertConfirmation(title, headerText, contextText)) {
                changeScene(event, "registration.fxml");
            }
        });

        // Show password if CheckBox is selected
        authorizationCheckBox.setOnAction(event -> showPassword(authorizationCheckBox, authorizationShowPassword, authorizationTextPassword));

    }

}
