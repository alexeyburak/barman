package com.burak.barman;

/*
 * Barman
 * Created by Alexey Burak
 */

import com.burak.barman.daoImpl.CocktailsDaoImpl;
import com.burak.barman.models.Cocktail;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

import static com.burak.barman.daoImpl.UsersDaoImpl.user;
import static com.burak.barman.ChangeScene.changeScene;
import static com.burak.barman.utils.Greeting.choosingGreeting;
import static com.burak.barman.utils.Tools.showAlertConfirmation;
import static com.burak.barman.utils.Greeting.getRandom;


public class MainStageController implements Initializable {

    @FXML private Label labelSayHiName;
    @FXML private Label labelSayHi;
    @FXML private Button buttonGitHub;
    @FXML private Button buttonIngredient;
    @FXML private Button buttonConstructor;
    @FXML private Button buttonMyBar;
    @FXML private GridPane grid;
    @FXML private ComboBox<String> comboBoxAccount;
    private static final CocktailsDaoImpl cocktailsDao;
    static {
        cocktailsDao = new CocktailsDaoImpl();
    }


    Collection<String> greeting = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Add items tp box "Account"
        comboBoxAccount.getItems().addAll(
                "Change password",
                "Change username",
                "SignOut"
        );

        int column = 1, row = 1;
        List<Cocktail> cocktails = (List<Cocktail>) cocktailsDao.findAll();
        try {
            for (int i = 0; i < 3; i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("itemcocktail.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                ItemCocktail itemController = fxmlLoader.getController();
                Cocktail cocktail = cocktails.get(i);
                itemController.setData(cocktail);
                grid.add(anchorPane, column++, row);
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_COMPUTED_SIZE);
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_COMPUTED_SIZE);
                GridPane.setMargin(anchorPane, new Insets(5, 5, 5, 5));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Choosing a day for greeting
        choosingGreeting(greeting);

        // Box "Account" action
        comboBoxAccount.setOnAction(this::boxChoose);

        // Go to project GitHub
        buttonGitHub.setOnAction(event -> {
            String gitHubLink = "https://github.com/alexeyburak/barman";
            try {
                Desktop.getDesktop().browse(new URI(gitHubLink));
            }  catch (URISyntaxException | IOException e) {
                System.out.println("Go to GitHub error " + e);
            }
        });

        // Print username
        labelSayHiName.setText(user.getUsername());

        // Print greeting
        labelSayHi.setText(getRandom(greeting));

        // Go to Ingredient Scene
        buttonIngredient.setOnAction(event -> changeScene(event, "ingredient.fxml"));

        // Go to Constructor Scene
        buttonConstructor.setOnAction(event -> changeScene(event, "constructor.fxml"));

        // Go to MyBar Scene
        buttonMyBar.setOnAction(event -> changeScene(event, "myBar.fxml"));

    }

    // selections in "Account"
    private void boxChoose(ActionEvent event) {
        switch(comboBoxAccount.getValue()) {
            case "Change password":
                // Go to changing password
                changeScene(event, "changePassword.fxml");
                break;
            case "Change username":
                //Go to changing username
                changeScene(event, "changeUsername.fxml");
                break;
            case "SignOut":
                // Alert to confirm LogOut
                if (showAlertConfirmation("Confirm the decision", "Only authorized users can use Barman", null)) {
                    changeScene(event, "authorization.fxml");
                }
                break;
        }
    }
}