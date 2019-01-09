package com.mobile.android.app.web;

import com.mobile.android.R;
import com.mobile.android.widgets.CustomProgressDialog;
import com.mobile.hyoukalibrary.base.BaseActivity;

/**
 * Created by wangqiang on 2019/1/9.
 */
public abstract class WrapActivity extends BaseActivity {

    protected CustomProgressDialog mCustomProgressDialog;

    /**
     * 显示一个ProgressDialog
     */
    public void showProgressDialog() {

        if (mCustomProgressDialog == null) {
            mCustomProgressDialog = new CustomProgressDialog(this,
                    R.drawable.anim_progressr);
        }
        mCustomProgressDialog.setCanceledOnTouchOutside(false);
        mCustomProgressDialog.setCancelable(true);// 设置按返回键是否关闭dialog
        if (!mCustomProgressDialog.isShowing()) {
            mCustomProgressDialog.show();
        }
    }

    /**
     * 关闭 dialog
     */
    public void dimissDialog() {
        if (mCustomProgressDialog == null) {
            return;
        }
        if (mCustomProgressDialog.isShowing()) {
            mCustomProgressDialog.dismiss();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dimissDialog();
        if (mCustomProgressDialog != null) {
            mCustomProgressDialog = null;
        }
    }
}
