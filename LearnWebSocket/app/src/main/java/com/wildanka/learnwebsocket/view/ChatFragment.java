package com.wildanka.learnwebsocket.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.Ack;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import com.wildanka.learnwebsocket.R;
import com.wildanka.learnwebsocket.model.Message;
import com.wildanka.learnwebsocket.view.adapter.MessageAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChatFragment extends Fragment {
    private static final String TAG = "ChatFragment";
    private View rootView;
    private RecyclerView rvChatListDisplay;
    private Button btnSend;
    private TextView tvLastPrice, tvPriceChange, tvLowestPrice, tvHighestPrice, tvTxVolume, tvTxVolumeIDR;
    private EditText etChatInput;
    private List<Message> mMessages = new ArrayList<Message>();
    private MessageAdapter mAdapter;

    private Emitter.Listener handleIncomingMessages = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject data = (JSONObject) args[0];
                    String message;
                    try {
                        Log.d(TAG, "run: " + data.toString());
                        message = data.getString("message").toString();
                    } catch (JSONException e) {
                        e.printStackTrace();
                        return;
                    }
                    addMessage(message);
                }
            });
        }
    };

    private Emitter.Listener handleIncomingBTCIDR = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            getActivity().runOnUiThread(new Runnable() {
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
                    Log.d(TAG, "get data btcidr said : " + data.toString());
                    System.out.println("get data btcidr said : " + data.toString());
                }
            });
        }
    };

    private Socket socket;


    public ChatFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setting up the connection
        {
            try {
                socket = IO.socket("http://192.168.88.18:5000/");
            } catch (URISyntaxException e) {
//            e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
        //make connection with the socket
        socket.connect();
        socket.on("message", handleIncomingMessages);
//        socket.on("message", handleIncomingMessages);
        socket.on("get update data btcidr", handleIncomingBTCIDR);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_chat, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //instance the findviewById
        rvChatListDisplay = (RecyclerView) view.findViewById(R.id.rv_chat_display);
        rvChatListDisplay.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvChatListDisplay.setAdapter(mAdapter); //create the adapter
        tvLastPrice = (TextView) view.findViewById(R.id.tv_last_price);
        tvPriceChange = (TextView) view.findViewById(R.id.tv_price_change);
        tvLowestPrice = (TextView) view.findViewById(R.id.tv_low);
        tvHighestPrice = (TextView) view.findViewById(R.id.tv_high);
        tvTxVolume = (TextView) view.findViewById(R.id.tv_tx_volume);
        tvTxVolumeIDR = (TextView) view.findViewById(R.id.tv_tx_volume_in_idr);
        btnSend = (Button) view.findViewById(R.id.btn_send_chat);
        etChatInput = (EditText) view.findViewById(R.id.et_chat_input);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });
    }

    private void sendMessage() {
        String message = etChatInput.getText().toString();
        etChatInput.setText("");
        addMessage(message);
//        socket.emit("message", message);
//        socket.emit("ferret", "HELLOW", new Ack() {
//            @Override
//            public void call(Object... args) {
//                String response = (String)args[0];
//                Log.d(TAG, "ferrel said : "+response);
//            }
//        });
        socket.emit("get data btcidr", "", new Ack() {
            @Override
            public void call(final Object... args) {
                getActivity().runOnUiThread(new Runnable() {
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
                        Log.d(TAG, "get data btcidr said : " + data.toString());
                        System.out.println("get data btcidr said : " + data.toString());

                    }
                });
            }
        });
//        socket.emit("get data market", message, "j",
//                new Ack() {
//                    @Override
//                    public void call(Object... args) {
//                        String response = (String)args[0];
//                    }
//                }
////                new Emitter.Listener() {
////                    @Override
////                    public void call(Object... args) {
////                        Ack ack = (Ack) args[args.length - 1];
////                        ack.call("data");
////                        JSONObject data = (JSONObject) args[0];
////                        Log.d(TAG, "run: " + data.toString());
////
////                    }
////                }
//        );
    }

    private void addMessage(String message) {
        mMessages.add(new Message.Builder(Message.TYPE_MESSAGE)
                .message(message).build()
        );

        mAdapter.setupMessageData(mMessages);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mAdapter = new MessageAdapter(mMessages);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        socket.disconnect();
    }
}
