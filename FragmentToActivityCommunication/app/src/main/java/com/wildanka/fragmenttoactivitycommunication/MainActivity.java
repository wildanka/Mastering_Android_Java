package com.wildanka.fragmenttoactivitycommunication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnNext, btnPrev;
    private MainActivityViewModel viewModel;
    private ChooseNextFragmentListener chooseNextFragmentListener;
    public void setListener(ChooseNextFragmentListener chooseNextFragmentListener) {
        this.chooseNextFragmentListener = chooseNextFragmentListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        Button btnNext = findViewById(R.id.btn_next);
        Button btnPrev= findViewById(R.id.btn_prev);

        final FirstFragment firstFragment = new FirstFragment();
        final SecondFragment secondFragment = new SecondFragment();
        changeFragment(R.id.fl_container, firstFragment, "first");
        btnPrev.setEnabled(false);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pageIndex = viewModel.getPagesIndex();
                switch (pageIndex){
                    case 1 :
                        //change fragment to second fragment
//                        changeFragment(R.id.fl_container, secondFragment, "first");
                        chooseNextFragmentListener.checkError();
                        break;
                    case 2 :
                        viewModel.setPagesIndex(3);
//                        changeFragment(R.id.fl_container, firstFragment, "first");
                        break;
                    default:
//                        changeFragment(R.id.fl_container, secondFragment, "first");
                        firstFragment.checkError();
                        viewModel.setPagesIndex(1);
                }
            }
        });

//        btnPrev.setOnClickListener();
    }

    private void changeFragment(int idFragmentContainer, Fragment choosenFragment, String fragmentName) {
        getSupportFragmentManager()
                .beginTransaction()
//                .addToBackStack(null)
                .replace(idFragmentContainer, choosenFragment, fragmentName)
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                .commit();
    }

    @Override
    public void onClick(View v) {
        chooseNextFragmentListener.checkError();
    }
}
