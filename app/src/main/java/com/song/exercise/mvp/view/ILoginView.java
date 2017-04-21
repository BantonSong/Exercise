package com.song.exercise.mvp.view;

public interface ILoginView {
    String getUserName();

    String getPassword();

    void clear();

    void loginSuccess();

    void loginNameError();

    void loginPaswordError();

    void showLoding();

    void hideLoding();
}
