package com.luseen.iotesting.ui;

import android.arch.lifecycle.LifecycleActivity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.luseen.iotesting.R;
import com.luseen.iotesting.db.Database;
import com.luseen.iotesting.db.User;
import com.luseen.iotesting.db.UserDao;

import java.util.List;

public class MainActivity extends LifecycleActivity implements Runnable, Observer<List<User>> {

    private List<User> userList;
    private UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userDao = Database.getInstance().getUserDao();

        MyViewModel viewModel = ViewModelProviders.of(this).get(MyViewModel.class);
        viewModel.getUsers().observe(this, this);
    }

    @Override
    public void run() {
        long deleteStart = System.currentTimeMillis();
        userDao.deleteAll(userList);
        printTime("Delete", deleteStart);

        long insertStart = System.currentTimeMillis();
        userDao.insertAll(userList);
        printTime("Insert", insertStart);

        long readStart = System.currentTimeMillis();
        userDao.getAll();
        printTime("Read", readStart);
    }

    private void printTime(String action, long start) {
        long end = System.currentTimeMillis();
        long milliseconds = end - start;
        Log.d("PrintTime ", action + " time for " + userList.size() + " items - " + milliseconds + " milliseconds");
    }

    @Override
    public void onChanged(@Nullable List<User> users) {
        userList = users;
        new Thread(MainActivity.this).start();
    }
}
