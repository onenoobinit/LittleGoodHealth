package com.youyijia.goodhealth.updatebyrx2;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.widget.Toast;

import com.google.gson.Gson;
import com.youyijia.hyoukalibrary.utils.L;
import com.youyijia.goodhealth.R;

import java.io.File;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Created by zs on 2016/7/8.
 */
public class DownLoadService extends Service {

    /**
     * 目标文件存储的文件夹路径
     */
    private String destFileDir = Environment.getExternalStorageDirectory().getAbsolutePath() + File
            .separator + "Supervisor";
    /**
     * 目标文件存储的文件名
     */
    private String destFileName = "Supervisor.apk";
    private String url = "";
    private String fileName = "Supervisor.apk";
    private String baseUpdateUrl = "";
    private String fullPath = "";
    private Context mContext;
    private int preProgress = 0;
    private int NOTIFY_ID = 1000;
    private NotificationCompat.Builder builder;
    private NotificationManager notificationManager;
    private Retrofit retrofit;
    private CompositeDisposable cd = new CompositeDisposable();
    private static final String channelID = "1";

    private static final String channelName = "channel_name";

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        L.i("DownLoadService", "DownLoadService____start");
        mContext = this;
        url = intent.getStringExtra("url");
        //http://m.youjuke.com/download/designer_youhongbao.apk下载链接中必须要download
        //文件名为 截取的
        if (TextUtils.isEmpty(url)) {
            throw new InvalidParameterException("Invalid url !!!");
        }
        fileName = url.substring(url.lastIndexOf("download") + 9);
        fullPath = destFileDir + File.separator + fileName;
        baseUpdateUrl = url.substring(0, url.lastIndexOf("download/") + 9);
        subscribeEvent();
        loadFile();
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    /**
     * 下载文件
     */
    private void loadFile() {
        initNotification();
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .client(initOkHttpClient())
                    .baseUrl(baseUpdateUrl)
                    .addConverterFactory(GsonConverterFactory.create(new Gson()))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        L.i("DownLoadService", "loadFile_start");
        final Handler handler = new Handler();
        retrofit.create(IFileLoad.class)
                .loadFile(url)
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .map(new Function<ResponseBody, BufferedSource>() {
                    @Override
                    public BufferedSource apply(@NonNull ResponseBody responseBody) throws Exception {
                        return responseBody.source();
                    }
                })
                .subscribe(new Observer<BufferedSource>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        cd.add(d);
                    }

                    @Override
                    public void onNext(@NonNull BufferedSource source) {
                        L.i("download", "onNext");
                        try {
                            writeFile(source, new File(fullPath));
                        } catch (IOException e) {
                            e.printStackTrace();
                            //onError(e);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(mContext, "下载失败", Toast.LENGTH_SHORT).show();
                                cancelNotification();
                                UpdateManager.getInstance().retry();
                            }
                        });
                    }

                    @Override
                    public void onComplete() {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                UpdateManager.getInstance().finsh();
                                // 安装软件
                                cancelNotification();
                                installApk(new File(fullPath));
                            }
                        });
                    }
                });
    }

    public interface IFileLoad {
        @Streaming
        @GET
        Observable<ResponseBody> loadFile(@Url String url);
    }

    /**
     * 安装软件
     *
     * @param file
     */
    private void installApk(File file) {
        Uri uri = Uri.fromFile(file);
        Intent install = new Intent(Intent.ACTION_VIEW);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            install.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(mContext, "com.youjuke.supervisor.fileProvider", file);
            install.setDataAndType(contentUri, "application/vnd.android.package-archive");
        } else {
            install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            install.setDataAndType(uri, "application/vnd.android.package-archive");
        }
        // 执行意图进行安装
        mContext.startActivity(install);
    }

    /**
     * 初始化OkHttpClient
     *
     * @return
     */
    private OkHttpClient initOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(10, TimeUnit.SECONDS);
        builder.networkInterceptors().add(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Response originalResponse = chain.proceed(chain.request());
                return originalResponse
                        .newBuilder()
                        .body(new FileResponseBody(originalResponse))
                        .build();
            }
        });
        return builder.build();
    }

    /**
     * 初始化Notification通知
     */
    public void initNotification() {
        notificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelID,
                    channelName, NotificationManager.IMPORTANCE_DEFAULT);
            builder = new NotificationCompat.Builder(mContext, channelID)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentText("0%")
                    .setContentTitle("正在更新")
                    .setProgress(100, 0, false);
            notificationManager.createNotificationChannel(channel);
            notificationManager.notify(NOTIFY_ID, builder.build());
        } else {
            builder = new NotificationCompat.Builder(mContext)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentText("0%")
                    .setContentTitle("正在更新")
                    .setProgress(100, 0, false);
            notificationManager.notify(NOTIFY_ID, builder.build());
        }
    }

    /**
     * 更新通知
     */
    public void updateNotification(long progress) {
        int currProgress = (int) progress;
        if (preProgress < currProgress) {
            builder.setContentText(progress + "%");
            builder.setProgress(100, (int) progress, false);
            notificationManager.notify(NOTIFY_ID, builder.build());
        }
        preProgress = (int) progress;
    }

    /**
     * 取消通知
     */
    public void cancelNotification() {
        notificationManager.cancel(NOTIFY_ID);
    }

    /**
     * 写入文件
     */
    private static void writeFile(BufferedSource source, File file) throws IOException {
        if (!file.getParentFile().exists())
            file.getParentFile().mkdirs();

        if (file.exists())
            file.delete();

        BufferedSink bufferedSink = Okio.buffer(Okio.sink(file));
        bufferedSink.writeAll(source);

        bufferedSink.close();
        source.close();
    }

    /**
     * 解除订阅
     *
     * @param cd 订阅关系集合
     */
    private static void unSubscribe(CompositeDisposable cd) {
        if (cd != null && !cd.isDisposed())
            cd.dispose();
    }

    /**
     * 订阅下载进度
     */
    private void subscribeEvent() {
        RxBus.getDefault().toObservable(10086, FileLoadingBean.class)
//                .toFlowable(BackpressureStrategy.BUFFER)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<FileLoadingBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        cd.add(d);
                    }

                    @Override
                    public void onNext(FileLoadingBean downloadBean) {
                        //更新进度
                        int progress = (int) (downloadBean.getProgress() * 100 / downloadBean.getTotal());
                        updateNotification(progress);
                        UpdateManager.getInstance().updateProgress(progress);
                    }

                    @Override
                    public void onError(Throwable e) {
                        subscribeEvent();
                    }

                    @Override
                    public void onComplete() {
                        subscribeEvent();
                    }
                });
    }

}
