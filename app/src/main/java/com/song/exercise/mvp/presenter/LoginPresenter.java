package com.song.exercise.mvp.presenter;

import android.os.Handler;

import com.song.exercise.mvp.listener.OnLoginListener;
import com.song.exercise.mvp.model.ILoginModel;
import com.song.exercise.mvp.model.impl.LoginModelImpl;
import com.song.exercise.mvp.view.ILoginView;

/**
 * Presenter层，处理业务逻辑
 */
public class LoginPresenter {
    private ILoginModel loginModel;
    private ILoginView loginView;

    private Handler handler = new Handler();

    public LoginPresenter(ILoginView loginView) {
        this.loginView = loginView;
        loginModel = new LoginModelImpl();
    }

    public void login() {
        loginView.showLoding();
        loginModel.login(loginView.getUserName(), loginView.getPassword(), new OnLoginListener() {
            @Override
            public void onUserNameError() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        loginView.hideLoding();
                        loginView.loginNameError();
                    }
                });
            }

            @Override
            public void onPasswordError() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        loginView.hideLoding();
                        loginView.loginPaswordError();
                    }
                });
            }

            @Override
            public void onSuccess() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        loginView.hideLoding();
                        loginView.loginSuccess();
                    }
                });
            }
        });
    }

    public void clear() {
        loginView.clear();
    }
}
