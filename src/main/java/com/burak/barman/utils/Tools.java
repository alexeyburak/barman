package com.burak.barman.utils;

import javafx.beans.value.ObservableValue;
import javafx.scene.control.*;
import javafx.scene.image.Image;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Barman
 * Created by Alexey Burak
 */

public class Tools {

    // Get numbers from string and return list of numbers which separated by comma
    public static List<Integer> getNumbersFromString(String string) {
        List<Integer> numbers = new ArrayList<>();
        String[] numbersString = string.split(",");
        for (String number : numbersString) {
            numbers.add(Integer.parseInt(number));
        }
        return numbers;
    }

    // Get an image
    public static Image getImage(String path) {
        File file = new File(path);
        return new Image(file.toURI().toString());
    }

    // Confirmation alert
    public static boolean showAlertConfirmation(String title, String headerText, String contextText) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contextText);
        Optional<ButtonType> option = alert.showAndWait();

        if (option.get() == ButtonType.OK) {
            return true;
        } else if (option.get() == ButtonType.CANCEL) {
            return false;
        } else {
            return false;
        }
    }

    // Change introduced password visibility
    public static void showPassword (CheckBox checkBox, TextField textField, PasswordField passwordField) {
        checkBox.selectedProperty()
                .addListener((ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) -> {
                    if (checkBox.isSelected()) {
                        textField.setText(passwordField.getText());
                        textField.setVisible(true);
                        passwordField.setVisible(false);
                        return;
                    }
                    passwordField.setText(textField.getText());
                    passwordField.setVisible(true);
                    textField.setVisible(false);
                });
    }

    // Contain collection from file
    public static void writeInListFromFile(String file, Collection<String> collection) {
        File listFile = new File(file);
        FileReader fileReader;
        try {
            fileReader = new FileReader(listFile);
            BufferedReader reader = new BufferedReader(Objects.requireNonNull(fileReader));
            String line;
            while ((line = reader.readLine()) != null) {
                collection.add(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Get a day in a string format
    public static String getDay(){
        SimpleDateFormat formatter = new SimpleDateFormat("EEEE");
        Date date = new Date();
        return formatter.format(date);
    }
}
