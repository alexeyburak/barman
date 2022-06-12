package com.burak.barman.dao;


import com.burak.barman.models.User;

import java.util.Collection;

/**
 * Barman
 * Created by Alexey Burak
 */

public interface IDao<T> {

    // Get all from database
    Collection<T> findAll();
    // Get one from database by title
    Collection<T> findOne(String title);
    // Get one from database by id
    Collection<T> findById(int id);
    // Insert new values to database
    void updateData(String description, User user);

}
