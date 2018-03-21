package com.example.wzw.biubiubiu;

import android.app.Application;
import android.content.Context;

/**
 * Created by wzw on 2018/3/21.
 */

public class MyApplication extends Application {

    private Context mApplicationContext;

    @Override
    public void onCreate() {
        mApplicationContext = getApplicationContext();
        super.onCreate();
    }
}
