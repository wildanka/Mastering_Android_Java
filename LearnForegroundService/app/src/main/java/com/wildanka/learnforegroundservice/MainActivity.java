package com.wildanka.learnforegroundservice;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText etInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etInput = (EditText) findViewById(R.id.et_input);
    }

    public void startService(View v) {
        String input = etInput.getText().toString();
        Intent serviceIntent = new Intent(this,ExampleService.class);
        serviceIntent.putExtra( "inputExtra", input);

        //if we want to start our service while app services is on the background. we can use this
        //if we call the startService from the background, it will caused IllegalStateException, if on the mainthread then it's okay
//        startForegroundService(serviceIntent);

        //startForefroundService is only work on API 25 and above, so we need to use ContextCompat.startForegroundService()
        ContextCompat.startForegroundService(this, serviceIntent);
    }

    public void stopService(View v) {
        Intent serviceIntent = new Intent(this,ExampleService.class);
        stopService(serviceIntent);
    }
}