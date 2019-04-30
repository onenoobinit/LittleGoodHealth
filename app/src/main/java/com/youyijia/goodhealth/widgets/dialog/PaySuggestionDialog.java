package com.youyijia.goodhealth.widgets.dialog;

import android.app.Dialog;
import android.content.Context;
import android.widget.TextView;

import com.youyijia.goodhealth.R;

/**
 * Created by wangqiang on 2019/4/25.
 */
public abstract class PaySuggestionDialog extends Dialog {

    private final Context context;
    private final TextView tv_sure_leave;
    private final TextView tv_pay_still;


    public PaySuggestionDialog(final Context context) {
        super(context, R.style.MyDialogStyles);
        this.context = context;
        setContentView(R.layout.dialog_pay_suggest);
        setCanceledOnTouchOutside(true);

        tv_sure_leave = findViewById(R.id.tv_sure_leave);
        tv_pay_still = findViewById(R.id.tv_pay_still);
        tv_pay_still.setOnClickListener(v -> dismiss());
        tv_sure_leave.setOnClickListener(v -> {
            setOnleave();
        });
    }

    public abstract void setOnleave();
}