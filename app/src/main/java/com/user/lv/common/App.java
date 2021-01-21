package com.user.lv.common;

import android.app.Application;
import android.content.Context;

public class App extends Application {
    private static App context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }
    public static App get(){
        return context;
    }
}
