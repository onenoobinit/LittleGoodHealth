package com.youyijia.hyoukalibrary.OkhttpCacheInterceptor;

import com.youyijia.hyoukalibrary.base.BaseEntity;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created by wangqiang on 2019/4/15.
 */
public class CookieHeadInterceptor implements Interceptor {

    private BaseEntity baseEntity;

    public CookieHeadInterceptor(BaseEntity baseEntity) {
        this.baseEntity = baseEntity;
    }


    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());
        return null;
    }
}
