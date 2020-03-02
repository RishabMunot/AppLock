package com.one.apperz.nick_app.Services;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.core.app.JobIntentService;

import com.one.apperz.nick_app.broadcast.ReceverAppLock;

class ServiceAppLockJobIntent extends JobIntentService {


    public static final int JOB_ID = 15462;

    public static void enqueueWork(Context context, Intent work) {
        enqueueWork(context, ServiceAppLockJobIntent.class, JOB_ID, work);
    }

    @Override
    protected void onHandleWork(@NonNull Intent intent) {
        runAppLock();
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
//        BackgroundManager.getInstance().init(this).startService();

        BackgroundManager.getInstance().init(this).startAlarmManager();
        super.onTaskRemoved(rootIntent);
    }

    @Override
    public void onDestroy() {
//        BackgroundManager.getInstance().init(this).startService();

        BackgroundManager.getInstance().init(this).startAlarmManager();
        super.onDestroy();
    }

    public void runAppLock() {
        long endTime = System.currentTimeMillis() + 210;
        while (System.currentTimeMillis() < endTime) {
            synchronized (this) {
                try {
                    Intent intent = new Intent(this, ReceverAppLock.class);
                    sendBroadcast(intent);
                    wait(endTime - System.currentTimeMillis());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
