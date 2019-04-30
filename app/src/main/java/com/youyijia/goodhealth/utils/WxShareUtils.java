package com.youyijia.goodhealth.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.youyijia.goodhealth.GoodHealthApp;
import com.youyijia.hyoukalibrary.utils.ToastUtil;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by wangqiang on 2019/4/26.
 */
public class WxShareUtils {
    private static IWXAPI api;
    private static Bitmap bitmap;

    public static void shareWeb(Context context, String webUrl, String title, String content, String imageUrl, Boolean friend) {
        api = WXAPIFactory.createWXAPI(context, GoodHealthApp.APP_ID);
        api.registerApp(GoodHealthApp.APP_ID);
        if (!api.isWXAppInstalled()) {
            ToastUtil.show(context, "您还没有安装微信");
            return;
        }
        WXWebpageObject webpageObject = new WXWebpageObject();
        webpageObject.webpageUrl = webUrl;
        WXMediaMessage msg = new WXMediaMessage(webpageObject);
        msg.title = title;
        msg.description = content;
        Bitmap bitmap1 = returnBitMap(imageUrl);
        msg.setThumbImage(bitmap1);

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = "sharenews";
        req.message = msg;
        req.scene = friend ? SendMessageToWX.Req.WXSceneTimeline : SendMessageToWX.Req.WXSceneSession;
        api.sendReq(req);
    }

    public static Bitmap returnBitMap(final String url) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                URL imageurl = null;

                try {
                    imageurl = new URL(url);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                try {
                    HttpURLConnection conn = (HttpURLConnection) imageurl.openConnection();
                    conn.setDoInput(true);
                    conn.connect();
                    InputStream is = conn.getInputStream();
                    bitmap = BitmapFactory.decodeStream(is);
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        return bitmap;
    }
}
