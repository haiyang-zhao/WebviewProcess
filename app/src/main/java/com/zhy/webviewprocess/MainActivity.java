package com.zhy.webviewprocess;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.zhy.common.autoservice.IWebViewService;

import java.util.ServiceLoader;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.open).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IWebViewService webViewService =
                        ServiceLoader.load(IWebViewService.class).iterator().next();
                webViewService.startWebViewActivity(MainActivity.this,
                        "https://juejin.im/post/6844903609079971854",
                        "DataBinding",
                        false);
            }
        });
    }
}