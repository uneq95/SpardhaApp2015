package com.ritesh.spardha.events;

import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by Abhishek on 8/29/2015.
 */
public class clientweb extends WebViewClient {


    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return super.shouldOverrideUrlLoading(view, url);
    }
}
