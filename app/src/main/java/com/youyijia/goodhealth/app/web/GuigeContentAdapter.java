package com.youyijia.goodhealth.app.web;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.youyijia.goodhealth.R;
import com.youyijia.goodhealth.entity.ShopDetailInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangqiang on 2019/4/24.
 */
public class GuigeContentAdapter extends RecyclerView.Adapter<GuigeContentAdapter.MyViewHolder> {

    private final Context mContext;
    private final int onePostion;
    private List<ShopDetailInfo.SpecificationsBeanX.SpecificationsBean.AttrvalueBean> datas;
    public static int selectPostion = -1;
    private HashMap<Integer, Integer> status = new HashMap<>();


    public GuigeContentAdapter(Context mContext, List<ShopDetailInfo.SpecificationsBeanX.SpecificationsBean.AttrvalueBean> portList, int onePostion) {
        this.mContext = mContext;
        this.datas = portList;
        this.onePostion = onePostion;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.adapter_guige_content, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.cb_gg_item.setText(datas.get(position).getInfoName());
        holder.cb_gg_item.setOnCheckedChangeListener((button, isCheck) -> {
            if (isCheck) {
               /* holder.cb_gg_item.setBackgroundResource(R.drawable.bg_gg_check);
                holder.cb_gg_item.setTextColor(Color.parseColor("#FFFFFF"));
                selectPostion = position;
                if (popupItemClickListener != null) {
                    popupItemClickListener.popupItemClick(position, onePostion);
                }*/

//                status.put(onePostion, position);
            } else {
                /*selectPostion = -1;


                holder.cb_gg_item.setTextColor(Color.parseColor("#aaaaaa"));
*/
                /*Iterator<Map.Entry<Integer, Integer>> it = status.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry<Integer, Integer> entry = it.next();
                    Integer key = entry.getKey();
                    if (key == position) {
                        it.remove();
                    }
                }*/
            }
            notifyDataSetChanged();
        });

        /*if (selectPostion == position) {
            holder.cb_gg_item.setBackgroundResource(R.drawable.bg_gg_check);
            holder.cb_gg_item.setTextColor(Color.parseColor("#FFFFFF"));
        } else {
            holder.cb_gg_item.setBackgroundResource(R.drawable.bg_gg_uncheck);
            holder.cb_gg_item.setTextColor(Color.parseColor("#aaaaaa"));
        }*/
    }


    @Override
    public int getItemCount() {
        return datas != null ? datas.size() : 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private final CheckBox cb_gg_item;

        public MyViewHolder(View itemView) {
            super(itemView);
            cb_gg_item = itemView.findViewById(R.id.cb_gg_item);

        }
    }

    public Map<Integer, Integer> getStauts() {
        return status;
    }

//    public abstract void setOnSecondClick(int position);

    //设置回调方法，在popupwindow条目点击事件中调用，传递值给activity刷新数据
    public interface PopupItemClickListener {
        void popupItemClick(int postion, int onepostion);
    }


    private PopupItemClickListener popupItemClickListener;

    public void setOnPopupItemClickListener(PopupItemClickListener popupItemClickListener) {
        this.popupItemClickListener = popupItemClickListener;
    }

}
