package com.youyijia.goodhealth.app.shopcart;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.youyijia.goodhealth.R;
import com.youyijia.goodhealth.entity.ChangeSizePost;
import com.youyijia.goodhealth.entity.ShopCartDataInfo;
import com.youyijia.goodhealth.entity.ShopCartPriceInfo;
import com.youyijia.goodhealth.entity.ShopCartPricePost;
import com.youyijia.goodhealth.entity.ShopdeletePost;
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
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class ShopCartActivity extends BaseActivity implements MyCartAdapter.RefreshPriceInterface {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_cart_edit)
    TextView tvCartEdit;
    @BindView(R.id.logi_tool_bar)
    Toolbar logiToolBar;
    @BindView(R.id.rl_top)
    RelativeLayout rlTop;
    @BindView(R.id.rl_have_data)
    RelativeLayout rlHaveData;
    @BindView(R.id.iv_no_data)
    ImageView ivNoData;
    @BindView(R.id.arl_no_data)
    RelativeLayout arlNoData;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.cb_whole)
    CheckBox cbWhole;
    @BindView(R.id.tv_toatal)
    TextView tvToatal;
    @BindView(R.id.tv_submit)
    TextView tvSubmit;
    @BindView(R.id.ll_cart_bottom)
    LinearLayout llCartBottom;
    @BindView(R.id.ll_total_price)
    LinearLayout llTotalPrice;
    @BindView(R.id.tv_delete)
    TextView tvDelete;
    @BindView(R.id.rv_shop_cart)
    RecyclerView rvShopCart;
    private CartAdapter adapter;
    //    private ArrayList<ShopCartDataInfo> goodsList = new ArrayList<>();
    public boolean isComplate = false;
    private int totalCount;
    private double totalPrice;
    private MyCartAdapter myCartAdapter;
    private ArrayList<ShopCartDataInfo> shopCarts = new ArrayList<>();
    private List<Integer> itemIds = new ArrayList<>();
    private ArrayList<Integer> deleteIds = new ArrayList<>();

    @Override
    protected void initViews(Bundle savedInstanceState) {
        StatusBarCompat.setTranslucentForImageView(this, 0, null);
        ButterKnife.bind(this);
        logiToolBar.setNavigationOnClickListener(v -> finish());
        refreshLayout.setOnRefreshListener(refreshLayout1 -> {
            refreshLayout.autoRefresh();
            getData();
        });
        LinearLayoutManager manager = new LinearLayoutManager(ShopCartActivity.this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rvShopCart.setLayoutManager(manager);
        myCartAdapter = new MyCartAdapter(ShopCartActivity.this, shopCarts) {
            @Override
            public void setAdd(int position) {
                getSizeadd(position);
            }

            @Override
            public void setReduce(int position) {
                getSizereduce(position);
            }

            @Override
            public void setOnEdit(int position, String s) {
                getEditext(position, s);
            }

            @Override
            public void setOnCheckChange(int position) {
                Map<Integer, Integer> map = myCartAdapter.getPitchOnMap();
                priceControl(map);
            }
        };

        rvShopCart.setAdapter(myCartAdapter);
        getData();
    }

    private void getData() {
        RetrofitManager.getInstance().create(CommonService.class)
                .getShopCartData()
                .throttleFirst(1, TimeUnit.SECONDS)
                .retryWhen(new RetryWhenNetworkException(2, 500, 500))
                .compose(RxSchedulers.io_main())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onHandleSuccess(BaseEntity baseEntity) {
                        if ("200".equals(baseEntity.getCode())) {
                            List<ShopCartDataInfo> datas = gson.fromJson(baseEntity.getData(), new TypeToken<List<ShopCartDataInfo>>() {
                            }.getType());
                            if (datas != null && datas.size() > 0) {
                                initFrist(datas);
                            } else {
                                rlHaveData.setVisibility(View.GONE);
                                llCartBottom.setVisibility(View.GONE);
                                arlNoData.setVisibility(View.VISIBLE);
                            }
                        } else {
                            ToastUtil.show(ShopCartActivity.this, baseEntity.getMessage());
                            rlHaveData.setVisibility(View.GONE);
                            llCartBottom.setVisibility(View.GONE);
                            arlNoData.setVisibility(View.VISIBLE);
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

    private void initFrist(List<ShopCartDataInfo> datas) {
        rlHaveData.setVisibility(View.VISIBLE);
        llCartBottom.setVisibility(View.VISIBLE);
        arlNoData.setVisibility(View.GONE);
        shopCarts.clear();
        shopCarts.addAll(datas);

        refreshData(shopCarts, false);
        Map<Integer, Integer> map = myCartAdapter.getPitchOnMap();
        priceControl(map);
    }

    private void getEditext(int position, String s) {
        int itemId = shopCarts.get(position).getItemId();
        int num = Integer.parseInt(s);
        if (num < 1 || num == 0) {
            num = 1;
        } else {
            num = num;
        }

        ChangeSizePost loginPost = new ChangeSizePost();
        ChangeSizePost.PostInfoBean postInfoBean = loginPost.new PostInfoBean();
        postInfoBean.setItemId(itemId + "");
        postInfoBean.setCommodityCount(num + "");

        loginPost.setPostInfoBean(postInfoBean);
        String s1 = gson.toJson(postInfoBean);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), s1);

        RetrofitManager.getInstance().create(CommonService.class)
                .changeSize(requestBody)
                .throttleFirst(1, TimeUnit.SECONDS)
                .retryWhen(new RetryWhenNetworkException(2, 500, 500))
                .compose(RxSchedulers.io_main())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onHandleSuccess(BaseEntity baseEntity) {
                        if ("200".equals(baseEntity.getCode())) {
                            getData();
                        } else {
                            ToastUtil.show(ShopCartActivity.this, baseEntity.getMessage());
                        }
                    }
                });
    }

    private void getSizeadd(int position) {
        int itemId = shopCarts.get(position).getItemId();
        int commodityCount = shopCarts.get(position).getCommodityCount();
        commodityCount = commodityCount + 1;
        ChangeSizePost loginPost = new ChangeSizePost();
        ChangeSizePost.PostInfoBean postInfoBean = loginPost.new PostInfoBean();
        postInfoBean.setItemId(itemId + "");
        postInfoBean.setCommodityCount(commodityCount + "");

        loginPost.setPostInfoBean(postInfoBean);
        String s = gson.toJson(postInfoBean);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), s);

        RetrofitManager.getInstance().create(CommonService.class)
                .changeSize(requestBody)
                .throttleFirst(1, TimeUnit.SECONDS)
                .retryWhen(new RetryWhenNetworkException(2, 500, 500))
                .compose(RxSchedulers.io_main())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onHandleSuccess(BaseEntity baseEntity) {
                        if ("200".equals(baseEntity.getCode())) {
                            getData();
                        } else {
                            ToastUtil.show(ShopCartActivity.this, baseEntity.getMessage());
                        }
                    }
                });
    }

    private void getSizereduce(int postion) {
        int itemId = shopCarts.get(postion).getItemId();
        int commodityCount = shopCarts.get(postion).getCommodityCount();
        if (commodityCount == 1) {
            commodityCount = 1;
        } else {
            commodityCount = commodityCount - 1;
        }
        ChangeSizePost loginPost = new ChangeSizePost();
        ChangeSizePost.PostInfoBean postInfoBean = loginPost.new PostInfoBean();
        postInfoBean.setItemId(itemId + "");
        postInfoBean.setCommodityCount(commodityCount + "");

        loginPost.setPostInfoBean(postInfoBean);
        String s = gson.toJson(postInfoBean);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), s);

        RetrofitManager.getInstance().create(CommonService.class)
                .changeSize(requestBody)
                .throttleFirst(1, TimeUnit.SECONDS)
                .retryWhen(new RetryWhenNetworkException(2, 500, 500))
                .compose(RxSchedulers.io_main())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onHandleSuccess(BaseEntity baseEntity) {
                        if ("200".equals(baseEntity.getCode())) {
                            getData();
                        } else {
                            ToastUtil.show(ShopCartActivity.this, baseEntity.getMessage());
                        }
                    }
                });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_shop_cart;
    }

    @Override
    public void initToolBar() {

    }

    @OnClick({R.id.tv_cart_edit, R.id.tv_submit, R.id.cb_whole, R.id.tv_delete})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_cart_edit:
                if (isComplate == false) {
                    initEdit();
                    tvCartEdit.setText("完成");
                    tvDelete.setVisibility(View.VISIBLE);
                    tvSubmit.setVisibility(View.GONE);
                    llTotalPrice.setVisibility(View.GONE);
                } else {
                    initComplate();
                    tvCartEdit.setText("编辑");
                    tvDelete.setVisibility(View.GONE);
                    tvSubmit.setVisibility(View.VISIBLE);
                    llTotalPrice.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.tv_submit:
                break;
            case R.id.cb_whole:
                AllTheSelected();
                break;
            case R.id.tv_delete:
                initDelete();
                break;
            default:
                break;
        }
    }

    private void initDelete() {

        Map<Integer, Integer> map = myCartAdapter.getPitchOnMap();
        for (int i = 0; i < shopCarts.size(); i++) {
            Integer integer = map.get(shopCarts.get(i).getCommodityId());
            if (integer == 1) {
                deleteIds.add(shopCarts.get(i).getItemId());
            }
        }
        if (deleteIds.size() == 0) {
            ToastUtil.show(ShopCartActivity.this, "请选择你要删除的商品！");
            return;
        } else {
            deleteShop();
        }
    }

    private void deleteShop() {
        ShopdeletePost loginPost = new ShopdeletePost();
        ShopdeletePost.PostInfoBean postInfoBean = loginPost.new PostInfoBean();
        postInfoBean.setIds(deleteIds);

        loginPost.setPostInfoBean(postInfoBean);
        String s = gson.toJson(postInfoBean.getIds());
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), s);
        RetrofitManager.getInstance().create(CommonService.class)
                .deleteShopItems(requestBody)
                .throttleFirst(1, TimeUnit.SECONDS)
                .retryWhen(new RetryWhenNetworkException(2, 500, 500))
                .compose(RxSchedulers.io_main())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onHandleSuccess(BaseEntity baseEntity) {
                        if ("200".equals(baseEntity.getCode())) {
                            ToastUtil.show(ShopCartActivity.this, "删除商品成功！");
                            getData();
                        } else {
                            ToastUtil.show(ShopCartActivity.this, baseEntity.getMessage());
                        }
                    }
                });
    }

    private void initEdit() {
        Map<Integer, Integer> map = myCartAdapter.getPitchOnMap();
        for (int i = 0; i < shopCarts.size(); i++) {
            map.put(shopCarts.get(i).getCommodityId(), 0);
        }
        priceControl(map);
        cbWhole.setChecked(false);
        myCartAdapter.setPitchOnMap(map);
        myCartAdapter.notifyDataSetChanged();
        isComplate = !isComplate;
    }

    private void initComplate() {
        Map<Integer, Integer> map = myCartAdapter.getPitchOnMap();
        for (int i = 0; i < shopCarts.size(); i++) {
            map.put(shopCarts.get(i).getCommodityId(), 1);
        }
        cbWhole.setChecked(true);
        priceControl(map);
        myCartAdapter.setPitchOnMap(map);
        myCartAdapter.notifyDataSetChanged();
        isComplate = !isComplate;
    }

    private void AllTheSelected() {
        Map<Integer, Integer> map = myCartAdapter.getPitchOnMap();
        boolean isCheck = false;
        boolean isUnCheck = false;
        Iterator iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            if (Integer.valueOf(entry.getValue().toString()) == 1) isCheck = true;
            else isUnCheck = true;
        }
        if (isCheck == true && isUnCheck == false) {//已经全选,做反选
            for (int i = 0; i < shopCarts.size(); i++) {
                map.put(shopCarts.get(i).getCommodityId(), 0);
            }
            cbWhole.setChecked(false);
//            itemIds.clear();
        } else if (isCheck == true && isUnCheck == true) {//部分选择,做全选
            for (int i = 0; i < shopCarts.size(); i++) {
                map.put(shopCarts.get(i).getCommodityId(), 1);
            }
            cbWhole.setChecked(true);
            for (int i = 0; i < shopCarts.size(); i++) {
                itemIds.add(shopCarts.get(i).getItemId());
            }
        } else if (isCheck == false && isUnCheck == true) {//一个没选,做全选
            for (int i = 0; i < shopCarts.size(); i++) {
                map.put(shopCarts.get(i).getCommodityId(), 1);
            }
            cbWhole.setChecked(true);
            for (int i = 0; i < shopCarts.size(); i++) {
                itemIds.add(shopCarts.get(i).getItemId());
            }
        }
        priceControl(map);
        myCartAdapter.setPitchOnMap(map);
        myCartAdapter.notifyDataSetChanged();
    }

    @Override
    public void refreshPrice(Map<Integer, Integer> pitchOnMap) {
        priceControl(pitchOnMap);
    }

    //控制价格展示
    private void priceControl(Map<Integer, Integer> pitchOnMap) {
        boolean isCheck = false;
        boolean isUnCheck = false;
        Iterator iter = pitchOnMap.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            if (Integer.valueOf(entry.getValue().toString()) == 1) isCheck = true;
            else isUnCheck = true;
        }
        if (isCheck == true && isUnCheck == false) {//已经全选
            cbWhole.setChecked(true);
        } else if (isCheck == true && isUnCheck == true) {//部分选择
            cbWhole.setChecked(false);
        } else if (isCheck == false && isUnCheck == true) {//一个没选
            cbWhole.setChecked(false);
        }


        itemIds.clear();
        for (int i = 0; i < shopCarts.size(); i++) {
            if (pitchOnMap.get(shopCarts.get(i).getCommodityId()) == 1) {
                itemIds.add(shopCarts.get(i).getItemId());
            }
        }
        if (itemIds.size() == 0) {
            tvToatal.setText("0");
        } else if (itemIds.size() > 0) {
            getTotalPrice();
        }
    }

    private void getTotalPrice() {
        ShopCartPricePost loginPost = new ShopCartPricePost();
        ShopCartPricePost.PostInfoBean postInfoBean = loginPost.new PostInfoBean();

        postInfoBean.setItemIds(itemIds);

        loginPost.setPostInfoBean(postInfoBean);
        String s1 = gson.toJson(postInfoBean);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), s1);


        RetrofitManager.getInstance().create(CommonService.class)
                .getCartPrice(requestBody)
                .throttleFirst(1, TimeUnit.SECONDS)
                .retryWhen(new RetryWhenNetworkException(2, 500, 500))
                .compose(RxSchedulers.io_main())
                .subscribe(new BaseObserver() {

                    @Override
                    protected void onHandleSuccess(BaseEntity baseEntity) {
                        if ("200".equals(baseEntity.getCode())) {
                            ShopCartPriceInfo shopCartPriceInfo = gson.fromJson(baseEntity.getData(), ShopCartPriceInfo.class);
                            tvToatal.setText(shopCartPriceInfo.getOriginProductAmountTotal() + "");
                        } else {
                            ToastUtil.show(ShopCartActivity.this, baseEntity.getMessage());
                        }
                    }
                });
    }

    private void refreshData(ArrayList<ShopCartDataInfo> datas, boolean isLoadMore) {
        int size = myCartAdapter.getData().size();
        if (!isLoadMore) {
            myCartAdapter.notifyItemRangeRemoved(0, myCartAdapter.getData().size());
        }
        myCartAdapter.setData(datas);
        if (isLoadMore) {
            rvShopCart.getAdapter().notifyItemInserted(size);
        } else {
            rvShopCart.getAdapter().notifyDataSetChanged();
        }

    }
}
