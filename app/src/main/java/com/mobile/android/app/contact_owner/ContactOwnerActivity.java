package com.mobile.android.app.contact_owner;


import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.mobile.android.R;
import com.mobile.android.SupervisorApp;
import com.mobile.android.entity.ContactOwerBean;
import com.mobile.android.retrofit.ApiContstants;
import com.mobile.android.widgets.dialog.LoadingDialog;
import com.mobile.hyoukalibrary.base.BaseActivity;
import com.mobile.hyoukalibrary.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author clz
 * @desc 联系业主页面
 */
public class ContactOwnerActivity extends BaseActivity {


    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.edit_owner_id)
    EditText mEditOwnerId;
    @BindView(R.id.tv_owner_name)
    TextView mTvOwnerName;
    @BindView(R.id.rcl_phones)
    RecyclerView mRclPhones;
    @BindView(R.id.view_bottom)
    View mViewBottom;
    private List<ContactOwerBean> mData = new ArrayList<>();
    private OwerPhonesAdapter mAdapter;
    private LoadingDialog mLoadingDialog;

    @Override
    protected void initViews(Bundle savedInstanceState) {
        ButterKnife.bind(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_contact_owner;
    }

    @Override
    public void initToolBar() {
        mTvTitle.setText("联系业主");
    }


    @OnClick({R.id.iv_back, R.id.iv_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_search:
                search();
            default:
                break;
        }
    }

    /**
     * 获取业主信息
     */
    private void search() {
        final String id = mEditOwnerId.getText().toString().trim();
        if (TextUtils.isEmpty(id)) {
            ToastUtil.show(this, "请输入业主ID");
            return;
        }
        if (mLoadingDialog == null) {
            mLoadingDialog = new LoadingDialog(this);
        }
        mLoadingDialog.show();
        params.clear();
//        params.put(ApiContstants.UID, SupervisorApp.getUser().getUid());
        params.put(ApiContstants.TOKEN, SupervisorApp.getUser().getToken());
        params.put(ApiContstants.BM_ID, id);
        /*RetrofitManager.getInstance().create(CommonService.class)
                .getOwnerInfo(params)
                .throttleFirst(1, TimeUnit.SECONDS)
                .retryWhen(new RetryWhenNetworkException(2, 500, 500))
                .compose(RxSchedulers.<BaseEntity<List<ContactOwerBean>>>io_main())
                .subscribe(new BaseObserver<List<ContactOwerBean>>() {
                    @Override
                    protected void onFinally() {
                        super.onFinally();
                        mLoadingDialog.dismiss();
                    }

                    @Override
                    protected void onHandleSuccess(List<ContactOwerBean> data) {
                        L.i("contact_owner", data.toString());
                        mData.clear();
                        mViewBottom.setBackgroundColor(ContextCompat.getColor(ContactOwnerActivity.this, R.color.c_EFEFEF));
                        mTvOwnerName.setVisibility(View.VISIBLE);
                        mTvOwnerName.setText("业主姓名: " + data.get(0).getName());
                        mData.addAll(data);
                        initRecyclerView();
                    }
                });*/
    }

    private void initRecyclerView() {
        if (mAdapter == null) {
            mRclPhones.setLayoutManager(new LinearLayoutManager(this));
            mAdapter = new OwerPhonesAdapter(R.layout.item_contact_owner_phone, mData);
            mRclPhones.setAdapter(mAdapter);
            mAdapter.setOnItemChildClickListener((adapter, view, position) -> {
                final ContactOwerBean bean = mAdapter.getItem(position);
                contactOwner(bean);
            });
        } else {
            mAdapter.setNewData(mData);
        }
    }

    /**
     * 联系业主
     *
     * @param bean
     */
    private void contactOwner(ContactOwerBean bean) {
        params.clear();
//        params.put(ApiContstants.UID, SupervisorApp.getUser().getUid());
        params.put(ApiContstants.TOKEN, SupervisorApp.getUser().getToken());
        params.put(ApiContstants.BM_ID, bean.getBaoming_id());
        params.put("type", bean.getType());
        params.put("is_main", bean.getIs_main());
        if (null == mLoadingDialog) {
            mLoadingDialog = new LoadingDialog(this);
        }
        mLoadingDialog.show();
       /* RetrofitManager.getInstance().create(CommonService.class)
                .callOutPhone(params)
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
    }
}
