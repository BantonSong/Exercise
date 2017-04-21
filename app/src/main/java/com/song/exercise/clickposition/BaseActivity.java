package com.song.exercise.clickposition;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {
    protected Map<Integer, String> labelMap = new HashMap();

    @Override
    protected final void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayoutId());

        initView();
        initViewLabel();
        init();
    }

    public void init() {
    }

    public abstract int setLayoutId();

    /**
     * 初始化视图，可以使用框架
     */
    public abstract void initView();

    /**
     * 初始化按钮和id、label等信息
     * 为数据采集使用
     */
    public abstract void initViewLabel();

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            Toast.makeText(this, "X:" + ev.getX() + ",Y:" + ev.getY(), Toast.LENGTH_SHORT).show();
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void onClick(View v) {
        //TODO
        Set<Map.Entry<Integer, String>> set = labelMap.entrySet();
        for (Map.Entry<Integer, String> entry : set) {
            if (entry.getKey().equals(v.getId())) {
                Toast.makeText(this, entry.getValue(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
