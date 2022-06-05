package com.burak.barman;

import com.burak.barman.models.Cocktail;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

import static com.burak.barman.ChangeScene.changeScene;
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
    @FXML private Label name;
    @FXML private Label preparation;
    @FXML private ImageView img;
    private Cocktail cocktail;

    public void setData(Cocktail cocktail) {
        this.cocktail = cocktail;
        name.setText(cocktail.getName());
        String path = "./src/main/resources/com/burak/barman/images/" + cocktail.getImg();
        img.setImage(getImage(path));
        preparation.setText(cocktail.getPreparation());
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
