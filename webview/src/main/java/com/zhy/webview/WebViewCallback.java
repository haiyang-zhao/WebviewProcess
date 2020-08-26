package com.zhy.webview;

public interface WebViewCallback {
    void onPageStarted(String url);

    void onPageFinished(String url);

    void onError();

    void updateTitle(String title);
}
