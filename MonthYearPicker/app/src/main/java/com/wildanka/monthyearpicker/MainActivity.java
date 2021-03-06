package com.wildanka.monthyearpicker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;

import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText etMonthYear = findViewById(R.id.et_month_year);

        etMonthYear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final MonthYearPickerDialog dialog = new MonthYearPickerDialog(MainActivity.this);
                dialog.show(getSupportFragmentManager(), "MonthYearDialog");
            }
        });

//        Locale locale = new Locale("in");
//        final NumberPicker npMonth = findViewById(R.id.np_month);
//        final NumberPicker npYear = findViewById(R.id.np_year);
//
//        npYear.setMinValue(1900);
//        npYear.setMaxValue(2100);
//
//        npMonth.setMinValue(0); // January
//        npMonth.setMaxValue(11); // December
//
//        //formatter
//        final String[] months = DateFormatSymbols.getInstance(locale).getShortMonths();
//        npMonth.setFormatter(new NumberPicker.Formatter() {
//            @Override
//            public String format(int value) {
//                return months[value];
//            }
//        });
//
//        int year = Calendar.getInstance().get(Calendar.YEAR); //get default year
//        int month = Calendar.getInstance().get(Calendar.MONTH); //get default month
//        npYear.setValue(year);
//        npMonth.setDisplayedValues(months); //set the displayed value first
//        npMonth.setValue(month); // then set defaultValues
//
//        MonthYearPickerDialog monthYearPickerDialog = new MonthYearPickerDialog(this);
//        monthYearPickerDialog.show(getSupportFragmentManager(), "MonthYearPicker");

//        npMonth.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
//        npYear.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
    }
}
