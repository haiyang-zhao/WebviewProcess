package com.zhy.webview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;
import com.zhy.base.loadsir.ErrorCallback;
import com.zhy.base.loadsir.LoadingCallback;
import com.zhy.webview.client.AppWebViewChromeClient;
import com.zhy.webview.client.AppWebViewClient;
import com.zhy.webview.databinding.FragmentWebviewBinding;
import com.zhy.webview.utils.Constants;

public class WebViewFragment extends Fragment implements WebViewCallback {

    private String mUrl;
    private boolean mEnableRefresh;
    private boolean mIsError;
    private FragmentWebviewBinding mBinding;
    private LoadService mLoadService;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_webview,
                container, false);
        mBinding.webview.getSettings().setJavaScriptEnabled(true);

        mBinding.webview.setWebViewClient(new AppWebViewClient(this));
        mBinding.webview.setWebChromeClient(new AppWebViewChromeClient(this));
        mBinding.smartrefreshlayout.setEnableRefresh(mEnableRefresh);
        mBinding.smartrefreshlayout.setEnableLoadMore(false);
        mLoadService = LoadSir.getDefault()
                .register(mBinding.smartrefreshlayout, new Callback.OnReloadListener() {
                    @Override
                    public void onReload(View v) {
                        mLoadService.showCallback(LoadingCallback.class);
                        mBinding.webview.reload();
                    }
                });

        mBinding.webview.loadUrl(mUrl);
        return mLoadService.getLoadLayout();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        if (null != arguments) {
            mUrl = arguments.getString(Constants.URL, "");
            mEnableRefresh = arguments.getBoolean(Constants.CAN_NATIVE_REFRESH,
                    false);
        }
    }

    public static WebViewFragment createInstance(String mUrl, boolean enableRefresh) {
        WebViewFragment fragment = new WebViewFragment();
        Bundle arguments = new Bundle();
        arguments.putString(Constants.URL, mUrl);
        arguments.putBoolean(Constants.CAN_NATIVE_REFRESH, enableRefresh);
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public void onPageStarted(String url) {
        if (null != mLoadService) {
            mLoadService.showCallback(LoadingCallback.class);
        }
    }

    @Override
    public void onPageFinished(String url) {
        if (mIsError) {
            mBinding.smartrefreshlayout.setEnableRefresh(true);
        } else {
            mBinding.smartrefreshlayout.setEnableRefresh(mEnableRefresh);
        }
        mBinding.smartrefreshlayout.finishRefresh();

        if (null != mLoadService) {
            if (mIsError) {
                mLoadService.showCallback(ErrorCallback.class);
            } else {
                mLoadService.showSuccess();
            }
        }
        mIsError = false;
    }

    @Override
    public void onError() {
        mIsError = true;
        mBinding.smartrefreshlayout.finishRefresh();
    }

    @Override
    public void updateTitle(String title) {
        FragmentActivity activity = getActivity();
        if (activity instanceof WebViewActivity) {
            ((WebViewActivity) activity).updateTitle(title);
        }
    }
}
