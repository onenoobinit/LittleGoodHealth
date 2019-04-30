package com.youyijia.hyoukalibrary.base.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.youyijia.hyoukalibrary.base.BaseFragment;

/**
 * @author chenliangzhi
 * @date 2018/3/19
 * @describe
 */

public abstract class MvpFragment<V, P extends BasePresenter<V>> extends BaseFragment {
    protected P mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void doSomeThingInStart() {
        super.doSomeThingInStart();
        mPresenter = initPresenter();
        mPresenter.attach((V) this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        mPresenter.detach();
        super.onDestroyView();
    }

    /**
     * 初始化presenter
     *
     * @return
     */
    public abstract P initPresenter();
}
