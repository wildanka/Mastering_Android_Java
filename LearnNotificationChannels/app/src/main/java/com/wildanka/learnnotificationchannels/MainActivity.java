package com.wildanka.learnnotificationchannels;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import static com.wildanka.learnnotificationchannels.NotificationChannelExampleApp.CHANNEL_1_ID;
import static com.wildanka.learnnotificationchannels.NotificationChannelExampleApp.CHANNEL_2_ID;

public class MainActivity extends AppCompatActivity {
    //we create NotifManagerCompat because it wraps a number of notification manager, and suitable for BackwardsCompatibility,
    // but cannot create notification channel, that's why we need to use different notification channels
    private NotificationManagerCompat notificationManagerCompat;
    private EditText etTitle;
    private EditText etMessage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        notificationManagerCompat = NotificationManagerCompat.from(this);
        etTitle = findViewById(R.id.et_title_channel_1);
        etMessage = findViewById(R.id.et_message_channel_1);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void sendOnChannel1(View v){
        String title = etTitle.getText().toString();
        String message = etMessage.getText().toString();

        //create intent to be execute after the notification is clicked
        Intent activityIntent = new Intent(this, MainActivity.class);
        //because we directing the intent to the mainActivity, we don't need to use any flags yet
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, activityIntent, 0);

        Intent broadcastIntent = new Intent(this, NotificationReceiver.class);
        broadcastIntent.putExtra("toastBroadcastMessage",message);
        PendingIntent actionIntent = PendingIntent.getBroadcast(this, 0, broadcastIntent, PendingIntent.FLAG_UPDATE_CURRENT);


        Notification notification = new NotificationCompat.Builder(this, CHANNEL_1_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setColor(Color.CYAN)
                .setAutoCancel(true)
                .setContentIntent(contentIntent)
                .setOnlyAlertOnce(true)
                .addAction(R.mipmap.ic_launcher, "SHOw TOAST", actionIntent)
                .build();
        notificationManagerCompat.notify(1, notification);
    }

    public void sendOnChannel2(View v){
        long timestamp1 = 1124324244;
        long timestamp2 = 1124324245;
        long timestamp3 = 1124324246;
        long timestamp4 = 1124324247;
        String title = etTitle.getText().toString();
        String message = etMessage.getText().toString();
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_2_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setStyle(new NotificationCompat.MessagingStyle("Me")
                        .setConversationTitle("Team Lunch")
                        .addMessage("Hi", timestamp1, "User 1") // Pass in null for user.
                        .addMessage("What's up?", timestamp2, "Coworker")
                        .addMessage("Not much", timestamp3, "User 2")
                        .addMessage("How about lunch?", timestamp4, "Coworker"))
                .build();

        notificationManagerCompat.notify(2, notification);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}