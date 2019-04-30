package com.youyijia.goodhealth.widgets.dialog;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.youyijia.goodhealth.R;
import com.zhy.autolayout.AutoRelativeLayout;

/**
 * Created by wangqiang on 2019/1/21.
 */
public abstract class ProgramDetailDialog extends Dialog {
    private final ImageView iv_index_dialog_colse;
    private final Context context;
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
    private final View v_bottom1;
    public static int bWidth;
    private String portCity = "";

    @SuppressLint("WrongViewCast")
    public ProgramDetailDialog(final Context context) {
        super(context, R.style.fullWindowDialogStyle);
        this.context = context;
        setContentView(R.layout.dialog_program_detail);
        setCanceledOnTouchOutside(false);
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
        v_bottom1 = findViewById(R.id.v_bottom1);
        int width = v_bottom1.getWidth();
        bWidth = width;

        iv_index_dialog_colse.setOnClickListener(view -> dismiss());

        tv_index_sure.setOnClickListener(view -> {
            sureClick(tv_index_start, tv_index_end, et_show1, et_show2, et_show3, tv_tips);
        });

    }

    public abstract void sureClick(TextView tv1, TextView tv2, EditText et_show1, EditText et_show2, EditText et_show3, TextView tv3);

    public void setPort(String port) {
        tv_index_end.setText(port);
        iv_index_dialog_close2.setVisibility(View.VISIBLE);
    }

    @Override
    public void dismiss() {
        super.dismiss();
        tv_tips.setVisibility(View.INVISIBLE);
        tv_tips.setText("");
    }

    public void setPortCity(String endHY) {
        portCity = endHY;
    }

    public void setStart(String start) {
        tv_index_start.setText(start);
        iv_index_dialog_close1.setVisibility(View.VISIBLE);
    }

    public void setNumber(String number) {
        et_show1.setText(number);
    }

    public void setWeight(String weight) {
        et_show2.setText(weight);
    }

    public void setVol(String vol) {
        et_show3.setText(vol);
    }
}
