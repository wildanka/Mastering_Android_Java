package com.wildanka.learnforegroundservice;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;

import static com.wildanka.learnforegroundservice.App.CHANNEL_ID;

public class ExampleService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    //this method will be triggered when we start our service
//    be careful as onStartCommand is running on mainThread, so it can affect your app performance
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String inString = intent.getStringExtra("inputExtra");

        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("ExampleService")
                .setContentText(inString)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .build();

//        if we don't start our service in foreground, our service will be killed in about one minutes.
//        for example, just try to comment startForeground line below and see on the running services via developer options menu in your android emulator/smartphone
        startForeground(1, notification);


///        a heavy background task
        //well actually if we want to stop our service automatically (without calling from outside),
        // we can use stopSelf() so the service will stop itself from working on the background
        stopSelf();

        return START_NOT_STICKY; //not sticky so this service will only create one service if there is a change or modification
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    //this is mandatory, but this is only for binding purpose so other components can communicate by binding, but the method that matters
    // is onStartCommand()
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}