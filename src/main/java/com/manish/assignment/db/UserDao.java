package com.manish.assignment.db;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM user")
    List<User> getAllUsers();


    @Insert
    void insertUser(User... users);

    @Delete
    void delete(User user);

    @Query("DELETE FROM user")
    void deleteAll();

    @Query("SELECT EXISTS(SELECT * FROM user WHERE name = :name)")
    boolean isCountryNAmeExists(String name);
}
