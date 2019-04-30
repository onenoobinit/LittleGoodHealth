package com.youyijia.goodhealth.app.company;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.youyijia.goodhealth.R;
import com.youyijia.goodhealth.entity.MyHouseInfo;
import com.youyijia.goodhealth.widgets.GlideCircleTransform;

import java.util.ArrayList;

/**
 * Created by wangqiang on 2019/1/19.
 */
public abstract class ExpertAskBannerOneAdapter extends PagerAdapter {

    private final ArrayList<MyHouseInfo.NurseListBean> datas;
    private Context mContext;
    public static int selectPostion = 3;

    public ExpertAskBannerOneAdapter(Context context, ArrayList<MyHouseInfo.NurseListBean> data) {
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
        View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_banner, container, false);
        LinearLayout ll_llzx = view.findViewById(R.id.ll_llzx);
        ImageView iv_house_head = view.findViewById(R.id.iv_house_head);
        TextView tv_name = view.findViewById(R.id.tv_name);
        TextView tv_content = view.findViewById(R.id.tv_content);

        tv_name.setText(datas.get(position).getNurseName());
        tv_content.setText(datas.get(position).getNurseIntroduction());
        Glide.with(mContext)
                .load(datas.get(position).getNurseImg())
                .apply(new RequestOptions().transform(new GlideCircleTransform()))
                .into(iv_house_head);

        ll_llzx.setOnClickListener(v -> {
            Intent telIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + datas.get(position).getNursePhone()));
            mContext.startActivity(telIntent);
        });
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
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    public abstract void onItemClick(int position);
}
