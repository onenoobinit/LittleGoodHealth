package com.mobile.android.app.order.fragment;

import android.os.Bundle;

import com.mobile.android.R;
import com.mobile.hyoukalibrary.base.BaseFragment;

/**
 * Created by wangqiang on 2019/1/4.
 */
public class ExmineFragment extends BaseFragment {

    public static ExmineFragment newInstance() {
        ExmineFragment fragment = new ExmineFragment();
        return fragment;
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_exmine;
    }

    @Override
    public void finishCreateView(Bundle state) {

    }

}
