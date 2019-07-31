package com.wildanka.fragmenttoactivitycommunication;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class FirstFragment extends Fragment implements ChooseNextFragmentListener {
    private static final String TAG = "FirstFragment";
    private TextView tvFirstFragment;
    public FirstFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        tvFirstFragment = view.findViewById(R.id.tv_first_fragment);

        return view;
    }

    @Override
    public void checkError() {
        Log.e(TAG, "checkError: Triggered");
        tvFirstFragment.setText("The Main Activity just called me?");
    }
}
