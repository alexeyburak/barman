package com.burak.barman.daoImpl;

import com.burak.barman.dao.AbstractDao;
import com.burak.barman.dao.DataBase;
import com.burak.barman.dao.IDao;
import com.burak.barman.models.Cocktail;

import java.sql.*;
import java.util.Collection;
import java.util.LinkedList;

/**
 * Barman
 * Created by Alexey Burak
 */

public class CocktailsDaoImpl extends AbstractDao implements IDao<Cocktail> {

    @Override
    public Connection getConnection() {
        return DataBase.getInstance().getConnection();

    }

    @Override
    public Collection<Cocktail> findAll() {
        Collection<Cocktail> cocktails = new LinkedList<>();
        PreparedStatement prepareStatement = null;
        ResultSet resultSet = null;

        try {
            prepareStatement = getConnection().prepareStatement("SELECT GROUP_CONCAT(cocktails.id), cocktails.name, " +
                    "GROUP_CONCAT(recipe.id_ingredient),GROUP_CONCAT(recipe.amount), " +
                    "GROUP_CONCAT(cocktails.preparation), GROUP_CONCAT(cocktails.img) FROM cocktails LEFT JOIN recipe ON recipe.id_cocktail=cocktails.id " +
                    "LEFT JOIN ingredients ON recipe.id_ingredient=ingredients.id GROUP BY name");
            resultSet = prepareStatement.executeQuery();

            while (resultSet.next()) {
                String id = resultSet.getString("GROUP_CONCAT(cocktails.id)");
                String name = resultSet.getString("cocktails.name");
                String recipe = resultSet.getString("GROUP_CONCAT(recipe.id_ingredient)");
                String recipeAmount = resultSet.getString("GROUP_CONCAT(recipe.amount)");
                String preparation = resultSet.getString("GROUP_CONCAT(cocktails.preparation)");
                String img = resultSet.getString("GROUP_CONCAT(cocktails.img)");
                char c = id.charAt(0);
                int y = Character.getNumericValue(c);

                Cocktail cocktail = new Cocktail(y, name, recipe, preparation, recipeAmount, img);

                cocktails.add(cocktail);

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
        return cocktails;
    }

    @Override
    public Collection<Cocktail> findOne(String title) {
        return null;
    }
}
