package com.youyijia.hyoukalibrary.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import com.tencent.smtt.sdk.QbSdk;
import com.tencent.smtt.sdk.TbsDownloader;
import com.youyijia.hyoukalibrary.base.BaseApplication;

/**
 * 描述:
 * <p>
 * Tbs预加载 优化TBS WebView 启动黑屏
 * 工程:
 * #0000    Tian Xiao    2016-11-23 16:44
 */
public class PreLoadX5Service extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initX5();
        preinitX5WebCore();
    }

    private void initX5() {
        TbsDownloader.needDownload(this, false);
        QbSdk.setDownloadWithoutWifi(true);
        QbSdk.initX5Environment(BaseApplication.getInstance(),cb);
        }

    QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {

        @Override
        public void onViewInitFinished(boolean arg0) {
        }

        @Override
        public void onCoreInitFinished() {
            preinitX5WebCore();
        }
    };
    private void preinitX5WebCore() {

        if (!QbSdk.isTbsCoreInited()) {

            if (!QbSdk.isTbsCoreInited()) {
                // preinit只需要调用一次，如果已经完成了初始化，那么就直接构造view
                QbSdk.preInit(BaseApplication.getInstance(), null);// 设置X5初始化完成的回调接口
            }
            com.tencent.smtt.sdk.WebView webView = new com.tencent.smtt.sdk.WebView(this);
            int width = webView.getView().getWidth();
        }
    }
}
