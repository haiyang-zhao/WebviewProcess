package com.zhy.webview.client;

import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.zhy.webview.WebViewCallback;

public class AppWebViewChromeClient extends WebChromeClient {

    private WebViewCallback mWebViewCallback;

    public AppWebViewChromeClient(WebViewCallback webViewCallback) {
        this.mWebViewCallback = webViewCallback;
    }

    @Override
    public void onReceivedTitle(WebView view, String title) {
        super.onReceivedTitle(view, title);
        if (null != this.mWebViewCallback) {
            this.mWebViewCallback.updateTitle(title);
        }
    }
}
