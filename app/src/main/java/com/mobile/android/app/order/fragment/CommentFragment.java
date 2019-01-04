package com.mobile.android.app.order.fragment;

import android.os.Bundle;

import com.mobile.android.R;
import com.mobile.hyoukalibrary.base.BaseFragment;

/**
 * Created by wangqiang on 2019/1/4.
 */
public class CommentFragment extends BaseFragment {

    public static CommentFragment newInstance() {
        CommentFragment fragment = new CommentFragment();
        return fragment;
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_comment;
    }

    @Override
    public void finishCreateView(Bundle state) {

    }
}
