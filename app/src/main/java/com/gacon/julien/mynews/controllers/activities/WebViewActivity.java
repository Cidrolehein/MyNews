package com.gacon.julien.mynews.controllers.activities;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.gacon.julien.mynews.R;
import com.gacon.julien.mynews.controllers.fragments.articleFragment.BaseArticleFragment;
import java.util.Objects;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * For Web View
 */

public class WebViewActivity extends AppCompatActivity {

    // Web View Layout
    @BindView(R.id.webview)
    WebView mWebView;
    WebViewClient mWebViewClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        // Initialize ButterKnife
        ButterKnife.bind(this);
        // Toolbar
        configureToolbar();
        // Configure webview
        this.getWebView();
    }

    private void getWebView() {
        mWebViewClient = new WebViewClient();
        mWebView.setWebViewClient(mWebViewClient);
        String url=getIntent().getStringExtra(BaseArticleFragment.BUNDLE_URL);
        mWebView.loadUrl(url);
    }

    private void configureToolbar(){
        //Get the toolbar view inside the activity layout
        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        // Set the Toolbar
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        // Enable the up button
        assert ab != null;
        Objects.requireNonNull(ab).setDisplayHomeAsUpEnabled(true);
    }

}
