package com.tomreaddle.mvpexample.model;

import android.text.TextUtils;

import com.tomreaddle.mvpexample.activities.MainActivity;
import com.tomreaddle.mvpexample.presenter.Presenter;
import com.tomreaddle.mvpexample.view.View;

public class Model implements Presenter {

    String APIusername , APIpassword;
    View view;
    MainActivity main;

    public Model(View view) {
        this.view = view;
    }

    @Override
    public void performLogin(String username, String password) {

        APIusername = main.APIuserame;
        APIpassword = main.APIpassword;

        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)){
                view.loginValidation();
        } else {
            if (username.equals(APIusername) && password.equals(APIpassword)){
                view.loginSuccess();
            } else {
                view.loginError();
            }
        }
    }
}
