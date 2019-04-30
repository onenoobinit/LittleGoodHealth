package com.youyijia.goodhealth.widgets.dialog;

import android.app.Dialog;
import android.content.Context;
import android.widget.TextView;

import com.youyijia.goodhealth.R;
import com.youyijia.goodhealth.entity.SubmitInfo;

/**
 * Created by wangqiang on 2019/1/24.
 */
public abstract class SubmitTipsDialog extends Dialog {

    private final Context context;
    private final TextView tv_submit_content;
    private final TextView tv_submit_tips_sure;
    private final SubmitInfo.TipsBean data;
    private final String frist;
    private String isOne = "0";// 0只有一个弹框 1有两个

    public SubmitTipsDialog(final Context context, SubmitInfo.TipsBean data, String frist) {
        super(context, R.style.fullWindowDialogStyle);
        this.context = context;
        this.data = data;
        this.frist = frist;
        setContentView(R.layout.dialog_submit_tips);
        setCanceledOnTouchOutside(false);
        tv_submit_content = findViewById(R.id.tv_submit_content);
        tv_submit_tips_sure = findViewById(R.id.tv_submit_tips_sure);

        if ("1".equals(data.getOverdue().getType()) && "0".equals(data.getPaymentPay().getType()) && frist.equals("1")) {
            tv_submit_content.setText(data.getOverdue().getContent());
            isOne = "0";
        } else if ("1".equals(data.getPaymentPay().getType()) && "0".equals(data.getOverdue().getType()) && frist.equals("1")) {
            tv_submit_content.setText(data.getPaymentPay().getContent());
            isOne = "0";
        } else if ("1".equals(data.getPaymentPay().getType()) && "1".equals(data.getOverdue().getType()) && frist.equals("1")) {
            tv_submit_content.setText(data.getOverdue().getContent());
            isOne = "1";
        } else if ("1".equals(data.getPaymentPay().getType()) && "1".equals(data.getOverdue().getType()) && frist.equals("2")) {
            tv_submit_content.setText(data.getPaymentPay().getContent());
            isOne = "1";
        }
        tv_submit_tips_sure.setOnClickListener(view -> {
            setOnSureClick(frist, isOne);
        });
    }

    public abstract void setOnSureClick(String s, String isone);
}
