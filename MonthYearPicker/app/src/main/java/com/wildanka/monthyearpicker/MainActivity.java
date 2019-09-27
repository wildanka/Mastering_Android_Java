package com.wildanka.monthyearpicker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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

        Locale locale = new Locale("in");
        NumberPicker npMonth = findViewById(R.id.np_month);
        NumberPicker npYear = findViewById(R.id.np_year);

        npYear.setMinValue(1900);
        npYear.setMaxValue(2100);

        npMonth.setMinValue(0); // January
        npMonth.setMaxValue(11); // December

        //formatter
        final String[] months = DateFormatSymbols.getInstance(locale).getShortMonths();
        npMonth.setFormatter(new NumberPicker.Formatter() {
            @Override
            public String format(int value) {
                return months[value];
            }
        });

        int year = Calendar.getInstance().get(Calendar.YEAR); //get default year
        int month = Calendar.getInstance().get(Calendar.MONTH); //get default month

        npYear.setValue(year);
        npMonth.setValue(month);

    }
}
