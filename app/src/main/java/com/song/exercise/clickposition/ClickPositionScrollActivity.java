package com.song.exercise.clickposition;

import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;
import android.widget.Toast;

import com.song.exercise.R;

/**
 * Created by songyawei on 2017/4/12.
 */
public class ClickPositionScrollActivity extends BaseActivity {
    private ScrollView scrollView;

    @Override
    public void init() {
    }

    public int setLayoutId() {
        return R.layout.activity_positon_click_scroll;
    }

    @Override
    public void initView() {
        scrollView = (ScrollView) findViewById(R.id.scrollView);
    }

    @Override
    public void initViewLabel() {
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            Toast.makeText(this, "Scroll:" + scrollView.getScrollY(), Toast.LENGTH_SHORT).show();
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
    }
}
