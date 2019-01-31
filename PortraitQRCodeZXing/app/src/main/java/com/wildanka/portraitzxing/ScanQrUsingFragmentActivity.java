package com.wildanka.portraitzxing;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

public class ScanQrUsingFragmentActivity extends AppCompatActivity {
    FrameLayout flScanQrUsingFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_qr_using_fragment);
        flScanQrUsingFragment = (FrameLayout) findViewById(R.id.fl_scan_fragment);

        ScanQrFragment scanQrFragment = new ScanQrFragment();
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction()
                .replace(R.id.fl_scan_fragment, scanQrFragment)
                .commit();
    }
}
