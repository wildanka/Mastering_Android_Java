package com.wildanka.learnforegroundservice;

import android.content.Intent;
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

        startService(serviceIntent);
    }

    public void stopService(View v) {
        Intent serviceIntent = new Intent(this,ExampleService.class);
        stopService(serviceIntent);
    }
}
