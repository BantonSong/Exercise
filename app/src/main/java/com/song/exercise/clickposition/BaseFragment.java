package com.song.exercise.clickposition;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public abstract class BaseFragment extends Fragment implements View.OnClickListener {
    protected Map<Integer, String> labelMap = new HashMap();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(setLayoutId(), null);

        init();
        initView(view);
        initViewLabel();
        return view;
    }

    public void init() {
    }

    public abstract int setLayoutId();

    /**
     * 初始化视图，可以使用框架
     */
    public abstract void initView(View view);

    /**
     * 初始化按钮和id、label等信息
     * 为数据采集使用
     */
    public abstract void initViewLabel();

    @Override
    public void onClick(View v) {
        //TODO
        Set<Map.Entry<Integer, String>> set = labelMap.entrySet();
        for (Map.Entry<Integer, String> entry : set) {
            if (entry.getKey().equals(v.getId())) {
                Toast.makeText(getActivity(), entry.getValue(), Toast.LENGTH_SHORT).show();
            }
        }
    }

}
