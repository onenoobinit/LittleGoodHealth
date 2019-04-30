package com.youyijia.hyoukalibrary.base.mvp;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chenliangzhi
 * @date 2018/3/16
 * @describe 在BasePresenter里面去初始化View对象，同时提供释放View对象以防止内存溢出
 */

public abstract class BasePresenter<T> {
    public Map<String, Object> params = new HashMap<>();
    public T view;

    public void attach(T view) {
        this.view = view;
    }

    public void detach() {
        this.view = null;
    }
}
