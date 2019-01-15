package com.mobile.android.widgets.dialog;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobile.android.R;
import com.zhy.autolayout.AutoRelativeLayout;

/**
 * Created by wangqiang on 2019/1/6.
 */
public abstract class IndexDialog extends Dialog {

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

    @SuppressLint("WrongViewCast")
    public IndexDialog(final Context context) {
        super(context, R.style.fullWindowDialogStyle);
        this.context = context;
        setContentView(R.layout.dialog_index);
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
        arl_show1.setOnClickListener(view -> {
            startClick(v_bottom1, tv_index_start, iv_index_dialog_close1);
        });
        arl_show2.setOnClickListener(view -> endClick(tv_index_end, iv_index_dialog_close2));

        iv_index_dialog_close1.setOnClickListener(view -> startCloseClick(tv_index_start, iv_index_dialog_close1));

        iv_index_dialog_close2.setOnClickListener(view -> endCloseClick(tv_index_end,iv_index_dialog_close2));

        et_show1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                numberListener(String.valueOf(editable));
            }
        });

        et_show2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                weightListener(String.valueOf(editable));
            }
        });

        et_show3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                volListener(String.valueOf(editable));
            }
        });
    }

    public abstract void startClick(View view, TextView tv, ImageView iv);

    public abstract void endClick(TextView tv, ImageView iv);

    public abstract void startCloseClick(TextView tv, ImageView iv);

    public abstract void endCloseClick(TextView tv, ImageView iv);

    public abstract void numberListener(String number);

    public abstract void weightListener(String weight);

    public abstract void volListener(String vol);

    public abstract void sureClick(TextView tv1, TextView tv2, EditText et_show1, EditText et_show2, EditText et_show3, TextView tv3);

    public void setPort(String port) {
        tv_index_end.setText(port);
        tv_index_end.setTextColor(Color.parseColor("#000000"));
        iv_index_dialog_close2.setVisibility(View.VISIBLE);
    }
}
