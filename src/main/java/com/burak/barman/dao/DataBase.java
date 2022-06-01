package com.burak.barman.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Barman
 * Created by Alexey Burak
 */

public class DataBase {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/barman";
    private static final String DATABASE_USER = "root";
    private static final String DATABASE_PASSWORD = "alexeyburak";
    private static final DataBase ourInstance = new DataBase();
    private static Connection connection;

    public static DataBase getInstance() {
        return ourInstance;
    }

    public Connection getConnection() {
        return connection;
    }

    private DataBase() {
        try {
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
        } catch (SQLException e) {
            System.out.println("Database connection error " + e);
        }
    }
}
