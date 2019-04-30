package com.youyijia.goodhealth.retrofit;


import android.util.Base64;

import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.google.gson.GsonBuilder;
import com.youyijia.goodhealth.GoodHealthApp;
import com.youyijia.goodhealth.R;
import com.youyijia.hyoukalibrary.OkhttpCacheInterceptor.CacheInterceptor;
import com.youyijia.hyoukalibrary.base.BaseApplication;
import com.youyijia.hyoukalibrary.base.BaseEntity;
import com.youyijia.hyoukalibrary.utils.HttpsUtils;
import com.youyijia.hyoukalibrary.utils.SPUtil;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Cookie;
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
    private static ClearableCookieJar cookieJar;
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
        Cache cache = new Cache(new File(GoodHealthApp.getInstance().getCacheDir(), "HttpCache"), 1024 * 1024 * 100);
        cookieJar = new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(BaseApplication.getInstance()) {
            @Override
            public void saveAll(Collection<Cookie> cookies) {
                super.saveAll(cookies);
                if (cookies.size() != 0 && cookies.size() == 1) {
                    String cookie = cookies.toString();
                    String[] split = cookie.split(";");
                    String inkey = split[0];
                    String[] split1 = inkey.split("=");
                    String key = split1[1];
//                    String getkey = getkey(key);
                    String enUid = new String(Base64.encode(key.getBytes(), Base64.DEFAULT));
                    SPUtil.setObject(GoodHealthApp.getInstance(), "TOKEN", enUid);
                }
            }
        });
        client = new OkHttpClient.Builder()
                .cache(cache)
                .cookieJar(cookieJar)
                .addInterceptor(new CacheInterceptor(GoodHealthApp.getInstance()))
                .addInterceptor(new Interceptor() {//全局添加头部信息
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request newRequest = chain.request().newBuilder()
                                .addHeader("User-Agent", "kzxw_android")
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
                                GoodHealthApp.getInstance(),
                                "BKS", R.raw.apphome))
                .build();
    }

    public static void clearCook() {
        cookieJar.clear();
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


    //---------------------------------------线上-------------------------------------------------
//    public static String baseUrl = "https://app.uhealth-online.com.cn/";


    //     ---------------------------------------线下-------------------------------------------------
    public static final String baseUrl = "http://192.168.8.20:8100/";


}
