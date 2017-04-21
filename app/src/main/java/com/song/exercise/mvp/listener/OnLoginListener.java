package com.song.exercise.mvp.listener;

public interface OnLoginListener {
    void onUserNameError();

    void onPasswordError();

    void onSuccess();
}
