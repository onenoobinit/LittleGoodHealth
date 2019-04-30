package com.youyijia.goodhealth.app.jknews.adapter;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.annotation.ItemProviderTag;
import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.youyijia.goodhealth.Constants;
import com.youyijia.goodhealth.R;
import com.youyijia.goodhealth.app.jknews.QaType;
import com.youyijia.goodhealth.app.web.NewsWebActivity;
import com.youyijia.goodhealth.entity.JkNewsInfo;

import java.util.List;

/**
 * Created by wangqiang on 2019/4/12.
 */

@ItemProviderTag(
        viewType = QaType.QAMorePIC,
        layout = R.layout.rl_zj_adapter_more_picture
)
public class QaMorePicItemProvider extends BaseItemProvider<JkNewsInfo, BaseViewHolder> {
    @Override
    public void convert(BaseViewHolder helper, JkNewsInfo data, int position) {
        helper.setText(R.id.tv_more_title, data.getTitle());
        helper.setText(R.id.tv_more_look, data.getPageviews() + "");
        ImageView iv_more_tag = helper.getView(R.id.iv_more_tag);
        if ("孕妇".equals(data.getLabel().getText())) {
            helper.setTextColor(R.id.tv_more_tag, Color.parseColor("#FFC35A"));
            iv_more_tag.setImageResource(R.mipmap.ic_new_yunfu);
        }else if ("儿童".equals(data.getLabel().getText())) {
            helper.setTextColor(R.id.tv_more_tag, Color.parseColor("#FFA5A5"));
            iv_more_tag.setImageResource(R.mipmap.ic_new_children);
        }else if ("心理".equals(data.getLabel().getText())) {
            helper.setTextColor(R.id.tv_more_tag, Color.parseColor("#90E849"));
            iv_more_tag.setImageResource(R.mipmap.ic_new_nurse);
        }else if ("女性".equals(data.getLabel().getText())) {
            helper.setTextColor(R.id.tv_more_tag, Color.parseColor("#FF89C1"));
            iv_more_tag.setImageResource(R.mipmap.ic_new_women);
        }else if ("老人".equals(data.getLabel().getText())) {
            helper.setTextColor(R.id.tv_more_tag, Color.parseColor("#BD9DFF"));
            iv_more_tag.setImageResource(R.mipmap.ic_new_old);
        }else if ("综合".equals(data.getLabel().getText())) {
            helper.setTextColor(R.id.tv_more_tag, Color.parseColor("#4EB2FF"));
            iv_more_tag.setImageResource(R.mipmap.ic_new_zonghe);
        }
        helper.setText(R.id.tv_more_tag, data.getLabel().getText());
        RecyclerView rv_more = helper.getView(R.id.rv_more);
        final ImageAdapter imageAdapter = new ImageAdapter(R.layout.rl_zj_adapter_three_pic_item, data.getTitleImgList());
        final LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rv_more.setLayoutManager(layoutManager);
        rv_more.setAdapter(imageAdapter);
        LinearLayout ll_item = helper.getView(R.id.ll_item);
        ll_item.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, NewsWebActivity.class);
            intent.putExtra("url", Constants.JK_URL + data.getInformationId());
            intent.putExtra("title", "健康资讯");
            intent.putExtra("directionId", data.getInformationId());
            intent.putExtra("articleType", "HEALTH_INFORMATION");
            mContext.startActivity(intent);
        });
    }

    @Override
    public void onClick(BaseViewHolder helper, JkNewsInfo data, int position) {

    }

    @Override
    public boolean onLongClick(BaseViewHolder helper, JkNewsInfo data, int position) {
        return false;
    }

    class ImageAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public ImageAdapter(int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            ImageView ivMoreItem = helper.getView(R.id.iv_three_item);
            Glide.with(mContext).load(item).into(ivMoreItem);
        }
    }
}
