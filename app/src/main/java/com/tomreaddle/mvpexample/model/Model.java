package com.tomreaddle.mvpexample.model;

import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.tomreaddle.mvpexample.API.APIModel;
import com.tomreaddle.mvpexample.API.APIinterface;
import com.tomreaddle.mvpexample.activities.MainActivity;
import com.tomreaddle.mvpexample.presenter.Presenter;
import com.tomreaddle.mvpexample.view.View;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.ContentValues.TAG;

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
            if (username.equals(APIusername) && password.equals(APIpassword)){
                view.loginSuccess();
            } else {
                view.loginError();
            }
        }
    }
}
