package com.youyijia.goodhealth.widgets.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.TextView;

import com.youyijia.goodhealth.R;

/**
 * Created by wangqiang on 2019/4/12.
 */
public class GreenTellDialog extends Dialog {
    private final Context context;
    private final TextView tv_sure;
    private final TextView tv_cancel;


    public GreenTellDialog(final Context context) {
        super(context, R.style.MyDialogStyles);
        this.context = context;
        setContentView(R.layout.dialog_green_tell);
        setCanceledOnTouchOutside(false);

        tv_sure = findViewById(R.id.tv_sure);
        tv_cancel = findViewById(R.id.tv_cancel);
        tv_sure.setOnClickListener(v -> {
            Intent telIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:400-656-0320"));
            context.startActivity(telIntent);
        });
        tv_cancel.setOnClickListener(v -> dismiss());
    }
}
