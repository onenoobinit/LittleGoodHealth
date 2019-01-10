package com.mobile.hyoukalibrary.base;

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

public abstract class BaseObserver extends DisposableObserver<BaseEntity> {
    @Override
    public void onNext(@NonNull BaseEntity tBaseEntity) {
        if (tBaseEntity != null) {
            onHandleSuccess(tBaseEntity);
        } else {
            L.e("BaseObserver", tBaseEntity.getGeneralErrMsg() + "--" + tBaseEntity.getErrMsg());
            ToastUtil.show(BaseApplication.getInstance(), "服务器异常，请稍后再试", Gravity.CENTER);
        }
        onFinally();
    }

    @Override
    public void onError(@NonNull Throwable e) {
        e.printStackTrace();
        //有数据和没有数据时接口返回格式有问题 TODO
        if (e instanceof JsonSyntaxException) {
            ToastUtil.show(BaseApplication.getInstance(), "数据异常，请稍后再试", Gravity.CENTER);
        } else
        if (!(e instanceof UnknownHostException)) {
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

    protected abstract void onHandleSuccess(BaseEntity baseEntity);

    protected void onFinally() {
    }

    /*protected void onHandleFailed(String message) {
        ToastUtil.show(BaseApplication.getInstance(), message, Gravity.CENTER);
    }*/

    protected void onStatusNotSuccssed(BaseEntity baseEntity) {

    }

}
