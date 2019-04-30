package com.youyijia.goodhealth.app.web;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.youyijia.goodhealth.R;
import com.youyijia.goodhealth.entity.CommentListInfo;
import com.youyijia.goodhealth.widgets.GlideCircleTransform;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by wangqiang on 2019/4/17.
 */
public abstract class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.MyViewHolder> {

    private final Context mContext;
    private ArrayList<CommentListInfo> datas;
    public static String zan = "";
    public static int selectPostion = -1;

    public CommentAdapter(Context context, ArrayList<CommentListInfo> datas) {
        this.mContext = context;
        this.datas = datas;
    }

    public void setData(ArrayList<CommentListInfo> datas) {
        this.datas = datas;
    }

    public ArrayList<CommentListInfo> getData() {
        return datas;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.adapter_commment, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        Glide.with(mContext)
                .load(datas.get(i).getCustomerPortrait())
                .apply(new RequestOptions().transform(new GlideCircleTransform()).error(R.mipmap.zz_zxal_mrbj_icon))
                .into(myViewHolder.iv_comment);
        myViewHolder.tv_comment_name.setText(datas.get(i).getCustomerName());
        myViewHolder.tv_comment_date.setText(datas.get(i).getCommentTime());
        myViewHolder.tv_comment_content.setText(datas.get(i).getCommentContent());
        myViewHolder.tv_zan_number.setText(datas.get(i).getLikeCounts() + "");
        if ("LIKE".equals(datas.get(i).getArticleCommentLikeState().getName())) {
            myViewHolder.cb_zan_staust.setChecked(true);
        } else {
            myViewHolder.cb_zan_staust.setChecked(false);
        }

        myViewHolder.cb_zan_staust.setOnCheckedChangeListener((button, isCheck) -> {
            int numbers = Integer.parseInt(myViewHolder.tv_zan_number.getText().toString().trim());
            if (isCheck) {
                numbers = numbers + 1;
                myViewHolder.tv_zan_number.setText(numbers + "");
                setOnzan(i);
            } else {
                numbers = numbers - 1;
                myViewHolder.tv_zan_number.setText(numbers + "");
                setOnzan(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas != null ? datas.size() : 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private final CircleImageView iv_comment;
        private final CheckBox cb_zan_staust;
        private final TextView tv_comment_name;
        private final TextView tv_comment_date;
        private final TextView tv_comment_content;
        private final TextView tv_zan_number;
        private final RelativeLayout rl_commnet_item;
        private final LinearLayout ll_zan;


        public MyViewHolder(View itemView) {
            super(itemView);
            iv_comment = itemView.findViewById(R.id.iv_comment);
            cb_zan_staust = itemView.findViewById(R.id.cb_zan_staust);
            tv_comment_name = itemView.findViewById(R.id.tv_comment_name);
            tv_comment_date = itemView.findViewById(R.id.tv_comment_date);
            tv_zan_number = itemView.findViewById(R.id.tv_zan_number);
            tv_comment_content = itemView.findViewById(R.id.tv_comment_content);
            rl_commnet_item = itemView.findViewById(R.id.rl_commnet_item);
            ll_zan = itemView.findViewById(R.id.ll_zan);

        }
    }

    public abstract void setOnzan(int position);
}
