package com.mobile.android.updatebyrx2;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.daimajia.numberprogressbar.NumberProgressBar;
import com.mobile.android.R;


/**
 * 描述: 更新的dialog
 * --------------------------------------------
 * 工程:
 * #0000     mwy     创建日期: 2017-01-03 13:57
 */

public class UpdateDialog extends Dialog {

    private boolean canCancelable;

    private TextView mTvDialogTitle;
    private TextView mTvDialogMessage;
    private NumberProgressBar mNumberProgressBar;
    private TextView mDialogLeftTxt;
    private View mDialogLine;
    private TextView mDialogRightTxt;

    private void assignViews() {
        mTvDialogTitle = (TextView) findViewById(R.id.tv_dialog_title);
        mTvDialogMessage = (TextView) findViewById(R.id.tv_dialog_message);
        mNumberProgressBar = (NumberProgressBar) findViewById(R.id.number_progress_bar);
        mDialogLeftTxt = (TextView) findViewById(R.id.dialog_left_txt);
        mDialogLine = findViewById(R.id.dialog_line);
        mDialogRightTxt = (TextView) findViewById(R.id.dialog_right_txt);
    }
    public UpdateDialog(Context context) {
        super(context,R.style.UpdateDialogStyle);
        setContentView(LayoutInflater.from(context).inflate(R.layout.dialog_update, null));
        setCancelable(canCancelable);
        assignViews();
    }

    public UpdateDialog setCanCancelable(boolean canCancelable) {
        this.canCancelable = canCancelable;
        return this;
    }

    public UpdateDialog setMessage(String message){
        if (null != message){
            mTvDialogMessage.setText(message);
        }
        return this;
    }

    public UpdateDialog setRightOnClickListener(View.OnClickListener onClickListener){
        if (onClickListener != null){
            mDialogRightTxt.setOnClickListener(onClickListener);
        }

        return this;
    }

    public  UpdateDialog setProgress(int currentProgress){
        mNumberProgressBar.setProgress(currentProgress);
        return this;
    }

    public  UpdateDialog incrementProgressBy(int by){
        mNumberProgressBar.incrementProgressBy(by);
        return this;
    }

    public UpdateDialog setRightText(String text){
        if (text == null) {
            return this;
        }
        mDialogRightTxt.setText(text);
        return this;
    }

    public UpdateDialog setRightEnable(boolean enable){
        mDialogRightTxt.setEnabled(enable);
        return this;
    }

    public UpdateDialog setProgressVisiable(boolean visiable){
        if (visiable){
            mNumberProgressBar.setVisibility(View.VISIBLE);
        }else{
            mNumberProgressBar.setVisibility(View.GONE);
        }
        return this;
    }

}
