package com.example.dan.fragmentviewpagercommunication.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.dan.fragmentviewpagercommunication.R;
import com.example.dan.fragmentviewpagercommunication.view.interactor.ICommunicator;
import com.example.dan.fragmentviewpagercommunication.view.interactor.ITest;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentThree extends Fragment implements ITest{
    private static final String TAG = "FragmentThree";

    public FragmentThree() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_three, container, false);
    }

    public void onRefresh() {
        Toast.makeText(getActivity(), "Fragment 3: Refresh called.",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showToast() {
        Log.e(TAG, "Show toast called from ITest interface Fragment 3");
        Log.e(TAG,"Fragment Three");
    }
}
