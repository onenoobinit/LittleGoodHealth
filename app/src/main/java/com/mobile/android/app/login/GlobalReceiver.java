package com.mobile.android.app.login;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.mobile.hyoukalibrary.manager.ActivityManager;
import com.mobile.hyoukalibrary.utils.L;
import com.mobile.hyoukalibrary.utils.SPUtil;
import com.mobile.android.MainActivity;
import com.mobile.android.SupervisorApp;
import com.mobile.android.utils.Constant;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

/**
 * @author Administrator
 * @date 2018/1/20
 */

public class GlobalReceiver extends BroadcastReceiver {

    public static final String ACTION_TO_LOGIN = "android.content.BroadcastReceiver.ACTION_TO_LOGIN";

    @Override
    public void onReceive(Context context, Intent intent) {
        switch (intent.getAction()) {
            //去登陆
            case ACTION_TO_LOGIN:
                SPUtil.put(context, Constant.IS_LOGIN, false);
                SPUtil.remove(SupervisorApp.getInstance(), "user");
                L.i("tologin","tologin");
                ActivityManager.getInstance().finishActivity(MainActivity.class);
                Intent intent1 = new Intent(context, LoginActivity.class);
                intent1.setFlags(FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent1);
            default:
                break;
        }
    }

}
