package com.gacon.julien.mynews.controllers.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.gacon.julien.mynews.R;
import com.gacon.julien.mynews.controllers.fragments.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WebViewActivity extends AppCompatActivity {

    private static final String TAG = "WebViewActivity";
    public static final int WEB_ACTIVITY_REQUEST_CODE = 6;
    String url;

    @BindView(R.id.webview)
    WebView mWebView;
    WebViewClient mWebViewClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        ButterKnife.bind(this);
        mWebViewClient = new WebViewClient();
        mWebView.setWebViewClient(mWebViewClient);

        String url=getIntent().getStringExtra(BaseFragment.BUNDLE_URL);
        mWebView.loadUrl(url);

    }

}
