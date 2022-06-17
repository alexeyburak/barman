package com.burak.barman;

import com.burak.barman.controllers.ItemIngredientController;
import com.burak.barman.daoImpl.IngredientsDaoImpl;
import com.burak.barman.models.Ingredient;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;

import java.io.IOException;
import java.net.URL;
import java.util.List;
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
    @FXML private Button findButton;
    @FXML private Button showAllButton;
    @FXML private Label labelWrong;
    @FXML private Label icon;
    @FXML private TextField findTextField;
    @FXML private GridPane grid;

    private static final IngredientsDaoImpl ingredientsDao;
    static {
        ingredientsDao = new IngredientsDaoImpl();
    }

    private void addItem(List<Ingredient> ingredients, GridPane grid) {
        int column = 0, row = 1;
        try {
            // If there are no ingredients
            if (ingredients.size() == 0) {
                labelWrong.setText("No ingredients were found!");
                return;
            }
            for (Ingredient ingredient : ingredients) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("itemIngredient.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                ItemIngredientController itemIngredient = fxmlLoader.getController();
                itemIngredient.setData(ingredient);
                grid.add(anchorPane, column++, row);
                if (column == 4) {
                    column = 0;
                    row++;
                }
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_COMPUTED_SIZE);
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_COMPUTED_SIZE);
                grid.setPadding(new Insets(0, 10, 10, 10));
                grid.setVgap(10);
                grid.setHgap(10);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // If icon is clicked, go to the main page
        icon.setOnMouseClicked(event -> {
           buttonBack.fire();
        });

        // If Enter is pressed on the keyboard
        findTextField.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER) && !findTextField.getText().isEmpty()) {
                findButton.fire();
            } else if (event.getCode().equals(KeyCode.ENTER) && findTextField.getText().isEmpty()) {
                showAllButton.fire();
            }
        });

        // Add all ingredients to the grid
        List<Ingredient> ingredients = (List<Ingredient>) ingredientsDao.findAll();
        addItem(ingredients, grid);

        // Show finding result
        findButton.setOnAction(event -> {
            labelWrong.setText("");
            if (!findTextField.getText().isEmpty()) {
                List<Ingredient> findIngredient = (List<Ingredient>) ingredientsDao.findOne(findTextField.getText());
                grid.getChildren().clear();
                addItem(findIngredient, grid);
            }
        });

        // Show all ingredients
        showAllButton.setOnAction(event -> {
            labelWrong.setText("");
            grid.getChildren().clear();
            addItem(ingredients, grid);
        });

        // Go to the main page
        buttonBack.setOnAction(event -> changeScene(event, "mainStage.fxml")
        );

        // Go to the constructor page
        buttonConstructor.setOnAction(event -> changeScene(event, "constructor.fxml"));

        // Go to my bar page
        buttonMyBar.setOnAction(event -> changeScene(event, "myBar.fxml"));

    }
}
