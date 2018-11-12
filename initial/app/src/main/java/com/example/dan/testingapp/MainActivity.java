package com.example.dan.testingapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dan.testingapp.api.ApiInterface;
import com.example.dan.testingapp.helper.RetrofitClient;
import com.example.dan.testingapp.model.Datum;
import com.example.dan.testingapp.model.UserData;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
//    private ApiInterface mApiInterface;
    private String BASE_URL = "http://192.168.88.12:8000/";

    private EditText etEmail;
    private EditText etPassword;
    private Button btnLogin;

    private String emailLogin;
    private String passwordLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //binding
        etEmail = (EditText) findViewById(R.id.et_email);
        etPassword = (EditText) findViewById(R.id.et_password);
        btnLogin = (Button) findViewById(R.id.btn_login);

        // inisial instance API Interface

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emailLogin = etEmail.getText().toString();
                passwordLogin = etPassword.getText().toString();


                postLogin(emailLogin,passwordLogin);
//                getUserRequest();
//                sendLoginPost(emailLogin,passwordLogin);

            }
        });


        //FAB
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                getUserRequest();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void sendLoginPost(String email, String password){
//        if (retrofit==null) {
//            retrofit = new Retrofit.Builder()
//                    .baseUrl(baseUrl)
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build();
//        }
//
//        ApiInterface apiInterface = retrofit.create(ApiInterface.class);
//        Call<UserLogin> call = ApiInterface.getData();
//        mApiInterface.userLogin(email,password).enqueue(new Callback<UserLogin>() {
//            @Override
//            public void onResponse(Call<UserLogin> call, Response<UserLogin> response) {
//                Log.d(TAG, "onResponse: Login Success "+response.toString());
//                Log.i(TAG, "post submitted to API." + response.body().toString());
//
//            }
//
//            @Override
//            public void onFailure(Call<UserLogin> call, Throwable t) {
//                Log.e(TAG, "Unable to submit login to API. (post)");
//            }
//        });
    }

    private void postLogin(String email, String password){
        Call<ResponseBody> responseBodyCall = RetrofitClient
                .getInstance()
                .getApiInterface()
                .loginAuth(email,password);

        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String s = response.body().string();
                    Toast.makeText(MainActivity.this,s, Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Log.d(TAG, "onResponse: Server Response "+response.toString());
                Log.d(TAG, "onResponse: Received information "+response.body().toString());
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, "onFailure: Something Went Wrong"+t.getMessage());
                Toast.makeText(MainActivity.this,"Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getUserRequest(){
//    private void getUserRequest( UserLogin userLogin){
//post ke web service menggunakan retrofit
//         #1 create retrofit instance using Retrofit.Builder,
//           specify the baseUrl -> converter factory,
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();

        //get interface and call object for the request
        ApiInterface apiInterface = retrofit.create(ApiInterface.class);

        Call<UserData> call = apiInterface.getData();

        call.enqueue(new Callback<UserData>() {
            @Override
            public void onResponse(Call<UserData> call, Response<UserData> response) {
                Log.d(TAG, "onResponse: Server Response "+response.toString());
                Log.d(TAG, "onResponse: Received information "+response.body().toString());
                List<Datum> datumList = response.body().getData();
                for (int i = 0; i < datumList.size(); i++) {
                    Toast.makeText(MainActivity.this,datumList.get(i).getEmail(), Toast.LENGTH_SHORT).show();

                    System.out.println(datumList.get(i).getEmail());

                    System.out.println(datumList.get(i).getEmail());
                }
            }
            @Override
            public void onFailure(Call<UserData> call, Throwable t) {
                Log.e(TAG, "onFailure: Something Went Wrong"+t.getMessage());
                Toast.makeText(MainActivity.this,"Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });


    }

    /*
    *
                Log.d(TAG, "onResponse: Server Response "+response.toString());
                Log.d(TAG, "onResponse: Received information "+response.body().toString());

Log.e(TAG, "onFailure: Something Went Wrong"+t.getMessage());
                Toast.makeText(MainActivity.this,"Something went wrong",Toast.LENGTH_SHORT).show();
    * */
}
