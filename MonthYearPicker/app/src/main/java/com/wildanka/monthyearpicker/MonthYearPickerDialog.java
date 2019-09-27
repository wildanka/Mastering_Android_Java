package com.wildanka.monthyearpicker;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Locale;

public class MonthYearPickerDialog extends DialogFragment {
    private Context mContext;

    public MonthYearPickerDialog(Context mContext) {
        this.mContext = mContext;
    }

    /*
    * https://stackoverflow.com/questions/27544122/the-diff-with-oncreateview-and-oncreatedialog-in-dialogfragment/37303419
    * */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.month_year_picker_dialog, container, false);

        Locale locale = new Locale("in");
        final NumberPicker npMonth = view.findViewById(R.id.np_month);
        final NumberPicker npYear = view.findViewById(R.id.np_year);

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
        npMonth.setDisplayedValues(months); //set the displayed value first
        npMonth.setValue(month); // then set defaultValues
        return view;
    }
}
