package com.wildanka.learnintentservice;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

    public void startIntentService(View view) {
        String input = etInput.getText().toString();
        Log.d("MainActivity", input);
        Intent intentService = new Intent(this, ExampleIntentService.class);
        intentService.putExtra("inputExtra", input);

        ContextCompat.startForegroundService(this, intentService);
    }
}
