package com.burak.barman;

/*
 * Barman
 * Created by Alexey Burak
 */

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

import static com.burak.barman.DBUtils.changeScene;
import static com.burak.barman.DBUtils.showAlertConfirmation;


public class MainStageController implements Initializable {

    @FXML private Label labelSayHi;
    @FXML private ComboBox<String> comboBoxAccount;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboBoxAccount.getItems().addAll(
                "Change password",
                "Sign Out"
        );

        comboBoxAccount.setOnAction(this::boxChoose);

    }

    public void setUserInformation(String username) {
        if (labelSayHi != null) {
            labelSayHi.setText(username + "HI");
        }
    }

    private void boxChoose(ActionEvent event) {
        switch(comboBoxAccount.getValue()) {
            case "Change password":
                // Got to changing password
                changeScene(event, "changePassword.fxml", 0, null);
                break;
            case "LogOut":
                // Alert to confirm LogOut
                if (showAlertConfirmation("Confirm the decision", "Only authorized users can use Barman", null)) {
                    changeScene(event, "authorization.fxml", 0, null);
                }
                break;
        }
    }
}