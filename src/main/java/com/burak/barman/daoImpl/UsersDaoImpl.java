package com.burak.barman.daoImpl;


import com.burak.barman.dao.AbstractDao;
import com.burak.barman.dao.DataBase;
import com.burak.barman.dao.IDao;
import com.burak.barman.models.User;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

import java.sql.*;
import java.util.Collection;

import static com.burak.barman.ChangeScene.changeScene;

/**
 * Barman
 * Created by Alexey Burak
 */

public class UsersDaoImpl extends AbstractDao implements IDao<User> {

    public static User user = new User();

    @Override
    public Connection getConnection() {
        return DataBase.getInstance().getConnection();
    }

    // Registration new users
    public void signUpUser(ActionEvent event, String username, String password, Label labelWrong) {
        PreparedStatement psInsert = null;
        PreparedStatement psCheckUserExists = null;
        ResultSet resultSet = null;

        try {
            psCheckUserExists = getConnection().prepareStatement("SELECT * FROM users WHERE username = ?");
            psCheckUserExists.setString(1, username);
            resultSet = psCheckUserExists.executeQuery();

            if (resultSet.isBeforeFirst()) {
                labelWrong.setText("User exists");
            } else {
                psInsert = getConnection().prepareStatement("INSERT INTO users (username, password) VALUES (?, ?)");
                psInsert.setString(1, username);
                psInsert.setString(2, password);
                psInsert.executeUpdate();

                user.setUsername(username);
                System.out.println(user.toString() + " signUp system");
                changeScene(event, "mainStage.fxml");
            }
        } catch (SQLException e) {
            System.out.println("signUpUser error " + e);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    System.out.println("error closing the stream " + e);
                }
            }
            if (psCheckUserExists != null) {
                try {
                    psCheckUserExists.close();
                } catch (SQLException e) {
                    System.out.println("error closing the stream " + e);
                }
            }
            if (psInsert != null) {
                try {
                    psInsert.close();
                } catch (SQLException e) {
                    System.out.println("error closing the stream " + e);
                }
            }
        }
    }

    // Change users username
    public void changeUsername(String newUsername, Label labelWrong) {
        PreparedStatement prepareStatement = null;

        if (!newUsername.isEmpty()) {
            try {
                prepareStatement = getConnection().prepareStatement("UPDATE users SET username = ? WHERE username = ?");
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
                        System.out.println("error closing the stream " + e);
                    }
                }
            }
        } else {
            labelWrong.setText("Fill!!");
        }
    }

    // Change users password
    private void changePassword(String newPassword) {
        PreparedStatement prepareStatement= null;

        try {
            prepareStatement = getConnection().prepareStatement("UPDATE users SET password = ? WHERE username = ?");
            prepareStatement.setString(1, newPassword);
            prepareStatement.setString(2, user.getUsername());

            prepareStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("changePassword error" + e);
        } finally {
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                } catch (SQLException e) {
                    System.out.println("error closing the stream " + e);
                }
            }
        }
    }

    // Check old password in Data
    public void checkPassword(String oldPassword, String newPassword, Label labelWrong) {
        PreparedStatement prepareStatement = null;
        ResultSet resultSet = null;

        try {
            prepareStatement = getConnection().prepareStatement("SELECT password FROM users WHERE username = ?");
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
            System.out.println("checkPassword error " + e);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    System.out.println("error closing the stream " + e);
                }
            }
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                } catch (SQLException e) {
                    System.out.println("error closing the stream " + e);
                }
            }
        }
    }

    // Login users
    public void logInUser(ActionEvent event, String username, String password, Label labelWrong, PasswordField passwordField) {
        PreparedStatement prepareStatement = null;
        ResultSet resultSet = null;

        try {
            prepareStatement = getConnection().prepareStatement("SELECT password, favorites FROM users WHERE username = ?");
            prepareStatement.setString(1, username);
            resultSet = prepareStatement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                labelWrong.setText("There is no account");
                passwordField.clear();
            } else {
                while (resultSet.next()) {
                    String retrievedPassword = resultSet.getString("password");
                    String userFavorites = resultSet.getString("favorites");
                    if (retrievedPassword.equals(password)) {
                        user.setUsername(username);
                        user.setFavorites(userFavorites);
                        System.out.println(user.toString() + " logIn system");
                        changeScene(event, "mainStage.fxml");
                    } else {
                        labelWrong.setText("Pass didnt match");
                        passwordField.clear();
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("logInUser error" + e);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    System.out.println("error closing the stream " + e);
                }
            }
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();

                } catch (SQLException e) {
                    System.out.println("error closing the stream " + e);
                }
            }
        }
    }

    // Get favorites cocktails
    public String getFavoritesFromDao(User userUsername) {
        PreparedStatement prepareStatement = null;
        ResultSet resultSet = null;
        String retrievedFavorites = null;

        try {
            prepareStatement = getConnection().prepareStatement("SELECT favorites FROM users WHERE username = ?");
            prepareStatement.setString(1, userUsername.getUsername());

            resultSet = prepareStatement.executeQuery();

            while (resultSet.next()) {
                retrievedFavorites = resultSet.getString("favorites");

            }
        } catch (SQLException e) {
            System.out.println("getFavorites error" + e);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    System.out.println("error closing the stream " + e);
                }
            }
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                } catch (SQLException e) {
                    System.out.println("error closing the stream " + e);
                }
            }
        }
        return retrievedFavorites;
    }

    @Override
    public void updateData(String favorites, User user) {
        PreparedStatement prepareStatement= null;

        try {
            prepareStatement = getConnection().prepareStatement("UPDATE users SET favorites = ? WHERE username = ?");
            prepareStatement.setString(1, favorites);
            prepareStatement.setString(2, user.getUsername());
            prepareStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("updateData error" + e);
        } finally {
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                } catch (SQLException e) {
                    System.out.println("error closing the stream " + e);
                }
            }
        }
    }

    @Override
    public Collection<User> findAll() {
        return null;
    }

    @Override
    public Collection<User> findOne(String title) {
        return null;
    }

    @Override
    public Collection<User> findById(int id) {
        return null;
    }
}