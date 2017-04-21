package com.song.exercise.h5;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.song.exercise.R;

/**
 * Created by songaywei on 2017/3/29.
 */
public class H5WebActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private WebView webView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_h5);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        webView = (WebView) findViewById(R.id.webView);

        //设置编码
        webView.getSettings().setDefaultTextEncodingName("utf-8");
        //支持js
        webView.getSettings().setJavaScriptEnabled(true);
        //设置本地调用对象及其接口
        webView.addJavascriptInterface(new H5Test(this), "androidTest");
        //载入js
        webView.loadUrl("file:///android_asset/helloh5.html");
    }

    public void callJs(View view) {
        webView.loadUrl("javascript:funFromjs()");
    }

    class H5Test {
        private Activity activity;

        public H5Test(Activity activity) {
            this.activity = activity;
        }

        @JavascriptInterface
        public void fun1FromAndroid(final String name) {

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(activity, "JS调Native方法:" + name, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
