package com.mobile.android.widgets.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;

import com.mobile.android.R;

/**
 * @author chenliangzhi
 * @date 2018/5/17
 * @describe
 */

public class LoadingDialog extends Dialog {
    private Context mContext;

    public LoadingDialog(@NonNull Context context) {
        super(context, R.style.MyProgressDialogStyle);
        this.mContext = context;
        initView();
    }

    private void initView() {
        View contentView = LayoutInflater.from(mContext).inflate(R.layout.dialog_loading, null);
        setContentView(contentView);
        final Window window = getWindow();
        window.setGravity(Gravity.CENTER);
        setCancelable(true);
        setCanceledOnTouchOutside(false);
    }
}
