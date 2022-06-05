package com.burak.barman;

import com.burak.barman.daoImpl.CocktailsDaoImpl;
import com.burak.barman.models.Cocktail;
import com.burak.barman.utils.ICatchClicking;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static com.burak.barman.ChangeScene.changeScene;

/**
 * Barman
 * Created by Alexey Burak
 */

public class ConstructorController implements Initializable  {

    @FXML private Button buttonBack;
    @FXML private Button buttonIngredients;
    @FXML private Button buttonMyBar;
    @FXML private Button buttonShowAll;
    @FXML private Button buttonFind;
    @FXML private TextField findTextField;
    @FXML private Label labelWrong;
    @FXML private GridPane grid;
    private ICatchClicking onClick;
    private static final CocktailsDaoImpl cocktailsDao;
    static {
        cocktailsDao = new CocktailsDaoImpl();
    }

    // Add List of Cocktails to GridPane
    private void addToGrid(List<Cocktail> cocktails, GridPane grid, ICatchClicking onClick) {
        int column = 0, row = 1;
        try {
            // If there are no cocktails
            if (cocktails.size() == 0) {
                labelWrong.setText("No cocktails were found!");
                return;
            }
            for (Cocktail cocktail : cocktails) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("itemcocktail.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                ItemCocktail itemController = fxmlLoader.getController();
                itemController.setData(cocktail, onClick);
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
                grid.setPadding(new Insets(10, 10, 10, 10));
                grid.setVgap(10);
                grid.setHgap(10);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // !!!!!!!!!!
    private void goToCocktail(Cocktail cocktail) {
        labelWrong.setText(cocktail.getRecipe());

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        onClick = new ICatchClicking() {
            @Override
            public void onClick(Cocktail cocktail) {
                goToCocktail(cocktail);
            }
        };

        // If Enter is pressed on the keyboard
        findTextField.setOnKeyPressed(event -> {
            if (event.getCode().equals(javafx.scene.input.KeyCode.ENTER) && !findTextField.getText().isEmpty()) {
                buttonFind.fire();
            } else if (event.getCode().equals(javafx.scene.input.KeyCode.ENTER) && findTextField.getText().isEmpty()) {
                buttonShowAll.fire();
            }
        });

        // Add all cocktails to the grid
        List<Cocktail> cocktails = (List<Cocktail>) cocktailsDao.findAll();
        addToGrid(cocktails, grid, onClick);

        // Show finding result
        buttonFind.setOnAction(event -> {
            if (!findTextField.getText().isEmpty()) {
                labelWrong.setText("");
                List<Cocktail> findOneCocktail = (List<Cocktail>) cocktailsDao.findOne(findTextField.getText());
                grid.getChildren().clear();
                addToGrid(findOneCocktail, grid, onClick);
            }
        });

        // Show all cocktails
        buttonShowAll.setOnAction(event -> {
            labelWrong.setText("");
            grid.getChildren().clear();
            addToGrid(cocktails, grid, onClick);
        });


        // Go to main stage
        buttonBack.setOnAction(event -> changeScene(event, "mainStage.fxml"));

        // Go to ingredient stage
        buttonIngredients.setOnAction(event -> changeScene(event, "ingredient.fxml"));

        // Go to my bar stage
        buttonMyBar.setOnAction(event -> changeScene(event, "myBar.fxml"));

    }
}
