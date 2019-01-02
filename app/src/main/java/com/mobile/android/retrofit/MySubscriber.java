package com.mobile.android.retrofit;


import org.reactivestreams.Subscriber;

/**
 * Created by Administrator on 2017/5/23.
 */

public abstract class MySubscriber<T> implements Subscriber<T> {
    @Override
    public void onComplete() {
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
    }

    @Override
    public void onNext(T t) {

    }
}
