package com.burak.barman;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class AuthorizationController implements Initializable {

    @FXML private TextField authorizationTextUsername;
    @FXML private PasswordField authorizationTextPassword;
    @FXML private Button authorizationButtonAccept;
    @FXML private Button authorizationButtonNewUser;
    @FXML private Button authorizationButtonBack;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        authorizationButtonAccept.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.logInUser(event, authorizationTextUsername.getText(), authorizationTextPassword.getText());

            }
        });

        authorizationButtonBack.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "mainStage.fxml");

            }
        });

        authorizationButtonNewUser.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "registration.fxml");
            }
        });

    }

}
