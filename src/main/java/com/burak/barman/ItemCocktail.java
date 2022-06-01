package com.burak.barman;

import com.burak.barman.models.Cocktail;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

import static com.burak.barman.utils.Tools.getImage;

/**
 * Barman
 * Created by Alexey Burak
 */

public class ItemCocktail implements Initializable {

    @FXML Label name;
    @FXML ImageView img;

    public void setData(Cocktail cocktail) {
        name.setText(cocktail.getName());
        String path = "./src/main/resources/com/burak/barman/images/" + cocktail.getImg();
        img.setImage(getImage(path));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
