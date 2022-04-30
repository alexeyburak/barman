package com.burak.barman;

import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.Objects;

public class DBUtils {

    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/barman";
    private static final String DATABASE_USER = "root";
    private static final String DATABASE_PASSWORD = "alexeyburak";

    // Changing Scenes
    public static void changeScene(ActionEvent event, String fxmlFile) {
        Parent root = null;

        try {
            root = FXMLLoader.load(Objects.requireNonNull(DBUtils.class.getResource(fxmlFile)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(Objects.requireNonNull(root)));
        stage.show();
    }

    // Registration new users
    public static void signUpUser(ActionEvent event, String username, String password, Label labelWrong) {
        Connection connection = null;
        PreparedStatement psInsert = null;
        PreparedStatement psCheckUserExists = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
            psCheckUserExists = connection.prepareStatement("SELECT * FROM users WHERE username = ?");
            psCheckUserExists.setString(1, username);
            resultSet = psCheckUserExists.executeQuery();

            if (resultSet.isBeforeFirst()) {
                labelWrong.setText("User exists");
            } else {
                psInsert = connection.prepareStatement("INSERT INTO users (username, password) VALUES (?, ?)");
                psInsert.setString(1, username);
                psInsert.setString(2, password);
                psInsert.executeUpdate();

                changeScene(event, "mainStage.fxml");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (psCheckUserExists != null) {
                try {
                    psCheckUserExists.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (psInsert != null) {
                try {
                    psInsert.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    // Login users
    public static  void logInUser(ActionEvent event, String username, String password, Label labelWrong, PasswordField passwordField) {
        Connection connection = null;
        PreparedStatement prepareStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
            prepareStatement = connection.prepareStatement("SELECT password FROM users WHERE username = ?");
            prepareStatement.setString(1, username);
            resultSet = prepareStatement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                labelWrong.setText("There is no account");
                passwordField.setText("");
            } else {
                while (resultSet.next()) {
                    String retrievedPassword = resultSet.getString("password");
                    if (retrievedPassword.equals(password)) {
                        changeScene(event, "mainStage.fxml");
                    } else {
                        labelWrong.setText("Pass didnt match");
                        passwordField.setText("");
                    }
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();

                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
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

}
