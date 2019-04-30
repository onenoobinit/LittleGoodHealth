package com.youyijia.hyoukalibrary.OkhttpCacheInterceptor.Header;

import java.io.IOException;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by wangqiang on 2019/4/25.
 */
public class HhmOkInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        //获取request
        Request request = chain.request();
        //获取request的创建者builder
        Request.Builder builder = request.newBuilder();
        //从request中获取headers，通过给定的键url_name
        List<String> headerValues = request.headers("base_url");
        if (headerValues != null && headerValues.size() > 0) {
            //如果有这个header，先将配置的header删除，因此header仅用作app和okhttp之间使用
            builder.removeHeader("base_url");
            //匹配获得新的BaseUrl
            String headerValue = headerValues.get(0);
            HttpUrl newBaseUrl;
            /*if ("one".equals(headerValue)) {
                newBaseUrl = HttpUrl.parse(.BASE_URL_ONE);
            } else{
                //还是原来的地址
                newBaseUrl = HttpUrl.parse(API.BASE_URL);
            }*/
            //从request中获取原有的HttpUrl实例oldHttpUrl
            HttpUrl oldHttpUrl = request.url();
            //重建新的HttpUrl，修改需要修改的url部分
            HttpUrl newFullUrl = oldHttpUrl
                    .newBuilder()
//                    .scheme(newBaseUrl.scheme())
//                    .host(newBaseUrl.host())
//                    .port(newBaseUrl.port())
                    .build();
            //重建这个request，通过builder.url(newFullUrl).build()；
            //然后返回一个response至此结束修改
            return chain.proceed(builder.url(newFullUrl).build());
        } else {
            return chain.proceed(request);
        }
    }
}
