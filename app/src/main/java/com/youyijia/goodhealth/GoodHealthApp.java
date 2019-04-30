package com.youyijia.goodhealth;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.text.TextUtils;

import com.tencent.bugly.crashreport.CrashReport;
import com.youyijia.goodhealth.entity.User;
import com.youyijia.goodhealth.utils.Constant;
import com.youyijia.goodhealth.utils.CrashHandler;
import com.youyijia.hyoukalibrary.base.BaseApplication;
import com.youyijia.hyoukalibrary.utils.SPUtil;
import com.zhy.autolayout.config.AutoLayoutConifg;

/**
 * @author chenliangzhi
 * @date 2018/5/15
 * @describe
 */

public class GoodHealthApp extends BaseApplication {
    private static User user = null;
    private boolean wifi = true;
    private boolean EnablaWifi = true;//wifi的打开与关闭
    public static GoodHealthApp mInstance;
    public static String APP_ID = "wx89bfbe26609a6b54";

    public User getUser() {
        if (user != null) {
            return user;
        } else {
            if (SPUtil.contains(getInstance(), Constant.USER)) {
                user = SPUtil.getObject(getInstance(), Constant.USER, User.class);
            }
        }
        return user == null ? new User() : user;
    }

    public void setUser(User user, Boolean isLogined) {
        GoodHealthApp.user = user;
        if (user != null) {
            user.setLogined(isLogined);
            SPUtil.setObject(this, "user", user);
        } else {
            SPUtil.remove(this, "user");
        }
    }

    public boolean isEnablaWifi() {
        return EnablaWifi;
    }

    public void setEnablaWifi(boolean enablaWifi) {
        EnablaWifi = enablaWifi;
    }

    public boolean isWifi() {
        return wifi;
    }

    public void setWifi(boolean wifi) {
        this.wifi = wifi;
    }

    public static GoodHealthApp getInstance() {
        return mInstance;
    }

    /**
     * 验证是否登录
     *
     * @return true 需要登录   false不需要登录
     */
    public boolean isNeedLogin() {
        if (user == null) {
            if (SPUtil.contains(this, "user")) {
                user = SPUtil.getObject(this, "user", User.class);
            }
        }

        if (TextUtils.isEmpty(getUser().getId()) || !getUser().isLogined()) {
            return true;
        }
        return false;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        //自动布局
        AutoLayoutConifg.getInstance().useDeviceSize();
        CrashReport.initCrashReport(getApplicationContext(), "bfdee4d1c8", false);
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
