package com.youyijia.goodhealth.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.youyijia.goodhealth.R;
import com.youyijia.goodhealth.entity.RollMessageInfo;
import com.youyijia.hyoukalibrary.utils.DensityUtil;

import java.util.ArrayList;
import java.util.List;


public class MarqueeView extends ViewFlipper {

    private Context mContext;
    private List<RollMessageInfo> notices;
    private boolean isSetAnimDuration = false;
    private OnItemClickListener onItemClickListener;

    private int interval = 4000;
    private int animDuration = 800;
    private int textSize = 14;
    private int textColor = 0xffffffff;

    private boolean singleLine = false;
    private int gravity = Gravity.LEFT | Gravity.CENTER_VERTICAL;
    private static final int TEXT_GRAVITY_LEFT = 0, TEXT_GRAVITY_CENTER = 1, TEXT_GRAVITY_RIGHT = 2;

    public MarqueeView(Context context) {
        this(context, null);
    }

    public MarqueeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        this.mContext = context;
        if (notices == null) {
            notices = new ArrayList<>();
        }

        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.MarqueeViewStyle, defStyleAttr, 0);
        interval = typedArray.getInteger(R.styleable.MarqueeViewStyle_mvInterval, interval);
        isSetAnimDuration = typedArray.hasValue(R.styleable.MarqueeViewStyle_mvAnimDuration);
        singleLine = typedArray.getBoolean(R.styleable.MarqueeViewStyle_mvSingleLine, false);
        animDuration = typedArray.getInteger(R.styleable.MarqueeViewStyle_mvAnimDuration, animDuration);
        if (typedArray.hasValue(R.styleable.MarqueeViewStyle_mvTextSize)) {
            textSize = (int) typedArray.getDimension(R.styleable.MarqueeViewStyle_mvTextSize, textSize);
            textSize = (int) DensityUtil.px2sp(mContext, textSize);
        }
        textColor = typedArray.getColor(R.styleable.MarqueeViewStyle_mvTextColor, textColor);
        int gravityType = typedArray.getInt(R.styleable.MarqueeViewStyle_mvGravity, TEXT_GRAVITY_LEFT);
        switch (gravityType) {
            case TEXT_GRAVITY_CENTER:
                gravity = Gravity.CENTER;
                break;
            case TEXT_GRAVITY_RIGHT:
                gravity = Gravity.RIGHT | Gravity.CENTER_VERTICAL;
                break;
        }
        typedArray.recycle();

        setFlipInterval(interval);
        Animation animIn = AnimationUtils.loadAnimation(mContext, R.anim.anim_marquee_in);
        if (isSetAnimDuration) {
            animIn.setDuration(animDuration);
        }
        setInAnimation(animIn);
        Animation animOut = AnimationUtils.loadAnimation(mContext, R.anim.anim_marquee_out);
        if (isSetAnimDuration) {
            animOut.setDuration(animDuration);
        }
        setOutAnimation(animOut);
    }

   /* // 根据公告字符串启动轮播
    public void startWithText(final String notice) {
        if (TextUtils.isEmpty(notice)) return;
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                getViewTreeObserver().removeGlobalOnLayoutListener(this);
                startWithFixedWidth(notice, getWidth());
            }
        });
    }*/

    // 根据公告字符串列表启动轮播
    public void startWithList(List<RollMessageInfo> notices, int position) {
        setNotices(notices);
        start(position);
    }

    /* // 根据宽度和公告字符串启动轮播
     private void startWithFixedWidth(String notice, int width) {
         int noticeLength = notice.length();
         int dpW = (int) DensityUtil.px2dp(mContext, width);
         int limit = dpW / textSize;
         if (dpW == 0) {
             throw new RuntimeException("Please set MarqueeView width !");
         }

         if (noticeLength <= limit) {
             notices.add(notice);
         } else {
             int size = noticeLength / limit + (noticeLength % limit != 0 ? 1 : 0);
             for (int i = 0; i < size; i++) {
                 int startIndex = i * limit;
                 int endIndex = ((i + 1) * limit >= noticeLength ? noticeLength : (i + 1) * limit);
                 notices.add(notice.substring(startIndex, endIndex));
             }
         }
         start();
     }*/

    // 启动轮播
    public boolean start(int position) {
        removeAllViews();
        if (notices == null || notices.size() == 0) {
            return false;
        }

        for (int i = 0; i < notices.size(); i++) {

            addView(createView(i));
        }

        if (notices.size() > 1) {
            setDisplayedChild(position);
            startFlipping();
        }
        return true;
    }

    // 创建ViewFlipper下的TextView
    private View createView(int position) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.home_marquee, null);
        TextView tv_content = inflate.findViewById(R.id.tv_content);
        TextView tv_red = inflate.findViewById(R.id.tv_red);
        TextView tv_new = inflate.findViewById(R.id.tv_new);
        LinearLayout rl_mq = inflate.findViewById(R.id.rl_mq);
        tv_content.setText(notices.get(position).getTitle());
        if (notices.get(position).getFlag() != null && notices.get(position).getFlag().getText().equals("最新")) {
            tv_red.setVisibility(GONE);
            tv_new.setVisibility(VISIBLE);
        } else {
            tv_new.setVisibility(GONE);
            tv_red.setVisibility(VISIBLE);
        }
        /*Glide.with(mContext)
                .load(notices.get(position).getImg_url())
                .transform(new CircleTransform(mContext))
                .error(R.mipmap.zz_zxal_mrbj_icon)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .skipMemoryCache(true)
                .placeholder(R.mipmap.head_portrait)
                .into(iv_image);*/

        rl_mq.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(position);
            }
        });

        return inflate;
    }

    public int getPosition() {
        return (int) getCurrentView().getTag();
    }

    public List<RollMessageInfo> getNotices() {
        return notices;
    }

    public void setNotices(List<RollMessageInfo> notices) {
        this.notices = notices;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

}
