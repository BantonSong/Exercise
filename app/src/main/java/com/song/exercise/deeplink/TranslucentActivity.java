package com.song.exercise.deeplink;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.song.exercise.MainActivity;

/**
 * Created by songyawei on 2017/5/22.
 */
public class TranslucentActivity extends BaseActivity {
    final String TAG = TranslucentActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        if (intent != null && intent.getData() != null) {
            Uri uri = intent.getData();
            Log.e(TAG, "URI:" + uri.toString());

            String host = uri.getHost();
            Log.e(TAG, "getHost:" + host);

            Log.e(TAG, "getScheme:" + uri.getScheme());
            Log.e(TAG, "getPath:" + uri.getPath());
            Log.e(TAG, "getPort:" + uri.getPort());

            Log.e(TAG, "getQuery:" + uri.getQuery());
            for (String key : uri.getQueryParameterNames()) {
                Log.e(TAG, "key:" + key + "   :value" + uri.getQueryParameter(key));
            }
            if ("weburl".equals(host)) {
                ActivityManager.removeActivity(this);
                Activity activity = ActivityManager.getCurrentActivity();
                if (activity != null) {
                    Intent openIntent = new Intent(activity, DeepLinkActivity.class);
                    openIntent.putExtra("URL", uri.getQueryParameter("url"));
                    activity.startActivity(openIntent);
                } else {
                    Intent openIntent = new Intent(this, MainActivity.class);
                    openIntent.putExtra("URL", uri.getQueryParameter("url"));
                    startActivity(openIntent);
                }
            }
        } else {
            Intent openIntent = new Intent(this, MainActivity.class);
            startActivity(openIntent);
        }
        finish();
    }
}
