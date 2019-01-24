package com.mobile.android.app.submit;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.mobile.android.R;
import com.mobile.android.entity.SubmitInfo;
import com.mobile.hyoukalibrary.base.BaseActivity;
import com.mobile.hyoukalibrary.utils.SPUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wangqiang on 2019/1/19.
 */
public class ContactHistoryActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.logi_tool_bar)
    Toolbar logiToolBar;
    @BindView(R.id.rv_contact_history)
    RecyclerView rvContactHistory;
    private ContactHistoryAdapter contactHistoryAdapter;
    private List<SubmitInfo.OrderBillBean.ContactInfoBean> datas = new ArrayList<>();
    private List<SubmitInfo.OrderBillBean.ContactInfoBean> contactList;

    @Override
    protected void initViews(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        datas.clear();
        datas = SPUtil.getObject(ContactHistoryActivity.this, "contactList", List.class);
        LinearLayoutManager portionAdapter = new LinearLayoutManager(this);
        portionAdapter.setOrientation(LinearLayoutManager.VERTICAL);
        rvContactHistory.setLayoutManager(portionAdapter);
        contactHistoryAdapter = new ContactHistoryAdapter(ContactHistoryActivity.this, datas) {
            @Override
            public void setOnItemClik(int postion) {
//                RxBus.get().post("submitContact", postion);
                setResult(postion);
                finish();
            }
        };
        rvContactHistory.setAdapter(contactHistoryAdapter);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_contact_history;
    }

    @Override
    public void initToolBar() {
        logiToolBar.setNavigationOnClickListener(view -> finish());
        setResult(-1);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        setResult(-1);
    }
}
