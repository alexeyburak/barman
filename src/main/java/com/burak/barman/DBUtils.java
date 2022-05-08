package com.burak.barman;

/*
 * Barman
 * Created by Alexey Burak
 */

import com.burak.barman.models.User;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Pattern;

public class DBUtils {

    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/barman";
    private static final String DATABASE_USER = "root";
    private static final String DATABASE_PASSWORD = "alexeyburak";
    static User user = new User();

    // Changing Scenes
    public static void changeScene(ActionEvent event, String fxmlFile) {
        FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlFile));
        Parent root = null;
        try {
            root = loader.load();
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
            System.out.println("signUpUser error");
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

    // Change users username
    public static void changeUsername(String newUsername, Label labelWrong) {
        Connection connection = null;
        PreparedStatement prepareStatement = null;

        if (!newUsername.isEmpty()) {
            try {
                connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
                prepareStatement = connection.prepareStatement("UPDATE users SET username = ? WHERE username = ?");
                prepareStatement.setString(1, newUsername);
                prepareStatement.setString(2, user.getUsername());
                user.setUsername(newUsername);
                prepareStatement.executeUpdate();
                labelWrong.setText("username has changed");
            } catch (SQLException e) {
                System.out.println("changeUsername error" + e);
            } finally {
                if (prepareStatement != null) {
                    try {
                        prepareStatement.close();
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
        } else {
            labelWrong.setText("Fill!!");
        }
    }

    // Change users password
    private static void changePassword(String newPassword) {
        Connection connection= null;
        PreparedStatement prepareStatement= null;

        try {
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
            prepareStatement = connection.prepareStatement("UPDATE users SET password = ? WHERE username = ?");
            prepareStatement.setString(1, newPassword);
            prepareStatement.setString(2, user.getUsername());

            prepareStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("changePassword error");
        } finally {
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

    // Check old password in Data
    public static void checkPassword(String oldPassword, String newPassword, Label labelWrong) {
        Connection connection = null;
        PreparedStatement prepareStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
            prepareStatement = connection.prepareStatement("SELECT password FROM users WHERE username = ?");
            prepareStatement.setString(1, user.getUsername());
            resultSet = prepareStatement.executeQuery();

            while (resultSet.next()) {
                String retrievedPassword = resultSet.getString("password");
                if (retrievedPassword.equals(oldPassword)) {
                    changePassword(newPassword);
                } else {
                    labelWrong.setText("you entered the wrong password");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("checkPassword error");
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

    // Login users
    public static void logInUser(ActionEvent event, String username, String password, Label labelWrong, PasswordField passwordField) {
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
                passwordField.clear();
            } else {
                while (resultSet.next()) {
                    String retrievedPassword = resultSet.getString("password");
                    if (retrievedPassword.equals(password)) {
                        user.setUsername(username);
                        System.out.println(user.toString() + " logIn system");
                        changeScene(event, "mainStage.fxml");
                    } else {
                        labelWrong.setText("Pass didnt match");
                        passwordField.clear();
                    }
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("logInUser error");
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

    // Checking whether a string is only a number
    public static boolean isNumeric(String string) {
        String regex = "[0-9]+[.]?[0-9]*";
        return Pattern.matches(regex, string);
    }

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

}
