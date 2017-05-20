package com.luseen.iotesting.db;

import android.arch.persistence.room.Room;

import com.luseen.iotesting.App;

/**
 * Created by Chatikyan on 19.05.2017.
 */

public class Database  {//095611222

    private static Database database = null;

    private UserDao userDao;

    private Database() {
        String name = "test-database";
        AppDatabase appDatabase = Room.databaseBuilder(
                App.getInstance(),
                AppDatabase.class,
                name).build();
        userDao = appDatabase.userDao();
    }

    public static Database getInstance() {
        if (database == null) {
            synchronized (Database.class) {
                database = new Database();
            }
        }
        return database;
    }

    public UserDao getUserDao() {
        return userDao;
    }
}