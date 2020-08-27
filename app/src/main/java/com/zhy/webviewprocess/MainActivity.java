package com.zhy.webviewprocess;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.zhy.base.autoservice.XiangxueServiceLoader;
import com.zhy.common.autoservice.IWebViewService;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.open).setOnClickListener(v -> {
            IWebViewService webViewService =
                    XiangxueServiceLoader.load(IWebViewService.class);
            if (null != webViewService) {
//                webViewService.startWebViewActivity(MainActivity.this,
//                        "https://www.baidu.com/",
//                        "DataBinding",
//                        true);
                webViewService.startDemoHtml(this);
            }
        });
    }
}