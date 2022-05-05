package com.burak.barman;

/*
 * Barman
 * Created by Alexey Burak
 */

import com.burak.barman.utils.GetHash;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;

import static com.burak.barman.DBUtils.logInUser;
import static com.burak.barman.DBUtils.changeScene;
import static com.burak.barman.DBUtils.showPassword;
import static com.burak.barman.DBUtils.checkAge;

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
                        logInUser(event, authorizationTextUsername.getText(), String.valueOf(GetHash.getHash(authorizationShowPassword.getText())), authorizationLabelWrong, authorizationTextPassword);
                    } else {
                        // If user does not see password being entered
                        logInUser(event, authorizationTextUsername.getText(), String.valueOf(GetHash.getHash(authorizationTextPassword.getText())), authorizationLabelWrong, authorizationTextPassword);
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
            if (checkAge()) {
                changeScene(event, "registration.fxml", 0 ,null);
            }
        });

        // Show password if CheckBox is selected
        authorizationCheckBox.setOnAction(event -> showPassword(authorizationCheckBox, authorizationShowPassword, authorizationTextPassword));

    }

}
