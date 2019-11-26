package com.tomreaddle.mvpexample.model;

import android.text.TextUtils;
import android.util.Log;
import com.tomreaddle.mvpexample.presenter.Presenter;
import com.tomreaddle.mvpexample.view.View;

import static android.content.ContentValues.TAG;

public class Model implements Presenter {

    View view;
    String APIusername , APIpassword;


    public Model(View view , String APIusername , String APIpassword) {
        this.APIusername = APIusername;
        this.APIpassword = APIpassword;
        this.view = view;
    }

    @Override
    public void performLogin(String username, String password) {

        if (username.equals(APIusername) && password.equals(APIpassword)){  view.loginSuccess();    }
        else if(TextUtils.isEmpty(username) && TextUtils.isEmpty(password)) view.Empty();
        else if(TextUtils.isEmpty(username)) view.emailEmpty();
        else if(TextUtils.isEmpty(password)) view.passwordEmpty();
        else if(!isValidEmailAddress(username)) view.emailNotCorrect();
        else {  view.loginError(); }
        Log.d(TAG, "performLogin: "+APIusername + "\n" + APIpassword);
    }


    public boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }
}
