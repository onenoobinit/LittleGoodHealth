package com.mobile.android.widgets;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ScrollView;

/**
 * Created by wangqiang on 2019/1/8.
 */
public class LazyScrollView extends ScrollView {

    /**
     * 子View
     */
    private View mChild;

    /**
     * 触摸点在Y轴方向上的坐标
     */
    private float mTouchY;

    /**
     * 用于保存子View开始调用layout重新布局前的位置信息
     * 确保松手后能恢复原来的位置
     */
    private Rect mOldRect = new Rect();

    /**
     * 用于标识当前动画是否正在执行
     */
    private boolean mAnimation = false;

    /**
     * 动画执行时间
     */
    private static final int DURATION = 200;


    private int mOffest;

    public LazyScrollView(Context context) {
        this(context, null);
    }

    public LazyScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LazyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (getChildCount() > 0) {
            mChild = getChildAt(0);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        mOffest = getMeasuredHeight() / 2;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (mChild == null) {
            return super.onTouchEvent(ev);
        } else {
            if (mAnimation) {
                return super.onTouchEvent(ev);
            }

            int action = ev.getAction();
            switch (action) {
                case MotionEvent.ACTION_DOWN:
                    mTouchY = ev.getY();
                    break;
                case MotionEvent.ACTION_UP:
                    mTouchY = 0;
                    if (!mOldRect.isEmpty()) {
                        runAnimation();
                    }
                    break;
                case MotionEvent.ACTION_MOVE:
                    //此处为了防止子View还没完全复原，又快速触摸屏幕时出现跳屏现象
                    final float preY = mTouchY == 0 ? ev.getY() : mTouchY;
                    //获取滑动后的位置
                    float moveY = ev.getY();

                    //计算当前位置与上一个位置的差值
                    int diffY = (int) (preY - moveY + 0.5f);

                    //更新位置信息
                    mTouchY = moveY;

                    /**
                     * 这里获取ScrollView的底部与子View底部的差值
                     * 如果这个差值等与getScrollY()的是那就证明已经滚动到了底部临界点
                     */
                    int offset = mChild.getMeasuredHeight() - getHeight();

                    /**
                     * 该值等于0,证明滚动到了顶部
                     */
                    int scrollY = getScrollY();
                    if (scrollY == 0 || scrollY == offset) {
                        if (mOldRect.isEmpty()) {
                            /**
                             * 保存原有的位置信息
                             */
                            mOldRect.set(mChild.getLeft(), mChild.getTop(), mChild.getRight(), mChild.getBottom());
                        }


                        //随着移动的增加，逐渐增加粘性效果
                        int df = 2;
                        if (Math.abs(mChild.getTop()) > mOffest / 3) {
                            df = 3;
                        } else if (Math.abs(mChild.getTop()) > mOffest / 2) {
                            df = 4;
                        } else if (Math.abs(mChild.getTop()) > mOffest) {
                            df = 5;
                        }

                        /**
                         * 重新进行布局操作
                         */
                        mChild.layout(mChild.getLeft(), mChild.getTop() - diffY / df, mChild.getRight(), mChild.getBottom() - diffY / df);
                    } else {
                        super.onTouchEvent(ev);
                    }
                    break;
                default:
                    break;
            }

        }
        return super.onTouchEvent(ev);
    }


    /**
     * 恢复View的位置时的动画实现
     */
    public void runAnimation() {
        TranslateAnimation ta = new TranslateAnimation(0, 0, 0, mOldRect.top - mChild.getTop());
        ta.setDuration(DURATION);
        ta.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                mAnimation = true;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mChild.clearAnimation();
                mChild.layout(mOldRect.left, mOldRect.top, mOldRect.right, mOldRect.bottom);
                mOldRect.setEmpty();//清除位置信息
                mAnimation = false;
            }
        });
        mChild.startAnimation(ta);
    }
}
