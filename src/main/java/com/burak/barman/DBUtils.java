package com.burak.barman;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.Objects;

public class DBUtils {
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

    public static void signUpUser(ActionEvent event, String username, String password) {
        Connection connection = null;
        PreparedStatement psInsert = null;
        PreparedStatement psCheckUserExists = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/barman", "root", "alexeyburak");
            psCheckUserExists = connection.prepareStatement("SELECT * FROM users WHERE username = ?");
            psCheckUserExists.setString(1, username);
            resultSet = psCheckUserExists.executeQuery();

            if (resultSet.isBeforeFirst()) {
                System.out.println("User exists");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("dafad");
                alert.show();
            } else {
                psInsert = connection.prepareStatement("INSERT INTO users (username, password) VALUES (?, ?))");
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

    public static  void logInUser(ActionEvent event, String username, String password) {
        Connection connection = null;
        PreparedStatement prepareStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/barman", "root", "alexeyburak");
            prepareStatement = connection.prepareStatement("SELECT password FROM users WHERE username = ?");
            prepareStatement.setString(1, username);
            resultSet = prepareStatement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.show();
            } else {
                while (resultSet.next()) {
                    String retrievedPassword = resultSet.getString("password");
                    if (retrievedPassword.equals(password)) {
                        changeScene(event, "mainStage.fxml");
                    } else {
                        System.out.println("Pass didnt match");
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

}
