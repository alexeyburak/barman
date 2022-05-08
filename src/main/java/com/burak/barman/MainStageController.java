package com.burak.barman;

/*
 * Barman
 * Created by Alexey Burak
 */

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;

import static com.burak.barman.DBUtils.changeScene;
import static com.burak.barman.utils.Tools.showAlertConfirmation;
import static com.burak.barman.DBUtils.user;
import static com.burak.barman.utils.Greeting.getRandom;
import static com.burak.barman.utils.Tools.writeInListFromFile;


public class MainStageController implements Initializable {

    @FXML private Label labelSayHiName;
    @FXML private Label labelSayHi;
    @FXML private Button buttonGitHub;
    @FXML private ComboBox<String> comboBoxAccount;

    Collection<String> greeting = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Add items tp box "Account"
        comboBoxAccount.getItems().addAll(
                "Change password",
                "Change username",
                "SignOut"
        );

        //  Feeling collection for greeting
        writeInListFromFile("./src/main/resources/com/burak/barman/files/greeting.txt", greeting);

        // Box "Account" action
        comboBoxAccount.setOnAction(this::boxChoose);

        // Go to project GitHub
        buttonGitHub.setOnAction(event -> {
            String gitHubLink = "https://github.com/alexeyburak/barman";
            try {
                Desktop.getDesktop().browse(new URI(gitHubLink));
            }  catch (URISyntaxException | IOException e) {
                System.out.println("Go to GitHub error " + e);
            }
        });

        // Print username
        labelSayHiName.setText(user.getUsername());

        // Print greeting
        labelSayHi.setText(getRandom(greeting));

    }

    // selections in "Account"
    private void boxChoose(ActionEvent event) {
        switch(comboBoxAccount.getValue()) {
            case "Change password":
                // Go to changing password
                changeScene(event, "changePassword.fxml");
                break;
            case "Change username":
                //Go to changing username
                changeScene(event, "changeUsername.fxml");
                break;
            case "SignOut":
                // Alert to confirm LogOut
                if (showAlertConfirmation("Confirm the decision", "Only authorized users can use Barman", null)) {
                    changeScene(event, "authorization.fxml");
                }
                break;
        }
    }
}