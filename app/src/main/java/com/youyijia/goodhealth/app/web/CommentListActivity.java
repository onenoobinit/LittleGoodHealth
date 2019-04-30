package com.youyijia.goodhealth.app.web;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.youyijia.goodhealth.R;
import com.youyijia.goodhealth.entity.CommentListInfo;
import com.youyijia.goodhealth.entity.PageInfo;
import com.youyijia.goodhealth.entity.PutCommentPost;
import com.youyijia.goodhealth.entity.ZanPost;
import com.youyijia.goodhealth.retrofit.RetrofitManager;
import com.youyijia.goodhealth.retrofit.RetryWhenNetworkException;
import com.youyijia.goodhealth.retrofit.RxSchedulers;
import com.youyijia.goodhealth.retrofit.api.CommonService;
import com.youyijia.goodhealth.widgets.dialog.CommentDialog;
import com.youyijia.goodhealth.widgets.dialog.MyDialog;
import com.youyijia.goodhealth.widgets.pop.AddPopWindow;
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

public class CommentListActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.ib_back)
    ImageButton ibBack;
    @BindView(R.id.rl_top)
    RelativeLayout rlTop;
    @BindView(R.id.rv_comment)
    RecyclerView rvComment;
    @BindView(R.id.rl_have_data)
    LinearLayout rlHaveData;
    @BindView(R.id.iv_no_data)
    ImageView ivNoData;
    @BindView(R.id.arl_no_data)
    RelativeLayout arlNoData;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.tv_comment_title)
    TextView tvCommentTitle;
    @BindView(R.id.tv_comment_type)
    TextView tvCommentType;
    @BindView(R.id.tv_comment_date)
    TextView tvCommentDate;
    @BindView(R.id.tv_look)
    TextView tvLook;
    @BindView(R.id.tv_total_comment)
    TextView tvTotalComment;
    @BindView(R.id.ll_sort)
    LinearLayout llSort;
    @BindView(R.id.et_content)
    TextView etContent;
    @BindView(R.id.tv_type)
    TextView tvType;
    //定义当前的刷新的状态
    private LOADSTATE mCurrentState = LOADSTATE.IDLE;//第一次进来空闲状态
    private int pageNum;
    private int totalPage;
    private ArrayList<CommentListInfo> comments = new ArrayList<>();
    private CommentAdapter commentAdapter;
    private String doctorId;
    private String id;
    private String articleId;
    private String articleType;
    private String title;
    private String author;
    private String date;
    private String pageviews;
    private ArrayList<String> selects = new ArrayList<>();
    private AddPopWindow addPopWindow;
    private String articleCommentOrderBy = "LIKE_COUNT_DESC";
    private String commentId;
    private CommentDialog commentDialog;
    private MyDialog myDialog;

    //上拉,下拉,空闲
    private enum LOADSTATE {
        LOAD, //加载中
        MORE, //上拉更多
        IDLE //空闲
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        StatusBarCompat.setTranslucentForImageView(this, 0, null);
        ButterKnife.bind(this);
        selects.add("按热度");
        selects.add("按时间");
        articleId = getIntent().getStringExtra("articleId");
        articleType = getIntent().getStringExtra("articleType");
        title = getIntent().getStringExtra("title");
        author = getIntent().getStringExtra("author");
        date = getIntent().getStringExtra("date");
        pageviews = getIntent().getStringExtra("pageviews");

        tvCommentTitle.setText(title);
        tvCommentType.setText(author);
        tvCommentDate.setText(date);
        tvLook.setText(pageviews);
        ibBack.setOnClickListener(v -> finish());
        LinearLayoutManager manager = new LinearLayoutManager(CommentListActivity.this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rvComment.setLayoutManager(manager);
        commentAdapter = new CommentAdapter(CommentListActivity.this, comments) {
            @Override
            public void setOnzan(int position) {
                commentId = comments.get(position).getCommentId();
                getZan();
            }
        };
        rvComment.setAdapter(commentAdapter);
        refreshLayout.setOnRefreshListener(refreshLayout1 -> {
            getdata();
        });

        refreshLayout.setOnLoadMoreListener(refreshLayout1 -> {
            refreshLayout.autoLoadMore();
            getMoreData();
        });

        rvComment.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (mCurrentState == LOADSTATE.LOAD) {
                    return true;
                } else {
                    return false;
                }
            }
        });
        getdata();
        addPopWindow = new AddPopWindow(CommentListActivity.this);
        addPopWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1f);
            }
        });
    }

    private void getZan() {
        ZanPost loginPost = new ZanPost();
        ZanPost.PostInfoBean postInfoBean = loginPost.new PostInfoBean();
        postInfoBean.setCommentId(commentId);

        loginPost.setPostInfoBean(postInfoBean);
        String s = gson.toJson(postInfoBean);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), s);
        RetrofitManager.getInstance().create(CommonService.class)
                .getCommentZan(requestBody)
                .throttleFirst(1, TimeUnit.SECONDS)
                .retryWhen(new RetryWhenNetworkException(2, 500, 500))
                .compose(RxSchedulers.io_main())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onHandleSuccess(BaseEntity baseEntity) {
                        if ("200".equals(baseEntity.getCode())) {
                            String data = baseEntity.getData();

                        } else {
                            ToastUtil.show(CommentListActivity.this, baseEntity.getMessage());
                        }
                    }
                });
    }


    private void getMoreData() {
        if (mCurrentState != LOADSTATE.IDLE) {
            return;
        }
        if (pageNum == totalPage || totalPage == 0) {
            refreshLayout.finishLoadMore();
            ToastUtil.show(CommentListActivity.this, "没有更多的信息了！");
            return;
        }
        mCurrentState = LOADSTATE.MORE;
        params.clear();
        params.put("articleId", articleId);
        params.put("articleType", articleType);
        params.put("articleCommentOrderBy", articleCommentOrderBy);
        pageNum += 1;
        params.put("pageNum", pageNum);
        RetrofitManager.getInstance().create(CommonService.class)
                .getCommentList(params)
                .throttleFirst(1, TimeUnit.SECONDS)
                .retryWhen(new RetryWhenNetworkException(2, 500, 500))
                .compose(RxSchedulers.io_main())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onHandleSuccess(BaseEntity baseEntity) {
                        if ("200".equals(baseEntity.getCode())) {
                            List<CommentListInfo> datas = gson.fromJson(baseEntity.getData(), new TypeToken<List<CommentListInfo>>() {
                            }.getType());
                            comments.addAll(datas);
                            refreshData(comments, true);
                            commentAdapter.notifyDataSetChanged();
                        } else {
                            ToastUtil.show(CommentListActivity.this, baseEntity.getMessage());
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

    private void getdata() {
        if (mCurrentState != LOADSTATE.IDLE) {
            return;
        }
        mCurrentState = LOADSTATE.LOAD;
        params.clear();
        params.put("articleId", articleId);
        params.put("articleType", articleType);
        params.put("articleCommentOrderBy", articleCommentOrderBy);
        pageNum = 1;
        params.put("pageNum", pageNum);
        RetrofitManager.getInstance().create(CommonService.class)
                .getCommentList(params)
                .throttleFirst(1, TimeUnit.SECONDS)
                .retryWhen(new RetryWhenNetworkException(2, 500, 500))
                .compose(RxSchedulers.io_main())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onHandleSuccess(BaseEntity baseEntity) {
                        if ("200".equals(baseEntity.getCode())) {
                            ArrayList<CommentListInfo> datas = (ArrayList<CommentListInfo>) gson.fromJson(baseEntity.getData(), new TypeToken<List<CommentListInfo>>() {
                            }.getType());
                            if (datas != null && datas.size() > 0) {
                                rlHaveData.setVisibility(View.VISIBLE);
                                arlNoData.setVisibility(View.GONE);
                                PageInfo pageInfo = gson.fromJson(baseEntity.getPageInfo(), PageInfo.class);
                                totalPage = pageInfo.getTotalPage();
                                int totalCount = pageInfo.getTotalCount();
                                tvTotalComment.setText("全部评论(" + totalCount + ")");
                                comments.clear();
                                comments = datas;
                                refreshData(comments, false);
                                commentAdapter.notifyDataSetChanged();
                            } else {
                                rlHaveData.setVisibility(View.GONE);
                                arlNoData.setVisibility(View.VISIBLE);
                            }
                        } else {
                            rlHaveData.setVisibility(View.GONE);
                            arlNoData.setVisibility(View.VISIBLE);
                            ToastUtil.show(CommentListActivity.this, baseEntity.getMessage());
                        }
                    }

                    @Override
                    protected void onFinally() {
                        super.onFinally();
                        if (refreshLayout != null) {
                            refreshLayout.finishRefresh();
                        }
                        mCurrentState = LOADSTATE.IDLE;
                    }
                });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_comment_list;
    }

    @Override
    public void initToolBar() {

    }

    @OnClick({R.id.ll_sort, R.id.et_content})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_sort:
                addPopWindow.showPopupWindow(llSort, selects);
                backgroundAlpha(0.5f);
                addPopWindow.setOnPopupItemClickListener((sid, postion) -> {
                    tvType.setText(sid);
                    if ("按热度".equals(sid)) {
                        articleCommentOrderBy = "LIKE_COUNT_DESC";
                    } else if ("按时间".equals(sid)) {
                        articleCommentOrderBy = "COMMENT_TIME_DESC";
                    }
                    getdata();
                });
                break;
            case R.id.et_content:
                commentDialog = new CommentDialog(CommentListActivity.this, new CommentDialog.SendListener() {
                    @Override
                    public void sendComment(String inputText) {
                        getComment(inputText);
                    }
                });
                commentDialog.show(getSupportFragmentManager(), "comment");
                break;
        }
    }

    private void getComment(String content) {
        if (myDialog == null) {
            myDialog = new MyDialog(CommentListActivity.this);
        }
        myDialog.showDialog();
        PutCommentPost loginPost = new PutCommentPost();
        PutCommentPost.PostInfoBean postInfoBean = loginPost.new PostInfoBean();
        postInfoBean.setArticleId(articleId);
        postInfoBean.setArticleType(articleType);
        postInfoBean.setCommentContent(content);

        loginPost.setPostInfoBean(postInfoBean);
        String s = gson.toJson(postInfoBean);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), s);
        RetrofitManager.getInstance().create(CommonService.class)
                .putComment(requestBody)
                .throttleFirst(1, TimeUnit.SECONDS)
                .retryWhen(new RetryWhenNetworkException(2, 500, 500))
                .compose(RxSchedulers.io_main())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onHandleSuccess(BaseEntity baseEntity) {
                        if ("200".equals(baseEntity.getCode())) {
                            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(getWindow().getCurrentFocus().getWindowToken(), 0);
                            getdata();
                        } else {
                            ToastUtil.show(CommentListActivity.this, baseEntity.getMessage());
                        }
                    }

                    @Override
                    protected void onFinally() {
                        super.onFinally();
                        if (myDialog != null) {
                            myDialog.dismissDialog();
                        }
                    }
                });
    }

    private void refreshData(ArrayList<CommentListInfo> datas, boolean b) {
        int size = commentAdapter.getData().size();
        if (!b) {
            commentAdapter.notifyItemRangeRemoved(0, commentAdapter.getData().size());
        }
        commentAdapter.setData(datas);
        if (b) {
            rvComment.getAdapter().notifyItemInserted(size);
        } else {
            rvComment.getAdapter().notifyDataSetChanged();
        }
    }

    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getWindow().setAttributes(lp);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (commentDialog != null) {
            commentDialog.dismiss();
        }
        if (myDialog != null) {
            myDialog.dismissDialog();
        }
    }
}
