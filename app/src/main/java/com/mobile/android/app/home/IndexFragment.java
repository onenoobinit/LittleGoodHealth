package com.mobile.android.app.home;

import android.os.Bundle;

import com.mobile.android.R;
import com.mobile.android.widgets.dialog.IndexDialog;
import com.mobile.hyoukalibrary.base.BaseFragment;
import com.zhy.autolayout.AutoRelativeLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by wangqiang on 2019/1/3.
 */
public class IndexFragment extends BaseFragment {
    @BindView(R.id.arl_circle)
    AutoRelativeLayout arlCircle;
    Unbinder unbinder;
    @Override
    public int getLayoutResId() {
        return R.layout.fragment_index;
    }

    @Override
    public void finishCreateView(Bundle state) {
        unbinder = ButterKnife.bind(this, parentView);
    }


    public static IndexFragment newInstance() {
        return new IndexFragment();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.arl_circle)
    public void onClick() {
        IndexDialog indexDialog = new IndexDialog(mContext, "");
        indexDialog.show();
    }
}
