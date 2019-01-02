package com.mobile.android;

import android.content.Context;
import android.support.multidex.MultiDex;

import com.mobile.hyoukalibrary.base.BaseApplication;
import com.mobile.hyoukalibrary.utils.SPUtil;
import com.mobile.android.entity.User;
import com.mobile.android.utils.Constant;
import com.mobile.android.utils.CrashHandler;
import com.zhy.autolayout.config.AutoLayoutConifg;

/**
 * @author chenliangzhi
 * @date 2018/5/15
 * @describe
 */

public class SupervisorApp extends BaseApplication {
    private static User user;

    public static User getUser() {
        if (user != null) {
            return user;
        } else {
            if (SPUtil.contains(getInstance(), Constant.USER)) {
                user = SPUtil.getObject(getInstance(), Constant.USER, User.class);
            }
        }
        return user == null ? new User() : user;
    }

    public static void setUser(User user) {
        SupervisorApp.user = user;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //自动布局
        AutoLayoutConifg.getInstance().useDeviceSize();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void setDefaultUncaughtExceptionHandler() {
        Thread.setDefaultUncaughtExceptionHandler(new CrashHandler(this));
    }
}
