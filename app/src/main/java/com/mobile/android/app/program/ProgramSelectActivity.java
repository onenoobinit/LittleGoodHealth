package com.mobile.android.app.program;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobile.android.R;
import com.mobile.android.app.program.adapter.DateAdapter;
import com.mobile.android.utils.DateUtils;
import com.mobile.android.widgets.ObservableScrollView;
import com.mobile.hyoukalibrary.base.BaseActivity;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by wangqiang on 2019/1/6.
 */
public class ProgramSelectActivity extends BaseActivity {
    @BindView(R.id.logi_tool_bar)
    Toolbar logiToolBar;
    @BindView(R.id.tv_start_py)
    TextView tvStartPy;
    @BindView(R.id.tv_start_hy)
    TextView tvStartHy;
    @BindView(R.id.tv_end_hy)
    TextView tvEndHy;
    @BindView(R.id.tv_end_py)
    TextView tvEndPy;
    @BindView(R.id.all_set_line)
    AutoLinearLayout allSetLine;
    @BindView(R.id.tv_just)
    TextView tvJust;
    @BindView(R.id.tv_prepare)
    TextView tvPrepare;
    @BindView(R.id.tv_tray)
    TextView tvTray;
    @BindView(R.id.tv_bulk)
    TextView tvBulk;
    @BindView(R.id.all_program_dialog)
    AutoLinearLayout allProgramDialog;
    @BindView(R.id.collapsingToolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.v_line)
    View vLine;
    @BindView(R.id.rv_date)
    RecyclerView rvDate;
    @BindView(R.id.appbarLayout)
    AppBarLayout appbarLayout;
    @BindView(R.id.all_compresive_sort)
    AutoLinearLayout allCompresiveSort;
    @BindView(R.id.all_sale_sort)
    AutoLinearLayout allSaleSort;
    @BindView(R.id.all_price_sort)
    AutoLinearLayout allPriceSort;
    @BindView(R.id.fl_content)
    AutoRelativeLayout flContent;
    @BindView(R.id.iv_pare_sort)
    ImageView ivPareSort;
    @BindView(R.id.tv_pare_sort)
    TextView tvPareSort;
    @BindView(R.id.iv_sale_sort)
    ImageView ivSaleSort;
    @BindView(R.id.tv_sale_sort)
    TextView tvSaleSort;
    @BindView(R.id.iv_price_sort)
    ImageView ivPriceSort;
    @BindView(R.id.tv_price_sort)
    TextView tvPriceSort;
    @BindView(R.id.scroll)
    ObservableScrollView scroll;
    private DateAdapter dateAdapter;
    private ArrayList<String> dates = new ArrayList();

    @Override
    protected void initViews(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        initScroll();
        initRecycleView();
        TextView viewById = findViewById(R.id.tv_next);
        viewById.setOnClickListener(view -> startActivity(new Intent(this, ProgramDetailActivity.class)));
    }

    private void initScroll() {
        scroll.setScrollViewListener((view, scrollX, scrollY, clampedX, clampedY) -> {
            if (scrollY == 0) {
                vLine.setVisibility(View.GONE);
            } else {
                vLine.setVisibility(View.VISIBLE);
            }
        });
    }

    private void initRecycleView() {
        for (int i = 2; i < 12; i++) {
            String oldDate = DateUtils.getOldDate(i);
            dates.add(oldDate);
        }
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvDate.setLayoutManager(manager);
        dateAdapter = new DateAdapter(ProgramSelectActivity.this, dates);
        rvDate.setAdapter(dateAdapter);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_program_select;
    }

    @Override
    public void initToolBar() {
        logiToolBar.setNavigationOnClickListener(view -> finish());
    }


    @OnClick({R.id.tv_just, R.id.tv_prepare, R.id.tv_tray, R.id.tv_bulk, R.id.all_compresive_sort, R.id.all_sale_sort, R.id.all_price_sort})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_just:
                initSelectColor(tvJust, tvPrepare, 1);
                break;
            case R.id.tv_prepare:
                initSelectColor(tvJust, tvPrepare, 2);
                break;
            case R.id.tv_tray:
                initSelectColor(tvTray, tvBulk, 1);
                break;
            case R.id.tv_bulk:
                initSelectColor(tvTray, tvBulk, 2);
                break;
            case R.id.all_compresive_sort:
                initSortColor(0);
                break;
            case R.id.all_sale_sort:
                initSortColor(1);
                break;
            case R.id.all_price_sort:
                initSortColor(2);
                break;
        }
    }

    private void initSelectColor(TextView tv1, TextView tv2, int i) {
        if (i == 1) {
            tv1.setBackgroundResource(R.drawable.bg_tv_just);
            tv1.setTextColor(Color.parseColor("#ffffff"));
            tv2.setBackgroundResource(R.drawable.bg_tv_back);
            tv2.setTextColor(Color.parseColor("#00A7F7"));
        } else if (i == 2) {
            tv1.setBackgroundResource(R.drawable.bg_tv_back);
            tv1.setTextColor(Color.parseColor("#00A7F7"));
            tv2.setBackgroundResource(R.drawable.bg_tv_just);
            tv2.setTextColor(Color.parseColor("#ffffff"));
        }
    }

    private void initSortColor(int i) {
        if (i == 0) {
            ivPareSort.setImageResource(R.mipmap.zhineng_sort_select);
            ivSaleSort.setImageResource(R.mipmap.sale_sort);
            ivPriceSort.setImageResource(R.mipmap.price_sort);
            tvPareSort.setTextColor(Color.parseColor("#00A7F7"));
            tvSaleSort.setTextColor(Color.parseColor("#575757"));
            tvPriceSort.setTextColor(Color.parseColor("#575757"));
        } else if (i == 1) {
            ivPareSort.setImageResource(R.mipmap.zhineng_sort);
            ivSaleSort.setImageResource(R.mipmap.sale_sort_select);
            ivPriceSort.setImageResource(R.mipmap.price_sort);
            tvPareSort.setTextColor(Color.parseColor("#575757"));
            tvSaleSort.setTextColor(Color.parseColor("#00A7F7"));
            tvPriceSort.setTextColor(Color.parseColor("#575757"));
        } else if (i == 2) {
            ivPareSort.setImageResource(R.mipmap.zhineng_sort);
            ivSaleSort.setImageResource(R.mipmap.sale_sort);
            ivPriceSort.setImageResource(R.mipmap.price_sort_select);
            tvPareSort.setTextColor(Color.parseColor("#575757"));
            tvSaleSort.setTextColor(Color.parseColor("#575757"));
            tvPriceSort.setTextColor(Color.parseColor("#00A7F7"));
        }
    }

}
