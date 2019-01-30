package com.mobile.android.app.program.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.mobile.android.R;
import com.mobile.android.entity.ProgramSelectInfo;

import java.util.List;

/**
 * Created by wangqiang on 2019/1/28.
 */
public class TestLeftAdapter extends RecyclerView.Adapter<TestLeftAdapter.MyViewHolder> {

    private final Context mContext;
    private final List<ProgramSelectInfo.AirlineListBean> datas;


    public TestLeftAdapter(Context context, List<ProgramSelectInfo.AirlineListBean> weights) {
        this.mContext = context;
        this.datas = weights;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.recyclerview_item_search_sort_left, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Glide.with(mContext)
                .load(datas.get(position).getImgLink())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .fitCenter()
                .skipMemoryCache(true)
                .dontTransform()
                .placeholder(R.mipmap.zz_zxal_mrbj_icon)
                .error(R.mipmap.zz_zxal_mrbj_icon)
                .into(holder.iv_left_icon);

        holder.tv_left_name.setText(datas.get(position).getNameAbbr());
    }

    @Override
    public int getItemCount() {
        return datas != null ? datas.size() : 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private final ImageView iv_left_icon;
        private final TextView tv_left_name;

        public MyViewHolder(View itemView) {
            super(itemView);
            iv_left_icon = itemView.findViewById(R.id.iv_left_icon);
            tv_left_name = itemView.findViewById(R.id.tv_left_name);
        }
    }
}
