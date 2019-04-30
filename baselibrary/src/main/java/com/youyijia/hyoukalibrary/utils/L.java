package com.youyijia.hyoukalibrary.utils;

import android.util.Log;

import com.youyijia.hyoukalibrary.base.BaseApplication;

/**
 * Log统一管理类
 */
public class L {

    private L() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    // 是否需要打印log，可以在application的onCreate函数里面初始化
    public static boolean isDebug = ApplicationUtils.isApkDebugable(BaseApplication.getInstance());
    private static final String TAG = "MWY";

    // 下面四个是默认tag的函数 
    public static void i(String msg) {
        if (isDebug) {
            msg = replaceNullMsg(msg);
            Log.i(TAG, msg);
        }
    }

    public static void d(String msg) {
        if (isDebug) {
            msg = replaceNullMsg(msg);
            Log.d(TAG, msg);
        }
    }

    public static void e(String msg) {
        if (isDebug) {
            msg = replaceNullMsg(msg);
            Log.e(TAG, msg);
        }
    }

    public static void v(String msg) {
        if (isDebug) {
            msg = replaceNullMsg(msg);
            Log.v(TAG, msg);
        }
    }

    // 下面是传入自定义tag的函数 
    public static void i(String tag, String msg) {
        if (isDebug) {
            msg = replaceNullMsg(msg);
            Log.i(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (isDebug) {
            msg = replaceNullMsg(msg);
            Log.d(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (isDebug) {
            msg = replaceNullMsg(msg);
            Log.e(tag, msg);
        }
    }

    public static void v(String tag, String msg) {
        if (isDebug) {
            msg = replaceNullMsg(msg);
            Log.v(tag, msg);
        }
    }

    private static String replaceNullMsg(String msg){
        if (msg == null){
            msg = "(Warning: log print is null!)";
        }else if ("".equals(msg.trim())){
            msg = "(Warning: log print is space!)";
        }
        return msg;
    }
}