package com.burak.barman.daoImpl;

import com.burak.barman.dao.AbstractDao;
import com.burak.barman.dao.DataBase;

import java.sql.Connection;

/**
 * Barman
 * Created by Alexey Burak
 */

public class IngredientsDaoImpl extends AbstractDao {

    @Override
    public Connection getConnection() {
        return DataBase.getInstance().getConnection();
    }
}
