package com.song.exercise.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by songyawei on 2017/4/17.
 */
public class BaseViewPagerFragment extends Fragment {
    private boolean isCreated = false;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        LogUtil.log();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.log();
        isCreated = true;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LogUtil.log();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LogUtil.log();
    }

    @Override
    public void onStart() {
        super.onStart();
        LogUtil.log();
    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtil.log();
    }

    @Override
    public void onPause() {
        super.onPause();
        LogUtil.log();
    }

    @Override
    public void onStop() {
        super.onStop();
        LogUtil.log();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        LogUtil.log();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtil.log();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        LogUtil.log();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            LogUtil.log(this, "Fragment可见");
        } else {
            LogUtil.log(this, "Fragment不可见");
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (!isCreated) {
            return;
        }
        if (isVisibleToUser) {
            LogUtil.log(this, "Fragment可见");
        } else {
            LogUtil.log(this, "Fragment不可见");
        }
    }
}
