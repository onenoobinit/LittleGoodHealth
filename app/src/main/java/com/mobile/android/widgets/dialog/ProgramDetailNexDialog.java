package com.mobile.android.widgets.dialog;

import android.app.Dialog;
import android.content.Context;
import android.widget.TextView;

import com.mobile.android.R;

/**
 * Created by wangqiang on 2019/1/21.
 */
public abstract class ProgramDetailNexDialog extends Dialog {
    private final Context context;
    private final TextView tv;

    public ProgramDetailNexDialog(final Context context) {
        super(context, R.style.fullWindowDialogStyle);
        this.context = context;
        setContentView(R.layout.dialog_program_detail_next);
        setCanceledOnTouchOutside(true);
        tv = findViewById(R.id.tv_prodetail_sure);

        tv.setOnClickListener(view -> {
            setOnSureClick();
        });
    }

    public abstract void setOnSureClick();
}
