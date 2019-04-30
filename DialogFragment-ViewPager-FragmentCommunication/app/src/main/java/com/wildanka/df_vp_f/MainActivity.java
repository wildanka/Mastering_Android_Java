package com.wildanka.df_vp_f;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnShowDialog = (Button) findViewById(R.id.btn_show_dialog);
        final DialogExample dialogExample = new DialogExample();

        btnShowDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogExample.show(getSupportFragmentManager(), "DialogExample");
            }
        });
    }
}
