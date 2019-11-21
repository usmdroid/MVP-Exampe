package com.tomreaddle.mvpexample.model;

import android.text.TextUtils;

import com.tomreaddle.mvpexample.presenter.Presenter;
import com.tomreaddle.mvpexample.view.View;

public class Model implements Presenter {

    View view;

    public Model(View view) {
        this.view = view;
    }

    @Override
    public void performLogin(String username, String password) {
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)){
                view.loginValidation();
        } else {
            if (username.equals("usmdroid") && password.equals("usmdroid")){
                view.loginSuccess();
            } else {
                view.loginError();
            }
        }
    }
}
