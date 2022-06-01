package com.burak.barman;

import com.burak.barman.models.Cocktail;
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

public class ItemIngredient implements Initializable {

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
