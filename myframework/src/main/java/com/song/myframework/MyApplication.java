package com.song.myframework;

import android.app.Application;

import com.song.myframework.network.Network;

/**
 * Created by songyawei on 2017/4/13.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Network.init();
    }


    /**
     * 退出应用程序
     */
    public void appExit() {
        try {
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
