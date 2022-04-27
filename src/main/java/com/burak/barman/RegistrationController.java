package com.burak.barman;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class RegistrationController implements Initializable {

    @FXML private TextField registrationTextUsername;
    @FXML private PasswordField registrationPassword;
    @FXML private PasswordField registrationConfirmPassword;
    @FXML private Button registrationButtonSignUp;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        registrationButtonSignUp.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if (!registrationTextUsername.getText().trim().isEmpty()) {
                    DBUtils.signUpUser(event, registrationTextUsername.getText(), registrationPassword.getText());
                } else {
                    System.out.println("Fill all");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.show();
                }
            }
        });


    }
}
