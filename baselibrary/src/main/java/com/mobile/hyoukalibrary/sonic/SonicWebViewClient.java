package com.mobile.hyoukalibrary.sonic;
import com.tencent.smtt.export.external.interfaces.WebResourceRequest;
import com.tencent.smtt.export.external.interfaces.WebResourceResponse;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import com.tencent.sonic.sdk.SonicSession;

/**
 * 描述: 继承WebViewClient 编写SonicSession加载完成关闭
 * ------------------------------------------------------------------------
 * 工程:
 * #0000     tian wangqiang     创建日期: 2018-03-09 15:33
 */
public class SonicWebViewClient extends WebViewClient {
    private SonicSession sonicSession;

    public SonicWebViewClient(SonicSession sonicSession) {
        super();
        this.sonicSession=sonicSession;
    }

    @Override
    public void onPageFinished(WebView webView, String s) {
        super.onPageFinished(webView, s);
        if (sonicSession != null) {
            sonicSession.getSessionClient().pageFinish(s);
        }
    }

    @Override
    public WebResourceResponse shouldInterceptRequest(WebView webView, WebResourceRequest webResourceRequest) {
        return super.shouldInterceptRequest(webView, webResourceRequest.getUrl().toString());
    }

    @Override
    public WebResourceResponse shouldInterceptRequest(WebView webView, String s) {
        if (sonicSession != null) {
            return (WebResourceResponse) sonicSession.getSessionClient().requestResource(s);
        }
        return null;
    }

}
