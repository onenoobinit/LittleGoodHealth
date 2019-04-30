package com.youyijia.goodhealth.widgets.dialog;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;

import com.youyijia.goodhealth.R;
import com.youyijia.goodhealth.widgets.RatingBarView;
import com.youyijia.hyoukalibrary.utils.ToastUtil;

/**
 * Created by wangqiang on 2019/4/26.
 */
public abstract class CommentSubmitDialog extends Dialog {
    private final Context context;
    private final RatingBarView rbv_commnet;
    private final EditText et_content;
    private final TextView tv_sunmit_comment;
    private int number = 0;

    public CommentSubmitDialog(final Context context) {
        super(context, R.style.MyDialogStyles);
        this.context = context;
        setContentView(R.layout.dialog_comment_submit);
        setCanceledOnTouchOutside(true);

        rbv_commnet = findViewById(R.id.rbv_commnet);
        et_content = findViewById(R.id.et_content);
        tv_sunmit_comment = findViewById(R.id.tv_sunmit_comment);
        rbv_commnet.setStar(0, false);
        rbv_commnet.setOnRatingListener(new RatingBarView.OnRatingListener() {
            @Override
            public void onRating(Object bindObject, int RatingScore) {
                number = RatingScore;
            }
        });

        tv_sunmit_comment.setOnClickListener(v -> {
            if (number == 0) {
                ToastUtil.show(context, "请选择评价等级！");
                return;
            } else if (TextUtils.isEmpty(et_content.getText().toString().trim()) || "请写下您的评价".equals(et_content.getText().toString().trim())) {
                ToastUtil.show(context, "请写下您的评价");
                return;
            } else {
                setOnComment(number,et_content.getText().toString().trim());
            }
        });

    }

    public abstract void setOnComment(int number, String content);
}
