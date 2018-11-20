package com.example.dan.dialog_dialogfragment;

import android.app.DialogFragment;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dan.dialog_dialogfragment.databinding.DialogLayoutBinding;

public class CustomDialog extends DialogFragment {
    private static final String TAG = "CustomDialog";
    DialogLayoutBinding binding;

    public interface OnInputListener{
        void sendInput(String input);
    }
    public OnInputListener mOnInputListener;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_layout, container,false);

        binding.tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG,"onClick : Closing Dialog");
                getDialog().dismiss();
            }
        });

        binding.tvOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG,"onClick : Capturing Input");

                String inputPrice = binding.etPrice.getText().toString();
//                if (!inputPrice.equals("")){
//                    //Easiest Way, just set the value
//                    ((MainActivity)getActivity()).binding.mainContent.tvDialogResult.setText(inputPrice);
//                }
                // "Best Practice" but it takes longer
                mOnInputListener.sendInput(inputPrice);

                getDialog().dismiss();
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            mOnInputListener = (OnInputListener) getActivity();
        }catch(ClassCastException e){
            Log.e(TAG, "onAttach : " + e.getMessage());
        }
    }
}
