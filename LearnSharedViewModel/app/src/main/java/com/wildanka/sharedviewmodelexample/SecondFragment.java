package com.wildanka.sharedviewmodelexample;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wildanka.sharedviewmodelexample.databinding.FragmentSecondBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class SecondFragment extends Fragment {
    private ExampleSharedViewModel viewModel;

    private FragmentSecondBinding binding;
    public SecondFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(getActivity()).get(ExampleSharedViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_second, container, false);
        binding.btnSaveFragmentTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateViewModel();
                observeSharedViewModel();
            }
        });
        return binding.getRoot();
    }

    private void updateViewModel(){
        viewModel.setPhoneNumber(binding.etPhoneNumber.getText().toString());
    }

    private void observeSharedViewModel(){
        viewModel.getName().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                System.out.println("NAME : "+s);
            }
        });

        viewModel.getAddress().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                System.out.println("ADDRESS : "+s);
            }
        });

        viewModel.getAge().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                System.out.println("AGE : "+s);
                binding.tvAgeValue.setText(s);
            }
        });

        viewModel.getPhoneNumber().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                System.out.println("Phone Number : "+s);
                binding.etPhoneNumber.setText(s);
            }
        });
    }
}