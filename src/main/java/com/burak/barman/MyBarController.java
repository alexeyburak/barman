package com.burak.barman;

import com.burak.barman.controllers.CocktailMainPageController;
import com.burak.barman.controllers.ItemCocktailController;
import com.burak.barman.daoImpl.CocktailsDaoImpl;
import com.burak.barman.daoImpl.UsersDaoImpl;
import com.burak.barman.models.Cocktail;
import com.burak.barman.utils.ICatchClicking;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;

import static com.burak.barman.ChangeScene.changeScene;
import static com.burak.barman.daoImpl.UsersDaoImpl.user;
import static com.burak.barman.utils.Tools.getNumbersFromString;

/**
 * Barman
 * Created by Alexey Burak
 */

public class MyBarController implements Initializable {

    @FXML Button buttonBack;
    @FXML Button buttonConstructor;
    @FXML Button buttonIngredients;
    @FXML GridPane grid;
    @FXML ScrollPane scroll;
    @FXML Label labelWrong;
    @FXML Label icon;
    private static final UsersDaoImpl usersDaoImpl;
    private static final CocktailsDaoImpl cocktailsDaoImpl;
    static {
        usersDaoImpl = new UsersDaoImpl();
        cocktailsDaoImpl = new CocktailsDaoImpl();
    }


    // Add List of Cocktails to GridPane
    private void addToGrid(Collection<Cocktail> cocktails, GridPane grid, ICatchClicking onClick) {
        int column = 0, row = 1;
        try {
            for (Cocktail cocktail : cocktails) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("itemcocktail.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                ItemCocktailController itemController = fxmlLoader.getController();
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
            System.out.println("add to grid error" + e);
        }
    }

    // Open chosen cocktail
    private void goToCocktail(Cocktail cocktail) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("cocktailMainPage.fxml"));
            Parent root = fxmlLoader.load();
            CocktailMainPageController cocktailController = fxmlLoader.getController();
            cocktailController.setData(cocktail);
            Stage stage = new Stage();
            stage.setTitle(cocktail.getName() + " - New Window");
            stage.setScene(new Scene(Objects.requireNonNull(root)));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void emptyFavorites() {
        scroll.setVisible(false);
        labelWrong.setText("You don't have favorites yet!\nPlease add them in the constructor");
    }

    private List<Cocktail> findFavorites() {
        String favorites = usersDaoImpl.getFavoritesFromDao(user);
        if (favorites == null || favorites.isEmpty()) {
            emptyFavorites();
            return new ArrayList<>();
        }
        labelWrong.setVisible(false);
        scroll.setVisible(true);
        List<Integer> listWithFavorites = getNumbersFromString(favorites);
        List<Cocktail> cocktails = new ArrayList<>();
        for (Integer id : listWithFavorites) {
            cocktails.add(cocktailsDaoImpl.findById(id).iterator().next());
        }
        return cocktails;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // If icon is clicked, go to the main page
        icon.setOnMouseClicked(event -> buttonBack.fire());

        // goToCocktail method after clicking on item
        ICatchClicking onClick = this::goToCocktail;

        // Add all cocktails to the grid
        addToGrid(findFavorites(), grid, onClick);

        // Go to main menu
        buttonBack.setOnAction(event -> changeScene(event, "mainStage.fxml"));

        // Go to constructor menu
        buttonConstructor.setOnAction(event -> changeScene(event, "constructor.fxml"));

        // Go to ingredients menu
        buttonIngredients.setOnAction(event -> changeScene(event, "ingredient.fxml"));


    }
}
