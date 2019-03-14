package com.gacon.julien.mynews.controllers.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.gacon.julien.mynews.R;
import com.gacon.julien.mynews.controllers.fragments.articleFragment.BaseArticleFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WebViewActivity extends AppCompatActivity {

    @BindView(R.id.webview)
    WebView mWebView;
    WebViewClient mWebViewClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        ButterKnife.bind(this);
        // Configure webview
        mWebViewClient = new WebViewClient();
        mWebView.setWebViewClient(mWebViewClient);
        String url=getIntent().getStringExtra(BaseArticleFragment.BUNDLE_URL);
        mWebView.loadUrl(url);
    }

}
