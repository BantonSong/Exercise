package com.song.exercise.clickposition;

import android.view.View;
import android.widget.Button;

import com.song.exercise.R;

/**
 * Created by songyawei on 2017/4/12.
 */
public class ClickPositionFragment extends BaseFragment {
    private Button btnLogin;
    private Button btnClear;

    @Override
    public void init() {

    }

    @Override
    public int setLayoutId() {
        return R.layout.fragment_positon_click_;
    }

    @Override
    public void initView(View view) {
        btnLogin = (Button) view.findViewById(R.id.btnLogin);
        btnClear = (Button) view.findViewById(R.id.btnClear);

        btnLogin.setOnClickListener(this);
        btnClear.setOnClickListener(this);
    }

    @Override
    public void initViewLabel() {
        labelMap.put(R.id.btnLogin, "0201");
        labelMap.put(R.id.btnClear, "0202");
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
    }
}
