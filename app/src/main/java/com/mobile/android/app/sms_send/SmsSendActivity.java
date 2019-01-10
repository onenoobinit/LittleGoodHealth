package com.mobile.android.app.sms_send;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.mobile.android.R;
import com.mobile.android.SupervisorApp;
import com.mobile.android.entity.SmsSendCodeBean;
import com.mobile.android.retrofit.ApiContstants;
import com.mobile.android.widgets.dialog.ListSelectDialog;
import com.mobile.android.widgets.dialog.LoadingDialog;
import com.mobile.hyoukalibrary.base.BaseActivity;
import com.mobile.hyoukalibrary.utils.ToastUtil;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author clz
 * @desc 短信发送页面
 */
public class SmsSendActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.edit_owner_id)
    EditText mEditOwnerId;
    @BindView(R.id.tv_owner_name)
    TextView mTvOwnerName;
    @BindView(R.id.tv_send_node)
    TextView mTvSendNode;
    @BindView(R.id.tv_msg_content)
    TextView mTvMsgContent;
    @BindView(R.id.arl_send_msg)
    AutoRelativeLayout mArlSendMsg;
    private List<SmsSendCodeBean.NodeBean> mSmsNodeBeans = new ArrayList<>();
    private ListSelectDialog mSelectDialog;
    private LoadingDialog mLoadingDialog;
    private List<String> nodes = new ArrayList<>();
    //业主id
    private String mOwnerId;
    private String mMsgContent;
    private String mMsgId;

    @Override
    protected void initViews(Bundle savedInstanceState) {
        ButterKnife.bind(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_sms_send;
    }

    @Override
    public void initToolBar() {
        mTvTitle.setText("短信发送");
    }


    @OnClick({R.id.iv_back, R.id.iv_search, R.id.tv_send_msg, R.id.all_send_node})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            //查询业主的id
            case R.id.iv_search:
                search();
                break;
            //发送短信
            case R.id.tv_send_msg:
                sendMsg();
                break;
            case R.id.all_send_node:
                showSmsNode();
                break;
            default:
                break;
        }
    }

    /**
     * 发送短信
     */
    private void sendMsg() {
        params.clear();
//        params.put(ApiContstants.UID, SupervisorApp.getUser().getUid());
        params.put(ApiContstants.TOKEN, SupervisorApp.getUser().getToken());
        if (mMsgContent == null) {
            ToastUtil.show(this, "请先选择发送节点");
            return;
        } else {
            params.put("content", mMsgContent);
        }
        if (!TextUtils.isEmpty(mOwnerId)) {
            params.put(ApiContstants.BM_ID, mOwnerId);
        } else {
            ToastUtil.show(this, "业主Id不正确,请重新输入");
            return;
        }
        if (null == mLoadingDialog) {
            mLoadingDialog = new LoadingDialog(this);
        }
        mLoadingDialog.show();
       /* RetrofitManager.getInstance().create(CommonService.class)
                .sendMsg(params)
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
                        new AlertDialog.Builder(SmsSendActivity.this)
                                .setMessage("短信发送成功")
                                .setPositiveButton("我知道了", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        mArlSendMsg.setVisibility(View.GONE);
                                        mTvMsgContent.setVisibility(View.GONE);
                                        mEditOwnerId.setText("");
                                        mTvSendNode.setText("选择发送节点");
                                        mMsgContent = null;
                                    }
                                }).show();
                    }
                });*/
    }

    /**
     * 弹出节点选择框
     */
    private void showSmsNode() {
        nodes.clear();
        if (mSmsNodeBeans.size() > 0 && nodes.size() == 0) {
            for (int i = 0; i < mSmsNodeBeans.size(); i++) {
                nodes.add(mSmsNodeBeans.get(i).getTitle());
            }
            mSelectDialog = new ListSelectDialog(this, nodes) {
                @Override
                public void itemClick(int position) {
                    mTvSendNode.setText(nodes.get(position));
                    getSmsTemplateByNode(mSmsNodeBeans.get(position));
                }
            };
        }
        mSelectDialog.show();
    }

    /**
     * 根据短信节点，获取短信模板
     *
     * @param nodeBean
     */
    private void getSmsTemplateByNode(SmsSendCodeBean.NodeBean nodeBean) {
        params.clear();
//        params.put(ApiContstants.UID, SupervisorApp.getUser().getUid());
        params.put(ApiContstants.TOKEN, SupervisorApp.getUser().getToken());
        params.put("message_id", nodeBean.getMessage_id());
        if (!TextUtils.isEmpty(mOwnerId)) {
            params.put(ApiContstants.BM_ID, mOwnerId);
        } else {
            ToastUtil.show(this, "业主Id不正确,请重新输入");
            return;
        }
        if (null == mLoadingDialog) {
            mLoadingDialog = new LoadingDialog(this);
        }
        mLoadingDialog.show();
       /* RetrofitManager.getInstance().create(CommonService.class)
                .getSmsContent(params)
                .throttleFirst(1, TimeUnit.SECONDS)
                .retryWhen(new RetryWhenNetworkException(2, 500, 500))
                .compose(RxSchedulers.<BaseEntity<SmsContentBean>>io_main())
                .subscribe(new BaseObserver<SmsContentBean>() {
                    @Override
                    protected void onFinally() {
                        super.onFinally();
                        mLoadingDialog.dismiss();
                    }

                    @Override
                    protected void onHandleSuccess(SmsContentBean data) {
                        mTvMsgContent.setVisibility(View.VISIBLE);
                        mTvMsgContent.setText(data.getContent());
                        mMsgContent = data.getContent();
                        mMsgId = nodeBean.getMessage_id();
                    }
                });*/
    }

    private void search() {
        final String ownerId = mEditOwnerId.getText().toString().trim();
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
       /* RetrofitManager.getInstance().create(CommonService.class)
                .getSmsNode(params)
                .throttleFirst(1, TimeUnit.SECONDS)
                .retryWhen(new RetryWhenNetworkException(2, 500, 500))
                .compose(RxSchedulers.<BaseEntity<SmsSendCodeBean>>io_main())
                .subscribe(new BaseObserver<SmsSendCodeBean>() {
                    @Override
                    protected void onFinally() {
                        super.onFinally();
                        mLoadingDialog.dismiss();
                    }

                    @Override
                    protected void onHandleSuccess(SmsSendCodeBean data) {
                        mArlSendMsg.setVisibility(View.VISIBLE);
                        mTvOwnerName.setText("业主姓名:  " + data.getName());
                        mSmsNodeBeans.clear();
                        nodes.clear();
                        mOwnerId = ownerId;
                        mSmsNodeBeans.addAll(data.getNode());
                    }
                });*/
    }
}
