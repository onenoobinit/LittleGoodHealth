package com.youyijia.goodhealth.widgets.dialog;

import android.app.Dialog;
import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.youyijia.goodhealth.R;

/**
 * Created by wangqiang on 2019/1/24.
 */
public abstract class SubmitShowDialog extends Dialog {
    private final Context context;
    private final TextView tv_submit_show_sure;
    private final ImageView iv_close;

    public SubmitShowDialog(final Context context) {
        super(context, R.style.fullWindowDialogStyle);
        this.context = context;
        setContentView(R.layout.dialog_submit_show);
        setCanceledOnTouchOutside(false);
        tv_submit_show_sure = findViewById(R.id.tv_submit_show_sure);
        iv_close = findViewById(R.id.iv_close);

        tv_submit_show_sure.setOnClickListener(view -> {
            setOnSureClick();
        });
        iv_close.setOnClickListener(view -> dismiss());
    }

    public abstract void setOnSureClick();
}
