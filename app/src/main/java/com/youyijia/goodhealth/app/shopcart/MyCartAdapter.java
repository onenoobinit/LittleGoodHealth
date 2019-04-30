package com.youyijia.goodhealth.app.shopcart;

import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.youyijia.goodhealth.R;
import com.youyijia.goodhealth.entity.ShopCartDataInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangqiang on 2019/4/28.
 */
public abstract class MyCartAdapter extends RecyclerView.Adapter<MyCartAdapter.MyViewHolder> {

    private final Context mContext;
    private ArrayList<ShopCartDataInfo> datas;
    private Map<Integer, Integer> pitchOnMap;
    private RefreshPriceInterface refreshPriceInterface;

    public MyCartAdapter(Context mContext, ArrayList<ShopCartDataInfo> portList) {
        this.mContext = mContext;
        this.datas = portList;
    }

    public void setData(ArrayList<ShopCartDataInfo> inspirations) {
        this.datas = inspirations;
        pitchOnMap = new HashMap<>();
        for (int i = 0; i < datas.size(); i++) {
            pitchOnMap.put(datas.get(i).getCommodityId(), 1);
        }
    }

    public ArrayList<ShopCartDataInfo> getData() {
        return datas;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_shopcart_product, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        RequestOptions options = new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL).fitCenter().skipMemoryCache(true).placeholder(R.mipmap.zz_zxal_mrbj_icon)
                .error(R.mipmap.zz_zxal_mrbj_icon);
        Glide.with(mContext)
                .load(datas.get(position).getCommodity().getCoverImage())
                .apply(options)
                .into(holder.icon);

        holder.name.setText(datas.get(position).getCommodity().getName());
        holder.num.setText(datas.get(position).getCommodityCount() + "");
        holder.price.setText("¥ " + datas.get(position).getCommodity().getSpecifications().getCommodityItem().get(0).getPresentPrice());
        holder.type.setText("¥ " + datas.get(position).getCommodity().getSpecifications().getCommodityItem().get(0).getOriginalPrice());
        holder.type.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        if (pitchOnMap.get(datas.get(position).getCommodityId()) == 0)
            holder.checkBox.setChecked(false);
        else holder.checkBox.setChecked(true);

        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int index = position;
                if (((CheckBox) view).isChecked())
                    pitchOnMap.put(datas.get(index).getCommodityId(), 1);
                else pitchOnMap.put(datas.get(index).getCommodityId(), 0);
                setOnCheckChange(position);
            }
        });
        holder.reduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setReduce(position);
            }
        });
        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setAdd(position);
            }
        });
        holder.num.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String content = s.toString();
                if (TextUtils.isEmpty(content)) {
                    content = "1";
                } else {
//                    setOnEdit(position, content);
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return datas != null ? datas.size() : 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private final CheckBox checkBox;
        private final ImageView icon;
        private final TextView name;
        private final TextView price;
        private final TextView type;
        private final TextView num;
        private final TextView reduce;
        private final TextView add;

        public MyViewHolder(View itemView) {
            super(itemView);
            checkBox = (CheckBox) itemView.findViewById(R.id.check_box);
            icon = (ImageView) itemView.findViewById(R.id.iv_adapter_list_pic);
            name = (TextView) itemView.findViewById(R.id.tv_goods_name);
            price = (TextView) itemView.findViewById(R.id.tv_goods_price);
            type = (TextView) itemView.findViewById(R.id.tv_type_size);
            num = (EditText) itemView.findViewById(R.id.tv_num);
            reduce = (TextView) itemView.findViewById(R.id.tv_reduce);
            add = (TextView) itemView.findViewById(R.id.tv_add);

        }
    }

    public Map<Integer, Integer> getPitchOnMap() {
        return pitchOnMap;
    }

    public void setPitchOnMap(Map<Integer, Integer> pitchOnMap) {
        this.pitchOnMap = new HashMap<>();
        this.pitchOnMap.clear();
        this.pitchOnMap.putAll(pitchOnMap);
    }

    public abstract void setAdd(int position);

    public abstract void setReduce(int position);

    public abstract void setOnEdit(int position, String s);

    public abstract void setOnCheckChange(int position);

    public interface RefreshPriceInterface {
        void refreshPrice(Map<Integer, Integer> pitchOnMap);
    }

    public void setRefreshPriceInterface(RefreshPriceInterface refreshPriceInterface) {
        this.refreshPriceInterface = refreshPriceInterface;
    }
}

