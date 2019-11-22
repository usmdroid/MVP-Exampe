package com.tomreaddle.mvpexample.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tomreaddle.mvpexample.API.APIModel;
import com.tomreaddle.mvpexample.API.APIinterface;
import com.tomreaddle.mvpexample.R;
import com.tomreaddle.mvpexample.model.Model;
import com.tomreaddle.mvpexample.presenter.Presenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, com.tomreaddle.mvpexample.view.View {

    public String APIuserame, APIpassword;
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

        apiConnect();

        presenter = new Model(MainActivity.this);
        signin.setOnClickListener(this);


    }

    private void apiConnect() {

        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(APIinterface.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIinterface apIinterface = retrofit.create(APIinterface.class);
        Call<APIModel> call = apIinterface.getData();

        call.enqueue(new Callback<APIModel>() {
            @Override
            public void onResponse(Call<APIModel> call, Response<APIModel> response) {
                APIModel data = response.body();
                APIuserame = data.getUsername();
                APIpassword = data.getPassword();
            }

            @Override
            public void onFailure(Call<APIModel> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                APIuserame = "null";
                APIpassword = "null";
            }
        });
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
