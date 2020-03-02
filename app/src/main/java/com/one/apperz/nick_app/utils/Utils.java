package com.one.apperz.nick_app.utils;

import android.app.AppOpsManager;
import android.content.Context;
import android.os.Process;

import static android.app.AppOpsManager.MODE_ALLOWED;

public class Utils {
    public static boolean checkPermission(Context ctx) {
        AppOpsManager appOpsManager = (AppOpsManager) ctx.getSystemService(Context.APP_OPS_SERVICE);
        int mode = appOpsManager.checkOpNoThrow(AppOpsManager.OPSTR_GET_USAGE_STATS, Process.myUid(), ctx.getPackageName());

        return mode == MODE_ALLOWED;

    }
}
