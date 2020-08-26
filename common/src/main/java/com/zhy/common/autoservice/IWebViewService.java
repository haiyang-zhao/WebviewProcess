package com.zhy.common.autoservice;

import android.content.Context;

import androidx.fragment.app.Fragment;

public interface IWebViewService {
    void startWebViewActivity(Context context, String url, String title, boolean showActionBar);

    Fragment getWebViewFragment(String url, boolean enableRefresh);
}
