package com.wildanka.df_vp_f;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.wildanka.df_vp_f.adapter.FSAdapter;
import com.wildanka.df_vp_f.interactor.IDialogFragmentToVPFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IDialogFragmentToVPFragment {
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnShowDialog = (Button) findViewById(R.id.btn_show_dialog);
        ViewPager viewPager = findViewById(R.id.viewPager);
        TabLayout tabLayout = findViewById(R.id.tabLayout);

        List<String> tabTitles = new ArrayList<>();
        tabTitles.add("Tab 1");
        tabTitles.add("Tab 2");
        FSAdapter adapter = new FSAdapter(getSupportFragmentManager(), tabTitles);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        final DialogExample dialogExample = new DialogExample();

        btnShowDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogExample.show(getSupportFragmentManager(), "DialogExample");
            }
        });
    }

    @Override
    public void updateSecondaryQty(boolean secondaryQtyState) {
        Toast.makeText(this, "Main Activity is triggered",Toast.LENGTH_LONG).show();
        Log.e(TAG, "updateSecondaryQty: TRIGGERED "+secondaryQtyState);
    }
}
