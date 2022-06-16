package com.burak.barman.controllers;

import com.burak.barman.daoImpl.IngredientsDaoImpl;
import com.burak.barman.daoImpl.UsersDaoImpl;
import com.burak.barman.models.Cocktail;
import com.burak.barman.models.Ingredient;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

import static com.burak.barman.ChangeScene.changeScene;
import static com.burak.barman.daoImpl.UsersDaoImpl.user;
import static com.burak.barman.utils.Tools.getNumbersFromString;
import static com.burak.barman.utils.Tools.removeNumber;
import static com.burak.barman.utils.Tools.addToString;
import static com.burak.barman.utils.Tools.getImage;

/**
 * Barman
 * Created by Alexey Burak
 */

public class CocktailMainPageController implements Initializable {


    @FXML private Button buttonMyBar;
    @FXML private Button buttonBack;
    @FXML private Button buttonConstructor;
    @FXML private Button buttonIngredients;
    @FXML private Button buttonAddFavorites;
    @FXML private Button buttonRemoveFavorites;
    @FXML private Label name;
    @FXML private Label preparation;
    @FXML private Label recipe;
    @FXML private ImageView img;
    private Cocktail cocktail;
    private static final UsersDaoImpl usersDaoImpl;
    private static final IngredientsDaoImpl ingredientsDao;
    static {
        usersDaoImpl = new UsersDaoImpl();
        ingredientsDao = new IngredientsDaoImpl();
    }

    // Set data to labels
    public void setData(final Cocktail cocktail) {
        this.cocktail = cocktail;
        List<Integer> listIngredients = getNumbersFromString(cocktail.getRecipe());
        List<Integer> listAmount = getNumbersFromString(cocktail.getRecipe_amount());
        List<String> listNames = new ArrayList<>();
        Collection<Ingredient> findId;
        for (int i = 0; i < listIngredients.size(); i++) {
            findId = ingredientsDao.findById(listIngredients.get(i));
            listNames.add(String.format(" %-100s %s%s\n", findId.iterator().next().getTitle(), listAmount.get(i), "ml"));
        }
        name.setText(cocktail.getName());
        preparation.setText(cocktail.getPreparation());
        preparation.setWrapText(true);
        String path = "./src/main/resources/com/burak/barman/images/" + cocktail.getImg();
        img.setImage(getImage(path));
        setTextFromList(recipe, listNames);
        changeButtonVisibility(cocktail);
    }

    public void setTextFromList(Label label, List<String> list) {
        StringBuilder sb = new StringBuilder();
        for (String s : list) {
            sb.append(s);
        }
        label.setText(sb.toString());
    }

    private void changeButtonVisibility(Cocktail cocktail) {
        if (user.getFavorites() == null) {
            buttonAddFavorites.setVisible(true);
            buttonRemoveFavorites.setVisible(false);
            return;
        }
        if (user.getFavorites().contains(cocktail.getId() + "")) {
            buttonAddFavorites.setVisible(false);
            buttonRemoveFavorites.setVisible(true);
        } else {
            buttonAddFavorites.setVisible(true);
            buttonRemoveFavorites.setVisible(false);
        }
    }

    private void updateDao(String updateString) {
        usersDaoImpl.updateData(updateString, user);
        user.setFavorites(updateString);
        changeButtonVisibility(cocktail);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        buttonRemoveFavorites.setOnAction(actionEvent -> {
            String updateString = removeNumber(user.getFavorites(), cocktail.getId());
            updateDao(updateString);
        });

        buttonAddFavorites.setOnAction(event -> {
            String updateString = addToString(user.getFavorites(), cocktail.getId());
            updateDao(updateString);
        });

        // Go to main page
        buttonBack.setOnAction(event -> changeScene(event, "mainStage.fxml"));

        // Go to my bar page
        buttonMyBar.setOnAction(event -> changeScene(event, "myBar.fxml"));

        // Go to constructor page
        buttonConstructor.setOnAction(event -> changeScene(event, "constructor.fxml"));

        // Go to ingredients page
        buttonIngredients.setOnAction(event -> changeScene(event, "ingredients.fxml"));

    }
}
