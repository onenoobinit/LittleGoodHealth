package com.mobile.android.app.submit;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobile.android.R;
import com.mobile.android.entity.SubmitInfo;
import com.zhy.autolayout.AutoLinearLayout;

/**
 * Created by wangqiang on 2019/1/19.
 */
public abstract class ExpertAskBannerOneAdapter extends PagerAdapter {
    private final SubmitInfo.GoodsExtensionInsuranceBean.PlanListBean.Plan1Bean mData1;
    private final SubmitInfo.GoodsExtensionInsuranceBean.PlanListBean.Plan2Bean mData2;
    private final SubmitInfo.GoodsExtensionInsuranceBean.PlanListBean.Plan3Bean mData3;
    private final String valid;
    //    private final String type;
    private SubmitInfo.GoodsExtensionInsuranceBean.PlanListBean mData;
    private Context mContext;
    public static int selectPostion = 3;
    private String payoutRatio;
    private String costRatio;
    private String insuranceFee;

    public ExpertAskBannerOneAdapter(Context context, SubmitInfo.GoodsExtensionInsuranceBean.PlanListBean.Plan1Bean data1
            , SubmitInfo.GoodsExtensionInsuranceBean.PlanListBean.Plan2Bean data2, SubmitInfo.GoodsExtensionInsuranceBean.PlanListBean.Plan3Bean data3
            , String valid) {
        mData1 = data1;
        mData2 = data2;
        mData3 = data3;
        mContext = context;
        this.valid = valid;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_banner, container, false);
        TextView tv_banner_style = view.findViewById(R.id.tv_banner_style);
        ImageView iv_banner_select = view.findViewById(R.id.iv_banner_select);
        TextView tv_banner_proport = view.findViewById(R.id.tv_banner_proport);
        TextView tv_banner_fl = view.findViewById(R.id.tv_banner_fl);
        TextView tv_banner_bf = view.findViewById(R.id.tv_banner_bf);
        if (position == 0) {
            tv_banner_style.setText("方案一");
        } else if (position == 1) {
            tv_banner_style.setText("方案二");
        } else if (position == 2) {
            tv_banner_style.setText("方案三");
        }
        if (position == 0 && mData1 != null) {
            payoutRatio = mData1.getPayoutRatio();
            costRatio = mData1.getCostRatio();
            insuranceFee = mData1.getInsuranceFee();
        }
        if (position == 1 && mData2 != null) {
            payoutRatio = mData2.getPayoutRatio();
            costRatio = mData2.getCostRatio();
            insuranceFee = mData2.getInsuranceFee();
        }

        if (position == 2 && mData3 != null) {
            payoutRatio = mData3.getPayoutRatio();
            costRatio = mData3.getCostRatio();
            insuranceFee = mData3.getInsuranceFee();
        }

        tv_banner_proport.setText(payoutRatio);
        tv_banner_fl.setText(costRatio);
        tv_banner_bf.setText(insuranceFee);
        AutoLinearLayout all_item_banner = view.findViewById(R.id.all_item_banner);
        if ("0".equals(valid)) {
            view.setClickable(false);
        } else if ("1".equals(valid)) {
            view.setClickable(true);
            view.setOnClickListener(view1 -> {
                onItemClick(position);
                selectPostion = position;
            });

            if (selectPostion == position) {
                all_item_banner.setBackgroundResource(R.drawable.bg_banner_chek);
                iv_banner_select.setVisibility(View.VISIBLE);
            } else {
                all_item_banner.setBackgroundResource(R.drawable.bg_banner);
                iv_banner_select.setVisibility(View.GONE);
            }
        }

        container.addView(view);
        return view;
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    public abstract void onItemClick(int position);
}
