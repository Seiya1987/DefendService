package com.zero.defendservice;

import android.app.Application;
import android.app.job.JobService;
import android.content.Intent;
import android.util.Log;

/**
 * Created by zero on 16/3/15.
 */
public class MyApp extends Application {
    private static final String TAG = MyApp.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, "MyApp onCreate");
        MyJobService.scheduleService(this.getApplicationContext());
        startService(new Intent(this.getApplicationContext(), MyJobService.class));
    }
}
