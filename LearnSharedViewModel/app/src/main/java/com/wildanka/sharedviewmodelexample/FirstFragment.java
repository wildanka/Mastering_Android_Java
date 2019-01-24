package com.wildanka.sharedviewmodelexample;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wildanka.sharedviewmodelexample.databinding.FragmentFirstBinding;


/**
 * A simple {@link Fragment} subclass.
 */
public class FirstFragment extends Fragment {
    private FragmentFirstBinding binding;
    private ExampleSharedViewModel viewModel;
    public FirstFragment() {
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
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_first, container, false);

        binding.btnSaveFragmentOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateViewModel();
                observeSharedViewModel();
            }
        });

        observeSharedViewModel();
        return binding.getRoot();
    }

    private void updateViewModel(){
        viewModel.setAddress(binding.etAddress.getText().toString());
    }

    private void observeSharedViewModel(){
        viewModel.getName().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                System.out.println("NAME : "+s);
                binding.tvNameValue.setText(s);
            }
        });

        viewModel.getAddress().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                System.out.println("ADDRESS : "+s);
                binding.etAddress.setText(s);
            }
        });

        viewModel.getAge().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                System.out.println("AGE : "+s);
            }
        });

        viewModel.getPhoneNumber().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                System.out.println("Phone Number : "+s);
            }
        });
    }

}