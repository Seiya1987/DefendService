package com.zero.defendservice;

import android.annotation.TargetApi;
import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
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
    public boolean onStartJob(JobParameters params) {
        Log.e(TAG, "MyJobService onStartJob");
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
        builder.setPeriodic(60 * 1000);     //设置1分钟执行一次
        js.cancel(JOB_ID);
        js.schedule(builder.build());
    }
}
