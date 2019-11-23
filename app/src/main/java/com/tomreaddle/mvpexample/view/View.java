package com.tomreaddle.mvpexample.view;

public interface View {
    void loginValidation();//empty event
    void loginSuccess();  //success event
    void loginError();   //error event
}
