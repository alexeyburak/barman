package com.burak.barman.controllers;

import com.burak.barman.models.Ingredient;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;


/**
 * Barman
 * Created by Alexey Burak
 */

public class ItemIngredientController implements Initializable {

    @FXML Label name;
    @FXML Label category;
    @FXML Label fortress;

    public void setData(Ingredient ingredient) {
        name.setText(ingredient.getTitle());
        category.setText("Category: "+ ingredient.getCategory());
        fortress.setText("Fortress: " + ingredient.getFortress());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
