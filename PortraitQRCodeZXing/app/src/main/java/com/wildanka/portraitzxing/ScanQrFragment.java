package com.wildanka.portraitzxing;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import static android.app.Activity.RESULT_OK;

public class ScanQrFragment extends Fragment {
    private static final String TAG = "ScanQrFragment";
    public static final int RECEIVE_QR = 2;
    private EditText etQrScanResult;

    public ScanQrFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_scan_qr, container, false);
        Button btnScan = (Button) rootView.findViewById(R.id.btn_scan_qr_fragment);
        etQrScanResult = (EditText) rootView.findViewById(R.id.et_scan_qr);

        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(getActivity(), SimpleScannerActivity.class), RECEIVE_QR);
            }
        });


        return rootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RECEIVE_QR){
            if (resultCode == RESULT_OK){
                //add to edittext
                Log.d(TAG, "onActivityResult: "+data.getData().toString());
                etQrScanResult.setText(data.getData().toString());
            }
        }
    }
}
