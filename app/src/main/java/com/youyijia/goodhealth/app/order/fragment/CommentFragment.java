package com.youyijia.goodhealth.app.order.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import com.google.gson.reflect.TypeToken;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.youyijia.goodhealth.R;
import com.youyijia.goodhealth.app.order.GreenOrderDetailActivity;
import com.youyijia.goodhealth.app.order.ShopOrderDetailActivity;
import com.youyijia.goodhealth.app.order.adapter.CommentQadapter;
import com.youyijia.goodhealth.entity.CommentPost;
import com.youyijia.goodhealth.entity.OrderInfo;
import com.youyijia.goodhealth.entity.PageInfo;
import com.youyijia.goodhealth.retrofit.RetrofitManager;
import com.youyijia.goodhealth.retrofit.RetryWhenNetworkException;
import com.youyijia.goodhealth.retrofit.RxSchedulers;
import com.youyijia.goodhealth.retrofit.api.CommonService;
import com.youyijia.goodhealth.widgets.dialog.CommentSubmitDialog;
import com.youyijia.goodhealth.widgets.dialog.MyDialog;
import com.youyijia.hyoukalibrary.base.BaseEntity;
import com.youyijia.hyoukalibrary.base.BaseObserver;
import com.youyijia.hyoukalibrary.base.LazyFragment;
import com.youyijia.hyoukalibrary.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by wangqiang on 2019/1/4.
 */
public class CommentFragment extends LazyFragment {
    @BindView(R.id.rv_comment)
    RecyclerView rvCommnet;
    @BindView(R.id.arl_have_data)
    RelativeLayout arlHaveData;
    @BindView(R.id.arl_no_data)
    RelativeLayout arlNoData;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    Unbinder unbinder;
    private int page = 1;
    private ArrayList<OrderInfo> complains = new ArrayList<>();
    private boolean isPrepared;
    //定义当前的刷新的状态
    private LOADSTATE mCurrentState = LOADSTATE.IDLE;//第一次进来空闲状态
    private CommentQadapter commentQadapter;
    private MyDialog myDialog;
    private int pageNum;
    private int totalPage;
    private CommentSubmitDialog commentSubmitDialog;

    //上拉,下拉,空闲
    private enum LOADSTATE {
        LOAD, //加载中
        MORE, //上拉更多
        IDLE //空闲
    }

    public static CommentFragment newInstance() {
        CommentFragment fragment = new CommentFragment();
        return fragment;
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_comment;
    }

    @Override
    public void finishCreateView(Bundle state) {
        unbinder = ButterKnife.bind(this, parentView);
        isPrepared = true;
        lazyLoad();
        refreshLayout.setOnRefreshListener(refreshLayout1 -> {
            refreshLayout.autoRefresh();
            getWhole();
        });
        refreshLayout.setOnLoadMoreListener(refreshLayout1 -> {
            refreshLayout.autoLoadMore();
            getMoreWhole();
        });

        rvCommnet.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (mCurrentState == LOADSTATE.LOAD) {
                    return true;
                } else {
                    return false;
                }
            }
        });
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rvCommnet.setLayoutManager(manager);
        commentQadapter = new CommentQadapter(complains);
        rvCommnet.setAdapter(commentQadapter);
        commentQadapter.setOnItemChildClickListener((adapter, view, position) -> {
            switch (view.getId()) {
                case R.id.tv_order_right:
                    String id = commentQadapter.getData().get(position).getOrderId() + "";
                    if (commentSubmitDialog == null) {
                        commentSubmitDialog = new CommentSubmitDialog(mContext) {
                            @Override
                            public void setOnComment(int number, String content) {
                                submitComment(number, content, id);
                            }
                        };
                    }
                    commentSubmitDialog.show();
                    break;
                case R.id.ll_order_item:
                    String oneId = commentQadapter.getData().get(position).getOrderId() + "";
                    String orderType = commentQadapter.getData().get(position).getOrderType().getText();
                    if ("绿通订单".equals(orderType)) {
                        Intent intent = new Intent(mContext, GreenOrderDetailActivity.class);
                        intent.putExtra("orderId", oneId);
                        mContext.startActivity(intent);
                    } else if ("商品订单".equals(orderType)) {
                        Intent intent = new Intent(mContext, ShopOrderDetailActivity.class);
                        intent.putExtra("orderId", oneId);
                        mContext.startActivity(intent);
                    }
                    break;
                case R.id.ll_more_item:
                    String moreId = commentQadapter.getData().get(position).getOrderId() + "";
                    String textType = commentQadapter.getData().get(position).getOrderType().getText();
                    if ("绿通订单".equals(textType)) {
                        Intent intent = new Intent(mContext, GreenOrderDetailActivity.class);
                        intent.putExtra("orderId", moreId);
                        mContext.startActivity(intent);
                    } else if ("商品订单".equals(textType)) {
                        Intent intent = new Intent(mContext, ShopOrderDetailActivity.class);
                        intent.putExtra("orderId", moreId);
                        mContext.startActivity(intent);
                    }
                    break;
                default:
                    break;
            }
        });
    }

    private void submitComment(int number, String content, String orderId) {
        CommentPost loginPost = new CommentPost();
        CommentPost.PostInfoBean postInfoBean = loginPost.new PostInfoBean();
        postInfoBean.setOrderId(orderId);
        postInfoBean.setEvaluateLevel(number + "");
        postInfoBean.setEvaluateContent(content);

        loginPost.setPostInfoBean(postInfoBean);
        String s1 = gson.toJson(postInfoBean);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), s1);

        RetrofitManager.getInstance().create(CommonService.class)
                .getComment(requestBody)
                .throttleFirst(1, TimeUnit.SECONDS)
                .retryWhen(new RetryWhenNetworkException(2, 500, 500))
                .compose(RxSchedulers.io_main())
                .subscribe(new BaseObserver() {

                    @Override
                    protected void onHandleSuccess(BaseEntity baseEntity) {
                        if ("200".equals(baseEntity.getCode())) {
                            if (commentSubmitDialog != null) {
                                commentSubmitDialog.dismiss();
                            }
                            ToastUtil.show(mContext, "评价成功");
                            getWhole();
                        } else {
                            ToastUtil.show(mContext, baseEntity.getMessage());
                        }
                    }
                });
    }

    @Override
    protected void lazyLoad() {
        if (!isPrepared || !isVisible) {
            return;
        }
        getWhole();
    }

    private void getMoreWhole() {
        if (mCurrentState != LOADSTATE.IDLE) {
            return;
        }
        if (page == totalPage || totalPage == 0) {
            refreshLayout.finishLoadMore();
            ToastUtil.show(mContext, "没有更多的信息了！");
            return;
        }
        mCurrentState = LOADSTATE.MORE;
        params.clear();
        params.put("orderStatus", "NON_EVALUATED");
        params.put("limit", "10");
        pageNum += 1;
        params.put("pageNum", pageNum);
        RetrofitManager.getInstance().create(CommonService.class)
                .getOrder(params)
                .throttleFirst(1, TimeUnit.SECONDS)
                .retryWhen(new RetryWhenNetworkException(2, 500, 500))
                .compose(RxSchedulers.io_main())
                .subscribe(new BaseObserver() {

                    @Override
                    protected void onHandleSuccess(BaseEntity baseEntity) {
                        if ("200".equals(baseEntity.getCode())) {
                            mCurrentState = LOADSTATE.IDLE;
                            List<OrderInfo> datas = gson.fromJson(baseEntity.getData(), new TypeToken<List<OrderInfo>>() {
                            }.getType());
                            complains.addAll(datas);
                            commentQadapter.setNewData(complains);
                        } else {
                            ToastUtil.show(mContext, baseEntity.getMessage());
                        }
                    }

                    @Override
                    protected void onFinally() {
                        super.onFinally();
                        if (refreshLayout != null) {
                            refreshLayout.finishLoadMore();
                        }
                        mCurrentState = LOADSTATE.IDLE;
                    }
                });
    }

    private void getWhole() {
        if (myDialog == null) {
            myDialog = new MyDialog(mContext);
        }
        myDialog.showDialog();
        if (mCurrentState != LOADSTATE.IDLE) {
            return;
        }
        mCurrentState = LOADSTATE.LOAD;
        complains.clear();
        params.clear();
        pageNum = 1;
        params.put("orderStatus", "NON_EVALUATED");
        params.put("limit", "10");
        params.put("pageNum", pageNum);
        RetrofitManager.getInstance().create(CommonService.class)
                .getOrder(params)
                .throttleFirst(1, TimeUnit.SECONDS)
                .retryWhen(new RetryWhenNetworkException(2, 500, 500))
                .compose(RxSchedulers.io_main())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onHandleSuccess(BaseEntity baseEntity) {
                        if ("200".equals(baseEntity.getCode())) {
                            List<OrderInfo> datas = gson.fromJson(baseEntity.getData(), new TypeToken<List<OrderInfo>>() {
                            }.getType());
                            PageInfo pageInfo = gson.fromJson(baseEntity.getPageInfo(), PageInfo.class);
                            totalPage = pageInfo.getTotalPage();
                            if (datas != null && datas.size() > 0) {
                                arlHaveData.setVisibility(View.VISIBLE);
                                arlNoData.setVisibility(View.GONE);
                                complains.clear();
                                complains.addAll(datas);
                                commentQadapter.setNewData(complains);
                            } else {
                                arlHaveData.setVisibility(View.GONE);
                                arlNoData.setVisibility(View.VISIBLE);
                            }
                        } else if (TextUtils.isEmpty(baseEntity.getData()) && baseEntity.getSuccess() != null) {
                            ToastUtil.show(mContext, baseEntity.getMessage());
                            arlHaveData.setVisibility(View.GONE);
                            arlNoData.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    protected void onFinally() {
                        super.onFinally();
                        if (myDialog != null) {
                            myDialog.dismissDialog();
                        }
                        if (refreshLayout != null) {
                            refreshLayout.finishRefresh();
                        }
                        mCurrentState = LOADSTATE.IDLE;
                    }
                });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        if (myDialog != null) {
            myDialog.dismissDialog();
        }
    }
}
