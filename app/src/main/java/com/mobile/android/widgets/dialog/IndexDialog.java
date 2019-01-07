package com.mobile.android.widgets.dialog;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobile.android.R;
import com.mobile.android.app.program.ProgramSelectActivity;
import com.zhy.autolayout.AutoRelativeLayout;

/**
 * Created by wangqiang on 2019/1/6.
 */
public class IndexDialog extends Dialog {

    private final ImageView iv_index_dialog_colse;
    private final Context context;
    private String mobile;
    private final TextView tv_index_start;
    private final AutoRelativeLayout arl_show1;
    private final ImageView iv_index_dialog_close1;
    private final AutoRelativeLayout arl_show2;
    private final TextView tv_index_end;
    private final ImageView iv_index_dialog_close2;
    private final EditText et_show1;
    private final EditText et_show2;
    private final EditText et_show3;
    private final TextView tv_tips;
    private final TextView tv_index_sure;

    @SuppressLint("WrongViewCast")
    public IndexDialog(final Context context, final String mobile) {
        super(context, R.style.fullWindowDialogStyle);
        this.context = context;
        this.mobile = mobile;
        setContentView(R.layout.dialog_index);
        setCanceledOnTouchOutside(true);
        iv_index_dialog_colse = findViewById(R.id.iv_index_dialog_colse);
        tv_index_start = findViewById(R.id.tv_index_start);
        arl_show1 = findViewById(R.id.arl_show1);
        iv_index_dialog_close1 = findViewById(R.id.iv_index_dialog_close1);
        arl_show2 = findViewById(R.id.arl_show2);
        tv_index_end = findViewById(R.id.tv_index_end);
        iv_index_dialog_close2 = findViewById(R.id.iv_index_dialog_close2);
        et_show1 = findViewById(R.id.et_show1);
        et_show2 = findViewById(R.id.et_show2);
        et_show3 = findViewById(R.id.et_show3);
        tv_tips = findViewById(R.id.tv_tips);
        tv_index_sure = findViewById(R.id.tv_index_sure);

        iv_index_dialog_colse.setOnClickListener(view -> dismiss());

        tv_index_sure.setOnClickListener(view -> {
            context.startActivity(new Intent(context, ProgramSelectActivity.class));
        });
    }
}
