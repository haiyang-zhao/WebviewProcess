package com.zhy.webview.process.client;

import android.graphics.Bitmap;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.zhy.webview.WebViewCallback;

public class AppWebViewClient extends WebViewClient {

    private WebViewCallback mWebViewCallback;

    public AppWebViewClient(WebViewCallback webViewCallback) {
        this.mWebViewCallback = webViewCallback;
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        if (null != mWebViewCallback) {
            mWebViewCallback.onPageStarted(url);
        }
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        if (null != mWebViewCallback) {
            mWebViewCallback.onPageFinished(url);
        }
    }
}
