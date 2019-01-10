package com.mobile.android.app.build_material_feedback;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.mobile.android.R;
import com.mobile.android.SupervisorApp;
import com.mobile.android.entity.JcFeedBackType;
import com.mobile.android.entity.JcOwnerOrderInfo;
import com.mobile.android.retrofit.ApiContstants;
import com.mobile.android.widgets.dialog.ListSelectDialog;
import com.mobile.android.widgets.dialog.LoadingDialog;
import com.mobile.android.widgets.pop.AddPopWindow;
import com.mobile.hyoukalibrary.base.BaseActivity;
import com.mobile.hyoukalibrary.utils.ToastUtil;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author clz
 * @desc 建材反馈页面
 */
public class BuildingMaterialsFeedBackActivity extends BaseActivity {


    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_feed_back_type)
    TextView mTvFeedBackType;
    @BindView(R.id.et_desiginer)
    EditText mEtDesiginer;
    @BindView(R.id.edit_feedback_content)
    EditText mEditFeedbackContent;
    @BindView(R.id.tv_content_count)
    TextView mTvContentCount;
    @BindView(R.id.all_feedback)
    AutoLinearLayout mAllFeedback;
    @BindView(R.id.arl_feedback_selector)
    AutoRelativeLayout mArlFeedbackSelector;
    @BindView(R.id.tv_owner_order)
    TextView mTvOwnerOrder;
    private ArrayList<String> mFeedBackTypes = new ArrayList<>();
    private AddPopWindow mFeedBackTypeWindow;
    private ListSelectDialog mSelectDialog;
    private List<String> mSelectTypes = new ArrayList<>();
    private LoadingDialog mLoadingDialog;
    private List<JcOwnerOrderInfo.ListBean> mOrderInfos;
    //反馈类型的id
    private int mFkTypeId;
    //业主订单id
    private int mOrderId;
    //是否发放服务手册
    private String mIsFwsc;
    //业主id
    private String mOwnerId;
    private List<JcFeedBackType.FkTypeBean> mFk_types;

    @Override
    protected void initViews(Bundle savedInstanceState) {
        ButterKnife.bind(this);
//        initData();
        initListener();
    }

    /**
     * 获取反馈类型
     *//*
    private void initData() {
        params.clear();
        params.put(ApiContstants.UID, SupervisorApp.getUser().getUid());
        L.i("uid2",SupervisorApp.getUser().getUid());
        params.put(ApiContstants.TOKEN, SupervisorApp.getUser().getToken());
        if (null == mLoadingDialog) {
            mLoadingDialog = new LoadingDialog(this);
        }
        mLoadingDialog.show();
        RetrofitManager.getInstance().create(CommonService.class)
                .getJcConfig(params)
                .throttleFirst(1, TimeUnit.SECONDS)
                .retryWhen(new RetryWhenNetworkException(2, 500, 500))
                .compose(RxSchedulers.<BaseEntity<JcFeedBackType>>io_main())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onFinally() {
                        super.onFinally();
                        mLoadingDialog.dismiss();
                    }

                    @Override
                    protected void onHandleSuccess(JcFeedBackType data) {
                        mFeedBackTypes.clear();
                        mFk_types = data.getFk_type();
                        mTvFeedBackType.setText(mFk_types.get(0).getType());
                        mFkTypeId = mFk_types.get(0).getId();
                        for (int i = 0; i < mFk_types.size(); i++) {
                            mFeedBackTypes.add(mFk_types.get(i).getType());
                        }
                        mFeedBackTypeWindow = new AddPopWindow(BuildingMaterialsFeedBackActivity.this, 100);
                        mFeedBackTypeWindow.setPopupItemClickListener2(new AddPopWindow.PopupItemClickListener2() {
                            @Override
                            public void popupItemClick(int position) {
                                mTvFeedBackType.setText(mFk_types.get(position).getType());
                                mFkTypeId = mFk_types.get(position).getId();
                                mEditFeedbackContent.setText("");
                                mOrderId=0;
                                mTvOwnerOrder.setText("选择业主订单");
                            }
                        });
                    }
                });

    }*/

    private void initListener() {
        mEditFeedbackContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                final String s = mEditFeedbackContent.getText().toString();
                if (s.length() > 500) {
                    mTvContentCount.setText("500");
                    ToastUtil.show(BuildingMaterialsFeedBackActivity.this, "反馈内容已超过最大输入长度");
                } else {
                    mTvContentCount.setText(s.trim().length() + "");
                }
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_building_materials_feed_back;
    }

    @Override
    public void initToolBar() {
        mTvTitle.setText("建材反馈");
    }

    @OnClick({R.id.iv_back, R.id.tv_submit, R.id.arl_feedback_selector, R.id.iv_search, R.id.all_owner_order})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_submit:
                submit();
                break;
            //反馈类型
            case R.id.arl_feedback_selector:
                mFeedBackTypeWindow.showPopupWindow(mArlFeedbackSelector, mFeedBackTypes);
                break;
            case R.id.iv_search:
                search();
                break;
            case R.id.all_owner_order:
                showOwnerOrder();
                break;
            default:
                break;
        }
    }

    /**
     * 提交反馈
     */
    private void submit() {
        params.clear();
//        params.put(ApiContstants.UID, SupervisorApp.getUser().getUid());
        params.put(ApiContstants.TOKEN, SupervisorApp.getUser().getToken());
        if (mFkTypeId == 0) {
            ToastUtil.show(this, "请选择反馈类型");
            return;
        } else {
            params.put("fk_type", mFkTypeId);
        }
        final String ownerid = mEtDesiginer.getText().toString().trim();
        if (TextUtils.isEmpty(ownerid)) {
            ToastUtil.show(this, "请输入业主id");
            return;
        }
        if (mOrderId == 0) {
            ToastUtil.show(this, "请选择业主订单号");
            return;
        } else {
            params.put("allot_id", mOrderId);
        }
        final String content = mEditFeedbackContent.getText().toString().trim();
        if (TextUtils.isEmpty(content)) {
            ToastUtil.show(this, "请输入反馈内容");
            return;
        } else {
            params.put("fk_content", content);
        }
        if (TextUtils.isEmpty(mIsFwsc)) {
            ToastUtil.show(this, "请输入正确的业主id");
            return;
        } else {
            params.put("is_fwsc", mIsFwsc);
        }
        if (null == mLoadingDialog) {
            mLoadingDialog = new LoadingDialog(this);
        }
        mLoadingDialog.show();
       /* RetrofitManager.getInstance().create(CommonService.class)
                .jcFkSubmit(params)
                .throttleFirst(1, TimeUnit.SECONDS)
                .retryWhen(new RetryWhenNetworkException(2, 500, 500))
                .compose(RxSchedulers.<BaseEntity<List>>io_main())
                .subscribe(new BaseObserver<List>() {
                    @Override
                    protected void onFinally() {
                        super.onFinally();
                        mLoadingDialog.dismiss();
                    }

                    @Override
                    protected void onHandleSuccess(List data) {
                        new AlertDialog.Builder(BuildingMaterialsFeedBackActivity.this)
                                .setMessage("反馈提交成功")
                                .setPositiveButton("我知道了", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        mEditFeedbackContent.setText("");
                                        mOrderId=0;
                                        mTvOwnerOrder.setText("选择业主订单");
                                        mEtDesiginer.setText("");
                                        mTvFeedBackType.setText(mFk_types.get(0).getType());
                                        mFkTypeId = mFk_types.get(0).getId();
                                        mEditFeedbackContent.setText("");
                                        mAllFeedback.setVisibility(View.GONE);
                                    }
                                }).show();
                    }
                });*/
    }

    /**
     * 弹出业主订单
     */
    private void showOwnerOrder() {
        if (mSelectTypes.size() > 0) {
            mSelectDialog = new ListSelectDialog(this, mSelectTypes) {
                @Override
                public void itemClick(int position) {
                    mTvOwnerOrder.setText(mSelectTypes.get(position));
                    mOrderId = mOrderInfos.get(position).getAllot_id();
                }
            };
            mSelectDialog.show();
        }

    }

    /**
     * 搜索业主订单信息
     */
    private void search() {
        final String ownerId = mEtDesiginer.getText().toString().trim();
        if (TextUtils.isEmpty(ownerId)) {
            ToastUtil.show(this, "请输入业主ID");
            return;
        }
        params.clear();
//        params.put(ApiContstants.UID, SupervisorApp.getUser().getUid());
        params.put(ApiContstants.TOKEN, SupervisorApp.getUser().getToken());
        params.put(ApiContstants.BM_ID, ownerId);
        if (null == mLoadingDialog) {
            mLoadingDialog = new LoadingDialog(this);
        }
        mLoadingDialog.show();
        /*RetrofitManager.getInstance().create(CommonService.class)
                .getJcOwnerOrderInfo(params)
                .throttleFirst(1, TimeUnit.SECONDS)
                .retryWhen(new RetryWhenNetworkException(2, 500, 500))
                .compose(RxSchedulers.<BaseEntity<JcOwnerOrderInfo>>io_main())
                .subscribe(new BaseObserver<JcOwnerOrderInfo>() {
                    @Override
                    protected void onFinally() {
                        super.onFinally();
                        mLoadingDialog.dismiss();
                    }

                    @Override
                    protected void onHandleSuccess(JcOwnerOrderInfo data) {
                        L.i("jcfk",data.toString());
                        mOwnerId = ownerId;
                        mAllFeedback.setVisibility(View.VISIBLE);
                        mIsFwsc = data.getIs_fwsc();
                        mOrderInfos = data.getList();
                        mSelectTypes.clear();
                        for (int i = 0; i < mOrderInfos.size(); i++) {
                            mSelectTypes.add(mOrderInfos.get(i).getAllot_id() + "");
                        }
                    }
                });*/
    }
}
