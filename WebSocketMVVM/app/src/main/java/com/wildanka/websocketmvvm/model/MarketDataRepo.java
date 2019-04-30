package com.wildanka.websocketmvvm.model;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.Socket;
import com.wildanka.websocketmvvm.WebSocketMVVMApplication;
import com.wildanka.websocketmvvm.model.entity.MarketData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MarketDataRepo {
    private static final String TAG = "MarketDataRepo";
    private static class SingletonHelper{
        private static final MarketDataRepo INSTANCE = new MarketDataRepo();
    }
    public static MarketDataRepo getInstance(){
        return SingletonHelper.INSTANCE;
    }


    public MutableLiveData<MarketData> observerMarketData(String eventName){
        final MutableLiveData<MarketData> marketDataMutableLiveData = new MutableLiveData<>();

        //region emitter listener
        Emitter.Listener handleIncomingBTCIDR = new Emitter.Listener() {
            @Override
            public void call(final Object... args) {
                JSONObject data = (JSONObject) args[0];
                try {
                    String lastPrice = data.getString("last_price");
                    String priceChange = data.getString("price_change_24h");
                    String lowestPrice = data.getString("lowest_price");
                    String highestPrice = data.getString("highest_price");
                    String txVolume = data.getString("tx_volume");
                    String txVolumeIDR = data.getString("tx_volume_in_idr");

                    System.out.println("last price : " + lastPrice);
                    System.out.println("priceChange : " + priceChange);
                    System.out.println("lowestPrice : " + lowestPrice);
                    System.out.println("last highestPrice : " + highestPrice);
//                            System.out.println("get data btcidr said : "+data.toString());
                    Log.d(TAG, "last price : " + lastPrice);

                    //construct the data
                    MarketData m = new MarketData(lastPrice, priceChange, lowestPrice, highestPrice, txVolume, txVolumeIDR);
                    marketDataMutableLiveData.postValue(m);
                    JSONArray dataJSONArray = data.getJSONArray("market_activity");
                    Log.d(TAG, "last price : " + lastPrice);
                    for (int i = 0; i < dataJSONArray.length(); i++) {
                        JSONObject dataObj = (JSONObject) dataJSONArray.get(i);
                        String id = dataObj.getString("price");
                        //Similarly you can extract for other fields.
                        Log.d(TAG, "m activity- " + i + " = " + lastPrice);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.d(TAG, "get data btcidr said : " + data.toString());
                System.out.println("get data btcidr said : " + data.toString());
            }
        };
        //endregion emitter listener

        WebSocketMVVMApplication.getSocket().on("get update data btcidr", handleIncomingBTCIDR);
        return marketDataMutableLiveData;
    }

}