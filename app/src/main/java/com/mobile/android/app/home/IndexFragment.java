package com.mobile.android.app.home;

import android.os.Bundle;

import com.mobile.android.R;
import com.mobile.hyoukalibrary.base.BaseFragment;

/**
 * Created by wangqiang on 2019/1/3.
 */
public class IndexFragment extends BaseFragment {
    @Override
    public int getLayoutResId() {
        return R.layout.fragment_index;
    }

    @Override
    public void finishCreateView(Bundle state) {

    }


    public static IndexFragment newInstance() {
        return new IndexFragment();
    }
}
