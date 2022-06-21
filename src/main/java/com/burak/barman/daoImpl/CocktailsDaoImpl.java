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

        PreparedStatement prepareStatement = null;
        ResultSet resultSet = null;

        try {
            prepareStatement = getConnection().prepareStatement("SELECT c.id, c.name, c.preparation, c.img, GROUP_CONCAT(r.id_ingredient) as id_ingredient, GROUP_CONCAT(r.amount) as amounts\n" +
                    "FROM cocktails as c LEFT JOIN recipe as r ON r.id_cocktail = c.id LEFT\n" +
                    "JOIN ingredients as i ON r.id_ingredient = i.id\n" +
                    "GROUP BY c.name, c.preparation, c.img, c.id\n" +
                    "order by c.id");
            resultSet = prepareStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String recipe = resultSet.getString("id_ingredient");
                String recipeAmount = resultSet.getString("amounts");
                String preparation = resultSet.getString("preparation");
                String img = resultSet.getString("img");

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
        List<Cocktail> cocktails = new ArrayList<>();

        PreparedStatement prepareStatement = null;
        ResultSet resultSet = null;

        try {
            prepareStatement = getConnection().prepareStatement("SELECT c.id, c.name, c.preparation, c.img, GROUP_CONCAT(r.id_ingredient) as id_ingredient, GROUP_CONCAT(r.amount) as amounts\n" +
                    "FROM cocktails as c LEFT JOIN recipe as r ON r.id_cocktail = c.id LEFT\n" +
                    "JOIN ingredients as i ON r.id_ingredient = i.id\n" +
                    "GROUP BY c.name, c.preparation, c.img, c.id\n" +
                    "having (c.name like '" + title +"%') or (GROUP_CONCAT(i.title) like '%" + title + "%')\n" +
                    "order by c.id");
            resultSet = prepareStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String recipe = resultSet.getString("id_ingredient");
                String recipeAmount = resultSet.getString("amounts");
                String preparation = resultSet.getString("preparation");
                String img = resultSet.getString("img");

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
    public Collection<Cocktail> findById(int id) {
        List<Cocktail> cocktails = new ArrayList<>();

        PreparedStatement prepareStatement = null;
        ResultSet resultSet = null;

        try {
            prepareStatement = getConnection().prepareStatement("SELECT c.id, c.name, c.preparation, c.img, GROUP_CONCAT(r.id_ingredient) as id_ingredient, GROUP_CONCAT(r.amount) as amounts\n" +
                    "FROM cocktails as c LEFT JOIN recipe as r ON r.id_cocktail = c.id LEFT\n" +
                    "JOIN ingredients as i ON r.id_ingredient = i.id\n" +
                    "where c.id = ?\n" +
                    "GROUP BY c.name, c.preparation, c.img, c.id\n" +
                    "order by c.id");
            prepareStatement.setInt(1, id);
            resultSet = prepareStatement.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String recipe = resultSet.getString("id_ingredient");
                String recipeAmount = resultSet.getString("amounts");
                String preparation = resultSet.getString("preparation");
                String img = resultSet.getString("img");

                Cocktail cocktail = new Cocktail(id, name, recipe, preparation, recipeAmount, img);

                cocktails.add(cocktail);

            }
        } catch (SQLException e) {
            System.out.println("findById error " + e);
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
    public void updateData(String favorites, User user) {

    }
}
