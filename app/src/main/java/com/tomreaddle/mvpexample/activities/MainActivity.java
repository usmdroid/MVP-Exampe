package com.tomreaddle.mvpexample.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tomreaddle.mvpexample.API.APIModel;
import com.tomreaddle.mvpexample.API.APIinterface;
import com.tomreaddle.mvpexample.R;
import com.tomreaddle.mvpexample.model.Model;
import com.tomreaddle.mvpexample.presenter.Presenter;
import com.valdesekamdem.library.mdtoast.MDToast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements com.tomreaddle.mvpexample.view.View {

    public String APIusername = "none", APIpassword = "none";
    EditText username , password;
    Button signin;
    Presenter presenter;
    MDToast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        signin = findViewById(R.id.signin);
        apiConnect();

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apiConnect();
                presenter.performLogin(username.getText().toString(), password.getText().toString());
            }
        });


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
                APIusername = response.body().getUsername(); //"usm@droid.com"
                APIpassword = response.body().getPassword(); //"usmdroid"
                presenter = new Model(MainActivity.this , APIusername , APIpassword);
            }

            @Override
            public void onFailure(Call<APIModel> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void Empty() {
        toast = MDToast.makeText(this, "Maydonlarni to'ldiring", Toast.LENGTH_SHORT , MDToast.TYPE_INFO);
        toast.show();
    }

    @Override
    public void emailEmpty() {
        toast = MDToast.makeText(this, "E-mailni kiriting", Toast.LENGTH_SHORT , MDToast.TYPE_INFO);
        toast.show();
    }

    @Override
    public void passwordEmpty() {
        toast = MDToast.makeText(this, "Parolni kiriting", Toast.LENGTH_SHORT , MDToast.TYPE_INFO);
        toast.show();
    }

    @Override
    public void emailNotCorrect() {
        toast = MDToast.makeText(this, "Bu e-mail manzil emas!", Toast.LENGTH_SHORT , MDToast.TYPE_WARNING);
        toast.show();
    }

    @Override
    public void loginSuccess() {
        toast = MDToast.makeText(this, "Email va parol to'g'ri", Toast.LENGTH_SHORT , MDToast.TYPE_SUCCESS);
        toast.show();
    }

    @Override
    public void loginError() {
        toast = MDToast.makeText(this, "Email yoki parol xato", Toast.LENGTH_SHORT , MDToast.TYPE_ERROR);
        toast.show();
    }
}
