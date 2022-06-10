package com.burak.barman.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

import static com.burak.barman.ChangeScene.changeScene;

/**
 * Barman
 * Created by Alexey Burak
 */

public class MyBarController implements Initializable {

    @FXML Button buttonBack;
    @FXML Button buttonConstructor;
    @FXML Button buttonIngredients;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Go to main menu
        buttonBack.setOnAction(event -> changeScene(event, "mainStage.fxml"));

        // Go to constructor menu
        buttonConstructor.setOnAction(event -> changeScene(event, "constructor.fxml"));

        // Go to ingredients menu
        buttonIngredients.setOnAction(event -> changeScene(event, "ingredient.fxml"));


    }
}
