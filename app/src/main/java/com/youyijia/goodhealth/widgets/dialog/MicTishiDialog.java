package com.youyijia.goodhealth.widgets.dialog;

import android.app.Dialog;
import android.content.Context;
import android.widget.TextView;

import com.youyijia.goodhealth.R;

/**
 * Created by wangqiang on 2019/4/10.
 */
public class MicTishiDialog extends Dialog {

    private final Context context;
    private final TextView tv_sure;
    private final TextView tv_ts_content;


    public MicTishiDialog(final Context context, String content) {
        super(context, R.style.MyDialogStyles);
        this.context = context;
        setContentView(R.layout.dialog_mic_tishi);
        setCanceledOnTouchOutside(true);

        tv_sure = findViewById(R.id.tv_sure);
        tv_ts_content = findViewById(R.id.tv_ts_content);
        tv_ts_content.setText(content);
        tv_sure.setOnClickListener(v -> dismiss());
    }
}
