package com.zhy.webview.process;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Toast;

import com.google.common.base.Strings;
import com.google.gson.Gson;
import com.zhy.webview.WebViewCallback;
import com.zhy.webview.model.JsParam;
import com.zhy.webview.process.client.AppWebViewChromeClient;
import com.zhy.webview.process.client.AppWebViewClient;
import com.zhy.webview.process.settings.WebViewDefaultSettings;

public class BaseWebView extends WebView {
    public BaseWebView(Context context) {
        super(context);
        init();
    }

    public BaseWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BaseWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public BaseWebView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        WebViewDefaultSettings.getInstance().setSettings(this);
        addJavascriptInterface(this, "xiangxuewebview");
    }

    @JavascriptInterface
    public void takeNativeAction(final String jsParam) {
        if (!Strings.isNullOrEmpty(jsParam)) {
            JsParam param = new Gson().fromJson(jsParam, JsParam.class);
            if (null != param) {
                if (param.name.equals("showToast")) {
                    Toast.makeText(getContext(), param.param.get("message").getAsString(), Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    public void registerWebViewCallBack(WebViewCallback webViewCallBack) {
        setWebViewClient(new AppWebViewClient(webViewCallBack));
        setWebChromeClient(new AppWebViewChromeClient(webViewCallBack));
    }
}
