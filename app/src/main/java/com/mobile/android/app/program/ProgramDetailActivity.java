package com.mobile.android.app.program;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.mobile.android.R;
import com.mobile.android.app.program.adapter.DetailDateAdapter;
import com.mobile.android.app.program.adapter.ProportionAdapter;
import com.mobile.android.app.program.adapter.WeightAdapter;
import com.mobile.android.utils.DateUtils;
import com.mobile.android.widgets.dialog.TargetDialog;
import com.mobile.hyoukalibrary.base.BaseActivity;
import com.mobile.hyoukalibrary.utils.ToastUtil;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by wangqiang on 2019/1/8.
 */
public class ProgramDetailActivity extends BaseActivity {
    @BindView(R.id.logi_tool_bar)
    Toolbar logiToolBar;
    @BindView(R.id.tv_start_py)
    TextView tvStartPy;
    @BindView(R.id.tv_start_hy)
    TextView tvStartHy;
    @BindView(R.id.tv_end_py)
    TextView tvEndPy;
    @BindView(R.id.tv_end_hy)
    TextView tvEndHy;
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
    @BindView(R.id.all_program_select)
    AutoLinearLayout allProgramSelect;
    @BindView(R.id.all_program_dialog)
    AutoLinearLayout allProgramDialog;
    @BindView(R.id.all_left)
    AutoLinearLayout allLeft;
    @BindView(R.id.tv_start_end)
    TextView tvStartEnd;
    @BindView(R.id.tv_shop_name)
    TextView tvShopName;
    @BindView(R.id.tv_detail)
    TextView tvDetail;
    @BindView(R.id.rv_detail)
    RecyclerView rvDetail;
    @BindView(R.id.tv_detail_start)
    TextView tvDetailStart;
    @BindView(R.id.tv_detail_end)
    TextView tvDetailEnd;
    @BindView(R.id.rv_weight)
    RecyclerView rvWeight;
    @BindView(R.id.rv_proportion)
    RecyclerView rvProportion;
    @BindView(R.id.tv_yugu)
    TextView tvYugu;
    @BindView(R.id.tv_detail_money)
    TextView tvDetailMoney;
    @BindView(R.id.tv_detail_next)
    TextView tvDetailNext;
    private ArrayList<String> dates = new ArrayList();
    private DetailDateAdapter dateAdapter;
    private WeightAdapter weightAdapter;
    private ProportionAdapter proportionAdapter;
    private TargetDialog targetDialog;

    @Override
    protected void initViews(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        initDate();
    }

    private void initDate() {
        for (int i = 2; i < 12; i++) {
            String oldDate = DateUtils.getOldDate(i);
            dates.add(oldDate);
        }
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvDetail.setLayoutManager(manager);
        dateAdapter = new DetailDateAdapter(ProgramDetailActivity.this, dates);
        rvDetail.setAdapter(dateAdapter);

        LinearLayoutManager weightmanager = new LinearLayoutManager(this);
        weightmanager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvWeight.setLayoutManager(weightmanager);
        weightAdapter = new WeightAdapter(ProgramDetailActivity.this, dates);
        rvWeight.setAdapter(weightAdapter);

        LinearLayoutManager portionAdapter = new LinearLayoutManager(this);
        portionAdapter.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvProportion.setLayoutManager(portionAdapter);
        proportionAdapter = new ProportionAdapter(ProgramDetailActivity.this, dates);
        rvProportion.setAdapter(proportionAdapter);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_program_detail;
    }

    @Override
    public void initToolBar() {
        logiToolBar.setNavigationOnClickListener(view -> finish());
    }


    @OnClick({R.id.all_set_line, R.id.tv_just, R.id.tv_prepare, R.id.tv_tray, R.id.tv_bulk, R.id.tv_detail, R.id.tv_detail_end, R.id.tv_detail_next})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.all_set_line://头部
                ToastUtil.show(this, "产品开发中!");
                break;
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
            case R.id.tv_detail://详情
                startActivity(new Intent(ProgramDetailActivity.this, ProductDetailActivity.class));
                break;
            case R.id.tv_detail_end://目的港
                targetDialog = new TargetDialog(this);
                targetDialog.show();
                break;
            case R.id.tv_detail_next://下一步
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
}
