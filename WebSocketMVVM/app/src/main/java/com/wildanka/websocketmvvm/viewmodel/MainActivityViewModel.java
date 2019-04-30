package com.wildanka.websocketmvvm.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.wildanka.websocketmvvm.model.MarketDataRepo;
import com.wildanka.websocketmvvm.model.entity.MarketData;

public class MainActivityViewModel extends ViewModel {
    private MutableLiveData<MarketData> marketDataMutableLiveData;

    public MutableLiveData<MarketData> getMarketDataMutableLiveData(String eventName) {
        loadMarketData(eventName);
        return marketDataMutableLiveData;
    }

    private void loadMarketData(String eventName){
        this.marketDataMutableLiveData = MarketDataRepo.getInstance().observerMarketData(eventName);
    }
}
