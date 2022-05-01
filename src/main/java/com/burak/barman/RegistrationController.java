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

import static com.burak.barman.DBUtils.isNumeric;
import static com.burak.barman.DBUtils.signUpUser;



public class RegistrationController implements Initializable {

    @FXML private TextField registrationTextUsername;
    @FXML private PasswordField registrationPassword;
    @FXML private PasswordField registrationConfirmPassword;
    @FXML private Button registrationButtonSignUp;
    @FXML private Label registrationLabelWrong;
    @FXML private Hyperlink registrationHyperlink;

    // Minimum password length
    private final int ACCEPTABLE_PASSWORD_LENGTH = 8;

    private void clearFields() {
        registrationPassword.clear();
        registrationConfirmPassword.clear();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Registration new user

        // The password implies a string of more than 8 characters, which are not just numbers
        registrationButtonSignUp.setOnAction(event -> {

            if(registrationPassword.getText().length() < ACCEPTABLE_PASSWORD_LENGTH && registrationConfirmPassword.getText().length() < ACCEPTABLE_PASSWORD_LENGTH) {
                registrationLabelWrong.setText("Short password"); // Check password length
                clearFields();
            } else if (!registrationPassword.getText().equals(registrationConfirmPassword.getText())) {
                registrationLabelWrong.setText("passwords don't match"); // Check password match
                clearFields();
            } else if (isNumeric(registrationPassword.getText())) { // Checking whether a password is only a number
                registrationLabelWrong.setText("the password must contain at least one letter");
                clearFields();
            } else if (!registrationTextUsername.getText().trim().isEmpty() && !registrationPassword.getText().isEmpty() && !registrationPassword.getText().isEmpty()) {
                try {
                    // it checks mistakes with Database
                    signUpUser(event, registrationTextUsername.getText(), String.valueOf(GetHash.getHash(registrationPassword.getText())), registrationLabelWrong);
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
            } else {
                // If not all fields are filled in
                registrationLabelWrong.setText("Fill all");
                clearFields();
            }
        });

        // Go to privacy police
        registrationHyperlink.setOnAction(event -> {

        });


    }
}
