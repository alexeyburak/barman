package com.burak.barman.dao;


import com.burak.barman.models.Ingredient;

import java.util.Collection;

/**
 * Barman
 * Created by Alexey Burak
 */

public interface IDao<T> {

    Collection<T> findAll();
    Collection<T> findOne(String title);
    Collection<T> findById(int id);

}
