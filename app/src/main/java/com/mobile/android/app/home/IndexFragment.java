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
import com.mobile.hyoukalibrary.utils.SPUtil;
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
    private String endHY;
    private List<StartInfo.StartPortBean> startPort;
    private String nameC = "";


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
                            startPort = startInfo.getStartPort();
                            for (int i = 0; i < startPort.size(); i++) {
                                starts.add(startPort.get(i).getPort());
                            }
                            SPUtil.setObject(mContext, "startList", startPort);
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
        nameC = "";
        indexDialog = new IndexDialog(mContext, "0") {

            @Override
            public void startClick(View view, TextView tv, ImageView iv) {
                addPopWindow.showPopupWindow(view, starts);
                addPopWindow.setOnPopupItemClickListener((sid, postion) -> {
                    tv.setText(sid);
                    tv.setTextColor(Color.parseColor("#000000"));
                    iv.setVisibility(View.VISIBLE);
                    nameC = startPort.get(postion).getNameC();
                });
            }

            @Override
            public void endClick(TextView tv, ImageView iv) {
                Intent intent = new Intent(mContext, SearchActivity.class);
                intent.putExtra("type", "0");
                mContext.startActivity(intent);
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
                    dismiss();
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
                } else if (!TextUtils.isEmpty(trim1) && !TextUtils.isEmpty(trim2) && !TextUtils.isEmpty(trim3)) {
                    int intWeight = Integer.parseInt(trim2);
                    if (intWeight < 100) {
                        tv3.setVisibility(View.VISIBLE);
                        tv3.setText("重量不得少于100kg");
                        return;
                    } else {
                        Intent intent = new Intent(mContext, ProgramSelectActivity.class);
                        intent.putExtra("start", tv1.getText().toString());
                        intent.putExtra("startHy", nameC);
                        intent.putExtra("end", tv2.getText().toString());
                        intent.putExtra("endHY", portCity);
                        intent.putExtra("number", et_show1.getText().toString());
                        intent.putExtra("weight", et_show2.getText().toString());
                        intent.putExtra("vol", et_show3.getText().toString());
                        startActivity(intent);
                        tv3.setVisibility(View.INVISIBLE);
                        tv3.setText("");
                        dismiss();
                    }
                } else {
                    Intent intent = new Intent(mContext, ProgramSelectActivity.class);
                    intent.putExtra("start", tv1.getText().toString());
                    intent.putExtra("startHy", nameC);
                    intent.putExtra("end", tv2.getText().toString());
                    intent.putExtra("endHY", portCity);
                    intent.putExtra("number", et_show1.getText().toString());
                    intent.putExtra("weight", et_show2.getText().toString());
                    intent.putExtra("vol", et_show3.getText().toString());
                    startActivity(intent);
                    tv3.setVisibility(View.INVISIBLE);
                    tv3.setText("");
                    dismiss();
                }
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
        String[] split = port.split(",");
        endHY = split[1];
//        System.out.println("AAAAA" + endHY);
        indexDialog.setPort(split[0]);
        indexDialog.setPortCity(endHY);
    }

    /*
     * 目的港选择结果
     */
    @Subscribe(
            thread = EventThread.MAIN_THREAD,
            tags = {@Tag("intypeIndex")}
    )
    public void intypeIndex(String search) {
        indexDialog.setPort(search);
    }
}
