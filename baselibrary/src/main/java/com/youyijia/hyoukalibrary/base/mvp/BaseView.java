package com.youyijia.hyoukalibrary.base.mvp;

/**
 * @author chenliangzhi
 * @date 2018/3/16
 * @describe baseView
 */

public interface BaseView {
    /**
     * 显示加载框
     */
    void showLoading();

    /**
     * 隐藏加载框
     */
    void hideLoading();

    /**
     * 弹出提示
     * @param s
     */
    void toast(String  s);
}
