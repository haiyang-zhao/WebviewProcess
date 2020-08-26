package com.zhy.base;

import android.app.Application;

import androidx.multidex.MultiDexApplication;

public class BaseApplication extends MultiDexApplication {

    public static Application sApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        sApplication = this;
    }
}
