package com.wildanka.portraitzxing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ScanQrUsingActivity extends AppCompatActivity {

    //well actually this is not a good practice, but we want it quick so here we are.
    public static TextView resultTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_qr_using_activities);

        resultTextView = (TextView) findViewById(R.id.tvResult);
    }

    public void startQrScan(View view) {
//        Intent in = new Intent(MainActivity.this, SimpleScannerActivity.class);
        startActivity(new Intent(getApplicationContext(), SimpleScannerActivity.class));
    }
}