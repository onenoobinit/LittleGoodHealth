package com.youyijia.goodhealth.wxapi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.youyijia.goodhealth.GoodHealthApp;
import com.youyijia.goodhealth.R;
import com.youyijia.goodhealth.app.pay.PaySucceedActivity;
import com.youyijia.hyoukalibrary.utils.L;

/**
 * Created by wangqiang on 2019/4/26.
 */
public class WXPayEntryActivity extends AppCompatActivity implements IWXAPIEventHandler {
    private static final String TAG = "MicroMsg.SDKSample.WXPayEntryActivity";

    private IWXAPI api;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_result);

        api = WXAPIFactory.createWXAPI(this, GoodHealthApp.APP_ID);
        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {
    }

    @Override
    public void onResp(BaseResp resp) {
        L.d(TAG, "onPayFinish, errCode = " + resp.errCode);

        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            /*AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("支付结果");
            builder.setMessage(String.valueOf(resp.errCode));
            builder.show();*/

            if (resp.errCode == 0) {
                startActivity(new Intent(WXPayEntryActivity.this, PaySucceedActivity.class));
                finish();
            }
        }
    }
}
