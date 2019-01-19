package com.wildanka.learnjobscheduler;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class ExampleJobScheduler extends JobService {
    private static final String TAG = "ExampleJobScheduler";
    private boolean jobCancelled = false; //for learning purpose

    /**
     * Override this method with the callback logic for your job. Any such logic needs to be performed on a separate thread, as this function is executed on your application's main thread.
     * @param params JobParameters: Parameters specifying info about this job, including the extras bundle you optionally provided at job-creation time.
     * @return boolean True if your service needs to process the work (on a separate thread).
     * False if there's no more work to be done for this job.
     */
    @Override
    public boolean onStartJob(JobParameters params) {
        Log.d(TAG, "onStartJob: ");
        anExampleOfBackgroundWork(params);

        return true;
    }

    private void anExampleOfBackgroundWork(final JobParameters params) {
        //a dummy background process
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 15; i++) {
                    Log.d(TAG, "run result: "+i);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
                Log.d(TAG, "run: jobFinished");
                /**
                 * Callback to inform the JobManager you've finished executing. This can be called from any thread, as it will ultimately be run on your application's main thread. When the system receives this message it will release the wakelock being held.
                 * You can specify post-execution behaviour to the scheduler here with needsReschedule
                 */
                jobFinished(params, false);
            }
        }).start();

    }


    @Override
    public boolean onStopJob(JobParameters params) {
        Log.d(TAG, "onStopJob: Job cancelled before completion");
        jobCancelled = true;
        return true; //do we want to create a reschedule?
    }
}
