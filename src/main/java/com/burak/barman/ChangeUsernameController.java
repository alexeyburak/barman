package com.burak.barman;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

import static com.burak.barman.DBUtils.changeUsername;
import static com.burak.barman.DBUtils.user;
import static com.burak.barman.DBUtils.changeScene;

/**
 * Barman
 * Created by Alexey Burak
 */

public class ChangeUsernameController implements Initializable {

    @FXML private TextField newUsername;
    @FXML private Label labelWrong;
    @FXML private Label labelOldUsername;
    @FXML private Button buttonSafe;
    @FXML private Button buttonBack;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        labelOldUsername.setText("your username is " + user.getUsername());

        buttonSafe.setOnAction(event -> {
            changeUsername(newUsername.getText());
            labelWrong.setText("username has changed");
        });

        buttonBack.setOnAction(event -> changeScene(event, "mainStage.fxml"));

    }
}
