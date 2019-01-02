package com.mobile.hyoukalibrary.base;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.baidu.mobstat.StatService;
import com.google.gson.Gson;
import com.tencent.sonic.sdk.SonicEngine;
import com.tencent.sonic.sdk.SonicSessionConfig;
import com.trello.rxlifecycle2.components.support.RxFragment;
import com.mobile.hyoukalibrary.sonic.SonicJavaScriptInterface;
import com.mobile.hyoukalibrary.utils.NetworkUtils;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * 描述:Fragment基类
 * <p>
 * 工程:
 * #0000    Tian Xiao    2016-09-06 13:41
 */
public abstract class BaseFragment extends RxFragment {
    /**
     * 访问网络提交数据的集合
     */
    protected Map<String, Object> params = new HashMap<String, Object>();

    protected Gson gson = new Gson();

    protected View parentView;

    protected FragmentActivity activity;

    protected LayoutInflater inflater;

    protected Context mContext;

    public abstract
    @LayoutRes
    int getLayoutResId();

    private static final String STATE_SAVE_IS_HIDDEN = "STATE_SAVE_IS_HIDDEN";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        doSomeThingInStart();
        //过场动画
        // “内存重启”时调用  解决重叠问题  #0001
        if (savedInstanceState != null) {
            boolean isSupportHidden = savedInstanceState.getBoolean(STATE_SAVE_IS_HIDDEN);

            FragmentTransaction ft = getFragmentManager().beginTransaction();
            if (isSupportHidden) {
                ft.hide(this);
            } else {
                ft.show(this);
            }
            ft.commit();
        }
        initWebView();
    }

    protected  void doSomeThingInStart(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle state) {
        mContext = container.getContext();
        //初始化Dialog
        this.inflater = inflater;
        parentView = inflater.inflate(getLayoutResId(), container, false);
        activity = getSupportActivity();
        return parentView;

    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        finishCreateView(savedInstanceState);
    }

    public abstract void finishCreateView(Bundle state);
    public  void initWebView(){}


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onAttach(Activity activity) {

        super.onAttach(activity);
        this.activity = (FragmentActivity) activity;
    }

    @Override
    public void onDetach() {

        super.onDetach();
        this.activity = null;
    }

    public FragmentActivity getSupportActivity() {

        return (FragmentActivity) super.getActivity();
    }

    public android.app.ActionBar getSupportActionBar() {

        return getSupportActivity().getActionBar();
    }

    public Context getApplicationContext() {

        return this.activity == null ? (getActivity() == null ? null : getActivity().getApplicationContext()) : this.activity.getApplicationContext();
    }

    protected View getParentView() {

        return parentView;
    }

    public <T extends View> T $(int id) {

        return (T) parentView.findViewById(id);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
    @Override
    public void onResume() {
        super.onResume();
        StatService.onResume(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        StatService.onPause(this);
    }

    private final long RETRY_TIMES = 1;
    public  <T> ObservableTransformer<T, T> newWorkAvailable() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(@NonNull Observable<T> upstream) {
                return upstream.retry(RETRY_TIMES)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .doOnSubscribe(new Consumer<Disposable>() {
                            @Override
                            public void accept(Disposable disposable) throws Exception {
                                Log.i("newWorkAvailable","Thread:"+ Thread.currentThread().getName());
                                if (!NetworkUtils.isConnected(mContext)) {
                                    Toast.makeText(mContext,
                                            "网络连接不可用，请检查网络 ", Toast.LENGTH_LONG).show();
                                    /*disposable.dispose();*/
                                    /*throw new NetworkErrorException();*/
                                }
                            }
                        }).subscribeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    /**
     * 跳转WEB ACtivity的方法。
     *  此方法为了提前预加载，使用sonic 让网页加载更快
     * @param url
     * @param intent
     */
    public void startWebActivity(@Nullable String url, @Nullable Intent intent) {
        //-----------------------
        SonicSessionConfig.Builder sessionConfigBuilder = new SonicSessionConfig.Builder();
        sessionConfigBuilder.setSupportLocalServer(true);
        boolean preloadSuccess = SonicEngine.getInstance().preCreateSession(url, sessionConfigBuilder.build());
        intent.putExtra(BaseWebActivity.URL,url);
        intent.putExtra(SonicJavaScriptInterface.PARAM_CLICK_TIME, System.currentTimeMillis());
        startActivity(intent);
    }
}
