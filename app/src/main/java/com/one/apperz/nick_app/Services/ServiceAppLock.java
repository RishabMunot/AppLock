package com.one.apperz.nick_app.Services;

import android.app.IntentService;
import android.content.Intent;

import androidx.annotation.Nullable;

import com.one.apperz.nick_app.broadcast.ReceverAppLock;

class ServiceAppLock extends IntentService {

    public ServiceAppLock() {
        super("ServiceAppLock");
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

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        runAppLock();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
//        BackgroundManager.getInstance().init(this).startService();

        BackgroundManager.getInstance().init(this).startAlarmManager();
        super.onTaskRemoved(rootIntent);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

    }

    @Override
    public void onDestroy() {
//        BackgroundManager.getInstance().init(this).startService();
        BackgroundManager.getInstance().init(this).startAlarmManager();
        super.onDestroy();
    }
}
