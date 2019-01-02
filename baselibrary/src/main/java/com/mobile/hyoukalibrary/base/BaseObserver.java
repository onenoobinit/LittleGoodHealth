package com.mobile.hyoukalibrary.base;

import android.content.Intent;
import android.view.Gravity;

import com.google.gson.JsonSyntaxException;
import com.mobile.hyoukalibrary.utils.L;
import com.mobile.hyoukalibrary.utils.NetworkUtils;
import com.mobile.hyoukalibrary.utils.ToastUtil;

import java.net.UnknownHostException;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;

/**
 * 描述: Observer的基础类
 * --------------------------------------------
 * 工程:
 * #0000     mwy     创建日期: 2017-06-06 14:17
 * #0001     mwy     2018-01-22 11:28 改为继承自DisposableObserver，方便手动取消请求，
 * 如需手动取消请求，改subscribe为subscribeWith就会返回BaseObserver对象，
 * 调用dispose()取消请求，也可以在onNext方法内dispose()
 */

public abstract class BaseObserver<T> extends DisposableObserver<BaseEntity<T>> {
    private final int SUCCESS_CODE = 200;
    private final int ERROR_CODE = 400;
    private final int NOT_LOGIN_CODE = 020;
    private final int TOKEN_INVALID_CODE = 4001;


    @Override
    public void onNext(@NonNull BaseEntity<T> tBaseEntity) {
        if (SUCCESS_CODE == (tBaseEntity.getStatus())) {
            T t = tBaseEntity.getData();
            onHandleSuccess(t);
        } else if (ERROR_CODE == (tBaseEntity.getStatus())) {
            onHandleFailed(tBaseEntity.getStatus(), tBaseEntity.getMessage());
            if (NOT_LOGIN_CODE == (tBaseEntity.getError()) || TOKEN_INVALID_CODE == (tBaseEntity.getError())) {
                BaseApplication.getInstance().sendBroadcast(new Intent("android.content.BroadcastReceiver.ACTION_TO_LOGIN"));
            }
            T t = tBaseEntity.getData();
            onStatusNotSuccssed(tBaseEntity.getStatus(),t);
        } else {
            L.e("BaseObserver", tBaseEntity.getStatus() + "--" + tBaseEntity.getMessage());
            ToastUtil.show(BaseApplication.getInstance(), "服务器异常，请稍后再试", Gravity.CENTER);

        }
        onFinally();
    }

    @Override
    public void onError(@NonNull Throwable e) {
        e.printStackTrace();
        if (e instanceof JsonSyntaxException) {
            ToastUtil.show(BaseApplication.getInstance(), "数据异常，请稍后再试", Gravity.CENTER);
        } else if (!(e instanceof UnknownHostException)) {
            ToastUtil.show(BaseApplication.getInstance(), "服务器异常，请稍后再试", Gravity.CENTER);
        }
        if (!NetworkUtils.isConnected(BaseApplication.getInstance())) {
            ToastUtil.show(BaseApplication.getInstance(), "网络连接不可用，检查你的网络设置", Gravity.CENTER);
        }
        onFinally();
    }

    @Override
    public void onComplete() {
    }

    protected abstract void onHandleSuccess(T t);

    protected void onFinally() {
    }

    protected void onHandleFailed(int error_code, String message) {
        if (error_code==TOKEN_INVALID_CODE){
            ToastUtil.showLong(BaseApplication.getInstance(),message,Gravity.CENTER);
        }else {
            ToastUtil.show(BaseApplication.getInstance(), message, Gravity.CENTER);
        }
    }
    protected void onStatusNotSuccssed(int error_code, T t) {

    }

}
