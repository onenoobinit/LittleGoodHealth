package com.youyijia.goodhealth.app.shopcart;

import android.content.Context;
import android.graphics.Paint;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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
 * Created by lipeng
 * 2017/6/5.
 */

public abstract class CartAdapter extends BaseAdapter {

    private final ArrayList<ShopCartDataInfo> goodsList;
    private Context context;
    private ViewHolder vh;
    private Map<Integer, Integer> pitchOnMap;
    private RefreshPriceInterface refreshPriceInterface;

    public CartAdapter(Context context, ArrayList<ShopCartDataInfo> list) {
        this.context = context;
        this.goodsList = list;

        pitchOnMap = new HashMap<>();
        for (int i = 0; i < goodsList.size(); i++) {
            pitchOnMap.put(goodsList.get(i).getCommodityId(), 1);
        }
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {
        vh = new ViewHolder();
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_shopcart_product, null);

            vh.checkBox = (CheckBox) view.findViewById(R.id.check_box);
            vh.icon = (ImageView) view.findViewById(R.id.iv_adapter_list_pic);
            vh.name = (TextView) view.findViewById(R.id.tv_goods_name);
            vh.price = (TextView) view.findViewById(R.id.tv_goods_price);
            vh.type = (TextView) view.findViewById(R.id.tv_type_size);
            vh.num = (EditText) view.findViewById(R.id.tv_num);
            vh.reduce = (TextView) view.findViewById(R.id.tv_reduce);
            vh.add = (TextView) view.findViewById(R.id.tv_add);

            view.setTag(vh);
        } else {
            vh = (ViewHolder) view.getTag();
        }

        if (goodsList.size() > 0) {
            if (pitchOnMap.get(goodsList.get(position).getCommodityId()) == 0)
                vh.checkBox.setChecked(false);
            else vh.checkBox.setChecked(true);

            vh.name.setText(goodsList.get(position).getCommodity().getName());
            vh.num.setText(goodsList.get(position).getCommodityCount() + "");
            vh.price.setText("¥ " + goodsList.get(position).getCommodity().getSpecifications().getCommodityItem().get(0).getPresentPrice());
            vh.type.setText("¥ " + goodsList.get(position).getCommodity().getSpecifications().getCommodityItem().get(0).getOriginalPrice());
            vh.type.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);

            vh.num.addTextChangedListener(new TextWatcher() {
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
                        setOnEdit(position, content);
                    }
                }
            });
            RequestOptions options = new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL).fitCenter().skipMemoryCache(true).placeholder(R.mipmap.zz_zxal_mrbj_icon)
                    .error(R.mipmap.zz_zxal_mrbj_icon);
            Glide.with(context)
                    .load(goodsList.get(position).getCommodity().getCoverImage())
                    .apply(options)
                    .into(vh.icon);
//            refreshPriceInterface.refreshPrice(pitchOnMap);

            vh.checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final int index = position;
                    if (((CheckBox) view).isChecked())
                        pitchOnMap.put(goodsList.get(index).getCommodityId(), 1);
                    else pitchOnMap.put(goodsList.get(index).getCommodityId(), 0);
                    refreshPriceInterface.refreshPrice(pitchOnMap);
                }
            });
            vh.reduce.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    setReduce(position);
                    refreshPriceInterface.refreshPrice(pitchOnMap);
                }
            });
            vh.add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    setAdd(position);
                    refreshPriceInterface.refreshPrice(pitchOnMap);
                }
            });
        }

        return view;
    }

    public Map<Integer, Integer> getPitchOnMap() {
        return pitchOnMap;
    }

    public void setPitchOnMap(Map<Integer, Integer> pitchOnMap) {
        this.pitchOnMap = new HashMap<>();
        this.pitchOnMap.clear();
        this.pitchOnMap.putAll(pitchOnMap);
    }

    public interface RefreshPriceInterface {
        void refreshPrice(Map<Integer, Integer> pitchOnMap);
    }

    public void setRefreshPriceInterface(RefreshPriceInterface refreshPriceInterface) {
        this.refreshPriceInterface = refreshPriceInterface;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public int getCount() {

        return goodsList == null ? 0 : goodsList.size();
    }

    class ViewHolder {
        CheckBox checkBox;
        ImageView icon;
        TextView name, price, type, reduce, add;
        EditText num;
    }

    public abstract void setAdd(int position);

    public abstract void setReduce(int position);

    public abstract void setOnEdit(int position, String s);

}
