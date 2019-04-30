package com.youyijia.hyoukalibrary.base;

/**
 * Created by wangqiang on 2019/1/11.
 */
public abstract class LazyFragment extends BaseFragment {
    protected boolean isVisible;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    protected abstract void lazyLoad();

    protected void onVisible(){
        lazyLoad();
    }

    protected void onInvisible(){

    }

}
