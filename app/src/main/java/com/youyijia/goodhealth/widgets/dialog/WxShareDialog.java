package com.youyijia.goodhealth.widgets.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.youyijia.goodhealth.R;
import com.youyijia.hyoukalibrary.base.BaseActivity;

/**
 * Created by wangqiang on 2019/4/26.
 */
public class WxShareDialog {
    private final Context context;
    private View inflate;
    private Dialog dialog;
    private LinearLayout ll_wx_in;
    private LinearLayout ll_wx_friend;
    private TextView tv_wx_cancle;
    private  WxListener listener;
    public WxShareDialog(Context context) {
        this.context = context;
        initView();

    }

    private void initView() {
        inflate = LayoutInflater.from(context).inflate(R.layout.dialog_share, null);
        dialog = new Dialog(context, R.style.Sweet_Alert_Dialog);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setContentView(inflate);
        Window dialogWindow = dialog.getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
        //设置dialog弹出时的动画效果，从屏幕底部向上弹出
        dialogWindow.setWindowAnimations(R.style.dialogStyle);
        //设置弹框的高为屏幕的一半宽是屏幕的宽
        WindowManager windowManager = ((BaseActivity) context).getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        lp.width = (int) (display.getWidth()); //设置宽度
        lp.height = (int) (ViewGroup.LayoutParams.WRAP_CONTENT); //设置宽度
        dialog.getWindow().setAttributes(lp);

        ll_wx_in = inflate.findViewById(R.id.ll_wx_in);
        ll_wx_friend = inflate.findViewById(R.id.ll_wx_friend);
        tv_wx_cancle = inflate.findViewById(R.id.tv_wx_cancle);

        ll_wx_friend.setOnClickListener(v ->{
            if(listener!=null){
                listener.setOnFriend();
            }
        });

        ll_wx_in.setOnClickListener( v ->{
            if(listener!=null){
                listener.setOnWx();
            }
        });
        tv_wx_cancle.setOnClickListener(v -> dismiss());
    }


    public void show() {
        if (dialog != null) {
            dialog.show();
        }
    }

    public void dismiss() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
            dialog.cancel();
            dialog = null;
        }
    }


    public WxShareDialog setListener(WxListener listener) {
        this.listener = listener;
        return this;
    }

    public interface WxListener{
          void setOnWx();
          void setOnFriend();
    }

}
