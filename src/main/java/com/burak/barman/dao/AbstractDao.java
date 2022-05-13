package com.burak.barman.dao;

import java.sql.Connection;

/**
 * Barman
 * Created by Alexey Burak
 */

public abstract class AbstractDao {
    public abstract Connection getConnection();

}
