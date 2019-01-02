package com.mobile.android.app.desinger;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mobile.android.R;

import java.util.ArrayList;

/**
 * Created by wangqiang on 2017/4/13.
 */

public class GridviewAdapter extends BaseAdapter {
    private final Context context;
    private final ArrayList<String> list;
    private TextView tv;
    private View view;
    public int selectIndex = 0;


    public GridviewAdapter(Context context, ArrayList<String> list) {
        this.context = context;
        this.list = list;

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        view = LayoutInflater.from(context).inflate(R.layout.search_label_tv, null);
        tv = (TextView) view.findViewById(R.id.popup_text);
        tv.setText(list.get(position));

        if (position == selectIndex) {
            tv.setTextColor(Color.parseColor("#a6a6a6"));
            tv.setBackgroundResource(R.drawable.search_label_pressed);
        }else{
            tv.setTextColor(Color.parseColor("#696969"));
            tv.setBackgroundResource(R.drawable.tv_press);
        }
        return view;
    }

}
