package com.tomreaddle.mvpexample.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tomreaddle.mvpexample.R;
import com.tomreaddle.mvpexample.model.Model;
import com.tomreaddle.mvpexample.presenter.Presenter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, com.tomreaddle.mvpexample.view.View {

    EditText username , password;
    Button signin;

    Presenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        signin = findViewById(R.id.signin);
        presenter = new Model(MainActivity.this);
        signin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String usernamedata = username.getText().toString();
        String passworddata = password.getText().toString();
        presenter.performLogin(usernamedata , passworddata);
    }

    @Override
    public void loginValidation() {
        Toast.makeText(this, "Username va parolni kiriting", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loginSuccess() {
        Toast.makeText(this, "Login va parol to'g'ri", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void loginError() {
        Toast.makeText(this, "Username yoki parol xato", Toast.LENGTH_SHORT).show();

    }
}
