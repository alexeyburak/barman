package com.burak.barman;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        registrationButtonSignUp.setOnAction(event -> {

            if (!registrationPassword.getText().equals(registrationConfirmPassword.getText())) {
                registrationLabelWrong.setText("passwords don't match");
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
    }
}
