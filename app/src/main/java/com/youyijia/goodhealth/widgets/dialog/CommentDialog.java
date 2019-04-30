package com.youyijia.goodhealth.widgets.dialog;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.youyijia.goodhealth.R;
import com.youyijia.hyoukalibrary.utils.ToastUtil;

import static android.content.Context.INPUT_METHOD_SERVICE;

/**
 * Created by wangqiang on 2019/4/19.
 */
public class CommentDialog extends DialogFragment implements TextWatcher, View.OnClickListener {

    private Context mContext;
    public SendListener sendListener;
    private TextView tv_send;
    private String hintText;
    private Dialog dialog;
    private EditText et_content;
    private ImageView iv_emoji;

    public CommentDialog() {
    }

    @SuppressLint("ValidFragment")
    public CommentDialog(Context context, SendListener sendBackListener) {
        this.sendListener = sendBackListener;
        this.mContext = context;
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        dialog = new Dialog(getActivity(), R.style.Comment_Dialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        View contentView = View.inflate(getActivity(), R.layout.dialog_comment, null);
        dialog.setContentView(contentView);
        dialog.setCanceledOnTouchOutside(true);
        Window window = dialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.gravity = Gravity.BOTTOM;
        lp.alpha = 1;
        lp.dimAmount = 0.5f;
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(lp);
        window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        et_content = (EditText) contentView.findViewById(R.id.et_content);
        tv_send = (TextView) contentView.findViewById(R.id.tv_send);
        et_content.addTextChangedListener(this);
        tv_send.setOnClickListener(this);
        et_content.setFocusable(true);
        et_content.setFocusableInTouchMode(true);
        et_content.requestFocus();
        final Handler handler = new Handler();
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(getActivity().getWindow().getDecorView().getWindowToken(), 0);
                    }
                }, 0);
            }
        });
        return dialog;
    }

    public void cleanText() {
        et_content.setText("");
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    @Override
    public void afterTextChanged(Editable s) {
        if (s.length() > 0) {
            tv_send.setEnabled(true);
            tv_send.setTextColor(Color.BLACK);
        } else {
            tv_send.setEnabled(false);
            tv_send.setTextColor(Color.GRAY);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_send:
                checkContent();
                break;
        }
    }

    private void checkContent() {
        String content = et_content.getText().toString().trim();
        if (TextUtils.isEmpty(content)) {
            ToastUtil.show(mContext, "评论内容不能为空！");
            return;
        }
        sendListener.sendComment(content);
        dismiss();
    }

    public interface SendListener {
        void sendComment(String inputText);
    }
}
