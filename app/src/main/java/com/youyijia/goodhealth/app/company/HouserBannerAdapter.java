package com.youyijia.goodhealth.app.company;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.youyijia.goodhealth.R;
import com.youyijia.goodhealth.entity.MyHouseInfo;

import java.util.ArrayList;

/**
 * Created by wangqiang on 2019/4/23.
 */
public abstract class HouserBannerAdapter extends PagerAdapter {

    private final ArrayList<MyHouseInfo.InnerImgListBean> datas;
    private Context mContext;
    public static int selectPostion = 3;

    public HouserBannerAdapter(Context context, ArrayList<MyHouseInfo.InnerImgListBean> data) {
        mContext = context;
        this.datas = data;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_banner_house, container, false);
        ImageView iv_house = view.findViewById(R.id.iv_house);

        RequestOptions options = new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL).fitCenter().skipMemoryCache(true).placeholder(R.mipmap.zz_zxal_mrbj_icon)
                .error(R.mipmap.zz_zxal_mrbj_icon);
        Glide.with(mContext)
                .load(datas.get(position).getUrl())
                .apply(options)
                .into(iv_house);
        view.setOnClickListener(view1 -> {
            onItemClick(position);
            selectPostion = position;
        });

        /*if (selectPostion == position) {
        } else {
        }*/

        container.addView(view);
        return view;
    }

    @Override
    public float getPageWidth(int position) {
        return (float) 0.33;
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