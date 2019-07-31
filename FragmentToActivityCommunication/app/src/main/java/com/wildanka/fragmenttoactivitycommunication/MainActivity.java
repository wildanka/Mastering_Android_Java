package com.wildanka.fragmenttoactivitycommunication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void changeFragment(int idFragmentContainer, Fragment choosenFragment, String fragmentName) {
        getSupportFragmentManager()
                .beginTransaction()
//                .addToBackStack(null)
                .replace(idFragmentContainer, choosenFragment, fragmentName)
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                .commit();
    }
}
