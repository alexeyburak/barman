package com.burak.barman;

/*
 * Barman
 * Created by Alexey Burak
 */

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class MainStageController implements Initializable {

    @FXML private Label labelSayHi;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setUserInformation(String username) {
        if (labelSayHi != null) {
            labelSayHi.setText(username + "HI");
        }
    }
}