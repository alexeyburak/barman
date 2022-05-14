package com.burak.barman.dao;


import java.util.Collection;

/**
 * Barman
 * Created by Alexey Burak
 */

public interface IDao<T> {

    Collection<T> findAll();
    Collection<T> findOne(String title);


}
