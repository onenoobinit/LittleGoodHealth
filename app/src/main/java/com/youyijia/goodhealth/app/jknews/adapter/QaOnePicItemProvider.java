package com.youyijia.goodhealth.app.jknews.adapter;

import android.content.Intent;
import android.graphics.Color;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.annotation.ItemProviderTag;
import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.youyijia.goodhealth.Constants;
import com.youyijia.goodhealth.R;
import com.youyijia.goodhealth.app.jknews.QaType;
import com.youyijia.goodhealth.app.web.NewsWebActivity;
import com.youyijia.goodhealth.entity.JkNewsInfo;

/**
 * Created by wangqiang on 2019/4/12.
 */

@ItemProviderTag(
        viewType = QaType.QAONEPIC,
        layout = R.layout.rl_zj_adapter_one_picture
)
public class QaOnePicItemProvider extends BaseItemProvider<JkNewsInfo, BaseViewHolder> {
    @Override
    public void convert(BaseViewHolder helper, JkNewsInfo data, int position) {
        helper.setText(R.id.tv_one_title, data.getTitle());
        ImageView iv_item_tag = helper.getView(R.id.iv_item_tag);
        if ("孕妇".equals(data.getLabel().getText())) {
            helper.setTextColor(R.id.tv_one_tag, Color.parseColor("#FFC35A"));
            iv_item_tag.setImageResource(R.mipmap.ic_new_yunfu);
        } else if ("儿童".equals(data.getLabel().getText())) {
            helper.setTextColor(R.id.tv_one_tag, Color.parseColor("#FFA5A5"));
            iv_item_tag.setImageResource(R.mipmap.ic_new_children);
        } else if ("心理".equals(data.getLabel().getText())) {
            helper.setTextColor(R.id.tv_one_tag, Color.parseColor("#90E849"));
            iv_item_tag.setImageResource(R.mipmap.ic_new_nurse);
        } else if ("女性".equals(data.getLabel().getText())) {
            helper.setTextColor(R.id.tv_one_tag, Color.parseColor("#FF89C1"));
            iv_item_tag.setImageResource(R.mipmap.ic_new_women);
        } else if ("老人".equals(data.getLabel().getText())) {
            helper.setTextColor(R.id.tv_one_tag, Color.parseColor("#BD9DFF"));
            iv_item_tag.setImageResource(R.mipmap.ic_new_old);
        } else if ("综合".equals(data.getLabel().getText())) {
            helper.setTextColor(R.id.tv_one_tag, Color.parseColor("#4EB2FF"));
            iv_item_tag.setImageResource(R.mipmap.ic_new_zonghe);
        }
        helper.setText(R.id.tv_one_tag, data.getLabel().getText());
        helper.setText(R.id.tv_one_look, data.getPageviews() + "");
        ImageView iv_one_item = helper.getView(R.id.iv_one_item);
        RequestOptions options = new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL).fitCenter().skipMemoryCache(true).placeholder(R.mipmap.zz_zxal_mrbj_icon)
                .error(R.mipmap.zz_zxal_mrbj_icon);
        Glide.with(mContext)
                .load(data.getTitleImgUrl1())
                .apply(options)
                .into(iv_one_item);
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
}
