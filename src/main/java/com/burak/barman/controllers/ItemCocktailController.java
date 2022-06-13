package com.burak.barman.controllers;

import com.burak.barman.models.Cocktail;
import com.burak.barman.utils.ICatchClicking;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

import static com.burak.barman.utils.Tools.getImage;

/**
 * Barman
 * Created by Alexey Burak
 */

public class ItemCocktailController implements Initializable {

    @FXML private Label name;
    @FXML private ImageView img;

    @FXML
    private void click(MouseEvent event) {
        onClick.onClick(cocktail);
    }

    private ICatchClicking onClick;
    private Cocktail cocktail;

    public void setData(Cocktail cocktail, ICatchClicking onClick) {
        this.cocktail = cocktail;
        this.onClick = onClick;
        name.setText(cocktail.getName());
        String path = "./src/main/resources/com/burak/barman/images/" + cocktail.getImg();
        img.setImage(getImage(path));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
