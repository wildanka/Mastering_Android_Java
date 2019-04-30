package com.wildanka.websocketmvvm.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.wildanka.websocketmvvm.R;
import com.wildanka.websocketmvvm.model.entity.MarketData;
import com.wildanka.websocketmvvm.viewmodel.MainActivityViewModel;

public class MainActivity extends AppCompatActivity {
    private MainActivityViewModel viewModel;
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView tvTest = findViewById(R.id.tv_last_price);

        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        viewModel.getMarketDataMutableLiveData("get update data btcidr").observe(this, new Observer<MarketData>() {
            @Override
            public void onChanged(@Nullable MarketData marketData) {
                Log.d(TAG, " last price : "+marketData.getLastPrice()+" \n price change : "+marketData.getPriceChange());
                tvTest.setText(marketData.getLastPrice());
            }
        });


    }
}