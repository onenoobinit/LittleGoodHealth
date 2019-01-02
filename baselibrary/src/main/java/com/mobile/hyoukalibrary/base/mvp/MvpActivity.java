package com.mobile.hyoukalibrary.base.mvp;

import com.mobile.hyoukalibrary.base.BaseActivity;

/**
 * @author clz
 * @desc 初始化P，并且连接V，在onDestroy()生命周期中释放P中引用的V。
 */
public abstract class MvpActivity<V, P extends BasePresenter<V>> extends BaseActivity {
    protected P mPresenter;

    @Override
    protected void doSomeThingBeforeInitView() {
        super.doSomeThingBeforeInitView();
        this.mPresenter = initPresenter();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.attach((V) this);
    }

    @Override
    protected void onDestroy() {
        mPresenter.detach();
        super.onDestroy();
    }

    /**
     * 初始化presenter
     *
     * @return
     */
    public abstract P initPresenter();
}
