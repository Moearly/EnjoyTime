package com.martn.enjoytime.base;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.content.res.Resources;


@SuppressLint("InflateParams")
public class BaseApplication extends Application{

    static Context context;
    static Resources resource;
    private boolean isMainOpened = false;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        resource = context.getResources();
    }

    public static synchronized BaseApplication context() {
        return (BaseApplication) context;
    }

    public static Resources resources() {
        return resource;
    }

    public boolean getIsMainOpened() {

        return isMainOpened;
    }

    public void setIsMainActivityOpened(boolean opened) {

        isMainOpened = opened;
    }
}
