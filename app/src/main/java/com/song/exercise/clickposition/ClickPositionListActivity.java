package com.song.exercise.clickposition;

import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.song.exercise.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by songyawei on 2017/4/12.
 */
public class ClickPositionListActivity extends BaseActivity {
    private ListView listView;

    @Override
    public void init() {
        List<String> list = new ArrayList();
        for (int i = 0; i < 100; i++) {
            list.add(i + "");
        }

        MyAdapter adapter = new MyAdapter(this, list);
        listView.setAdapter(adapter);
    }

    public int setLayoutId() {
        return R.layout.activity_positon_click_list;
    }

    @Override
    public void initView() {
        listView = (ListView) findViewById(R.id.listView);
    }

    @Override
    public void initViewLabel() {
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            Toast.makeText(this, "Scroll:" + getScrollY(), Toast.LENGTH_SHORT).show();
        }
        return super.dispatchTouchEvent(ev);
    }

    public int getScrollY() {
        View c = listView.getChildAt(0);
        if (c == null) {
            return 0;
        }
        int firstVisiblePosition = listView.getFirstVisiblePosition();
        int top = c.getTop();
        return -top + firstVisiblePosition * c.getHeight();
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
    }
}
