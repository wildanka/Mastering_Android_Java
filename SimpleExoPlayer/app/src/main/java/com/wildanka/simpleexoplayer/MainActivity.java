package com.wildanka.simpleexoplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnExo = findViewById(R.id.btn_go_to_exo);

        btnExo.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, ExampleMediaPlayerActivity.class));
        });
    }
}
