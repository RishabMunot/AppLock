package com.one.apperz.nick_app.Services;

import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import com.one.apperz.nick_app.broadcast.RestartServiceWhenStopped;

public class BackgroundManager {
    public static final int period = 15 * 10000;
    public static final int ALARM_ID = 159874;

    public static BackgroundManager instance;
    private Context context;

    public static BackgroundManager getInstance() {

        if (instance == null) {
            instance = new BackgroundManager();
        }

        return instance;
    }

    public BackgroundManager init(Context c) {
        context = c;
        return this;
    }

    public boolean isServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo serviceInfo : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(serviceInfo.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    public void startService() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (!isServiceRunning(ServiceAppLockJobIntent.class)) {
                Intent intent = new Intent(context, ServiceAppLockJobIntent.class);
                ServiceAppLockJobIntent.enqueueWork(context, intent);
            }
        } else {
            if (!isServiceRunning(ServiceAppLock.class)) {
                context.startService(new Intent(context, ServiceAppLock.class));
            }
        }
    }

    public void stopService(Class<?> serviceClass) {
        if (!isServiceRunning(serviceClass)) {
            context.stopService(new Intent(context, serviceClass));
        }
    }

    public void startAlarmManager() {
        Intent intent = new Intent(context, RestartServiceWhenStopped.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, ALARM_ID, intent, 0);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + period, pendingIntent);
    }

    public void stopAlarm() {
        Intent intent = new Intent(context, RestartServiceWhenStopped.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, ALARM_ID, intent, 0);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(pendingIntent);

    }

}
