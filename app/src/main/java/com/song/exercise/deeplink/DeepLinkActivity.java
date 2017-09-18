package com.song.exercise.deeplink;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.song.exercise.R;

/**
 * Created by songyawei on 2017/5/22.
 */
public class DeepLinkActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deeplink);

        Intent intent = getIntent();
        if (intent != null) {
            String url = intent.getStringExtra("URL");
            ((TextView) findViewById(R.id.textView)).setText(url);
        } else {
            ((TextView) findViewById(R.id.textView)).setText("DeepLink");
        }
    }
}
