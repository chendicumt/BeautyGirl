package com.panda.beautygirl.utils;

import android.app.Application;
import android.content.Context;

/**
 * Created by PC on 2017/10/13.
 */

public class MyApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        context=getApplicationContext();
    }
    public static Context getContext()
    {
        return context;
    }
}
