package com.wildanka.modalbottomsheet;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements ExampleBottomSheetDialog.BottomSheetListener {
    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvResult = (TextView) findViewById(R.id.tv_clicked_button);
        Button btnOpenBottomSheet = (Button) findViewById(R.id.btn_open_modal);
        btnOpenBottomSheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExampleBottomSheetDialog bottomSheetDialog = new ExampleBottomSheetDialog();

                bottomSheetDialog.show(getSupportFragmentManager(), "");
            }
        });
    }

    @Override
    public void onButtonClicked(String text) {
        tvResult.setText(text);
    }
}
