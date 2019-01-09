package com.mobile.android.app.web;

import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;

import com.mobile.android.R;
import com.mobile.hyoukalibrary.utils.L;
import com.tencent.smtt.sdk.WebView;

/**
 * 网页通用页面
 * Created by wangqiang on 2019/1/9.
 */
public class CommonWebActivity extends CommonWebJustCheckActivity {

    @Override
    public void alterSource() {
        super.alterSource();
    }

    @Override
    public void myShouldOverrideUrlLoading(WebView webView, String s) {
        if (s.contains("p.qiao.baidu.com/cps/")) {
            L.d("百度");
            return;
        }

        if (s.contains("tel:")) {
            startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(s)));
            return;
        }

        webView.loadUrl(s);
        return;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ib_back:
                if (tvTitle.getText().equals("请求失败")) {
                    finish();
                    break;
                }
                if (mTBSWebView.canGoBack()) {
                    //优先使用缓存：
                    mTBSWebView.goBack();
                    if (!TextUtils.isEmpty(title) && !mTBSWebView.canGoBack()) {
                        tvTitle.setText(title);
                    }
                } else {
                    finish();
                }
                break;

            default:
                break;

        }
    }

    /**
     * 浏览器回退
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (tvTitle.getText().equals("请求失败")) {
                finish();
            }
            if (mTBSWebView.canGoBack()) {
                mTBSWebView.goBack();
                if (!TextUtils.isEmpty(title) && !mTBSWebView.canGoBack()) {
                    tvTitle.setText(title);
                }
                return true;
            } else {
                out();
                return false;
            }

        }
        return false;
    }
}
