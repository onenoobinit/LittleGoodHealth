package com.mobile.hyoukalibrary.utils;

import android.content.Context;
import android.support.annotation.UiThread;
import android.view.Gravity;
import android.widget.Toast;

/**
 * Toast统一管理类
 */
public class ToastUtil {

    public static boolean isShow = true;
    private static Toast mToast = null;

    /*cannot be instantiated*/
    @UiThread
    private ToastUtil() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 短时间显示Toast
     *
     * @param context
     * @param message
     */
    @UiThread
    public static void showShort(Context context, CharSequence message) {
        if (isShow) {
            if (mToast == null) {
                mToast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
            } else {
                mToast.setText(message);
                mToast.setDuration(Toast.LENGTH_SHORT);
            }
            mToast.setGravity(Gravity.BOTTOM, 0, 50);
            mToast.show();
        }
    }

    /**
     * 短时间显示Toast
     *
     * @param context
     * @param message
     */
    @UiThread
    public static void showShort(Context context, int message) {
        if (isShow) {
            if (mToast == null) {
                mToast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
            } else {
                mToast.setText(message);
                mToast.setDuration(Toast.LENGTH_SHORT);
            }
            mToast.setGravity(Gravity.BOTTOM, 0, 50);
            mToast.show();
        }
    }

    /**
     * 长时间显示Toast
     *
     * @param context
     * @param message
     */
    @UiThread
    public static void showLong(Context context, CharSequence message) {
        if (isShow) {
            if (mToast == null) {
                mToast = Toast.makeText(context, message, Toast.LENGTH_LONG);
            } else {
                mToast.setText(message);
                mToast.setDuration(Toast.LENGTH_LONG);
            }
            mToast.setGravity(Gravity.BOTTOM, 0, 50);
            mToast.show();
        }
    }

    /**
     * 长时间显示Toast
     *
     * @param context
     * @param message
     */
    @UiThread
    public static void showLong(Context context, int message) {
        if (isShow) {
            if (mToast == null) {
                mToast = Toast.makeText(context, message, Toast.LENGTH_LONG);
            } else {
                mToast.setText(message);
                mToast.setDuration(Toast.LENGTH_LONG);
            }
            mToast.setGravity(Gravity.BOTTOM, 0, 50);
            mToast.show();
        }
    }
    /**
     * 长时间显示Toast
     *
     * @param context
     * @param message
     */
    @UiThread
    public static void showLong(Context context, String message,int duration) {
        if (isShow) {
            if (mToast == null) {
                mToast = Toast.makeText(context, message, Toast.LENGTH_LONG);
            } else {
                mToast.setText(message);
                mToast.setDuration(Toast.LENGTH_LONG);
            }
            mToast.setGravity(duration, 0, 0);
            mToast.show();
        }
    }

    /**
     * 自定义显示Toast时间
     *
     * @param context
     * @param message
     * @param duration
     */
    @UiThread
    public static void show(Context context, int message, int duration) {
        if (isShow) {
            if (mToast == null) {
                mToast = Toast.makeText(context, message, duration);
            } else {
                mToast.setText(message);
                mToast.setDuration(duration);
            }
            mToast.setGravity(Gravity.BOTTOM, 0, 50);
            mToast.show();
        }
    }

    @UiThread
    public static void show(Context context, String content, int Grivaty) {
        if (isShow) {
            if (mToast == null) {
                mToast = Toast.makeText(context, content, Toast.LENGTH_SHORT);
            } else {
                mToast.setText(content);
                mToast.setDuration(Toast.LENGTH_SHORT);
            }
            mToast.setGravity(Grivaty, 0, 0);
            mToast.show();
        }
    }

    /**
     * 默认短时间show
     *
     * @param context
     * @param content
     */
    @UiThread
    public static void show(Context context, String content) {
        if (isShow) {
            if (mToast == null) {
                mToast = Toast.makeText(context, content, Toast.LENGTH_SHORT);
            } else {
                mToast.setText(content);
                mToast.setDuration(Toast.LENGTH_SHORT);
            }
            mToast.setGravity(Gravity.BOTTOM, 0, 50);
            mToast.show();
        }
    }

}