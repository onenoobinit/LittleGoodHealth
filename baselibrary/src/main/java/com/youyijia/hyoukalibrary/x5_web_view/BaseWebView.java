package com.youyijia.hyoukalibrary.x5_web_view;

import android.content.Context;
import android.util.AttributeSet;

import com.tencent.smtt.sdk.WebView;
import com.youyijia.hyoukalibrary.utils.L;

import java.util.Map;

/**
 * 描述: 基于TBS的全局url添加channel
 * --------------------------------------------
 * 工程:
 * #0000     mwy     创建日期: 2017-08-25 11:18
 */

public class BaseWebView extends WebView {


    public BaseWebView(Context context) {
        super(context);
    }

    public BaseWebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public BaseWebView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override
    public void loadUrl(String s, Map<String, String> map) {
        if (s != null  && !s.toLowerCase().contains("javascript") && !s.toLowerCase().contains("file:")){
            /*s = CRequest.append(s,"platform=app");
            s = CRequest.append(s,"os=android");
            s = CRequest.append(s,"channel="+ BaseApplication.getInstance().getChannel());*/
        }
        super.loadUrl(s, map);
    }

    @Override
    public void loadUrl(String s) {
        if (s != null  && !s.toLowerCase().contains("javascript") && !s.toLowerCase().contains("file:")){
            /*s = CRequest.append(s,"platform=app");
            s = CRequest.append(s,"os=android");
            s = CRequest.append(s,"channel="+ BaseApplication.getInstance().getChannel());*/
        }
        L.i("loadUrl:"+s);
        super.loadUrl(s);
    }
}
