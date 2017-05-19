package com.luseen.iotesting.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.luseen.iotesting.R;
import com.luseen.iotesting.db.Database;
import com.luseen.iotesting.db.User;
import com.luseen.iotesting.db.UserDao;

public class MainActivity extends AppCompatActivity implements Runnable {

    private User[] user = new User[1000];
    private UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userDao = Database.getInstance().getUserDao();

        addDummyData();

        new Thread(this).start();
    }

    @Override
    public void run() {
        long deleteStart = System.currentTimeMillis();
        userDao.deleteAll(user);
        printTime("Delete", deleteStart);

        long insertStart = System.currentTimeMillis();
        userDao.insertAll(user);
        printTime("Insert", insertStart);

        long readStart = System.currentTimeMillis();
        userDao.getAll();
        printTime("Read", readStart);
    }

    private void addDummyData() {
        for (int i = 0; i < user.length; i++) {
            user[i] = new User(i, "Name " + i, "Surname " + i);
        }
    }

    private void printTime(String action, long start) {
        long end = System.currentTimeMillis();
        long milliseconds = end - start;
        Log.d("PrintTime ", action + " time for " + user.length + " items - " + milliseconds + " milliseconds");
    }
}
