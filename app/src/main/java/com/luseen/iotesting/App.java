package com.luseen.iotesting;

import android.app.Application;

/**
 * Created by Chatikyan on 19.05.2017.
 */

public class App extends Application {

    private static App instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static App getInstance() {
        return instance;
    }
}
