package com.mobile.android.retrofit;


import com.google.gson.GsonBuilder;
import com.mobile.android.R;
import com.mobile.android.SupervisorApp;
import com.mobile.hyoukalibrary.OkhttpCacheInterceptor.CacheInterceptor;
import com.mobile.hyoukalibrary.base.BaseEntity;
import com.mobile.hyoukalibrary.utils.HttpsUtils;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 描述: retrofit管理类
 * --------------------------------------------
 * 工程:
 * #0000     mwy     创建日期: 2016-08-12 18:07
 * #0001     xiao    修改日期: 2016-09-09 14:46 修改static 配置配置okHttp
 * #0002     xiao    修改日期: 2017-01-05 添加HTTPS 证书 更换https 接口 证书有效期2017-12-21
 * #0003     xiao    修改日期: 2018-01-13 RX系列升级2.0  添加接口缓存。getDataToCache有缓存请求方式
 * 添加全局头部信息
 * #0004     xiao    修改日期: 2017-01-05 添加HTTPS 证书 更换https 接口 证书有效期2019-01-19
 */
public class RetrofitManager {
    private static RetrofitManager instance;
    private static Retrofit retrofit;
    private static OkHttpClient client;
    private final static int CONNECT_TIMEOUT = 60;//超时
    private final static int READ_TIMEOUT = 100;
    private final static int WRITE_TIMEOUT = 60;

    /**
     * #0001
     * 初始化OKHttpClient     \
     * 设置缓存
     * 设置超时时间
     * 设置打印日志
     */
    private static void initOkHttpClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //设置Http缓存
        Cache cache = new Cache(new File(SupervisorApp.getInstance().getCacheDir(), "HttpCache"), 1024 * 1024 * 100);
        client = new OkHttpClient.Builder()
                .cache(cache)
                .addInterceptor(new CacheInterceptor(SupervisorApp.getInstance()))
                .addInterceptor(new Interceptor() {//全局添加头部信息
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request newRequest = chain.request().newBuilder()
                                .addHeader("platform", "android")
                                .build();
                        return chain.proceed(newRequest);
                    }
                })
                .addInterceptor(interceptor)
                .retryOnConnectionFailure(true)
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                .sslSocketFactory(
                        HttpsUtils.getSSLSocketFactory_Certificate(
                                SupervisorApp.getInstance(),
                                "BKS", R.raw.youdecroation))
                .build();
    }

    private RetrofitManager() {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(BaseEntity.class, new ResponseBeanDeserializer());
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(builder.create()))
                //.addConverterFactory(new ToStringConverterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();
    }

    public static RetrofitManager getInstance() {
        if (instance == null) {
            synchronized (RetrofitManager.class) {
                initOkHttpClient();
                instance = new RetrofitManager();
            }
        }
        return instance;
    }

    public <T> T create(Class<T> service) {
        return retrofit.create(service);
    }


    public static final String NOTICE_RULE_URL = "https://m.youjuke.com/designer/designer_incentive_rules";


    //---------------------------------------线上-------------------------------------------------
//    public static String baseUrl = "https://zx.youjuke.com/supervisorapi/";


    //     ---------------------------------------线下-------------------------------------------------
    public static final String baseUrl = "http://211.152.37.158:92/";

}
