package com.wildanka.learnjobscheduler;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.pm.ComponentInfo;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private JobScheduler scheduler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void startJobScheduler(View view) {
        //create a componentInfo, but a componentInfu needs a ComponentName
        ComponentName componentName = new ComponentName(this,ExampleJobScheduler.class);
        JobInfo info = new JobInfo.Builder(1,componentName)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
//                .setRequiresCharging(true)
                .setPersisted(true) //keep the job alive even if the device is rebooted
                .setPeriodic(15 * 60 *1000)
                .build();

        scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
//            assert scheduler != null;
        scheduler.schedule(info);
        int resCode = scheduler.schedule(info);
        if (resCode == JobScheduler.RESULT_SUCCESS){
            Log.d(TAG, "startJobScheduler: Job Scheduled");
        }else{
            Log.d(TAG, "startJobScheduler: Job Schedule Failed");
        }

    }

    public void cancelJobScheduler(View view) {
        scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        scheduler.cancel(1);
        Log.d(TAG, "Job cancelled");
    }
}
