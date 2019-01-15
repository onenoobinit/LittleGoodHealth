package com.mobile.android.app.home;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobile.android.R;
import com.mobile.android.SupervisorApp;
import com.mobile.android.app.program.ProgramSelectActivity;
import com.mobile.android.app.search.SearchActivity;
import com.mobile.android.entity.StartInfo;
import com.mobile.android.retrofit.RetrofitManager;
import com.mobile.android.retrofit.RetryWhenNetworkException;
import com.mobile.android.retrofit.RxSchedulers;
import com.mobile.android.retrofit.api.CommonService;
import com.mobile.android.widgets.dialog.IndexDialog;
import com.mobile.android.widgets.pop.AddPopWindow;
import com.mobile.hyoukalibrary.base.BaseEntity;
import com.mobile.hyoukalibrary.base.BaseFragment;
import com.mobile.hyoukalibrary.base.BaseObserver;
import com.mobile.hyoukalibrary.rxbus.RxBus;
import com.mobile.hyoukalibrary.rxbus.annotation.Subscribe;
import com.mobile.hyoukalibrary.rxbus.annotation.Tag;
import com.mobile.hyoukalibrary.rxbus.thread.EventThread;
import com.mobile.hyoukalibrary.utils.ToastUtil;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by wangqiang on 2019/1/3.
 */
public class IndexFragment extends BaseFragment {
    @BindView(R.id.arl_circle)
    AutoRelativeLayout arlCircle;
    Unbinder unbinder;
    private ArrayList<String> starts = new ArrayList<String>();
    private AddPopWindow addPopWindow;
    private String TOKEN;
    private IndexDialog indexDialog;


    @Override
    public int getLayoutResId() {
        return R.layout.fragment_index;
    }

    @Override
    public void finishCreateView(Bundle state) {
        unbinder = ButterKnife.bind(this, parentView);
        RxBus.get().register(this);
        TOKEN = SupervisorApp.getUser().getToken();
        getStart();
    }

    private void getStart() {
        params.clear();
        params.put("act", "getStartPortData");
//        params.put("token", SupervisorApp.getUser().getToken());
        RetrofitManager.getInstance().create(CommonService.class)
                .getStart(TOKEN, params)
                .throttleFirst(1, TimeUnit.SECONDS)
                .retryWhen(new RetryWhenNetworkException(2, 500, 500))
                .compose(RxSchedulers.io_main())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onHandleSuccess(BaseEntity baseEntity) {
                        if (!TextUtils.isEmpty(baseEntity.getErrMsg())) {
                            ToastUtil.show(mContext, baseEntity.getErrMsg());
                            return;
                        } else {
                            starts.clear();
                            StartInfo startInfo = gson.fromJson(baseEntity.getSuccess(), StartInfo.class);
                            List<StartInfo.StartPortBean> startPort = startInfo.getStartPort();
                            for (int i = 0; i < startPort.size(); i++) {
                                starts.add(startPort.get(i).getPort());
                            }
                        }
                    }
                });
    }


    public static IndexFragment newInstance() {
        return new IndexFragment();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        RxBus.get().unregister(this);
    }

    @OnClick(R.id.arl_circle)
    public void onClick() {
        indexDialog = new IndexDialog(mContext) {

            @Override
            public void startClick(View view, TextView tv, ImageView iv) {
                addPopWindow.showPopupWindow(view, starts);
                addPopWindow.setOnPopupItemClickListener(sid -> {
                    tv.setText(sid);
                    tv.setTextColor(Color.parseColor("#000000"));
                    iv.setVisibility(View.VISIBLE);
                });
            }

            @Override
            public void endClick(TextView tv, ImageView iv) {
                mContext.startActivity(new Intent(mContext, SearchActivity.class));
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
            public void numberListener(String number) {

            }

            @Override
            public void weightListener(String weight) {

            }

            @Override
            public void volListener(String vol) {

            }

            @Override
            public void sureClick(TextView tv1, TextView tv2, EditText et_show1, EditText et_show2, EditText et_show3, TextView tv3) {
                startActivity(new Intent(mContext, ProgramSelectActivity.class));
            }
        };
        indexDialog.show();
        int i = IndexDialog.bWidth;

        addPopWindow = new AddPopWindow((Activity) mContext, 672);
    }

    /*
     * 目的港选择结果
     */
    @Subscribe(
            thread = EventThread.MAIN_THREAD,
            tags = {@Tag("port")}
    )
    public void port(String port) {
        indexDialog.setPort(port);
    }
}
