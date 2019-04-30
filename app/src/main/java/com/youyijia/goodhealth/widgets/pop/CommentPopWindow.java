package com.youyijia.goodhealth.widgets.pop;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.youyijia.goodhealth.R;

/**
 * Created by wangqiang on 2019/4/17.
 */
public class CommentPopWindow extends PopupWindow {

    private final Activity context;
    private final LayoutInflater inflater;
    private final View conentView;
    private final EditText et_content;
    private final TextView tv_send;
    private View parent;

    public CommentPopWindow(final Activity context) {
        this.context = context;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        conentView = inflater.inflate(R.layout.pop_comment, null);
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setOutsideTouchable(true);
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0000000000);
        // 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
        this.setBackgroundDrawable(dw);
        et_content = (EditText) conentView.findViewById(R.id.et_content);
        tv_send = (TextView) conentView.findViewById(R.id.tv_send);

    }

    public void showAtLocation(View parent, int gravity, int x, int y) {
        // 这三行配置是关键
        this.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        this.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        this.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED); // 在显示输入法之后调用，否则popupwindow会在窗口底层
        super.showAtLocation(parent, gravity, x, y);
    }
}
