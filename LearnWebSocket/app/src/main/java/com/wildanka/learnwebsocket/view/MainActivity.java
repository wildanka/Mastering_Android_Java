package com.wildanka.learnwebsocket.view;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.wildanka.learnwebsocket.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        ChatFragment fragment = new ChatFragment();
        fm.beginTransaction().replace(R.id.fl_main, fragment).commit();
    }
}
