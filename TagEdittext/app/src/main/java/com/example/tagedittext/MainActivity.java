package com.example.tagedittext;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayout;
import com.google.android.flexbox.FlexboxLayoutManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    FlexboxLayout flexboxLayout;
    FlexboxLayout flexboxLayout2;
    RecyclerView rvTags;
    EditText editText, et2;
    Button btn, btn2;
    RvAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        flexboxLayout = findViewById(R.id.flexboxlayout);
        flexboxLayout2 = findViewById(R.id.flexboxlayout2);
        editText = findViewById(R.id.editText);
        et2 = findViewById(R.id.et2);
        btn = findViewById(R.id.button);
        btn2 = findViewById(R.id.button2);
        rvTags = findViewById(R.id.rv_tags);

        adapter = new RvAdapter();
        FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(this, FlexDirection.ROW, FlexWrap.WRAP);
        rvTags.setLayoutManager(layoutManager);
        rvTags.setAdapter(adapter);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!editText.getText().toString().isEmpty()) {
                    addSpecialist(editText.getText().toString());
                } else {
                    Toast.makeText(MainActivity.this, "tag tidak boleh kosong", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!et2.getText().toString().isEmpty()) {
                    addToRecyclerView(et2.getText().toString());
                } else {
                    Toast.makeText(MainActivity.this, "tag tidak boleh kosong", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void addToRecyclerView(String tag) {
        adapter.addTagSpecialist(tag);
    }

    private void addSpecialist(final String tagSpecialist) {
//        flexboxLayout.removeAllViews();

        TextView tvSpecialist = new TextView(this);
        tvSpecialist.setText(tagSpecialist);
        tvSpecialist.setTextSize(TypedValue.COMPLEX_UNIT_SP, Float.parseFloat("16"));
        tvSpecialist.setBackground(getResources().getDrawable(R.drawable.bordered_rectangle_rounded_corners));
        tvSpecialist.setTextColor(getResources().getColor(R.color.colorWhite));

        FlexboxLayout.LayoutParams layoutParamsRight = new FlexboxLayout.LayoutParams(FlexboxLayout.LayoutParams.WRAP_CONTENT, FlexboxLayout.LayoutParams.WRAP_CONTENT);
        tvSpecialist.setLayoutParams(layoutParamsRight);
        FlexboxLayout.LayoutParams lp = (FlexboxLayout.LayoutParams) tvSpecialist.getLayoutParams();
        lp.setMargins(10, 10, 10, 10);
        tvSpecialist.setLayoutParams(lp);
        flexboxLayout.addView(tvSpecialist);

        tvSpecialist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, tagSpecialist, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
