package com.youyijia.goodhealth.widgets.dialog;

import android.app.Dialog;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import com.youyijia.goodhealth.R;
import com.youyijia.goodhealth.entity.SubmitSuccessInfo;
import com.youyijia.hyoukalibrary.utils.Validator;

/**
 * Created by wangqiang on 2019/1/24.
 */
public abstract class SubmitSuccessDialog extends Dialog {
    private final Context context;
    private final TextView tv_submit_content;
    private final TextView tv_submit_tips_sure;
    private final SubmitSuccessInfo data;
    private final EditText et_submit_phone;

    public SubmitSuccessDialog(final Context context, SubmitSuccessInfo data) {
        super(context, R.style.fullWindowDialogStyle);
        this.context = context;
        this.data = data;
        setContentView(R.layout.dialog_submit_success);
        setCanceledOnTouchOutside(false);
        tv_submit_content = findViewById(R.id.tv_submit_content);
        tv_submit_tips_sure = findViewById(R.id.tv_submit_tips_sure);
        et_submit_phone = findViewById(R.id.et_submit_phone);
        et_submit_phone.setText(data.getReviewMobile());

        tv_submit_tips_sure.setOnClickListener(view -> {
            setOnSureClick(et_submit_phone.getText().toString().trim());
        });

        et_submit_phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                int length = editable.toString().trim().length();
                if (length == 11 && Validator.isMobile(et_submit_phone.getText().toString().trim())) {
                    tv_submit_tips_sure.setClickable(true);
                } else {
                    tv_submit_tips_sure.setClickable(false);
                }
            }
        });
    }

    public abstract void setOnSureClick(String mobile);
}
