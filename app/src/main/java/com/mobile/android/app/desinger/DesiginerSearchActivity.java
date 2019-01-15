package com.mobile.android.app.desinger;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobile.android.R;
import com.mobile.android.SupervisorApp;
import com.mobile.android.entity.DesiginerSearchInfo;
import com.mobile.android.retrofit.ApiContstants;
import com.mobile.android.widgets.dialog.LoadingDialog;
import com.mobile.android.widgets.pop.AddPopWindow;
import com.mobile.hyoukalibrary.base.BaseActivity;
import com.mobile.hyoukalibrary.utils.StatusBarCompat;
import com.mobile.hyoukalibrary.utils.ToastUtil;
import com.zhy.autolayout.AutoFrameLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 设计师查询页面
 * Created by wangqiang on 2018/5/16.
 */
public class DesiginerSearchActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tb_desiginer_search)
    Toolbar mTbDesiginerSearch;
    @BindView(R.id.et_desiginer)
    EditText mEtDesiginer;
    @BindView(R.id.iv_desiginer_search)
    ImageView mIvDesiginerSearch;
    /*@BindView(R.id.rv_desiginer)
    RecyclerView mRvDesiginer;*/
    @BindView(R.id.tv_desiginer_type)
    TextView mTvDesiginerType;
    @BindView(R.id.arl_desiginer_selector)
    AutoRelativeLayout mArlDesiginerSelector;
    @BindView(R.id.fl_content)
    AutoFrameLayout flContent;
    private AddPopWindow addPopWindow;
    private ArrayList<String> sList;
    private int dType = 2;
    private DesiginerAdapter desiginerAdapter;
    private int baoming_id;
    private LoadingDialog loadingDialog;
    private List<DesiginerSearchInfo> datas;
    private View nodataView;
    private View havedataView;
    private RecyclerView mRvDesiginer;


    @Override
    protected void initViews(Bundle savedInstanceState) {
        StatusBarCompat.setTranslucentForImageView(this, 0, null);
        ButterKnife.bind(this);
        sList = new ArrayList<>();
        sList.add("装修");
        sList.add("建材");
        mTbDesiginerSearch.setNavigationOnClickListener(view -> {
            finish();
        });
        mIvDesiginerSearch.setOnClickListener(this);
        mArlDesiginerSelector.setOnClickListener(this);
        addPopWindow = new AddPopWindow(DesiginerSearchActivity.this, 400);
        addPopWindow.setOnPopupItemClickListener(new AddPopWindow.PopupItemClickListener() {
            @Override
            public void popupItemClick(String sid) {
                mTvDesiginerType.setText(sid);
                if ("装修".equals(sid)) {
                    dType = 2;
                } else if ("建材".equals(sid)) {
                    dType = 1;
                }
                flContent.removeAllViews();
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_desiginer_search;
    }


    @Override
    public void initToolBar() {

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_desiginer_search:
                if (TextUtils.isEmpty(mEtDesiginer.getText().toString().trim())) {
                    ToastUtil.show(DesiginerSearchActivity.this, "请输入ID");
                } else {
                    baoming_id = Integer.parseInt(mEtDesiginer.getText().toString().trim());
                    if (loadingDialog == null) {
                        loadingDialog = new LoadingDialog(DesiginerSearchActivity.this);
                    }
                    loadingDialog.show();
                    getTypeData();
                }
                break;
            case R.id.arl_desiginer_selector:
//                addPopWindow.showPopupWindow(mArlDesiginerSelector, sList);
                break;
        }
    }

    private void getTypeData() {
        params.clear();
        params.put("token", SupervisorApp.getUser().getToken());
//        params.put("uid", SupervisorApp.getUser().getUid());
        params.put("baoming_id", baoming_id);
        params.put("type", dType);
        /*RetrofitManager.getInstance().create(CommonService.class)
                .getdesiginerinfo(params)
                .throttleFirst(1, TimeUnit.SECONDS)
                .retryWhen(new RetryWhenNetworkException(2, 500, 500))
                .compose(RxSchedulers.<BaseEntity<List<DesiginerSearchInfo>>>io_main())
                .subscribe(new BaseObserver<List<DesiginerSearchInfo>>() {
                    @Override
                    protected void onHandleSuccess(List<DesiginerSearchInfo> desiginerSearchInfos) {
                        loadingDialog.dismiss();
                        datas = desiginerSearchInfos;
                        if (datas == null) {
                            initnoDataView();
                        } else {
                            inithaveData();
                        }
                    }

                    @Override
                    protected void onFinally() {
                        super.onFinally();
                        loadingDialog.dismiss();
                    }
                });*/
    }

    private void inithaveData() {
        flContent.removeAllViews();
        havedataView = LayoutInflater.from(this).inflate(R.layout.des_havedata, null);
        mRvDesiginer = havedataView.findViewById(R.id.rv_desiginer);
        flContent.addView(havedataView);
        LinearLayoutManager lm = new LinearLayoutManager(DesiginerSearchActivity.this);
        mRvDesiginer.setLayoutManager(lm);
        if (desiginerAdapter == null) {
            desiginerAdapter = new DesiginerAdapter(DesiginerSearchActivity.this);
        }
        desiginerAdapter.setData(datas, dType);
        mRvDesiginer.setAdapter(desiginerAdapter);
        desiginerAdapter.notifyDataSetChanged();
        initData();
    }

    private void initnoDataView() {
        flContent.removeAllViews();
        nodataView = LayoutInflater.from(this).inflate(R.layout.des_nodata, null);
        flContent.addView(nodataView);
    }

    private void initData() {
        desiginerAdapter.setListener(new DesiginerAdapter.onListener() {
            @Override
            public void OnListener(int position) {
                call(position);
            }
        });
    }

    private void call(int position) {
        params.clear();
//        params.put(ApiContstants.UID, SupervisorApp.getUser().getUid());
        params.put(ApiContstants.TOKEN, SupervisorApp.getUser().getToken());
        params.put("type", datas.get(position).getType());
        if (dType == 1) {
            params.put("material_id", datas.get(position).getMaterial_id());
        } else if (dType == 2) {
            params.put("designer_id", datas.get(position).getDesigner_id());
        }
        if (null == loadingDialog) {
            loadingDialog = new LoadingDialog(this);
        }
        loadingDialog.show();
        /*RetrofitManager.getInstance().create(CommonService.class)
                .callOutPhone(params)
                .throttleFirst(1, TimeUnit.SECONDS)
                .retryWhen(new RetryWhenNetworkException(2, 500, 500))
                .compose(RxSchedulers.<BaseEntity<List>>io_main())
                .subscribe(new BaseObserver<List>() {
                    @Override
                    protected void onFinally() {
                        super.onFinally();
                        loadingDialog.dismiss();
                    }

                    @Override
                    protected void onHandleSuccess(List data) {
                        loadingDialog.dismiss();
                        if (data != null) {
                            L.i("contact_owner", data.toString());
                            showPromptDialog();
                        }
                    }
                });*/
    }

    private void showPromptDialog() {
        new AlertDialog.Builder(this)
                .setMessage("稍后会为你接通业主的电话，请耐心等待")
                .setPositiveButton("我知道了", null).show();
        mEtDesiginer.setText("");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (addPopWindow != null) {
            addPopWindow.dismiss();
        }
        if (loadingDialog != null) {
            loadingDialog.dismiss();
        }
    }
}
