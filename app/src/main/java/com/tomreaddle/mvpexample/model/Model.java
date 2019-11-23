package com.tomreaddle.mvpexample.model;

import android.text.TextUtils;
import com.tomreaddle.mvpexample.presenter.Presenter;
import com.tomreaddle.mvpexample.view.View;

public class Model implements Presenter {

    String APIusername , APIpassword;
    View view;

    public Model(View view , String APIusername , String APIpassword) {
        this.APIusername = APIusername;
        this.APIpassword = APIpassword;
        this.view = view;
    }

    @Override
    public void performLogin(String username, String password) {

        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)){
                view.loginValidation();
        } else {
            if (isValidEmailAddress(username) && password.equals(APIpassword)){
                view.loginSuccess();
            } else {
                view.loginError();
            }
        }
    }

    public boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }
}
