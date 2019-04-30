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
import com.youyijia.goodhealth.app.shopcart.ShopOrderActivity;
import com.youyijia.goodhealth.entity.AddressListInfo;
import com.youyijia.goodhealth.entity.AddressMrInfo;
import com.youyijia.goodhealth.entity.RemoveAddressPost;
import com.youyijia.goodhealth.retrofit.RetrofitManager;
import com.youyijia.goodhealth.retrofit.RetryWhenNetworkException;
import com.youyijia.goodhealth.retrofit.RxSchedulers;
import com.youyijia.goodhealth.retrofit.api.CommonService;
import com.youyijia.hyoukalibrary.base.BaseActivity;
import com.youyijia.hyoukalibrary.base.BaseEntity;
import com.youyijia.hyoukalibrary.base.BaseObserver;
import com.youyijia.hyoukalibrary.utils.StatusBarCompat;
import com.youyijia.hyoukalibrary.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class AdressActivity extends BaseActivity {

    @BindView(R.id.tv_add_address)
    TextView tvAddAddress;
    @BindView(R.id.logi_tool_bar)
    Toolbar logiToolBar;
    @BindView(R.id.rv_address)
    RecyclerView rvAddress;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.iv_no_data)
    ImageView ivNoData;
    @BindView(R.id.arl_no_data)
    RelativeLayout arlNoData;
    @BindView(R.id.rl_have_data)
    RelativeLayout rlHaveData;
    private ArrayList<AddressListInfo> address = new ArrayList<>();
    private AddressAdapter addressAdapter;
    private String id;
    private int removeId;
    private List<Integer> removes = new ArrayList<>();
    private String type;

    @Override
    protected void initViews(Bundle savedInstanceState) {
        StatusBarCompat.setTranslucentForImageView(this, 0, null);
        ButterKnife.bind(this);

        type = getIntent().getStringExtra("type");
        LinearLayoutManager manager = new LinearLayoutManager(AdressActivity.this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rvAddress.setLayoutManager(manager);
        addressAdapter = new AddressAdapter(AdressActivity.this, address, type) {
            @Override
            public void setMoren(int position) {
                id = address.get(position).getId() + "";
                getMoren();
            }

            @Override
            public void remove(int position) {
                removes.clear();
                removeId = address.get(position).getId();
                removes.add(removeId);
                getRemove();
            }

            @Override
            public void edit(int position) {
                Intent intent = new Intent(AdressActivity.this, AddAddressActivity.class);
                intent.putExtra("type", "1");
                intent.putExtra("id", address.get(position).getId() + "");
                intent.putExtra("receiver", address.get(position).getReceiver());
                intent.putExtra("phone", address.get(position).getPhone());
                intent.putExtra("provinceId", address.get(position).getProvinceId());
                intent.putExtra("provinceName", address.get(position).getProvinceName());
                intent.putExtra("cityId", address.get(position).getCityId());
                intent.putExtra("cityName", address.get(position).getCityName());
                intent.putExtra("counntryId", address.get(position).getCountryId());
                intent.putExtra("countryName", address.get(position).getCountryName());
                intent.putExtra("addressStatus", address.get(position).getAddressStatus().getName());
                intent.putExtra("addressDetail", address.get(position).getAddress());
                startActivityForResult(intent, 1125);
            }

            @Override
            public void setOnclickItem(int position) {
                Intent intent = new Intent(AdressActivity.this, ShopOrderActivity.class);
                intent.putExtra("name", address.get(position).getReceiver());
                intent.putExtra("phone", address.get(position).getPhone());
                String area = address.get(position).getProvinceName() + address.get(position).getCityName()
                        + address.get(position).getCountryName() + address.get(position).getAddress();
                intent.putExtra("area", area);
                intent.putExtra("receiveAddressId", address.get(position).getId());
                setResult(RESULT_OK, intent);
                finish();
            }
        };
        rvAddress.setAdapter(addressAdapter);

        refreshLayout.setOnRefreshListener(refreshLayout1 -> getdata());
        getdata();
    }

    private void getRemove() {
        RemoveAddressPost loginPost = new RemoveAddressPost();
        RemoveAddressPost.PostInfoBean postInfoBean = loginPost.new PostInfoBean();
        postInfoBean.setIds(removes);

        loginPost.setPostInfoBean(postInfoBean);
        String s = gson.toJson(postInfoBean.getIds());
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), s);
        RetrofitManager.getInstance().create(CommonService.class)
                .getAddressRemove(requestBody)
                .throttleFirst(1, TimeUnit.SECONDS)
                .retryWhen(new RetryWhenNetworkException(2, 500, 500))
                .compose(RxSchedulers.io_main())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onHandleSuccess(BaseEntity baseEntity) {
                        if ("200".equals(baseEntity.getCode())) {
                            AddressAdapter.selectPostion = -1;
                            getdata();
                        } else {
                            ToastUtil.show(AdressActivity.this, baseEntity.getMessage());
                        }
                    }
                });
    }

    private void getMoren() {
        AddressMrInfo loginPost = new AddressMrInfo();
        AddressMrInfo.PostInfoBean postInfoBean = loginPost.new PostInfoBean();
        postInfoBean.setId(id);

        loginPost.setPostInfoBean(postInfoBean);
        String s = gson.toJson(postInfoBean);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), s);
        RetrofitManager.getInstance().create(CommonService.class)
                .getAddressMr(requestBody)
                .throttleFirst(1, TimeUnit.SECONDS)
                .retryWhen(new RetryWhenNetworkException(2, 500, 500))
                .compose(RxSchedulers.io_main())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onHandleSuccess(BaseEntity baseEntity) {
                        if ("200".equals(baseEntity.getCode())) {
                            addressAdapter.notifyDataSetChanged();
                        } else {
                            ToastUtil.show(AdressActivity.this, baseEntity.getMessage());
                        }
                    }
                });
    }

    private void getdata() {
        RetrofitManager.getInstance().create(CommonService.class)
                .getAddressList()
                .throttleFirst(1, TimeUnit.SECONDS)
                .retryWhen(new RetryWhenNetworkException(2, 500, 500))
                .compose(RxSchedulers.io_main())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onHandleSuccess(BaseEntity baseEntity) {
                        if ("200".equals(baseEntity.getCode())) {
                            List<AddressListInfo> datas = gson.fromJson(baseEntity.getData(), new TypeToken<List<AddressListInfo>>() {
                            }.getType());
                            if (datas != null && datas.size() > 0) {
                                arlNoData.setVisibility(View.GONE);
                                rlHaveData.setVisibility(View.VISIBLE);
                                address.clear();
                                address.addAll(datas);
                                refreshData(address, false);
                            } else {
                                arlNoData.setVisibility(View.VISIBLE);
                                rlHaveData.setVisibility(View.GONE);
                            }
                        } else {
                            ToastUtil.show(AdressActivity.this, baseEntity.getMessage());
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
        return R.layout.activity_adress;
    }

    @Override
    public void initToolBar() {
        logiToolBar.setNavigationOnClickListener(view -> finish());
    }

    @OnClick(R.id.tv_add_address)
    public void onClick() {
        Intent intent = new Intent(AdressActivity.this, AddAddressActivity.class);
        intent.putExtra("type", "0");
        startActivityForResult(intent, 1124);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1124 && resultCode == RESULT_OK) {
            getdata();
        }

        if (requestCode == 1125 && resultCode == RESULT_OK) {
            getdata();
        }
    }

    private void refreshData(ArrayList<AddressListInfo> datas, boolean b) {
        int size = addressAdapter.getData().size();
        if (!b) {
            addressAdapter.notifyItemRangeRemoved(0, addressAdapter.getData().size());
        }
        addressAdapter.setData(datas);
        if (b) {
            rvAddress.getAdapter().notifyItemInserted(size);
        } else {
            rvAddress.getAdapter().notifyDataSetChanged();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AddressAdapter.selectPostion = -1;
    }
}
