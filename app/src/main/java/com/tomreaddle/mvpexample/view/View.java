package com.tomreaddle.mvpexample.view;

public interface View {
    void Empty();//email empty event
    void emailEmpty();//email empty event
    void passwordEmpty();//password empty event
    void emailNotCorrect();//email check event
    void loginSuccess();  //success event
    void loginError();   //error event
}
