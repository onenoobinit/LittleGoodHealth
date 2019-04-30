package com.youyijia.hyoukalibrary.base;

import android.app.Application;
import android.text.TextUtils;

import com.baidu.mobstat.StatService;
import com.youyijia.hyoukalibrary.sonic.SonicRuntimeImpl;
import com.youyijia.hyoukalibrary.utils.ApplicationUtils;
import com.youyijia.hyoukalibrary.utils.L;
import com.youyijia.hyoukalibrary.utils.MetaDataUtil;
import com.youyijia.hyoukalibrary.utils.SDCardUtil;
import com.youyijia.hyoukalibrary.utils.ToastUtil;
import com.tencent.sonic.sdk.SonicConfig;
import com.tencent.sonic.sdk.SonicEngine;

import java.io.File;


/**
 * 描述: Application 的基类
 * --------------------------------------------
 * 工程:
 * #0000     mwy     创建日期: 2016-07-14 11:04
 */
public abstract class BaseApplication extends Application {

    public static BaseApplication mInstance;

    /**
     * 是否是测试模式,打包上线APK的时候一定要更改
     *
     */
    private static boolean isDebug = true;

    /**
     * 首页按返回键的次数
     */
    public static int BackKeyCount = 0;

    /**
     * 是否使用自动堆栈管理
     */
    public static boolean isUseActivityManager = true;


    /**
     * 是否保存错误日志
     */
    public static boolean isSaveErrorLog = true;


    /**
     * SD卡项目地址
     */
    public static String fileName = "designer";
    private String channel;


    /**
     * 设置是否是测试模式
     */
    @SuppressWarnings("static-access")
    public void setIsDebug(boolean isDebug) {
        this.isDebug = isDebug;
    }

    /**
     * 获取当前是否是测试模式。true为测试环境，false为正式环境
     *
     * @return
     */
    public static boolean getIsDebug() {
        return isDebug;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        StatService.start(this);
        mInstance = this;
        // 设置工程是否打印LOG
        L.isDebug = isDebug;
        //配置时候显示toast
        ToastUtil.isShow = true;
        //设置SD卡 项目目录
        createAPPDir();
        //配置程序异常退出,异常捕获处理类
        setDefaultUncaughtExceptionHandler();
        // 必要时初始化音速引擎，或者U在应用程序创建时可以这样做
        if (!SonicEngine.isGetInstanceAllowed()) {
            SonicEngine.createInstance(new SonicRuntimeImpl(this), new SonicConfig.Builder().build());
        }
    }

    /**
     * 创建SD卡 项目目录
     */
    public void createAPPDir() {
        String sdFilePath = ApplicationUtils.getSDFilePath(this);
        fileName = ApplicationUtils.getPackgeName(this);
        if (SDCardUtil.isSDCardEnable()) {
            File file = new File(sdFilePath);
            if (!file.exists()) {
                file.mkdirs();
            }
        }
    }

    /**
     * 配置程序异常退出,异常捕获处理类
     */
    public abstract void setDefaultUncaughtExceptionHandler();

    public static BaseApplication getInstance() {
        return mInstance;
    }

    /**
     * 获取渠道号
     *
     * @return
     */
    public String getChannel() {
        if (TextUtils.isEmpty(channel)) {
            channel = MetaDataUtil.getFromApplication(this, "BaiduMobAd_CHANNEL");
            //根据后台需求 去掉渠道号下划线
            if (!TextUtils.isEmpty(channel) && channel.contains("_")) {
                channel = channel.replace("_", "");
            }
        }
        return channel;
    }
}
