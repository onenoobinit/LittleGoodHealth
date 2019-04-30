package com.youyijia.hyoukalibrary.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.provider.Settings;
import android.util.Log;

import java.lang.reflect.Method;
import java.util.List;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

/**
 * 描述:检查网络工具
 * --------------------------------------------
 * 工程:
 * #0000     mwy     创建日期: 2016-11-24 16:17
 */

public class NetworkUtils {

    /**
     * 判断网络是否连接
     *
     * @param context
     * @return
     */
    public static boolean isConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (null != connectivityManager) {
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected()) {
                if (networkInfo.getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
        }

        return false;
    }

    public static boolean isWIFI(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkINfo = cm.getActiveNetworkInfo();
        if (networkINfo != null
                && networkINfo.getType() == ConnectivityManager.TYPE_WIFI) {
            return true;
        }
        return false;
    }

    /**
     * 描述：得到所有的WiFi列表.
     * 此方法需要如下两个权限
     */
    public static List<ScanResult> getScanResults(Context context) {
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        List<ScanResult> list = null;
        //开始扫描WiFi
        @SuppressLint("MissingPermission") boolean f = wifiManager.startScan();
        if (!f) {
            getScanResults(context);
        } else {
            // 得到扫描结果
            list = wifiManager.getScanResults();
        }

        return list;
    }

    /**
     * 描述：获取连接的WIFI信息.
     * 此方法需要如下权限
     */
    public static WifiInfo getConnectionInfo(Context context) {
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        return wifiManager.getConnectionInfo();
    }

    /**
     * 判断当前网络是否是移动数据网络.
     *
     * @param context the context
     * @return boolean
     */
    public static boolean isMobileConnection(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetInfo != null
                && activeNetInfo.getType() == ConnectivityManager.TYPE_MOBILE;
    }

    /**
     * 打开网络设置界面
     */
    public static void openSettingNetActivity(Context context) {
        Intent intent = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
        intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    /**
     * 开启或关闭数据开关
     * 5.0以上没用
     * @param cxt
     * @param state
     */
    public static void setDataConnectionState(Context cxt, boolean state) {
        ConnectivityManager connectivityManager = null;
        Class connectivityManagerClz = null;
        try {
            connectivityManager = (ConnectivityManager) cxt
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            connectivityManagerClz = connectivityManager.getClass();
            Method[] methods = connectivityManagerClz.getMethods();
            for (Method method : methods) {
                Log.i("Android数据连接管理", method.toGenericString());
            }
            Method method = connectivityManagerClz.getMethod(
                    "setMobileDataEnabled", new Class[]{boolean.class});
            method.invoke(connectivityManager, state);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
