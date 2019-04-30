package com.youyijia.goodhealth.widgets.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.youyijia.goodhealth.R;
import com.youyijia.hyoukalibrary.base.BaseActivity;

/**
 * Created by wangqiang on 2019/4/6.
 */
public abstract class SexSeclecDialog extends Dialog {

    private final Context context;
    private final TextView tv_man;
    private final TextView tv_women;
    private final TextView tv_cancel;

    public SexSeclecDialog(final Context context) {
        super(context, R.style.MyDialogStyles);
        this.context = context;
        setContentView(R.layout.dialog_sex_select);
        setCanceledOnTouchOutside(true);
        Window window = this.getWindow();
        window.setGravity(Gravity.BOTTOM);
        //设置dialog弹出时的动画效果，从屏幕底部向上弹出
        window.setWindowAnimations(R.style.dialogStyle);
        //设置弹框的高为屏幕的一半宽是屏幕的宽
        WindowManager windowManager = ((BaseActivity) context).getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = this.getWindow().getAttributes();
        this.getWindow().setAttributes(lp);


        tv_man = findViewById(R.id.tv_man);
        tv_women = findViewById(R.id.tv_women);
        tv_cancel = findViewById(R.id.tv_cancel);

        tv_man.setOnClickListener(view -> {
            setSex("男");
        });

        tv_women.setOnClickListener(view -> {
            setSex("女");
        });

        tv_cancel.setOnClickListener(view -> {
            dismiss();
        });
    }

    public abstract void setSex(String sex);
}
