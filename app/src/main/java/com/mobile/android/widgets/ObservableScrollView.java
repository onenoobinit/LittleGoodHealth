package com.mobile.android.widgets;

import android.content.Context;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by wangqiang on 2017/3/7.
 * 自定义scrollview监听屏幕高度变化
 */

public class ObservableScrollView extends NestedScrollView {
    private ScrollViewListener scrollViewListener = null;
    private float xDistance;
    private float yDistance;
    private float xLast;
    private float yLast;
    private float alpha;

    public ObservableScrollView(Context context) {
        this(context,null);
    }

    public ObservableScrollView(Context context, AttributeSet attrs, int defStyle) {
        this(context, attrs);
    }

    public ObservableScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setScrollViewListener(ScrollViewListener scrollViewListener) {
        this.scrollViewListener = scrollViewListener;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if(this.scrollViewListener != null) {
            this.scrollViewListener.onScrollChanged(this, l, t, oldl, oldt);
        }

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch(ev.getAction()) {
            case 0:
                this.xDistance = this.yDistance = 0.0F;
                this.xLast = ev.getX();
                this.yLast = ev.getY();
            case 1:
            default:
                break;
            case 2:
                float curX = ev.getX();
                float curY = ev.getY();
                this.xDistance += Math.abs(curX - this.xLast);
                this.yDistance += Math.abs(curY - this.yLast);
                this.xLast = curX;
                this.yLast = curY;
                if(this.xDistance > this.yDistance) {
                    return false;
                }
        }

        return super.onInterceptTouchEvent(ev);
    }
}
