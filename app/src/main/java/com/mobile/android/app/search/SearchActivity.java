package com.mobile.android.app.search;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobile.android.R;
import com.mobile.android.SupervisorApp;
import com.mobile.android.entity.PortInfo;
import com.mobile.android.retrofit.RetrofitManager;
import com.mobile.android.retrofit.RetryWhenNetworkException;
import com.mobile.android.retrofit.RxSchedulers;
import com.mobile.android.retrofit.api.CommonService;
import com.mobile.android.utils.PinyinUtil;
import com.mobile.android.widgets.ObservableScrollView;
import com.mobile.hyoukalibrary.base.BaseActivity;
import com.mobile.hyoukalibrary.base.BaseEntity;
import com.mobile.hyoukalibrary.base.BaseObserver;
import com.mobile.hyoukalibrary.rxbus.RxBus;
import com.mobile.hyoukalibrary.utils.SPUtil;
import com.mobile.hyoukalibrary.utils.ToastUtil;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by wangqiang on 2019/1/9.
 */
public class SearchActivity extends BaseActivity {
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.iv_search_x)
    ImageView ivSearchX;
    @BindView(R.id.tv_search_cancle)
    TextView tvSearchCancle;
    @BindView(R.id.arl_header)
    AutoLinearLayout arlHeader;
    @BindView(R.id.v_line)
    View vLine;
    @BindView(R.id.rv_history)
    RecyclerView rvHistory;
    @BindView(R.id.tv_history_none)
    TextView tvHistoryNone;
    @BindView(R.id.all_program_dialog)
    AutoLinearLayout allProgramDialog;
    @BindView(R.id.collapsingToolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.rv_date)
    RecyclerView rvDate;
    @BindView(R.id.appbarLayout)
    AppBarLayout appbarLayout;
    @BindView(R.id.rv_content)
    RecyclerView rvContent;
    @BindView(R.id.scroll)
    ObservableScrollView scroll;
    @BindView(R.id.iv_search_remove)
    ImageView ivSearchRemove;
    @BindView(R.id.rv_resutl)
    RecyclerView rvResutl;
    @BindView(R.id.arl_search_result)
    AutoRelativeLayout arlSearchResult;
    @BindView(R.id.arl_X)
    AutoRelativeLayout arlX;
    private String TOKEN;
    private PortInfo portInfo;
    private HistoryAdapter historyAdapter;
    private PortItemAdapter portItemAdapter;
    private ArrayList<PortInfo.DestinationListBean> portItems = new ArrayList<>();
    private ArrayList<PortInfo.DestinationListBean.DataListBeanX> contentItems = new ArrayList<>();
    private ContentAdapter contentAdapter;
    InputMethodManager managerintype;
    private ArrayList<PortInfo.DestinationListBean.DataListBeanX> destinationListX;
    private ArrayList<PortInfo.DestinationListBean.DataListBeanX> destinationListx;
    private List<String> history = new ArrayList();
    private ArrayList<String> tests = new ArrayList<>();
    private FuzzySearchAdapter mFuzzySearchAdapter;
    private String type;
    private List<PortInfo.DestinationListAllBean> destinationListAll;

    @Override
    protected void initViews(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        TOKEN = SupervisorApp.getUser().getToken();
        type = getIntent().getStringExtra("type");

        //分类头部
        LinearLayoutManager managerPort = new LinearLayoutManager(this);
        managerPort.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvDate.setLayoutManager(managerPort);
        portItemAdapter = new PortItemAdapter(SearchActivity.this, portItems);
        rvDate.setAdapter(portItemAdapter);
        managerintype = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);

        history = SPUtil.getObject(SearchActivity.this, "history", List.class);

        //浏览记录
        search();
        if (history == null || history.size() == 0) {
            tvHistoryNone.setVisibility(View.VISIBLE);
            rvHistory.setVisibility(View.GONE);
            history = new ArrayList<>();
        } else if (history != null && history.size() > 0) {
            tvHistoryNone.setVisibility(View.GONE);
            rvHistory.setVisibility(View.VISIBLE);
        }

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvHistory.setLayoutManager(manager);
        Collections.reverse(history);
        historyAdapter = new HistoryAdapter(SearchActivity.this, history);
        rvHistory.setAdapter(historyAdapter);

        initData();
        getPort();
        arlX.setOnClickListener(view -> {
            arlSearchResult.setVisibility(View.GONE);
            etSearch.setText("");
            arlX.setVisibility(View.GONE);
        });
    }

    private void search() {
        etSearch.setOnKeyListener((v, keyCode, event) -> {
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                if (managerintype.isActive()) {
                    managerintype.hideSoftInputFromWindow(etSearch.getApplicationWindowToken(), 0);
                }
                //将搜索存入list
                history.add(etSearch.getText().toString().trim());
                SPUtil.setObject(SearchActivity.this, "history", history);

                if ("0".equals(type)) {
                    RxBus.get().post("intypeIndex", etSearch.getText().toString().trim());
                } else if ("1".equals(type)) {
                    RxBus.get().post("intypeSelect", etSearch.getText().toString().trim());
                }
                finish();
            }
            return false;
        });

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                arlX.setVisibility(View.VISIBLE);
                arlSearchResult.setVisibility(View.VISIBLE);
                mFuzzySearchAdapter.getFilter().filter(charSequence.toString().trim());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String s = editable.toString();
                if (TextUtils.isEmpty(s)) {
                    arlX.setVisibility(View.GONE);
                    arlSearchResult.setVisibility(View.GONE);
                }
            }
        });

    }

    private void getPort() {
        params.clear();
        params.put("act", "getAirlineAndDestinationSearchData");
//        params.put("token", SupervisorApp.getUser().getToken());
        RetrofitManager.getInstance().create(CommonService.class)
                .getPort(TOKEN, params)
                .throttleFirst(1, TimeUnit.SECONDS)
                .retryWhen(new RetryWhenNetworkException(2, 500, 500))
                .compose(RxSchedulers.io_main())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onHandleSuccess(BaseEntity baseEntity) {
                        if (!TextUtils.isEmpty(baseEntity.getErrMsg())) {
                            ToastUtil.show(SearchActivity.this, baseEntity.getErrMsg());
                            return;
                        } else {
                            portInfo = gson.fromJson(baseEntity.getSuccess(), PortInfo.class);

                            if (portItems != null) {
                                portItems.clear();
                            }
                            portItems = (ArrayList<PortInfo.DestinationListBean>) portInfo.getDestinationList();
                            SPUtil.setObject(SearchActivity.this, "portItems", portItems);
                            initPort();

                            if (destinationListX != null) {
                                destinationListX.clear();
                            }
                            destinationListX = (ArrayList<PortInfo.DestinationListBean.DataListBeanX>) portInfo.getDestinationList().get(0).getDataList();
                            SPUtil.setObject(SearchActivity.this, "destinationListX", destinationListX);
                            contentItems.clear();
                            contentItems = destinationListX;
                            initContent();

                            if (destinationListAll != null) {
                                destinationListAll.clear();
                            }
                            destinationListAll = portInfo.getDestinationListAll();
                            SPUtil.setObject(SearchActivity.this, "destinationListAll", destinationListAll);
                            initAll();

                            //分类头部点击切换
                            portItemAdapter.setOnContentItemClickListener(postion -> {
                                destinationListx = (ArrayList<PortInfo.DestinationListBean.DataListBeanX>) portInfo.getDestinationList().get(postion).getDataList();
                                contentItems.clear();
                                contentItems.addAll(destinationListx);
                                refreshDataContent(contentItems, false);
                            });
                            initData();
                        }
                    }
                });
    }

    private void initAll() {
        tests.clear();
        for (int i = 0; i < destinationListAll.size(); i++) {
            tests.add(destinationListAll.get(i).getAirport() + "," + destinationListAll.get(i).getCityNameC() + "," + destinationListAll.get(i).getCountryNameC());
        }
        final int size = tests.size();
        String[] strings = (String[]) tests.toArray(new String[size]);
        List<ItemEntity> itemEntities = fillData(strings);
        LinearLayoutManager managerResult = new LinearLayoutManager(this);
        managerResult.setOrientation(LinearLayoutManager.VERTICAL);
        rvResutl.setLayoutManager(managerResult);
        rvResutl.setAdapter(mFuzzySearchAdapter = new FuzzySearchAdapter(itemEntities) {
            @Override
            public void setOnItemClickListener(String position) {
                //将搜索存入list
                String[] split = position.split(",");
                history.add(split[0]);
                SPUtil.setObject(SearchActivity.this, "history", history);
                if ("0".equals(type)) {
                    RxBus.get().post("port", position);
                } else if ("1".equals(type)) {
                    RxBus.get().post("select", position);
                } else if ("2".equals(type)) {
                    RxBus.get().post("three", position);
                }
                finish();
            }
        });
    }

    private void initContent() {
        //分类内容展示列表
        LinearLayoutManager managerContent = new LinearLayoutManager(this);
        managerContent.setOrientation(LinearLayoutManager.VERTICAL);
        rvContent.setLayoutManager(managerContent);
        contentAdapter = new ContentAdapter(SearchActivity.this, contentItems) {
            @Override
            public void setOnItemCont(String name) {
                history.add(name);
                SPUtil.setObject(SearchActivity.this, "history", history);
                etSearch.setText(name);
                arlSearchResult.setVisibility(View.VISIBLE);
                mFuzzySearchAdapter.getFilter().filter(name);
            }
        };
        rvContent.setAdapter(contentAdapter);
    }

    private void initPort() {
        portItemAdapter.notifyDataSetChanged();
        refreshData(portItems, false);
    }

    private void initData() {
        //数据缓存处理
        portItems = SPUtil.getObject(SearchActivity.this, "portItems", ArrayList.class);
        if (portItems != null && portItems.size() > 0) {
            initPort();
        } else {
            portItems = new ArrayList<>();
        }

        contentItems = SPUtil.getObject(SearchActivity.this, "destinationListX", ArrayList.class);
        if (contentItems != null && contentItems.size() > 0) {
            initContent();
        } else {
            contentItems = new ArrayList<>();
        }

        //全部数据的转化 然后填充到搜索adapter
        destinationListAll = SPUtil.getObject(SearchActivity.this, "destinationListAll", List.class);
        if (destinationListAll != null && destinationListAll.size() > 0) {
            initAll();
        } else {
            destinationListAll = new ArrayList<>();
        }
    }

    private List<ItemEntity> fillData(String[] date) {
        List<ItemEntity> sortList = new ArrayList<>();
        for (String item : date) {
            String letter;
            //汉字转换成拼音
            List<String> pinyinList = PinyinUtil.getPinYinList(item);
            if (pinyinList != null && !pinyinList.isEmpty()) {
                // A-Z导航
                String letters = pinyinList.get(0).substring(0, 1).toUpperCase();
                // 正则表达式，判断首字母是否是英文字母
                if (letters.matches("[A-Z]")) {
                    letter = letters.toUpperCase();
                } else {
                    letter = "#";
                }
            } else {
                letter = "#";
            }
            sortList.add(new ItemEntity(item, letter, pinyinList));
        }
        return sortList;

    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    public void initToolBar() {
        tvSearchCancle.setOnClickListener(view -> finish());
    }

    private void refreshData(ArrayList<PortInfo.DestinationListBean> datas, boolean b) {
        int size = portItemAdapter.getData().size();
        if (!b) {
            portItemAdapter.notifyItemRangeRemoved(0, portItemAdapter.getData().size());
        }
        portItemAdapter.setData(datas);
        if (b) {
            rvDate.getAdapter().notifyItemInserted(size);
        } else {
            rvDate.getAdapter().notifyDataSetChanged();
        }
    }

    private void refreshDataContent(ArrayList<PortInfo.DestinationListBean.DataListBeanX> datas, boolean b) {
        int size = contentAdapter.getDataContent().size();
        if (!b) {
            contentAdapter.notifyItemRangeRemoved(0, contentAdapter.getDataContent().size());
        }
        contentAdapter.setDataContent(datas);
        if (b) {
            rvContent.getAdapter().notifyItemInserted(size);
        } else {
            rvContent.getAdapter().notifyDataSetChanged();
        }
    }

    @OnClick(R.id.iv_search_remove)
    public void onClick() {
        rvHistory.setVisibility(View.GONE);
        tvHistoryNone.setVisibility(View.VISIBLE);
        history.clear();
        SPUtil.setObject(SearchActivity.this, "history", history);
    }
}
