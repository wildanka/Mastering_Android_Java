package com.example.dan.fragmentviewpagercommunication.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.dan.fragmentviewpagercommunication.R;
import com.example.dan.fragmentviewpagercommunication.view.interactor.ITest;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentTwo extends Fragment implements ITest{
//    private ICommunicator mCallback;
    private static final String TAG = "FragmentTwo";

    public FragmentTwo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_two, container, false);
    }

    public void onRefresh() {
        Toast.makeText(getActivity(), "Fragment 2: Refresh called.",
                Toast.LENGTH_SHORT).show();
    }

  /*  @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            mCallback = (ICommunicator) context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString());
        }
    }

    @Override
    public void onDetach() {
        mCallback = null;
        super.onDetach();
    }*/

    @Override
    public void showToast() {
        Log.e(TAG, "Show toast called from ITest interface Fragment 2");
    }
}
