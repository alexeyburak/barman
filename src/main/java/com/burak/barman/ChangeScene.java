package com.burak.barman;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/**
 * Barman
 * Created by Alexey Burak
 */

public class ChangeScene {

    // Changing Scenes
    public static void changeScene(ActionEvent event, String fxmlFile) {
        FXMLLoader loader = new FXMLLoader(ChangeScene.class.getResource(fxmlFile));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(Objects.requireNonNull(root)));
        stage.show();
    }
}
