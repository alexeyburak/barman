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


public class RegistrationController implements Initializable {

    @FXML private TextField registrationTextUsername;
    @FXML private PasswordField registrationPassword;
    @FXML private PasswordField registrationConfirmPassword;
    @FXML private Button registrationButtonSignUp;
    @FXML private Label registrationLabelWrong;
    @FXML private Hyperlink registrationHyperlink;

    private final int ACCEPTABLE_PASSWORD_LENGTH = 8;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Registration new user
        registrationButtonSignUp.setOnAction(event -> {

            if(registrationPassword.getText().length() < ACCEPTABLE_PASSWORD_LENGTH && registrationConfirmPassword.getText().length() < ACCEPTABLE_PASSWORD_LENGTH) {
                registrationLabelWrong.setText("Short password"); // Check password length
            } else if (!registrationPassword.getText().equals(registrationConfirmPassword.getText())) {
                registrationLabelWrong.setText("passwords don't match"); // Check password match
            } else if (!registrationTextUsername.getText().trim().isEmpty() && !registrationPassword.getText().isEmpty() && !registrationPassword.getText().isEmpty()) {
                try {
                    DBUtils.signUpUser(event, registrationTextUsername.getText(), String.valueOf(GetHash.getHash(registrationPassword.getText())), registrationLabelWrong);
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
            } else {
                registrationLabelWrong.setText("Fill all");
            }
        });

        // Go to privacy police
        registrationHyperlink.setOnAction(event -> {

        });


    }
}
