package com.example.tagedittext;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
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
    ConstraintLayout constraintLayout;
    EditText initialEditText;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        constraintLayout = findViewById(R.id.constraintLayout);
        flexboxLayout = findViewById(R.id.flexboxlayout);
        initialEditText = findViewById(R.id.editText);
        btn = findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!initialEditText.getText().toString().isEmpty()) {
                    addSpecialist(initialEditText.getText().toString());
                    initialEditText.setText("");
                } else {
                    Toast.makeText(MainActivity.this, "tag tidak boleh kosong", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void addSpecialist(final String tagSpecialist) {
        if(initialEditText != null){
            flexboxLayout.removeView(initialEditText);
        }

        final TextView tvSpecialist = new TextView(this);
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
        //hitung selisih view
        int emptySpace = flexboxLayout.getRight() - tvSpecialist.getRight();
        FlexboxLayout.LayoutParams lpEditText;
        if (emptySpace < 75){
            lpEditText = new FlexboxLayout.LayoutParams(FlexboxLayout.LayoutParams.WRAP_CONTENT, FlexboxLayout.LayoutParams.FILL_PARENT);
        }else{
            lpEditText = new FlexboxLayout.LayoutParams(emptySpace, FlexboxLayout.LayoutParams.FILL_PARENT);
        }
        if (initialEditText == null){
            initialEditText = new EditText(this);
            initialEditText.setLayoutParams(lpEditText);
        }
        flexboxLayout.addView(initialEditText);

        tvSpecialist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flexboxLayout.removeView(tvSpecialist);
                Toast.makeText(MainActivity.this, tagSpecialist, Toast.LENGTH_SHORT).show();
            }
        });

        //add the edittext at the end of flexboxLayout
        final EditText etNew = new EditText(this);
    }
}
