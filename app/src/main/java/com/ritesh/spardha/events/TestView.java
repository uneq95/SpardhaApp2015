package com.ritesh.spardha.events;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Toast;

import com.ritesh.spardha.spardha2015.R;

/**
 * Created by Abhishek on 8/29/2015.
 */
public class TestView extends Activity{
    WebView brow;
    String address = "http://www.spardha.co.in/file/";
    String[] RuleBookUrl = {"athletics.pdf","badminton.pdf","basketball.pdf","boxing.pdf","carrom.pdf","chess.pdf","cricket.pdf","football.pdf","handball.pdf","hockey.pdf",
                            "kabaddi.pdf","kho-kho.pdf","squash.pdf","tae-kwon-do.pdf","tennis.pdf","table-tennis.pdf","volleyball.pdf","lifting.pdf","winner-athletics.pdf","winner-tae-kwon-do.pdf"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testview);
        brow = (WebView) findViewById(R.id.webView);
        Bundle b = getIntent().getExtras();
        int urln = b.getInt("url_no");
        brow.getSettings().getDatabaseEnabled();
        brow.getSettings().getAllowFileAccess();
        brow.getSettings().getBuiltInZoomControls();
        brow.getSettings().setSupportZoom(true);
        brow.getSettings().setBuiltInZoomControls(true);
        brow.getSettings().setDisplayZoomControls(true);
        brow.setDownloadListener(null);
        brow.setWebViewClient(new clientweb());
        brow.getSettings().setJavaScriptEnabled(true);
        brow.getSettings().setLoadWithOverviewMode(true);
        brow.getSettings().setUseWideViewPort(true);


        String final_url = address+RuleBookUrl[urln];

        try{
            brow.loadUrl("https://docs.google.com/viewer?url="+final_url);
        }catch (Exception e){
            Toast.makeText(this,e.toString(),Toast.LENGTH_LONG).show();
        }
    }
}
