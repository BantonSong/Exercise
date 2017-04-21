package com.song.exercise.mvp.model;

import com.song.exercise.mvp.listener.OnLoginListener;

public interface ILoginModel {
    void login(String userName, String password, OnLoginListener loginListener);
}
