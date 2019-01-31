package com.wildanka.portraitzxing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private Intent scanActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void scanUsingActivity(View view) {
        scanActivity = new Intent(MainActivity.this, ScanQrUsingActivity.class);
        startActivity(scanActivity);
    }

    public void scanUsingFragment(View view) {
        scanActivity = new Intent(MainActivity.this, ScanQrUsingFragmentActivity.class);
        startActivity(scanActivity);
    }

    public void scanDirectly(View view) {
        startActivity(new Intent(getApplicationContext(), SimpleScannerActivity.class));
    }
}
