package com.mobile.android.app.Memorandum;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.mobile.android.R;
import com.mobile.android.SupervisorApp;
import com.mobile.android.retrofit.ApiContstants;
import com.mobile.android.utils.DateUtils;
import com.mobile.android.widgets.dialog.LoadingDialog;
import com.mobile.hyoukalibrary.base.BaseActivity;
import com.mobile.hyoukalibrary.utils.ToastUtil;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author clz
 * @desc 新建备忘录
 */
public class AddEditMemoRandumActivity extends BaseActivity {


    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_memorandum_date)
    TextView mTvMemorandumDate;
    @BindView(R.id.edit_bw_content)
    EditText mEditBwContent;
    @BindView(R.id.tv_content_count)
    TextView mTvContentCount;
    @BindView(R.id.tv_submit)
    TextView mTvSubmit;
    /**
     * 1-->新建备忘录 2-->编辑备忘录
     */
    private int mType;
    private String mBwId;
    private String mBwDate;
    private String mBwContent;
    private LoadingDialog mLoadingDialog;

    @Override
    protected void initViews(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        initListener();
        initData();
    }

    private void initListener() {
        mEditBwContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                final String s = mEditBwContent.getText().toString();
                if (s.length() > 500) {
                    mTvContentCount.setText("500");
                    ToastUtil.show(AddEditMemoRandumActivity.this, "反馈内容已超过最大输入长度");
                } else {
                    mTvContentCount.setText(s.trim().length() + "");
                }
            }
        });
    }

    private void initData() {
        mType = getIntent().getIntExtra("type", -1);
        if (mType == 1) {
            mTvMemorandumDate.setText("备忘日期:  请选择备忘日期");
        } else if (mType == 2) {
            mBwDate = getIntent().getStringExtra("bwDate");
            mBwContent = getIntent().getStringExtra("bw_content");
            mBwId = getIntent().getStringExtra("bw_id");
            if (!TextUtils.isEmpty(mBwDate) && !TextUtils.isEmpty(mBwContent)) {
                mTvMemorandumDate.setText("备忘日期:    " + mBwDate);
                mEditBwContent.setText(mBwContent);
            }
        }

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_addd_edit_memo_randum;
    }

    @Override
    public void initToolBar() {
        mTvTitle.setText("备忘记录");
        mTvSubmit.setText("保存");
    }

    @OnClick({R.id.iv_back, R.id.tv_memorandum_date, R.id.tv_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_memorandum_date:
                showTimeDialog();
                break;
            case R.id.tv_submit:
                submit();
                break;
            default:
                break;
        }
    }

    private void submit() {
        if (mType == 1) {
            //新增备忘
            addBw();
        } else if (mType == 2) {
            //编辑备忘
            editBw();
        }
    }

    private void addBw() {
        params.clear();
//        params.put(ApiContstants.UID, SupervisorApp.getUser().getUid());
        params.put(ApiContstants.TOKEN, SupervisorApp.getUser().getToken());
        if (!TextUtils.isEmpty(mBwDate)) {
            params.put("remark_time", mBwDate);
        } else {
            ToastUtil.show(this, "请选择备忘时间");
            return;
        }
        final String content = mEditBwContent.getText().toString().trim();
        if (!TextUtils.isEmpty(content)) {
            params.put("remark_text", content);
        } else {
            ToastUtil.show(this, "请填写备忘内容");
            return;
        }
        if (null == mLoadingDialog) {
            mLoadingDialog = new LoadingDialog(this);
        }
        mLoadingDialog.show();
        /*RetrofitManager.getInstance().create(CommonService.class)
                .addBw(params)
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
                        ToastUtil.show(AddEditMemoRandumActivity.this, "添加成功");
                        finish();
                    }
                });*/
    }

    private void editBw() {
        params.clear();
//        params.put(ApiContstants.UID, SupervisorApp.getUser().getUid());
        params.put(ApiContstants.TOKEN, SupervisorApp.getUser().getToken());
        params.put("remark_id", mBwId);
        if (!TextUtils.isEmpty(mBwDate)) {
            params.put("remark_time", mBwDate);
        } else {
            ToastUtil.show(this, "请选择备忘时间");
            return;
        }
        final String content = mEditBwContent.getText().toString().trim();
        params.put("remark_text", content);
        if (null == mLoadingDialog) {
            mLoadingDialog = new LoadingDialog(this);
        }
        mLoadingDialog.show();
        /*RetrofitManager.getInstance().create(CommonService.class)
                .editBw(params)
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
                        Intent intent = new Intent();
                        intent.putExtra("bw_id", mBwId);
                        setResult(101, intent);
                        ToastUtil.show(AddEditMemoRandumActivity.this, "修改成功");
                        finish();
                    }
                });*/
    }

    /**
     * 显示时间选择框
     */
    private void showTimeDialog() {
        Calendar startDate = Calendar.getInstance();
        TimePickerView pvTime = new TimePickerBuilder(AddEditMemoRandumActivity.this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                Date currentDate = startDate.getTime();
                final boolean isLessThanCurrentDate = DateUtils.isDateOneBigger(currentDate, date);
                //只能选择此刻之后的时间
                if (isLessThanCurrentDate) {
                    ToastUtil.show(AddEditMemoRandumActivity.this, "只能选择此刻或之后的时间!");
                } else {
                    mTvMemorandumDate.setText("备忘日期:  " + getTime(date));
                    mBwDate = getTime(date);
                }
            }
        })
                .setRangDate(startDate, null)
                .setTitleText("请选择备忘日期")
                .setTitleColor(ContextCompat.getColor(AddEditMemoRandumActivity.this, R.color.c_85b175))
                .setCancelColor(ContextCompat.getColor(AddEditMemoRandumActivity.this, R.color.c_85b175))
                .setSubmitColor(ContextCompat.getColor(AddEditMemoRandumActivity.this, R.color.c_85b175))
                //默认设置为年月日时分秒
                .setLabel("年", "月", "日", "时", "分", "秒")
                // 默认全部显示
                .setType(new boolean[]{true, true, true, true, true, false})
                .build();
        pvTime.show();
    }

    private String getTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        return format.format(date);
    }
}
