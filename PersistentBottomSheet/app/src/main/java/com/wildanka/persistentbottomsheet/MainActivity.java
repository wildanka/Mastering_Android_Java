package com.wildanka.persistentbottomsheet;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private BottomSheetBehavior mBottomSheetBehavior;
    private TextView tvState;
    private Boolean expanded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View bottomSheet = findViewById(R.id.nsv_bottom_sheet);
        tvState = findViewById(R.id.tv_bottom_sheet_state);
        mBottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        Button btnExpand = findViewById(R.id.btn_show_hide);
        btnExpand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!expanded){
                    mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    expanded=true;
                }else{
                    mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    expanded=false;
                }
            }
        });

        mBottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View view, int newState) {
                switch (newState){
                    case BottomSheetBehavior.STATE_COLLAPSED :
                        tvState.setText("Collapsed");
                        expanded=false;
                        break;
                    case BottomSheetBehavior.STATE_DRAGGING :
                        tvState.setText("STATE_DRAGGING...");
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED :
                        expanded=true;
                        tvState.setText("STATE_EXPANDED");
                        break;
                    case BottomSheetBehavior.STATE_HALF_EXPANDED :
                        expanded=true;
                        tvState.setText("STATE_HALF_EXPANDED");
                        break;
                    case BottomSheetBehavior.STATE_HIDDEN :
                        tvState.setText("STATE_HIDDEN");
                        break;
                    case BottomSheetBehavior.STATE_SETTLING :
                        tvState.setText("STATE_SETTLING");
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View view, float v) {
                tvState.setText("Sliding...");
            }
        });
    }
}
