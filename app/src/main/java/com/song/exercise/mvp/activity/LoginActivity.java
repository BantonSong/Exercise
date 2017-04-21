package com.song.exercise.mvp.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.song.exercise.R;
import com.song.exercise.mvp.bean.User;
import com.song.exercise.mvp.presenter.LoginPresenter;
import com.song.exercise.mvp.view.ILoginView;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class LoginActivity extends Activity implements ILoginView, View.OnClickListener {
    private EditText edtUserName;
    private EditText edtPassword;
    private Button btnLogin;
    private Button btnClear;
    private LinearLayout llProgressBar;

    private LoginPresenter loginPresenter = new LoginPresenter(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUserName = (EditText) findViewById(R.id.edtUserName);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnClear = (Button) findViewById(R.id.btnClear);
        llProgressBar = (LinearLayout) findViewById(R.id.llProgressBar);

        btnLogin.setOnClickListener(this);
        btnClear.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogin:
                loginPresenter.login();
                break;
            case R.id.btnClear:
                loginPresenter.clear();
                break;
        }
    }

    @Override
    public String getUserName() {
        return edtUserName.getText().toString();
    }

    @Override
    public String getPassword() {
        return edtPassword.getText().toString();
    }

    @Override
    public void clear() {
        edtUserName.setText("");
        edtPassword.setText("");
    }

    @Override
    public void loginSuccess() {
        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loginNameError() {
        Toast.makeText(this, "没有此用户", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loginPaswordError() {
        Toast.makeText(this, "密码错误", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoding() {
        llProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoding() {
        llProgressBar.setVisibility(View.GONE);
    }
}
