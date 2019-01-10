package com.mobile.android.app.Memorandum;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.mobile.android.R;
import com.mobile.android.SupervisorApp;
import com.mobile.android.retrofit.ApiContstants;
import com.mobile.android.widgets.dialog.LoadingDialog;
import com.mobile.hyoukalibrary.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author clz
 * @desc 备忘录详情页
 */
public class MemoRandumDetailActivity extends BaseActivity {


    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_memorandum_date)
    TextView mTvMemorandumDate;
    @BindView(R.id.tv_memorandum_content)
    TextView mTvMemorandumContent;
    private String mBwId;
    private LoadingDialog mLoadingDialog;
    private String mBwDate;
    private String mBwContent;

    @Override
    protected void initViews(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        mBwId = getIntent().getStringExtra("bw_id");
        getBwDetailInfo();
    }

    /**
     * 获取备忘录详情
     */
    private void getBwDetailInfo() {
        params.clear();
//        params.put(ApiContstants.UID, SupervisorApp.getUser().getUid());
        params.put(ApiContstants.TOKEN, SupervisorApp.getUser().getToken());
        params.put("remark_id", mBwId + "");
        if (null == mLoadingDialog) {
            mLoadingDialog = new LoadingDialog(this);
        }
        mLoadingDialog.show();
        /*RetrofitManager.getInstance().create(CommonService.class)
                .getBwDetail(params)
                .throttleFirst(1, TimeUnit.SECONDS)
                .retryWhen(new RetryWhenNetworkException(2, 500, 500))
                .compose(RxSchedulers.<BaseEntity<MemoRandomDetailBean>>io_main())
                .subscribe(new BaseObserver<MemoRandomDetailBean>() {
                    @Override
                    protected void onFinally() {
                        super.onFinally();
                        mLoadingDialog.dismiss();
                    }

                    @Override
                    protected void onHandleSuccess(MemoRandomDetailBean data) {
                        mTvMemorandumDate.setText("备忘日期:" + "\n" + data.getRemark_time());
                        mTvMemorandumContent.setText(data.getRemark_txt());
                        mBwDate = data.getRemark_time();
                        mBwContent = data.getRemark_txt();
                    }
                });*/
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_meno_randum_detail;
    }

    @Override
    public void initToolBar() {
        mTvTitle.setText("备忘管理");
    }


    @OnClick({R.id.iv_back, R.id.tv_delete_bw, R.id.tv_edit_bw})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                setResult(102);
                finish();
                break;
            case R.id.tv_delete_bw:
                new AlertDialog.Builder(this)
                        .setMessage("确定删除该条备忘?")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                deleteBw();
                            }
                        })
                        .setNegativeButton("取消", null).show();
                break;
            case R.id.tv_edit_bw:
                Intent intent = new Intent(this, AddEditMemoRandumActivity.class);
                intent.putExtra("type", 2);
                intent.putExtra("bwDate", mBwDate);
                intent.putExtra("bw_id", mBwId);
                intent.putExtra("bw_content", mBwContent);
                startActivityForResult(intent, 100);
                break;
            default:
                break;
        }
    }

    /**
     * 删除备忘
     */
    private void deleteBw() {
        params.clear();
//        params.put(ApiContstants.UID, SupervisorApp.getUser().getUid());
        params.put(ApiContstants.TOKEN, SupervisorApp.getUser().getToken());
        params.put("remark_id", mBwId + "");
        if (null == mLoadingDialog) {
            mLoadingDialog = new LoadingDialog(this);
        }
        mLoadingDialog.show();
        /*RetrofitManager.getInstance().create(CommonService.class)
                .deleteBw(params)
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
                        setResult(102);
                        finish();
                        ToastUtil.show(MemoRandumDetailActivity.this, "备忘删除成功");
                        finish();
                    }
                });*/
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 101) {
            if (!TextUtils.isEmpty(data.getStringExtra("bw_id"))) {
                mBwId = data.getStringExtra("bw_id");
                getBwDetailInfo();
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        setResult(102);
    }
}
