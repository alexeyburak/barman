package com.burak.barman.daoImpl;

import com.burak.barman.dao.AbstractDao;
import com.burak.barman.dao.DataBase;
import com.burak.barman.dao.IDao;
import com.burak.barman.models.Cocktail;
import com.burak.barman.models.User;

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
                    "LEFT JOIN recipe ON recipe.id_cocktail=cocktails.id LEFT " +
                    "JOIN ingredients ON recipe.id_ingredient=ingredients.id  GROUP BY cocktails.id");
            prepareStatementID = getConnection().prepareStatement("SELECT name, id, img, preparation FROM cocktails");
            resultSet = prepareStatement.executeQuery();
            resultSetID = prepareStatementID.executeQuery();

            while (resultSet.next() && resultSetID.next()) {
                int id = resultSetID.getInt("id");
                String name = resultSetID.getString("name");
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
                    "LEFT JOIN recipe ON recipe.id_cocktail=cocktails.id LEFT " +
                    "JOIN ingredients ON recipe.id_ingredient=ingredients.id  GROUP BY cocktails.id");
            prepareStatementID = getConnection().prepareStatement("SELECT c.id, c.preparation, c.name, c.img\n" +
                    "FROM cocktails as c\n" +
                    "LEFT JOIN recipe as r ON r.id_cocktail = c.id LEFT\n" +
                    "JOIN ingredients as i ON r.id_ingredient=i.id\n" +
                    "where IF (c.name LIKE '" + title + "%' = 0, i.title LIKE '%" + title + "%', c.name LIKE '" + title + "%')" +
                    "GROUP BY c.id");
            resultSet = prepareStatement.executeQuery();
            resultSetID = prepareStatementID.executeQuery();

            while (resultSet.next() && resultSetID.next()) {
                int id = resultSetID.getInt("id");
                String name = resultSetID.getString("name");
                String recipe = resultSet.getString("GROUP_CONCAT(recipe.id_ingredient)");
                String recipeAmount = resultSet.getString("GROUP_CONCAT(recipe.amount)");
                String preparation = resultSetID.getString("preparation");
                String img = resultSetID.getString("img");

                Cocktail cocktail = new Cocktail(id, name, recipe, preparation, recipeAmount, img);

                cocktails.add(cocktail);

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
    public Collection<Cocktail> findById(int id) {
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
                    "LEFT JOIN recipe ON recipe.id_cocktail=cocktails.id LEFT " +
                    "JOIN ingredients ON recipe.id_ingredient=ingredients.id  GROUP BY cocktails.id");
            prepareStatementID = getConnection().prepareStatement("SELECT id, preparation, name, img FROM cocktails WHERE id = ?");
            prepareStatementID.setInt(1, id);
            resultSet = prepareStatement.executeQuery();
            resultSetID = prepareStatementID.executeQuery();

            while (resultSet.next() && resultSetID.next()) {
                String name = resultSetID.getString("name");
                String recipe = resultSet.getString("GROUP_CONCAT(recipe.id_ingredient)");
                String recipeAmount = resultSet.getString("GROUP_CONCAT(recipe.amount)");
                String preparation = resultSetID.getString("preparation");
                String img = resultSetID.getString("img");

                Cocktail cocktail = new Cocktail(id, name, recipe, preparation, recipeAmount, img);

                cocktails.add(cocktail);

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
    public void updateData(String favorites, User user) {

    }
}
