package com.youyijia.goodhealth.app.login;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.youyijia.goodhealth.GoodHealthApp;
import com.youyijia.goodhealth.retrofit.RetrofitManager;
import com.youyijia.hyoukalibrary.rxbus.RxBus;
import com.youyijia.hyoukalibrary.utils.SPUtil;

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
                RetrofitManager.clearCook();
                SPUtil.remove(GoodHealthApp.getInstance(), "TOKEN");
                RxBus.get().post("MeFragment.loginOut", "");
                Intent intent1 = new Intent(context, LoginActivity.class);
                intent1.putExtra("authId", "");
                context.startActivity(intent1);
            default:
                break;
        }
    }

}
