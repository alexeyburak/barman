package com.burak.barman.daoImpl;

import com.burak.barman.dao.AbstractDao;
import com.burak.barman.dao.DataBase;
import com.burak.barman.dao.IDao;
import com.burak.barman.models.Ingredient;

import java.sql.*;
import java.util.Collection;
import java.util.LinkedList;

/**
 * Barman
 * Created by Alexey Burak
 */

public class IngredientsDaoImpl extends AbstractDao implements IDao<Ingredient> {

    @Override
    public Connection getConnection() {
        return DataBase.getInstance().getConnection();
    }


    @Override
    public Collection<Ingredient> findAll() {
        Collection<Ingredient> ingredients = new LinkedList<>();
        PreparedStatement prepareStatement = null;
        ResultSet resultSet = null;

        try {
            prepareStatement = getConnection().prepareStatement("SELECT * FROM ingredients");
            resultSet = prepareStatement.executeQuery();

            while (resultSet.next()) {
                int id = Integer.parseInt(resultSet.getString("id"));
                String title = resultSet.getString("title");
                String category = resultSet.getString("category");
                int fortress = Integer.parseInt(resultSet.getString("fortress"));

                Ingredient ingredient = new Ingredient(title, id, fortress, category);
                ingredients.add(ingredient);
            }
        } catch (SQLException e) {
            System.out.println("findAll error " + e);
        } finally {
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                } catch (SQLException e) {
                    System.out.println("error closing the stream " + e);
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    System.out.println("error closing the stream " + e);
                }
            }
        }
        return ingredients;
    }

    @Override
    public Collection<Ingredient> findOne(String searchTitle) {
        Collection<Ingredient> ingredients = new LinkedList<>();
        PreparedStatement prepareStatement = null;
        ResultSet resultSet = null;

        try {
            prepareStatement = getConnection().prepareStatement("SELECT title, id, fortress, category FROM ingredients WHERE title LIKE '%" + searchTitle + "%'");
            resultSet = prepareStatement.executeQuery();

            while (resultSet.next()) {
                String title = resultSet.getString("title");
                int id = Integer.parseInt(resultSet.getString("id"));
                String category = resultSet.getString("category");
                int fortress = Integer.parseInt(resultSet.getString("fortress"));
                Ingredient ingredient = new Ingredient(title, id, fortress, category);
                ingredients.add(ingredient);

            }
        } catch (SQLException e) {
            System.out.println("findOne error " + e);
        } finally {
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                } catch (SQLException e) {
                    System.out.println("error closing the stream " + e);
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    System.out.println("error closing the stream " + e);
                }
            }
        }
        return ingredients;
    }
}
