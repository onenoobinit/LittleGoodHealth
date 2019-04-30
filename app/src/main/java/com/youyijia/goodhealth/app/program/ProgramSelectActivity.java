package com.youyijia.goodhealth.app.program;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.youyijia.goodhealth.R;
import com.youyijia.goodhealth.app.program.adapter.DateAdapter;
import com.youyijia.goodhealth.app.program.adapter.LeftAdapter;
import com.youyijia.goodhealth.app.program.adapter.RightAdapter;
import com.youyijia.goodhealth.app.program.adapter.TestAdapter;
import com.youyijia.goodhealth.app.program.adapter.TestLeftAdapter;
import com.youyijia.goodhealth.app.search.SearchActivity;
import com.youyijia.goodhealth.entity.ProgramSelectInfo;
import com.youyijia.goodhealth.entity.StartInfo;
import com.youyijia.goodhealth.retrofit.RetrofitManager;
import com.youyijia.goodhealth.retrofit.RetryWhenNetworkException;
import com.youyijia.goodhealth.retrofit.RxSchedulers;
import com.youyijia.goodhealth.retrofit.api.CommonService;
import com.youyijia.goodhealth.utils.DateUtils;
import com.youyijia.goodhealth.widgets.dialog.IndexDialog;
import com.youyijia.goodhealth.widgets.dialog.MyDialog;
import com.youyijia.goodhealth.widgets.pop.AddPopWindow;
import com.youyijia.hyoukalibrary.base.BaseActivity;
import com.youyijia.hyoukalibrary.base.BaseEntity;
import com.youyijia.hyoukalibrary.base.BaseObserver;
import com.youyijia.hyoukalibrary.rxbus.annotation.Subscribe;
import com.youyijia.hyoukalibrary.rxbus.annotation.Tag;
import com.youyijia.hyoukalibrary.rxbus.thread.EventThread;
import com.youyijia.hyoukalibrary.utils.L;
import com.youyijia.hyoukalibrary.utils.SPUtil;
import com.youyijia.hyoukalibrary.utils.ToastUtil;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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
    /*@BindView(R.id.scroll)
    ObservableScrollView scroll;*/
    @BindView(R.id.tv_head_number)
    TextView tvHeadNumber;
    @BindView(R.id.tv_head_weight)
    TextView tvHeadWeight;
    @BindView(R.id.tv_head_vol)
    TextView tvHeadVol;
    @BindView(R.id.tv_head_portion)
    TextView tvHeadPortion;
    @BindView(R.id.all_program_select)
    AutoLinearLayout allProgramSelect;
    @BindView(R.id.rv_sort_left)
    RecyclerView rvSortLeft;
    @BindView(R.id.rv_sort_right)
    RecyclerView rvSortRight;
    @BindView(R.id.rv_test)
    RecyclerView rvTest;
    @BindView(R.id.tv_item_order_date)
    TextView tvItemOrderDate;
    @BindView(R.id.iv_no_data)
    ImageView ivNoData;
    @BindView(R.id.arl_no_data)
    AutoRelativeLayout arlNoData;
    private DateAdapter dateAdapter;
    private ArrayList<String> dates = new ArrayList();
    private String start;
    private String end;
    private String endHY;
    private String number;
    private String weight;
    private String vol;
    private String startHy;
    private String TOKEN;
    private String book = "0";
    private String packget = "0";
    private String DATE = "";
    private IndexDialog indexDialog;
    private List<StartInfo.StartPortBean> startList;
    private AddPopWindow addPopWindow;
    private ArrayList<String> starts = new ArrayList<String>();
    private String nameC;
    private ProgramSelectInfo programSelectInfo;
    private LeftAdapter leftAdapter;
    private RightAdapter rightAdapter;
    private List<ProgramSelectInfo.AirlineListBean> airlineList;
    private List<ProgramSelectInfo.AirlineListBean> leftDatas = new ArrayList<>();
    private List<ProgramSelectInfo.ProductCardListBean> productCardList;
    private List<ProgramSelectInfo.ProductCardListBean> rightDatas = new ArrayList<>();
    private List<ProgramSelectInfo.AirlineListBean> mLeftList = new ArrayList<>();
    private final List<ProgramSelectInfo.ProductCardListBean> mRightList = new ArrayList<>();
    private final Map<Integer, Integer> indexMap = new HashMap<>();
    private TestLeftAdapter testLeftAdapter;
    private String imageUrl;
    private MyDialog myDialog;
    private TestAdapter testAdapter;

    @Override
    protected void initViews(Bundle savedInstanceState) {
        ButterKnife.bind(this);
//        TOKEN = GoodHealthApp.getUser().getToken();
        DATE = DateUtils.getOldDate(2);
        initScroll();
        initRecycleView();
        initFrist();
        //获取数据
        getData();
    }

    private void getData() {
        if (myDialog == null) {
            myDialog = new MyDialog(ProgramSelectActivity.this);
        }
        myDialog.showDialog();
        params.clear();
        params.put("act", "getProductsDataV2");
//        params.put("area", );
        if ("--".equals(tvStartPy.getText().toString().trim())) {
            params.put("startPort", "");
        } else {
            params.put("startPort", tvStartPy.getText().toString().trim());
        }
        params.put("destination", tvEndPy.getText().toString().trim());
        if ("填入".equals(tvHeadNumber.getText().toString().trim())) {
            params.put("goodNumber", "");
        } else {
            params.put("goodNumber", tvHeadNumber.getText().toString().trim());
        }
        if ("填入".equals(tvHeadWeight.getText().toString().trim())) {
            params.put("goodWeight", "");
        } else {
            params.put("goodWeight", tvHeadWeight.getText().toString().trim());
        }
        if ("填入".equals(tvHeadVol.getText().toString().trim())) {
            params.put("goodVolume", "");
        } else {
            params.put("goodVolume", tvHeadVol.getText().toString().trim());
        }
        params.put("bookingPosition", book);
        params.put("packageWay", packget);
        params.put("productDate", DATE);

        L.i("方案选择参数", params.toString());
        RetrofitManager.getInstance().create(CommonService.class)
                .getSelect(TOKEN, params)
                .throttleFirst(1, TimeUnit.SECONDS)
                .retryWhen(new RetryWhenNetworkException(2, 500, 500))
                .compose(RxSchedulers.io_main())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onHandleSuccess(BaseEntity baseEntity) {
                        if (!TextUtils.isEmpty(baseEntity.getData())) {
                            ToastUtil.show(ProgramSelectActivity.this, baseEntity.getData());
                            if ("暂无数据".equals(baseEntity.getData())) {
                                rvTest.setVisibility(View.GONE);
                                arlNoData.setVisibility(View.VISIBLE);
                                leftDatas.clear();
                                rightDatas.clear();
                                initData();
                            }
                            return;
                        } else {
                            rvTest.setVisibility(View.VISIBLE);
                            arlNoData.setVisibility(View.GONE);
                            programSelectInfo = gson.fromJson(baseEntity.getSuccess(), ProgramSelectInfo.class);
                            airlineList = programSelectInfo.getAirlineList();
                            mLeftList = airlineList;
                            productCardList = programSelectInfo.getProductCardList();
                            leftDatas.clear();
                            rightDatas.clear();
                            leftDatas = airlineList;
                            rightDatas = productCardList;

//                            initSort();//排序
                            Collections.sort(rightDatas, new Comparator<ProgramSelectInfo.ProductCardListBean>() {
                                @Override
                                public int compare(ProgramSelectInfo.ProductCardListBean p1, ProgramSelectInfo.ProductCardListBean p2) {
                                    int i1 = Integer.parseInt(p1.getSort().getIntegrated());
                                    int i2 = Integer.parseInt(p2.getSort().getIntegrated());
                                    int i = i1 - i2;
                                    return i1 - i2;
                                }
                            });
                            initData();
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


    private void initData() {
        /*// 左列表
        rvSortLeft.setLayoutManager(new LinearLayoutManager(this));
        ((SimpleItemAnimator) rvSortLeft.getItemAnimator()).setSupportsChangeAnimations(false);
        leftAdapter = new LeftAdapter();
        leftAdapter.setListData(mLeftList);
        rvSortLeft.setAdapter(leftAdapter);
        // 左侧列表的点击事件
        leftAdapter.setOnItemClickListener(new SimpleRecyclerAdapter.OnItemClickListener<ProgramSelectInfo.AirlineListBean>() {
            @Override
            public void onItemClick(ProgramSelectInfo.AirlineListBean item, int index) {
                // 左侧选中并滑到中间位置
                leftAdapter.setSelectedPosition(index);
                LeftUtils.moveToMiddle(rvSortLeft, index);
                // 右侧滑到对应位置
                ((GridLayoutManager) rvSortRight.getLayoutManager())
                        .scrollToPositionWithOffset(indexMap.get(index), 0);
            }
        });

        //右列表
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (productCardList.get(position).viewType == ItemType.BIG_SORT) {
                    return 1;
                } else {
                    return 1;
                }
            }
        });
        rvSortRight.setLayoutManager(gridLayoutManager);
        rightAdapter = new RightAdapter();
        rightAdapter.setListData(productCardList);
        rvSortRight.setAdapter(rightAdapter);
        rightAdapter.setOnItemClickListener(new SimpleRecyclerAdapter.OnItemClickListener<ProgramSelectInfo.ProductCardListBean>() {
            @Override
            public void onItemClick(ProgramSelectInfo.ProductCardListBean item, int index) {
//                Toast.makeText(MainActivity.this, item.name, Toast.LENGTH_SHORT).show();
            }
        });
        //右侧列表的滚动事件
        rvSortRight.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                //获取右侧列表的第一个可见Item的position
                int topPosition = ((GridLayoutManager) rvSortRight.getLayoutManager()).findFirstVisibleItemPosition();
                // 如果此项对应的是左边的大类的index
                if (productCardList.get(topPosition).position != -1) {
                    LeftUtils.moveToMiddle(rvSortLeft, productCardList.get(topPosition).position);
                    leftAdapter.setSelectedPosition(productCardList.get(topPosition).position);
                }

            }
        });*/


        //左侧展示
        //测试右边
        LinearLayoutManager leftLayout = new LinearLayoutManager(this);
        leftLayout.setOrientation(LinearLayoutManager.VERTICAL);
        rvSortLeft.setLayoutManager(leftLayout);
        testLeftAdapter = new TestLeftAdapter(ProgramSelectActivity.this, leftDatas);
        rvSortLeft.setAdapter(testLeftAdapter);


        //测试右边
        LinearLayoutManager weightmanager = new LinearLayoutManager(this);
        weightmanager.setOrientation(LinearLayoutManager.VERTICAL);
        rvTest.setLayoutManager(weightmanager);
        testAdapter = new TestAdapter(ProgramSelectActivity.this, rightDatas, book) {
            @Override
            public void seOnItemClick(int selectPostion, String airline) {
                for (int i = 0; i < leftDatas.size(); i++) {
                    if (airline.equals(leftDatas.get(i).getNameAbbr())) {
                        imageUrl = leftDatas.get(i).getImgLink();
                    }
                }

                Intent newintent = new Intent(ProgramSelectActivity.this, ProgramDetailActivity.class);
                newintent.putExtra("productNo", rightDatas.get(selectPostion).getProductNo());
                newintent.putExtra("startEnd", rightDatas.get(selectPostion).getStartPort());
                newintent.putExtra("productDate", rightDatas.get(selectPostion).getProductDate());
                if ("填入".equals(tvHeadNumber.getText().toString().trim())) {
                    newintent.putExtra("goodNumber", "");
                } else {
                    newintent.putExtra("goodNumber", tvHeadNumber.getText().toString().trim());
                }

                if ("填入".equals(tvHeadWeight.getText().toString().trim())) {
                    newintent.putExtra("goodWeight", "");
                } else {
                    newintent.putExtra("goodWeight", tvHeadWeight.getText().toString().trim());
                }

                if ("填入".equals(tvHeadVol.getText().toString().trim())) {
                    newintent.putExtra("goodVolume", "");
                } else {
                    newintent.putExtra("goodVolume", tvHeadVol.getText().toString().trim());
                }

                if ("- -".equals(tvHeadPortion.getText().toString().trim())) {
                    newintent.putExtra("proportion", "");
                } else {
                    newintent.putExtra("proportion", tvHeadPortion.getText().toString().trim());
                }
                newintent.putExtra("bookingPosition", book);
                newintent.putExtra("packageWay", packget);
                newintent.putExtra("endHy", tvEndHy.getText().toString().trim());
                newintent.putExtra("imageUrl", imageUrl);
                startActivity(newintent);
            }
        };
        rvTest.setAdapter(testAdapter);
    }


    //第一次进入的数据
    private void initFrist() {
        startList = SPUtil.getObject(ProgramSelectActivity.this, "startList", List.class);
        starts.clear();
        for (int i = 0; i < startList.size(); i++) {
            starts.add(startList.get(i).getPort());
        }

        start = getIntent().getStringExtra("start");
        startHy = getIntent().getStringExtra("startHy");
        end = getIntent().getStringExtra("end");
        endHY = getIntent().getStringExtra("endHY");
        number = getIntent().getStringExtra("number");
        weight = getIntent().getStringExtra("weight");
        vol = getIntent().getStringExtra("vol");

        if ("出发地".equals(start)) {
            tvStartPy.setText("--");
        } else {
            tvStartPy.setText(start);
        }
        tvStartHy.setText(startHy);
        tvEndPy.setText(end);
        tvEndHy.setText(endHY);
        if (!TextUtils.isEmpty(number)) {
            tvHeadNumber.setText(number);
        } else {
            tvHeadNumber.setText("填入");
        }

        if (!TextUtils.isEmpty(weight)) {
            tvHeadWeight.setText(weight);
        } else {
            tvHeadWeight.setText("填入");
        }

        if (!TextUtils.isEmpty(vol)) {
            tvHeadVol.setText(vol);
        } else {
            tvHeadVol.setText("填入");
        }

        if (!TextUtils.isEmpty(weight) && !TextUtils.isEmpty(vol)) {
            double v1 = Double.parseDouble(weight);
            double v2 = Double.parseDouble(vol);
            tvHeadPortion.setText("1:" + (int) (v1 / v2));
        } else {
            tvHeadPortion.setText("- -");
        }
    }

    private void initScroll() {
       /* scroll.setScrollViewListener((view, scrollX, scrollY, clampedX, clampedY) -> {
            if (scrollY == 0) {
                vLine.setVisibility(View.GONE);
            } else {
                vLine.setVisibility(View.VISIBLE);
            }
        });*/
    }

    private void initRecycleView() {
        for (int i = 2; i < 12; i++) {
            String oldDate = DateUtils.getOldDate(i);
            dates.add(oldDate);
        }
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvDate.setLayoutManager(manager);
        dateAdapter = new DateAdapter(ProgramSelectActivity.this, dates) {
            @Override
            public void setOnItemDate(String date) {
                DATE = date;
                getData();
            }
        };
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


    @OnClick({R.id.tv_just, R.id.tv_prepare, R.id.tv_tray, R.id.tv_bulk, R.id.all_compresive_sort, R.id.all_sale_sort, R.id.all_price_sort, R.id.all_set_line})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.all_set_line://头部弹框
                indexDialog = new IndexDialog(ProgramSelectActivity.this, "1") {

                    @Override
                    public void startClick(View view, TextView tv, ImageView iv) {
                        addPopWindow.showPopupWindow(view, starts);
                        addPopWindow.setOnPopupItemClickListener((sid, postion) -> {
                            tv.setText(sid);
                            tv.setTextColor(Color.parseColor("#000000"));
                            iv.setVisibility(View.VISIBLE);
                            nameC = startList.get(postion).getNameC();
                            tvStartHy.setText(nameC);
                        });
                    }

                    @Override
                    public void endClick(TextView tv, ImageView iv) {
                        Intent intent = new Intent(ProgramSelectActivity.this, SearchActivity.class);
                        intent.putExtra("type", "1");
                        startActivity(intent);
                    }

                    @Override
                    public void startCloseClick(TextView tv, ImageView iv) {
                        tv.setText("出发地");
                        tv.setTextColor(Color.parseColor("#969696"));
                        iv.setVisibility(View.GONE);
                    }

                    @Override
                    public void endCloseClick(TextView textView, ImageView iv) {
                        textView.setText("目的地");
                        textView.setTextColor(Color.parseColor("#969696"));
                        iv.setVisibility(View.GONE);
                    }


                    @Override
                    public void sureClick(TextView tv1, TextView tv2, EditText et_show1, EditText et_show2, EditText et_show3, TextView tv3, String portCity) {
                        String trim1 = et_show1.getText().toString().trim();
                        String trim2 = et_show2.getText().toString().trim();
                        String trim3 = et_show3.getText().toString().trim();
                        if ("出发地".equals(tv1.getText().toString()) && "目的地".equals(tv2.getText().toString()) && TextUtils.isEmpty(trim1) && TextUtils.isEmpty(trim2) && TextUtils.isEmpty(trim3)) {
                            tv3.setVisibility(View.VISIBLE);
                            tv3.setText("缺少目的港");
                            return;
                        } else if ("目的地".equals(tv2.getText().toString())) {
                            tv3.setVisibility(View.VISIBLE);
                            tv3.setText("缺少目的港");
                            return;
                        } else if (TextUtils.isEmpty(trim1) && !TextUtils.isEmpty(trim2) && !TextUtils.isEmpty(trim3)) {
                            tv3.setVisibility(View.VISIBLE);
                            tv3.setText("件数、重量、体积参数错误");
                            return;
                        } else if (!TextUtils.isEmpty(trim1) && TextUtils.isEmpty(trim2) && !TextUtils.isEmpty(trim3)) {
                            tv3.setVisibility(View.VISIBLE);
                            tv3.setText("件数、重量、体积参数错误");
                            return;
                        } else if (!TextUtils.isEmpty(trim1) && !TextUtils.isEmpty(trim2) && TextUtils.isEmpty(trim3)) {
                            tv3.setVisibility(View.VISIBLE);
                            tv3.setText("件数、重量、体积参数错误");
                            return;
                        } else if (!TextUtils.isEmpty(trim1) && TextUtils.isEmpty(trim2) && TextUtils.isEmpty(trim3)) {
                            tv3.setVisibility(View.VISIBLE);
                            tv3.setText("件数、重量、体积参数错误");
                            return;
                        } else if (TextUtils.isEmpty(trim1) && !TextUtils.isEmpty(trim2) && TextUtils.isEmpty(trim3)) {
                            tv3.setVisibility(View.VISIBLE);
                            tv3.setText("件数、重量、体积参数错误");
                            return;
                        } else if (TextUtils.isEmpty(trim1) && TextUtils.isEmpty(trim2) && !TextUtils.isEmpty(trim3)) {
                            tv3.setVisibility(View.VISIBLE);
                            tv3.setText("件数、重量、体积参数错误");
                            return;
                        } else if ("0".equals(et_show1.getText().toString().trim()) || "0".equals(et_show3.getText().toString().trim())
                                || "0.0".equals(et_show1.getText().toString().trim()) || "0.0".equals(et_show3.getText().toString().trim())) {
                            tv3.setVisibility(View.VISIBLE);
                            tv3.setText("件数、体积不可以为0");
                            return;
                        } else if (!TextUtils.isEmpty(trim1) && !TextUtils.isEmpty(trim2) && !TextUtils.isEmpty(trim3)) {
                            int intWeight = Integer.parseInt(trim2);
                            if (intWeight < 100) {
                                tv3.setVisibility(View.VISIBLE);
                                tv3.setText("重量不得少于100kg");
                                return;
                            } else {
                                if (TextUtils.isEmpty(tv1.getText().toString().trim()) || "出发地".equals(tv1.getText().toString().trim())) {
                                    tvStartPy.setText("--");
                                    tvStartHy.setText("");
                                } else {
                                    tvStartPy.setText(tv1.getText().toString().trim());
                                }

                                tvEndPy.setText(tv2.getText().toString().trim());
                                if (TextUtils.isEmpty(portCity)) {
                                    tvEndHy.setText(tvEndHy.getText().toString().trim());
                                } else {
                                    tvEndHy.setText(portCity);
                                }

                                if (!TextUtils.isEmpty(et_show1.getText().toString().trim())) {
                                    tvHeadNumber.setText(et_show1.getText().toString().trim());
                                } else {
                                    tvHeadNumber.setText("填入");
                                }

                                if (!TextUtils.isEmpty(et_show2.getText().toString().trim())) {
                                    tvHeadWeight.setText(et_show2.getText().toString().trim());
                                } else {
                                    tvHeadWeight.setText("填入");
                                }

                                if (!TextUtils.isEmpty(et_show3.getText().toString().trim())) {
                                    tvHeadVol.setText(et_show3.getText().toString().trim());
                                } else {
                                    tvHeadVol.setText("填入");
                                }

                                if (!"填入".equals(tvHeadWeight.getText().toString().trim()) && !"填入".equals(tvHeadVol.getText().toString().trim())) {
                                    double v1 = Double.parseDouble(tvHeadWeight.getText().toString().trim());
                                    double v2 = Double.parseDouble(tvHeadVol.getText().toString().trim());
                                    tvHeadPortion.setText("1:" + (int) (v1 / v2));
                                } else {
                                    tvHeadPortion.setText("- -");
                                }
                                tv3.setVisibility(View.INVISIBLE);
                                tv3.setText("");
                                dismiss();
                                getData();
                            }
                        } else {

                            if ("--".equals(tv1.getText().toString().trim()) || "出发地".equals(tv1.getText().toString().trim())) {
                                tvStartPy.setText("--");
                                tvStartHy.setText("");
                            } else {
                                tvStartPy.setText(tv1.getText().toString().trim());
                            }

                            tvEndPy.setText(tv2.getText().toString().trim());
                            if (TextUtils.isEmpty(portCity)) {
                                tvEndHy.setText(tvEndHy.getText().toString().trim());
                            } else {
                                tvEndHy.setText(portCity);
                            }

                            if (!TextUtils.isEmpty(et_show1.getText().toString().trim())) {
                                tvHeadNumber.setText(et_show1.getText().toString().trim());
                            } else {
                                tvHeadNumber.setText("填入");
                            }

                            if (!TextUtils.isEmpty(et_show2.getText().toString().trim())) {
                                tvHeadWeight.setText(et_show2.getText().toString().trim());
                            } else {
                                tvHeadWeight.setText("填入");
                            }

                            if (!TextUtils.isEmpty(et_show3.getText().toString().trim())) {
                                tvHeadVol.setText(et_show3.getText().toString().trim());
                            } else {
                                tvHeadVol.setText("填入");
                            }

                            if (!"填入".equals(tvHeadWeight.getText().toString().trim()) && !"填入".equals(tvHeadVol.getText().toString().trim())) {
                                double v1 = Double.parseDouble(tvHeadWeight.getText().toString().trim());
                                double v2 = Double.parseDouble(tvHeadVol.getText().toString().trim());
                                tvHeadPortion.setText("1:" + (int) (v1 / v2));
                            } else {
                                tvHeadPortion.setText("- -");
                            }
                            tv3.setVisibility(View.INVISIBLE);
                            tv3.setText("");
                            dismiss();
                            getData();
                        }
                    }
                };

                //初始化dialog数据
                if (!"--".equals(tvStartPy.getText().toString().trim())) {
                    indexDialog.setStart(tvStartPy.getText().toString().trim());
                }
                indexDialog.setPort(tvEndPy.getText().toString().trim());

                if (!"填入".equals(tvHeadNumber.getText().toString().trim())) {
                    indexDialog.setNumber(tvHeadNumber.getText().toString().trim());
                }
                if (!"填入".equals(tvHeadWeight.getText().toString().trim())) {
                    indexDialog.setWeight(tvHeadWeight.getText().toString().trim());
                }
                if (!"填入".equals(tvHeadVol.getText().toString().trim())) {
                    indexDialog.setVol(tvHeadVol.getText().toString().trim());
                }
                indexDialog.show();
                int i = IndexDialog.bWidth;
                addPopWindow = new AddPopWindow(ProgramSelectActivity.this);
                addPopWindow.setFocusable(true);
                addPopWindow.setTouchInterceptor((v, event) -> {
                    if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
                        addPopWindow.dismiss();
                        return true;
                    }
                    return false;
                });
                break;
            case R.id.tv_just://正装
                book = "0";
                initSelectColor(tvJust, tvPrepare, 1);
                getData();
                break;
            case R.id.tv_prepare://备装:
                book = "1";
                initSelectColor(tvJust, tvPrepare, 2);
                getData();
                break;
            case R.id.tv_tray://散货
                packget = "0";
                initSelectColor(tvTray, tvBulk, 1);
                getData();
                break;
            case R.id.tv_bulk://托盘:
                packget = "1";
                initSelectColor(tvTray, tvBulk, 2);
                getData();
                break;
            case R.id.all_compresive_sort://综合筛选
                initSortColor(0);
                Collections.sort(rightDatas, new Comparator<ProgramSelectInfo.ProductCardListBean>() {
                    @Override
                    public int compare(ProgramSelectInfo.ProductCardListBean p1, ProgramSelectInfo.ProductCardListBean p2) {
                        int i1 = Integer.parseInt(p1.getSort().getIntegrated());
                        int i2 = Integer.parseInt(p2.getSort().getIntegrated());
                        int i = i1 - i2;
                        return i1 - i2;
                    }
                });

                testAdapter.notifyDataSetChanged();
                break;
            case R.id.all_sale_sort://销售筛选
                initSortColor(1);
                Collections.sort(rightDatas, new Comparator<ProgramSelectInfo.ProductCardListBean>() {
                    @Override
                    public int compare(ProgramSelectInfo.ProductCardListBean p1, ProgramSelectInfo.ProductCardListBean p2) {
                        int i1 = Integer.parseInt(p1.getSort().getSales());
                        int i2 = Integer.parseInt(p2.getSort().getSales());
                        int i = i1 - i2;
                        return i1 - i2;
                    }
                });

                testAdapter.notifyDataSetChanged();
                break;
            case R.id.all_price_sort://价格筛选
                initSortColor(2);
                Collections.sort(rightDatas, new Comparator<ProgramSelectInfo.ProductCardListBean>() {
                    @Override
                    public int compare(ProgramSelectInfo.ProductCardListBean p1, ProgramSelectInfo.ProductCardListBean p2) {
                        int i1 = Integer.parseInt(p1.getSort().getPrice());
                        int i2 = Integer.parseInt(p2.getSort().getPrice());
                        int i = i1 - i2;
                        return i1 - i2;
                    }
                });

                testAdapter.notifyDataSetChanged();
                break;
        }
    }

    //设置整备装颜色
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


    //设置筛选颜色
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

    /*
     * 目的港选择结果
     */
    @Subscribe(
            thread = EventThread.MAIN_THREAD,
            tags = {@Tag("select")}
    )
    public void select(String port) {
        String[] split = port.split(",");
        endHY = split[1];
        indexDialog.setPort(split[0]);
        indexDialog.setPortCity(endHY);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (indexDialog != null) {
            indexDialog.dismiss();
        }
    }

}
