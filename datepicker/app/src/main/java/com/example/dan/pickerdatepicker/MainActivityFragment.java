package com.example.dan.pickerdatepicker;

import android.app.DatePickerDialog;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.dan.pickerdatepicker.util.DatePickerFragment;
import com.example.dan.pickerdatepicker.util.TimePickerFragment;

import java.util.Calendar;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    int year;
    int month;
    int day;
    Calendar mCurrentDate;
    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        Button btnShowDatePicker = rootView.findViewById(R.id.btn_date_picker);
        Button btnShowTimePicker = rootView.findViewById(R.id.btn_time_picker);
        final EditText etDate = rootView.findViewById(R.id.et_date);
        TextView tvDate = rootView.findViewById(R.id.tvDate);

        //now calendar
        mCurrentDate = Calendar.getInstance();
        day = mCurrentDate.get(Calendar.DAY_OF_MONTH);
        month = mCurrentDate.get(Calendar.MONTH);
        year = mCurrentDate.get(Calendar.YEAR);

        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dpDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int date) {
                        month = month+1;
                        etDate.setText(date+"-"+month+"-"+year);
                    }
                }, year ,month, day);
                dpDialog.show();
            }
        });

        btnShowDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                datePickerDialog.show();
                showDatePickerDialog();
            }
        });

        btnShowTimePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimePickerDialog();
            }
        });
        return rootView;
    }

    public void showDatePickerDialog(){
        DialogFragment newFragment = new DatePickerFragment();

        newFragment.show(getActivity().getSupportFragmentManager(), "timePicker");
    }

    public void showTimePickerDialog() {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getActivity().getSupportFragmentManager(), "datePicker");

    }
}
