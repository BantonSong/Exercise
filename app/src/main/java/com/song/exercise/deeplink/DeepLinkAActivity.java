package com.song.exercise.deeplink;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.song.exercise.R;

/**
 * Created by songyawei on 2017/5/22.
 */
public class DeepLinkAActivity extends BaseActivity {
    private Button btnA;
    private Button btnB;
    private Button btnC;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deeplink);
        ((TextView) findViewById(R.id.textView)).setText("A");

        btnA = (Button) findViewById(R.id.btnA);
        btnB = (Button) findViewById(R.id.btnB);
        btnC = (Button) findViewById(R.id.btnC);

        btnA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DeepLinkAActivity.this, DeepLinkAActivity.class);
                startActivity(intent);
            }
        });

        btnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DeepLinkAActivity.this, DeepLinkBActivity.class);
                startActivity(intent);
            }
        });

        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DeepLinkAActivity.this, DeepLinkCActivity.class);
                startActivity(intent);
            }
        });
    }
}
