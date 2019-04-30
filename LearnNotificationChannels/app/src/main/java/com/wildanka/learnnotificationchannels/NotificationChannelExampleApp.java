package com.wildanka.learnnotificationchannels;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

import java.util.Collections;

public class NotificationChannelExampleApp extends Application {
    public static final String CHANNEL_1_ID = "channelOne";
    public static final String CHANNEL_2_ID = "channelTwo";

    @Override
    public void onCreate() {
        super.onCreate();

        //setup Channels
        createNotificationChannels();
    }

    private void createNotificationChannels() {
        //check if we are on >= Oreo or lower, because notification channel class is not available below ore
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){ // API v26
            //Create Notification Channel
            NotificationChannel channelOne = new NotificationChannel(
                    CHANNEL_1_ID,
                    "Channel Satu Coy",
                    NotificationManager.IMPORTANCE_HIGH
            );
            channelOne.setDescription("this is channel one");
            channelOne.enableVibration(true);
            channelOne.enableLights(true);

            NotificationChannel channelTwo = new NotificationChannel(
                    CHANNEL_1_ID,
                    "Channel Satu Coy",
                    NotificationManager.IMPORTANCE_LOW
            );
            channelTwo.setDescription("Channel Two ini Two-h");
            channelTwo.enableVibration(true);
            channelTwo.enableLights(true);

            //put reference to NotificationManager
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channelOne);
            manager.createNotificationChannel(channelTwo);
        }
    }
}