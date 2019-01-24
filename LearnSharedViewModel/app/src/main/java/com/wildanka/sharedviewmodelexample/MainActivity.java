package com.wildanka.sharedviewmodelexample;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.wildanka.sharedviewmodelexample.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        FirstFragment fragment1 = new FirstFragment();
        SecondFragment fragment2 = new SecondFragment();
        this.getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fl1, fragment1, "fragmentOne")
                .commit();

        this.getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fl2, fragment2, "fragmentTwo")
                .commit();
    }
}
