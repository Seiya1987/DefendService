package com.zero.defendservice;

import android.annotation.TargetApi;
import android.app.Service;
import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

/**
 * Created by zero on 16/3/15.
 */
@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class MyJobService extends JobService {

    private static final int JOB_ID = 1000;
    private static final String TAG = MyJobService.class.getSimpleName();

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        Log.e(TAG, "MyJobService onStartCommand " + this.toString());
        return Service.START_STICKY;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, "MyJobService onCreate "  + this.toString());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "MyJobService onDestroy "  + this.toString());
    }

    @Override
    public boolean onStartJob(JobParameters params) {
        Log.e(TAG, "MyJobService onStartJob "  + this.toString());
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (true) {
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    Log.e(TAG, "running");
//                }
//            }
//        }).start();
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Log.e(TAG, "MyJobService onStopJob");
        return false;
    }

    public static void scheduleService(Context context) {
        JobScheduler js = (JobScheduler) context.getSystemService(Context.JOB_SCHEDULER_SERVICE);
        JobInfo.Builder builder = new JobInfo.Builder(JOB_ID, new ComponentName(context.getPackageName(), MyJobService.class.getName()));
        builder.setPersisted(true);     //设置开机启动
        builder.setPeriodic(3 * 1000);     //设置1分钟执行一次
        js.cancel(JOB_ID);
        js.schedule(builder.build());
    }
}
