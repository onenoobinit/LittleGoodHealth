package com.mobile.android.widgets.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.mobile.android.R;
import com.mobile.android.app.build_material_feedback.ListSelectAdapter;

import java.util.List;

/**
 * @author chenliangzhi
 * @date 2018/5/18
 * @describe
 */

public abstract class ListSelectDialog extends Dialog {
    private View mContentView;
    private Context mContext;
    private RecyclerView mRclSelects;
    private List<String> mData;

    public ListSelectDialog(@NonNull Context context, List<String> data) {
        super(context, R.style.normalDialogStyle);
        this.mContext = context;
        this.mData = data;
        initView();
    }

    private void initView() {
        mContentView = LayoutInflater.from(mContext).inflate(R.layout.dialog_list_select, null);
        setContentView(mContentView);
        mRclSelects = mContentView.findViewById(R.id.rcl_list_select);
        mRclSelects.setLayoutManager(new LinearLayoutManager(mContext));
        final ListSelectAdapter listSelectAdapter = new ListSelectAdapter(R.layout.item_list_select, mData);
        mRclSelects.setAdapter(listSelectAdapter);
        listSelectAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            itemClick(position);
            dismiss();
        });
        setCancelable(true);
        setCanceledOnTouchOutside(true);
        final Window window = getWindow();
        window.setGravity(Gravity.CENTER);
        WindowManager m = window.getWindowManager();
        Display defaultDisplay = m.getDefaultDisplay();
        WindowManager.LayoutParams lp = window.getAttributes();
        // 宽度设置为屏幕的0.70
        lp.width = (int) (defaultDisplay.getWidth() * 0.70);
        window.setGravity(Gravity.CENTER);
        window.setAttributes(lp);
    }

    public abstract void itemClick(int position);

}
