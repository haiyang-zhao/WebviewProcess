package com.zhy.webview;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.zhy.webview.databinding.ActivityWebviewBinding;
import com.zhy.webview.utils.Constants;

public class WebViewActivity extends AppCompatActivity {
    private ActivityWebviewBinding mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_webview);
        mBinding.title.setText(getIntent().getStringExtra(Constants.TITLE));
        mBinding.actionBar.setVisibility(getIntent().getBooleanExtra(Constants.IS_SHOW_ACTION_BAR,
                true) ? View.VISIBLE : View.GONE);
        mBinding.back.setOnClickListener(v -> WebViewActivity.this.finish());

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction tx = fm.beginTransaction();
        WebViewFragment fragment = WebViewFragment.createInstance(getIntent()
                .getStringExtra(Constants.URL), true);
        tx.replace(R.id.web_view_fragment, fragment);
        tx.commit();
    }

    public void updateTitle(String title) {
        mBinding.title.setText(title);
    }
}
