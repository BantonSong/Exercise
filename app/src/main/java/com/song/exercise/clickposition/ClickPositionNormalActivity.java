package com.song.exercise.clickposition;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.song.exercise.R;

/**
 * Created by songyawei on 2017/4/12.
 */
public class ClickPositionNormalActivity extends BaseActivity {
    private Button btnLogin;
    private Button btnClear;

    @Override
    public void init() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        ClickPositionFragment fragment = new ClickPositionFragment();
        ClickPositionFragment fragment2 = new ClickPositionFragment();
        fragmentTransaction.add(R.id.frameLayout, fragment);
        fragmentTransaction.add(R.id.frameLayout2, fragment2);
        fragmentTransaction.commit();
    }

    public int setLayoutId() {
        return R.layout.activity_positon_click_normal;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            if (isClickInView(findViewById(R.id.frameLayout), ev)) {
                Toast.makeText(this, "点击的是Fragment1", Toast.LENGTH_SHORT).show();
            }
            if (isClickInView(findViewById(R.id.frameLayout2), ev)) {
                Toast.makeText(this, "点击的是Fragment2", Toast.LENGTH_SHORT).show();
            }
        }

        return super.dispatchTouchEvent(ev);
    }

    private boolean isClickInView(View view, MotionEvent ev) {
        float x = ev.getRawX();
        float y = ev.getRawY();

        int[] location = new int[2];
        view.getLocationOnScreen(location);
        int left = location[0];
        int right = location[0] + view.getWidth();
        int top = location[1];
        int bottom = location[1] + view.getHeight();
        if (x > left && x < right && y > top && y < bottom) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void initView() {
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnClear = (Button) findViewById(R.id.btnClear);

        btnLogin.setOnClickListener(this);
        btnClear.setOnClickListener(this);
    }

    @Override
    public void initViewLabel() {
        labelMap.put(R.id.btnLogin, "0101");
        labelMap.put(R.id.btnClear, "0102");
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
    }
}
