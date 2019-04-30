package com.youyijia.goodhealth.wxapi;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.youyijia.goodhealth.GoodHealthApp;
import com.youyijia.goodhealth.R;
import com.youyijia.goodhealth.observer.WXLoginObserver;
import com.youyijia.hyoukalibrary.base.BaseActivity;
import com.youyijia.hyoukalibrary.utils.L;

public class WXEntryActivity extends BaseActivity implements IWXAPIEventHandler {
    private IWXAPI api;
    private String code;
    private int errorCode;


    @Override
    public void onReq(BaseReq baseReq) {
        L.i("onReq", "baseReq:" + baseReq);
    }

    @Override
    public void onResp(BaseResp baseResp) {
        WXLoginObserver.Companion.getINSTANCE().resultCode(baseResp);
        finish();
    }


    @Override
    protected void initViews(Bundle savedInstanceState) {
        api = WXAPIFactory.createWXAPI(this, GoodHealthApp.APP_ID, true);
        api.registerApp(GoodHealthApp.APP_ID);
        try {
            boolean result = api.handleIntent(getIntent(), this);
            if (!result) {
                L.d("参数不合法，未被SDK处理，退出");
                finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        api.handleIntent(data, this);
//    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
        finish();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_wxentry;
    }

    @Override
    public void initToolBar() {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
