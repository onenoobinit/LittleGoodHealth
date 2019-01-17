package com.mobile.android.app.search;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mobile.android.R;
import com.mobile.android.utils.FuzzySearchBaseAdapter;
import com.mobile.android.utils.IFuzzySearchRule;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.List;

/**
 * Created by wangqiang on 2019/1/15.
 */
public abstract class FuzzySearchAdapter extends FuzzySearchBaseAdapter<ItemEntity, FuzzySearchAdapter.ItemHolder> {
    private String cityName = "";

    public FuzzySearchAdapter() {
        super(null);
    }

    public FuzzySearchAdapter(IFuzzySearchRule rule) {
        super(rule);
    }

    public FuzzySearchAdapter(List<ItemEntity> dataList) {
        super(null, dataList);
    }

    public FuzzySearchAdapter(IFuzzySearchRule rule, List<ItemEntity> dataList) {
        super(rule, dataList);
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FuzzySearchAdapter.ItemHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_result, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        cityName = "";
        String value = mDataList.get(position).getValue();
        String[] split = value.split(",");
        holder.tv_result_sx.setText(split[0]);
        holder.tv_result_city.setText(split[2]);

        if (split[1].contains(",")) {
            String[] split1 = split[1].split(",");
            holder.tv_result_name.setText(split1[0]);
            cityName = split1[0];
        } else if (split[1].contains("，")) {
            String[] split1 = split[1].split("，");
            holder.tv_result_name.setText(split1[0]);
            cityName = split1[0];
        } else {
            holder.tv_result_name.setText(split[1]);
            cityName = split[1];
        }

        holder.all_result_item.setOnClickListener(view -> {
            setOnItemClickListener(split[0] + "," + cityName);
        });
    }

    static class ItemHolder extends RecyclerView.ViewHolder {

        TextView tv_result_sx;
        TextView tv_result_name;
        TextView tv_result_city;
        AutoLinearLayout all_result_item;

        ItemHolder(View itemView) {
            super(itemView);
            tv_result_sx = itemView.findViewById(R.id.tv_result_sx);
            tv_result_name = itemView.findViewById(R.id.tv_result_name);
            tv_result_city = itemView.findViewById(R.id.tv_result_city);
            all_result_item = itemView.findViewById(R.id.all_result_item);
        }
    }

    public abstract void setOnItemClickListener(String position);
}
