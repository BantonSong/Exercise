package com.song.exercise.mvp.model.impl;

import com.song.exercise.mvp.bean.User;
import com.song.exercise.mvp.listener.OnLoginListener;
import com.song.exercise.mvp.model.ILoginModel;

public class LoginModelImpl implements ILoginModel {
    @Override
    public void login(final String userName, final String password, final OnLoginListener loginListener) {
        // 一般是在UI线程中操作的 有网络请求
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //模拟登录成功
                if (!"test".equals(userName)) {
                    loginListener.onUserNameError();
                } else if (!"1234".equals(password)) {
                    loginListener.onPasswordError();
                } else {
                    User user = new User();
                    user.setUserName(userName);
                    user.setPassword(password);
                    loginListener.onSuccess();
                }
            }
        }.start();
    }
}
