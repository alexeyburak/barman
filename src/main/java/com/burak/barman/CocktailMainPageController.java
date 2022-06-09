package com.burak.barman;

import com.burak.barman.daoImpl.IngredientsDaoImpl;
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
import static com.burak.barman.utils.Tools.getImage;
import static com.burak.barman.utils.Tools.getNumbersFromString;

/**
 * Barman
 * Created by Alexey Burak
 */

public class CocktailMainPageController implements Initializable {


    @FXML private Button buttonMyBar;
    @FXML private Button buttonBack;
    @FXML private Button buttonConstructor;
    @FXML private Button buttonIngredients;
    @FXML private Label name;
    @FXML private Label preparation;
    @FXML private Label recipe;
    @FXML private ImageView img;
    private static final IngredientsDaoImpl ingredientsDao;
    static {
        ingredientsDao = new IngredientsDaoImpl();
    }

    // Set data to labels
    public void setData(final Cocktail cocktail) {
        List<Integer> listIngredients = getNumbersFromString(cocktail.getRecipe());
        List<Integer> listAmount = getNumbersFromString(cocktail.getRecipe_amount());
        List<String> listNames = new ArrayList<>();
        Collection<Ingredient> findId;
        for (int i = 0; i < listIngredients.size(); i++) {
            findId = ingredientsDao.findById(listIngredients.get(i));
            listNames.add(findId.iterator().next().getTitle() + "\t\t" + listAmount.get(i) + "ml\n");
        }
        name.setText(cocktail.getName());
        preparation.setText(cocktail.getPreparation());
        String path = "./src/main/resources/com/burak/barman/images/" + cocktail.getImg();
        img.setImage(getImage(path));
        recipe.setText(listNames.toString());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

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
