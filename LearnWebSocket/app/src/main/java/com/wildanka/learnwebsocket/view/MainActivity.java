package com.wildanka.learnwebsocket.view;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.Ack;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import com.wildanka.learnwebsocket.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private TextView tvLastPrice, tvPriceChange, tvLowestPrice, tvHighestPrice, tvTxVolume, tvTxVolumeIDR;
    private Socket socket;
    private Emitter.Listener handleIncomingBTCIDR = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject data = (JSONObject) args[0];
                    try {
                        String lastPrice = data.getString("last_price");
                        tvLastPrice.setText(lastPrice);
                        String priceChange = data.getString("price_change_24h");
                        tvPriceChange.setText(priceChange);
                        String lowestPrice = data.getString("lowest_price");
                        tvLowestPrice.setText(String.valueOf(lowestPrice));
                        String highestPrice = data.getString("highest_price");
                        tvHighestPrice.setText(String.valueOf(highestPrice));
                        String txVolume = data.getString("tx_volume");
                        tvTxVolume.setText(txVolume);
                        String txVolumeIDR = data.getString("tx_volume_in_idr");
                        tvTxVolumeIDR.setText(txVolumeIDR);

                        System.out.println("last price : " + lastPrice);
                        System.out.println("priceChange : " + priceChange);
                        System.out.println("lowestPrice : " + lowestPrice);
                        System.out.println("last highestPrice : " + highestPrice);
//                            System.out.println("get data btcidr said : "+data.toString());
                        Log.d(TAG, "last price : " + lastPrice);


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
                    Log.d(TAG, "get data btcidr ON said : " + data.toString());
                    System.out.println("get data btcidr ON said : " + data.toString());
                }
            });
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //instance the findviewById
        tvLastPrice = (TextView) findViewById(R.id.tv_last_price);
        tvPriceChange = (TextView) findViewById(R.id.tv_price_change);
        tvLowestPrice = (TextView) findViewById(R.id.tv_low);
        tvHighestPrice = (TextView) findViewById(R.id.tv_high);
        tvTxVolume = (TextView) findViewById(R.id.tv_tx_volume);
        tvTxVolumeIDR = (TextView) findViewById(R.id.tv_tx_volume_in_idr);

        //setting up the connection
        {
            try {
                socket = IO.socket("http://192.168.88.8:5000/");
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }
        }
        //make connection with the socket
        socket.connect();
        socket.on("get update data btcidr", handleIncomingBTCIDR);
//        FragmentManager fm = getSupportFragmentManager();
//        ChatFragment fragment = new ChatFragment();
//        fm.beginTransaction().replace(R.id.fl_main, fragment).commit();
        initEmit();
    }

    private void initEmit(){
        socket.emit("get data btcidr", "", new Ack() {
            @Override
            public void call(final Object... args) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        JSONObject data = (JSONObject) args[0];
                        try {
                            String lastPrice = data.getString("last_price");
                            tvLastPrice.setText(lastPrice);
                            String priceChange = data.getString("price_change_24h");
                            tvPriceChange.setText(priceChange);
                            String lowestPrice = data.getString("lowest_price");
                            tvLowestPrice.setText(String.valueOf(lowestPrice));
                            String highestPrice = data.getString("highest_price");
                            tvHighestPrice.setText(String.valueOf(highestPrice));
                            String txVolume = data.getString("tx_volume");
                            tvTxVolume.setText(txVolume);
                            String txVolumeIDR = data.getString("tx_volume_in_idr");
                            tvTxVolumeIDR.setText(txVolumeIDR);

                            System.out.println("last price : " + lastPrice);
                            System.out.println("priceChange : " + priceChange);
                            System.out.println("lowestPrice : " + lowestPrice);
                            System.out.println("last highestPrice : " + highestPrice);
//                            System.out.println("get data btcidr said : "+data.toString());
                            Log.d(TAG, "last price : " + lastPrice);


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
                        Log.d(TAG, "get data btcidr EMIT said : " + data.toString());
                        System.out.println("get data btcidr EMIT said : " + data.toString());

                    }
                });
            }
        });
    }
}
