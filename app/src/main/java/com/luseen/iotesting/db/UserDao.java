package com.luseen.iotesting.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by Chatikyan on 19.05.2017.
 */
@Dao
public interface UserDao {

    @Query("SELECT * FROM testUserTableName")
    List<User> getAll();

    @Query("SELECT * FROM testUserTableName WHERE uid IN (:userIds)")
    List<User> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM testUserTableName WHERE first_name LIKE :first AND "
            + "last_name LIKE :last LIMIT 1")
    User findByName(String first, String last);

    @Insert
    void insertAll(User... users);

    @Insert
    void insertAll(List<User> users);

    @Delete
    void delete(User user);

    @Delete
    void deleteAll(User... users);
}
