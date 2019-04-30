package com.youyijia.goodhealth.retrofit;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;

/**
 * 描述: 链接重试
 * --------------------------------------------
 * 工程:
 * #0000     mwy     创建日期: 2016-12-16 10:19
 */

public class RetryWhenNetworkException implements Function<Observable<Throwable>, ObservableSource<?>> {

    private int count = 3;
    private long delay = 500;
    private long increaseDelay = 0;

    public RetryWhenNetworkException() {

    }

    public RetryWhenNetworkException(int count, long delay) {
        this.count = count;
        this.delay = delay;
    }

    public RetryWhenNetworkException(int count, long delay, long increaseDelay) {
        this.count = count;
        this.delay = delay;
        this.increaseDelay = increaseDelay;
    }

    @Override
    public ObservableSource<?> apply(@NonNull Observable<Throwable> throwableObservable) throws Exception {
        return throwableObservable
                .zipWith(Observable.range(1, count + 1), new BiFunction<Throwable, Integer, Wrapper>() {
                    @Override
                    public Wrapper apply(@NonNull Throwable throwable, @NonNull Integer integer) throws Exception {
                        return new Wrapper(throwable, integer);
                    }
                }).flatMap(new Function<Wrapper, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(@NonNull Wrapper wrapper) throws Exception {
                        if ((wrapper.throwable instanceof ConnectException
                                || wrapper.throwable instanceof SocketTimeoutException
                                || wrapper.throwable instanceof UnknownHostException
                                || wrapper.throwable instanceof TimeoutException)
                                && wrapper.index < count + 1) { //如果超出重试次数也抛出错误，否则默认是会进入onCompleted
                            return Observable.timer(delay + (wrapper.index - 1) * increaseDelay, TimeUnit.MILLISECONDS);
                        }
                        return Observable.error(wrapper.throwable);
                    }
                });
    }

    private class Wrapper {
        private int index;
        private Throwable throwable;

        public Wrapper(Throwable throwable, int index) {
            this.index = index;
            this.throwable = throwable;
        }
    }

}