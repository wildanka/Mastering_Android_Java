package com.example.dan.dialog_dialogfragment;

import android.app.Fragment;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dan.dialog_dialogfragment.databinding.FragmentTestBinding;

public class FragmentTest extends Fragment implements CustomDialogForFragment.OnInputSelected{
    private static final String TAG = "FragmentTest";
    FragmentTestBinding binding;
    String mInput;

    @Override
    public void sendInput(String input) {
        Log.d(TAG, "sendInput: got the input: "+input);
        Log.d(TAG, "sendInput: found incoming input: " + input);

//        binding.tvDialogResult.setText(input);
        mInput = input;
        setInputToTextView();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_test,container,false);
        binding.btnOpenDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomDialogForFragment dialog = new CustomDialogForFragment();
                dialog.setTargetFragment(FragmentTest.this,1);
                dialog.show(getFragmentManager(),"CustomDialog");
            }
        });
        return binding.getRoot();
    }

    private void setInputToTextView(){
        binding.tvDialogResult.setText(mInput);
    }

}
