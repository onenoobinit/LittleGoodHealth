package com.mobile.android.updatebyrx2;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.mobile.hyoukalibrary.base.BaseEntity;
import com.mobile.hyoukalibrary.base.BaseObserver;
import com.mobile.hyoukalibrary.utils.L;
import com.mobile.android.SupervisorApp;
import com.mobile.android.entity.UpdateInfoBean;
import com.mobile.android.retrofit.ApiContstants;
import com.mobile.android.retrofit.RetrofitManager;
import com.mobile.android.retrofit.RetryWhenNetworkException;
import com.mobile.android.retrofit.RxSchedulers;
import com.mobile.android.retrofit.api.CommonService;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 基于此修改
 * https://github.com/shanyao0/DownLoadManager\
 */
public class UpdateManager {
    private long timeRange;
    private static UpdateManager instance;
    private UpdateDialog dialog;
    private Map<String, Object> params = new HashMap<>();

    private UpdateManager() {
    }

    public synchronized static UpdateManager getInstance() {
        if (instance == null) {
            instance = new UpdateManager();
        }
        return instance;
    }

    /**
     * 检查版本更新
     */
    public void checkUpdate(Context context) {
        params.clear();
        params.put(ApiContstants.UID, SupervisorApp.getUser().getUid());
        params.put(ApiContstants.TOKEN, SupervisorApp.getUser().getToken());
        params.put("new_code", getVersionName());
        L.i("update1", params.toString());
        RetrofitManager.getInstance().create(CommonService.class)
                .checkUpdate(params)
                .throttleFirst(1, TimeUnit.SECONDS)
                .retryWhen(new RetryWhenNetworkException(2, 500, 500))
                .compose(RxSchedulers.<BaseEntity<UpdateInfoBean>>io_main())
                .subscribe(new BaseObserver<UpdateInfoBean>() {
                    @Override
                    protected void onHandleSuccess(UpdateInfoBean updateInfoBean) {
                        L.i("update1", updateInfoBean.toString());
                    }

                    @Override
                    protected void onStatusNotSuccssed(int error_code, UpdateInfoBean updateInfoBean) {
                        super.onStatusNotSuccssed(error_code, updateInfoBean);
                        if (updateInfoBean!=null){
                            showUpdateDialog(updateInfoBean, context);
                            L.i("update1", updateInfoBean.toString());
                        }
                    }

                    @Override
                    protected void onHandleFailed(int error_code, String message) {
                    }
                });
    }

    private String getVersionName() {
        try {
            String pkName = SupervisorApp.getInstance().getPackageName();
            String versionName = SupervisorApp.getInstance().getPackageManager().getPackageInfo(
                    pkName, 0).versionName;
            return versionName;
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * 显示更新对话框
     */
    public void showUpdateDialog(UpdateInfoBean infoBean, final Context mContext) {

        dialog = new UpdateDialog(mContext);
        dialog.setRightOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //防抖动,两次点击间隔小于500ms都return;
                if (System.currentTimeMillis() - timeRange < 500) {
                    return;
                }
                dialog.setProgressVisiable(true);
                timeRange = System.currentTimeMillis();

                dialog.setRightText("正在下载");

                dialog.setRightEnable(false);
                //启动下载服务
                Intent intent = new Intent(mContext, DownLoadService.class);
                intent.putExtra("url", infoBean.getPacket_name());
                mContext.startService(intent);
            }
        });

        dialog.show();
    }

    /**
     * 更新进度条
     *
     * @param progress
     */
    public void updateProgress(int progress) {
        if (dialog == null || !dialog.isShowing() || progress <= 0) {
            L.i("updateProgress", "dialog为空或NoShowing");
            return;
        }
        dialog.setProgress(progress);
    }

    /**
     * 完成下载
     */
    public void finsh() {
        if (dialog == null || !dialog.isShowing()) {
            return;
        }
        dialog.dismiss();
        //把当前工具类 置空 ，防止updateManager 在loginActivity 关闭后 再次打开时 activity 是新的
        //而 updateManager 中 mContext 还是之前loginActivity的mContext 导致dialog 无法show出来而报错
        instance = null;
    }

    /**
     * 重试
     */
    public void retry() {
        if (dialog == null || !dialog.isShowing()) {
            return;
        }
        dialog.setRightText("重试");
        dialog.setRightEnable(true);
    }
}