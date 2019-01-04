package com.mobile.android.app.order.fragment;

import android.os.Bundle;

import com.mobile.android.R;
import com.mobile.hyoukalibrary.base.BaseFragment;

/**
 * Created by wangqiang on 2019/1/4.
 */
public class OperationFragment extends BaseFragment {

    public static OperationFragment newInstance() {
        OperationFragment fragment = new OperationFragment();
        return fragment;
    }
    @Override
    public int getLayoutResId() {
        return R.layout.fragment_operation;
    }

    @Override
    public void finishCreateView(Bundle state) {

    }
}
