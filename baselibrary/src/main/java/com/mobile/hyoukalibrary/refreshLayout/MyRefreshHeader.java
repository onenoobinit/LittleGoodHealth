package com.mobile.hyoukalibrary.refreshLayout;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mobile.hyoukalibrary.R;


/**
 * 描述:
 * ------------------------------------------------------------------------
 * 工程:
 * #0000     tian xiao     创建日期: 2017-03-15 15:46
 */
public class MyRefreshHeader extends FrameLayout implements RefreshHeader {

    private AnimationDrawable rotate_infinite;
    private ImageView arrowIcon;
    private ImageView loadingIcon;
    private Boolean isReFreshEnd = false;
    private TextView tvTitle;
    private RelativeLayout headerContent;

    public MyRefreshHeader(Context context) {
        this(context, null);
    }

    public MyRefreshHeader(Context context, AttributeSet attrs) {
        super(context, attrs);

        // 初始化动画
        inflate(context, R.layout.header, this);

        arrowIcon = (ImageView) findViewById(R.id.arrowIcon);
        headerContent = (RelativeLayout) findViewById(R.id.header_content);
        loadingIcon = (ImageView) findViewById(R.id.loadingIcon);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        //刷新动画
        rotate_infinite = (AnimationDrawable) loadingIcon.getDrawable();
    }

    @Override
    public void reset() {
        arrowIcon.setVisibility(VISIBLE);
        tvTitle.setText("下拉刷新");
        loadingIcon.setVisibility(INVISIBLE);
        loadingIcon.clearAnimation();
    }

    @Override
    public void pull() {
        isReFreshEnd = false;
    }

    @Override
    public void refreshing() {
        arrowIcon.setVisibility(INVISIBLE);
        loadingIcon.setVisibility(VISIBLE);
        rotate_infinite.start();
        tvTitle.setText("正在刷新中....");
    }

    private int[] drawables = {R.drawable.m9, R.drawable.m10, R.drawable.m11, R.drawable.m12,
            R.drawable.m13, R.drawable.m14, R.drawable.m15};

    @Override
    public void onPositionChange(float currentPos, float lastPos, float refreshPos, boolean isTouch, State state) {
//        Log.d("onPositionChange","currentPos:"+currentPos+"     lastPos:"+lastPos
//        +" refreshPos:"+refreshPos+"   isTouch:"+isTouch);
        int pos = (int) currentPos / 20;
        if (currentPos <= 120 && !isReFreshEnd && pos >= 0) {
            arrowIcon.setImageResource(drawables[pos]);
            tvTitle.setText("下拉刷新");
        } else if (currentPos > 120 && isTouch && pos >= 0) {
            arrowIcon.setImageResource(drawables[drawables.length - 1]);
            tvTitle.setText("够啦,松开刷给你看");
        }


        if (!isTouch && state == State.COMPLETE) {
            tvTitle.setText("刷新成功啦");
        }

        // 往上拉
        if (currentPos < refreshPos && lastPos >= refreshPos) {
            if (isTouch && state == State.PULL) {

            }
            // 往下拉
        } else if (currentPos > refreshPos && lastPos <= refreshPos) {
            if (isTouch && state == State.PULL) {

            }
        }
    }


    @Override
    public void complete() {
        //loadingIcon.setVisibility(INVISIBLE);
        //rotate_infinite.stop();
        // loadingIcon.clearAnimation();
        isReFreshEnd = true;
        //arrowIcon.setVisibility(VISIBLE);
    }
}
