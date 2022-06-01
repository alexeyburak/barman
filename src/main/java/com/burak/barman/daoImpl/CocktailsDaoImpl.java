package com.burak.barman.daoImpl;

import com.burak.barman.dao.AbstractDao;
import com.burak.barman.dao.DataBase;
import com.burak.barman.dao.IDao;
import com.burak.barman.models.Cocktail;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
        List<Cocktail> cocktails = new ArrayList<>();

        // Used twice to work around problems arising from GROUP_CONCAT
        // Used for id, preparation, img
        PreparedStatement prepareStatementID = null;
        // Used for name, recipe, recipeAmount
        PreparedStatement prepareStatement = null;
        ResultSet resultSetID = null;
        ResultSet resultSet = null;
        try {
            prepareStatement = getConnection().prepareStatement("SELECT cocktails.name, GROUP_CONCAT(recipe.id_ingredient),GROUP_CONCAT(recipe.amount) FROM cocktails " +
                    "left join recipe on recipe.id_cocktail=cocktails.id left " +
                    "join ingredients on recipe.id_ingredient=ingredients.id  Group by name\n");
            prepareStatementID = getConnection().prepareStatement("SELECT id, img, preparation FROM cocktails");
            resultSet = prepareStatement.executeQuery();
            resultSetID = prepareStatementID.executeQuery();

            while (resultSet.next() && resultSetID.next()) {
                int id = resultSetID.getInt("id");
                String name = resultSet.getString("cocktails.name");
                String recipe = resultSet.getString("GROUP_CONCAT(recipe.id_ingredient)");
                String recipeAmount = resultSet.getString("GROUP_CONCAT(recipe.amount)");
                String preparation = resultSetID.getString("preparation");
                String img = resultSetID.getString("img");

                Cocktail cocktail = new Cocktail(id, name, recipe, preparation, recipeAmount, img);

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
            if (prepareStatementID != null) {
                try {
                    prepareStatementID.close();
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
            if (resultSetID != null) {
                try {
                    resultSetID.close();
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
