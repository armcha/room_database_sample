package com.luseen.iotesting.ui;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.luseen.iotesting.db.User;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Chatikyan on 20.05.2017.
 */

public class MyViewModel extends ViewModel {

    private MutableLiveData<List<User>> users;

    public MyViewModel() {
        super();
        Log.d("MyViewModel ", "Created" );
    }

    public LiveData<List<User>> getUsers() {
        if (users == null) {
            users = new MutableLiveData<>();
            loadUsers();
        }
        return users;
    }

    private void loadUsers() {
        Log.d("MyViewModel", "loadUsers: started");
        User[] user = new User[100];
        for (int i = 0; i < user.length; i++) {
            user[i] = new User(i, "Name " + i, "Surname " + i);
        }
        users.setValue(Arrays.asList(user));
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d("MyViewModel ", "Cleared");
    }
}
