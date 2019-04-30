package com.youyijia.goodhealth.widgets.dialog;

import android.content.Context;

import com.youyijia.goodhealth.R;
import com.youyijia.goodhealth.widgets.CustomProgressDialog;

/**
 * Created by Administrator on 2016/11/29.
 */

public class MyDialog {
    CustomProgressDialog mCustomProgressDialog;

    public MyDialog(Context context) {
        this.context = context;
    }

    Context context;

    /**
     * 显示一个ProgressDialog
     */
    public void showDialog() {

        if (mCustomProgressDialog == null) {
            mCustomProgressDialog = new CustomProgressDialog(context, R.drawable.anim_progressr);
        }
        mCustomProgressDialog.setCancelable(true);// 设置按返回键是否关闭dialog
        mCustomProgressDialog.setCanceledOnTouchOutside(false);
        if (!mCustomProgressDialog.isShowing()) {
            mCustomProgressDialog.show();
        }
    }

    /**
     * 取消当前显示的ProgressDialog
     */
    public void dismissDialog() {
        if (mCustomProgressDialog != null
                || mCustomProgressDialog.isShowing()) {
            mCustomProgressDialog.dismiss();
        }

    }

    public void release() {
        if (mCustomProgressDialog != null) {
            mCustomProgressDialog.cancel();
            mCustomProgressDialog = null;
        }
    }
}
