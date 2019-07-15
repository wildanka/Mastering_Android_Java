package com.wildanka.learnstackwidget.service;

import android.annotation.TargetApi;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.os.Build;
import android.widget.RemoteViews;

import com.wildanka.learnstackwidget.R;
import com.wildanka.learnstackwidget.SimpleWidget.NumberGenerator;
import com.wildanka.learnstackwidget.SimpleWidget.RandomNumbersWidget;

import androidx.annotation.RequiresApi;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class UpdateWidgetService extends JobService {
    @Override
    public boolean onStartJob(JobParameters params) {
        AppWidgetManager manager = AppWidgetManager.getInstance(this);
        RemoteViews view = new RemoteViews(getPackageName(), R.layout.random_numbers_widget);
        ComponentName theWidget = new ComponentName(this, RandomNumbersWidget.class);
        String lastUpdate = "Random: " + NumberGenerator.Generate(100);
        view.setTextViewText(R.id.appwidget_text, lastUpdate);
        manager.updateAppWidget(theWidget, view);
        jobFinished(params, false);
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }
}