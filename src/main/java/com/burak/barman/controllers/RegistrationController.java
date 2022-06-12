package com.burak.barman.controllers;

/*
 * Barman
 * Created by Alexey Burak
 */

import com.burak.barman.daoImpl.UsersDaoImpl;
import com.burak.barman.utils.GetHash;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;

import static com.burak.barman.ChangeScene.changeScene;
import static com.burak.barman.utils.CheckPassword.checkInput;



public class RegistrationController implements Initializable {

    @FXML private TextField registrationTextUsername;
    @FXML private PasswordField registrationPassword;
    @FXML private PasswordField registrationConfirmPassword;
    @FXML private Button registrationButtonSignUp;
    @FXML private Label registrationLabelWrong;
    @FXML private Hyperlink registrationHyperlink;
    @FXML private Button registrationButtonBack;
    private static final UsersDaoImpl usersDao;
    static {
        usersDao = new UsersDaoImpl();
    }

    private void clearFields() {
        registrationPassword.clear();
        registrationConfirmPassword.clear();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Registration new user

        // If Enter is pressed on the keyboard when the username is focused, next field is focused
        registrationTextUsername.setOnKeyPressed(event -> {
            if (event.getCode().equals(javafx.scene.input.KeyCode.ENTER) && registrationTextUsername.isFocused()) {
                registrationPassword.requestFocus();
            }
        });

        // If Enter is pressed on the keyboard when the password is focused, next field is focused
        registrationPassword.setOnKeyPressed(event -> {
            if (event.getCode().equals(javafx.scene.input.KeyCode.ENTER) && registrationPassword.isFocused()) {
                registrationConfirmPassword.requestFocus();
            }
        });

        // If Enter is pressed on the keyboard, the button is clicked
        registrationConfirmPassword.setOnKeyPressed(event -> {
            if (event.getCode().equals(javafx.scene.input.KeyCode.ENTER) && registrationConfirmPassword.isFocused()) {
                registrationButtonSignUp.fire();
            }
        });

        // The password implies a string of more than 8 characters, which are not just numbers
        registrationButtonSignUp.setOnAction(event -> {

            if (!checkInput(registrationPassword.getText(), registrationConfirmPassword.getText(), registrationTextUsername.getText(), registrationLabelWrong)) {
                clearFields();
            } else if (!registrationTextUsername.getText().trim().isEmpty() && !registrationPassword.getText().isEmpty() && !registrationPassword.getText().isEmpty()) {
                try {
                    // it checks mistakes with Database
                    usersDao.signUpUser(event, registrationTextUsername.getText(), String.valueOf(GetHash.getHash(registrationPassword.getText())), registrationLabelWrong);
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
            }
        });

        // Go to privacy police
        registrationHyperlink.setOnAction(event -> {

        });

        // Go back to authorization
        registrationButtonBack.setOnAction(event -> changeScene(event, "authorization.fxml"));

    }
}
