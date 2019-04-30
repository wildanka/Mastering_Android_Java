package com.wildanka.learnintentservice;

import android.app.IntentService;
import android.app.Notification;
import android.content.Intent;
import android.os.Build;
import android.os.PowerManager;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import static com.wildanka.learnintentservice.App.CHANNEL_ID;


public class ExampleIntentService extends IntentService {
    private static final String TAG = "ExampleIntentService";
    private PowerManager.WakeLock wakeLock;

    public ExampleIntentService() {
        super(TAG);
//        setIntentRedelivery(false); //equivalent to START_NOT_STICKY
        setIntentRedelivery(true); //equivalent to START_STICKY
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: ");

        //create wakelock
        PowerManager powerManager = (PowerManager) getSystemService(POWER_SERVICE);
        wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,"ExampleIntentServiceApp:Wakelock");
        wakeLock.acquire();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                    .setContentTitle("Intent Service Example")
                    .setContentText("Running ... ")
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .build();

            startForeground(1, notification);
        }
    }

    /** Each coming intent will be executed sequentially one after another on one single thread
     * if you want to use multiple thread at the same time, then you have to extend class directly,
     * and also this one will be executed on background thread
     * as the same in onStartCommand() (read: foreground) we can use intent to send the data to the servers, and to persist our background thread
     * */
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.d(TAG, "onHandleIntent: ");
        String input = intent.getStringExtra("inputExtra");
        for (int i = 0; i < 10; i++) {
            Log.d(TAG, input+" - "+i);
            SystemClock.sleep(1000);
        }
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy");

        //release the wakelock
        Log.d(TAG, "WakeLock released");
        wakeLock.release();
        super.onDestroy();
    }
}