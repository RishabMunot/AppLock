package com.one.apperz.nick_app.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.one.apperz.nick_app.PatternLockActivity;
import com.one.apperz.nick_app.utils.Utils;

public class ReceverAppLock extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Utils utils = new Utils(context);
        String appRunning = utils.getLauncheerTopApp();

        if (utils.isLock(appRunning)) {
            if (!appRunning.equals(utils.getLastApp())) {
                utils.clearLastApp();
                utils.setLastApp(appRunning);
                Intent i = new Intent(context, PatternLockActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                i.putExtra("broadcast_reciever", "broadcast_reciever");
                context.startActivity(i);
            }
        }
    }
}
