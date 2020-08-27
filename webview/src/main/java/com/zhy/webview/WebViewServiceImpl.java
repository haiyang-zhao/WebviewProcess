package com.zhy.webview;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import com.google.auto.service.AutoService;
import com.zhy.common.autoservice.IWebViewService;
import com.zhy.webview.utils.Constants;

@AutoService({IWebViewService.class})
public class WebViewServiceImpl implements IWebViewService {
    @Override
    public void startWebViewActivity(Context context, String url, String title, boolean showActionBar) {
        if (null != context) {
            Intent intent = new Intent(context, WebViewActivity.class);
            intent.putExtra(Constants.URL, url);
            intent.putExtra(Constants.TITLE, title);
            intent.putExtra(Constants.IS_SHOW_ACTION_BAR, showActionBar);
            context.startActivity(intent);
        }
    }

    @Override
    public Fragment getWebViewFragment(String url, boolean enableRefresh) {
        return WebViewFragment.createInstance(url, enableRefresh);
    }

    @Override
    public void startDemoHtml(Context context) {
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra(Constants.TITLE, "本地Demo测试页");
        intent.putExtra(Constants.URL, Constants.ANDROID_ASSET_URI + "demo.html");
        context.startActivity(intent);
    }
}
