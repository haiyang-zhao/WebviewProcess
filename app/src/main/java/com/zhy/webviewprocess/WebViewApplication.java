package com.zhy.webviewprocess;

import com.kingja.loadsir.core.LoadSir;
import com.zhy.base.BaseApplication;
import com.zhy.base.loadsir.CustomCallback;
import com.zhy.base.loadsir.EmptyCallback;
import com.zhy.base.loadsir.ErrorCallback;
import com.zhy.base.loadsir.LoadingCallback;
import com.zhy.base.loadsir.TimeoutCallback;

public class WebViewApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        LoadSir.beginBuilder()
                .addCallback(new ErrorCallback())
                .addCallback(new EmptyCallback())
                .addCallback(new LoadingCallback())
                .addCallback(new TimeoutCallback())
                .addCallback(new CustomCallback())
                .setDefaultCallback(LoadingCallback.class)
                .commit();
    }
}
