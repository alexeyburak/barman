package com.burak.barman.controllers;

import com.burak.barman.daoImpl.IngredientsDaoImpl;
import com.burak.barman.models.Ingredient;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;

import static com.burak.barman.ChangeScene.changeScene;

/**
 * Barman
 * Created by Alexey Burak
 */

public class IngredientController implements Initializable {

    @FXML private Button buttonBack;
    @FXML private Button buttonConstructor;
    @FXML private Button buttonMyBar;
    @FXML private GridPane gridPane;
    @FXML private Label label;
    @FXML private TextField findOne;
    private static final IngredientsDaoImpl ingredientsDao;
    static {
        ingredientsDao = new IngredientsDaoImpl();
    }
    public static Collection<Ingredient> ingredients;


    private void show () {
        ingredients = ingredientsDao.findAll();
        for (int i = 0; i < ingredients.size(); i++) {
            for (int j = 0; j < ingredients.size(); j++) {
                for (Ingredient ingredient : ingredients) {
                    Button bb = new Button();
                    bb.setText(ingredient.getTitle());
                    gridPane.add(bb, i, j);
                }
            }
        }
    }

    private void showOne () {
        ingredients = ingredientsDao.findOne(findOne.getText());
        for (Ingredient ingredient : ingredients) {
            System.out.println(ingredient.getTitle() + " " + ingredient.getCategory());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        buttonBack.setOnAction(event -> //changeScene(event, "mainStage.fxml")
                showOne()
        );

        buttonConstructor.setOnAction(event -> changeScene(event, "constructor.fxml"));

        buttonMyBar.setOnAction(event -> changeScene(event, "myBar.fxml"));

        show();

    }
}
