package com.wildanka.websocketmvvm;

import android.app.Application;

import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import java.net.URISyntaxException;

public class WebSocketMVVMApplication extends Application {
    public static Socket socket;

    @Override
    public void onCreate() {
        super.onCreate();
        //setting up the connection
        {
            try {
                WebSocketMVVMApplication.socket = IO.socket("http://192.168.88.8:5000/");
            } catch (URISyntaxException e) {
//            e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
        //make connection with the socket
        socket.connect();
    }

    public static Socket getSocket() {
        return WebSocketMVVMApplication.socket;
    }
}
