package com.youyijia.goodhealth.app.adress;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.youyijia.goodhealth.R;
import com.youyijia.goodhealth.app.green.GreenBookActivity;
import com.youyijia.goodhealth.entity.GreenManInfo;
import com.youyijia.goodhealth.retrofit.RetrofitManager;
import com.youyijia.goodhealth.retrofit.RetryWhenNetworkException;
import com.youyijia.goodhealth.retrofit.RxSchedulers;
import com.youyijia.goodhealth.retrofit.api.CommonService;
import com.youyijia.hyoukalibrary.base.BaseActivity;
import com.youyijia.hyoukalibrary.base.BaseEntity;
import com.youyijia.hyoukalibrary.base.BaseObserver;
import com.youyijia.hyoukalibrary.utils.StatusBarCompat;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GreenInformationActivity extends BaseActivity {

    @BindView(R.id.tv_add_address)
    TextView tvAddAddress;
    @BindView(R.id.logi_tool_bar)
    Toolbar logiToolBar;
    @BindView(R.id.rv_green)
    RecyclerView rvGreen;
    @BindView(R.id.iv_no_data)
    ImageView ivNoData;
    @BindView(R.id.arl_no_data)
    RelativeLayout arlNoData;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.rl_top)
    RelativeLayout rlTop;
    @BindView(R.id.tv_sure)
    TextView tvSure;
    @BindView(R.id.rl_have_data)
    RelativeLayout rlHaveData;
    private ArrayList<GreenManInfo> greens = new ArrayList<>();
    private GreenYiAdapter greenYiAdapter;
    private String green;
    private String mname;
    private String mphone;
    private String mtype;
    private String mcard;
    private String mMedicalPersonId;

    @Override
    protected void initViews(Bundle savedInstanceState) {
        StatusBarCompat.setTranslucentForImageView(this, 0, null);
        ButterKnife.bind(this);
        green = getIntent().getStringExtra("green");
        if ("1".equals(green)) {
            tvSure.setVisibility(View.VISIBLE);
        } else {
            tvSure.setVisibility(View.GONE);
        }
        LinearLayoutManager manager = new LinearLayoutManager(GreenInformationActivity.this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rvGreen.setLayoutManager(manager);
        greenYiAdapter = new GreenYiAdapter(GreenInformationActivity.this, greens, green) {
            @Override
            public void setOnclick(String name, String phone, String type, String card, String medicalPersonId) {
                mname = name;
                mphone = phone;
                mtype = type;
                mcard = card;
                mMedicalPersonId = medicalPersonId;
            }

            @Override
            public void setEdit(int position) {
                Intent intent = new Intent(GreenInformationActivity.this, AddGreenYiActivity.class);
                intent.putExtra("saveType", "1");
                intent.putExtra("type", greens.get(position).getRelation().getText());
                intent.putExtra("name", greens.get(position).getName());
                intent.putExtra("phone", greens.get(position).getTelephone());
                intent.putExtra("cardType", greens.get(position).getIdCardType().getText());
                intent.putExtra("id", greens.get(position).getIdCardNo());
                intent.putExtra("jiuyiId", greens.get(position).getId());
                startActivityForResult(intent, 1116);
            }
        };
        rvGreen.setAdapter(greenYiAdapter);

        refreshLayout.setOnRefreshListener(refreshLayout1 -> getdata());
        getdata();
    }

    private void getdata() {
        RetrofitManager.getInstance().create(CommonService.class)
                .getJiuyi()
                .throttleFirst(1, TimeUnit.SECONDS)
                .retryWhen(new RetryWhenNetworkException(2, 500, 500))
                .compose(RxSchedulers.io_main())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onHandleSuccess(BaseEntity baseEntity) {
                        if ("200".equals(baseEntity.getCode())) {
                            List<GreenManInfo> datas = gson.fromJson(baseEntity.getData(), new TypeToken<List<GreenManInfo>>() {
                            }.getType());

                            if (datas != null && datas.size() > 0) {
                                arlNoData.setVisibility(View.GONE);
                                rlHaveData.setVisibility(View.VISIBLE);
                                greens.clear();
                                greens.addAll(datas);
                                refreshData(greens, false);
                            } else {
                                arlNoData.setVisibility(View.VISIBLE);
                                rlHaveData.setVisibility(View.GONE);
                            }
                        } else {
                            arlNoData.setVisibility(View.VISIBLE);
                            rlHaveData.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    protected void onFinally() {
                        super.onFinally();
                        if (refreshLayout != null) {
                            refreshLayout.finishRefresh();
                        }
                    }
                });
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_green_information;
    }

    @Override
    public void initToolBar() {
        logiToolBar.setNavigationOnClickListener(view -> finish());
    }


    @OnClick({R.id.tv_add_address, R.id.tv_sure})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_add_address:
                Intent intent1 = new Intent(GreenInformationActivity.this, AddGreenYiActivity.class);
                intent1.putExtra("saveType", "0");
                startActivityForResult(intent1, 1116);
                break;
            case R.id.tv_sure:
                Intent intent = new Intent(GreenInformationActivity.this, GreenBookActivity.class);
                intent.putExtra("name", mname);
                intent.putExtra("phone", mphone);
                intent.putExtra("type", mtype);
                intent.putExtra("card", mcard);
                intent.putExtra("medicalPersonId", mMedicalPersonId);
                setResult(RESULT_OK, intent);
                finish();
                break;
            default:
                break;
        }
    }

    private void refreshData(ArrayList<GreenManInfo> datas, boolean b) {
        int size = greenYiAdapter.getData().size();
        if (!b) {
            greenYiAdapter.notifyItemRangeRemoved(0, greenYiAdapter.getData().size());
        }
        greenYiAdapter.setData(datas);
        if (b) {
            rvGreen.getAdapter().notifyItemInserted(size);
        } else {
            rvGreen.getAdapter().notifyDataSetChanged();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1116 && resultCode == RESULT_OK) {
            getdata();
        }
    }
}
