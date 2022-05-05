package com.burak.barman.utils;

import javafx.scene.control.Label;

import static com.burak.barman.DBUtils.isNumeric;

/**
 * Barman
 * Created by Alexey Burak
 */

public class CheckPassword {

    // Minimum password length
    private static final int ACCEPTABLE_PASSWORD_LENGTH = 8;

    public static boolean checkInput(String password, String passwordConfirm, String username, Label labelWrong) {

        if (password.trim().isEmpty() && username.trim().isEmpty()) {
            labelWrong.setText("Fill all"); // If not all fields are filled in
            return false;
        } else if(password.length() < ACCEPTABLE_PASSWORD_LENGTH && passwordConfirm.length() < ACCEPTABLE_PASSWORD_LENGTH) {
            labelWrong.setText("Short password"); // Check password length
            return false;
        } else if (!password.equals(passwordConfirm)) {
            labelWrong.setText("passwords don't match"); // Check password match
            return false;
        } else if (isNumeric(password)) { // Checking whether a password is only a number
            labelWrong.setText("the password must contain at least one letter");
            return false;
        } else {
            return true;
        }
    }
}
