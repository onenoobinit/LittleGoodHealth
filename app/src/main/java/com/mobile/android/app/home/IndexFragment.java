package com.mobile.android.app.home;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobile.android.R;
import com.mobile.android.app.program.ProgramSelectActivity;
import com.mobile.android.app.search.SearchActivity;
import com.mobile.android.widgets.dialog.IndexDialog;
import com.mobile.android.widgets.pop.AddPopWindow;
import com.mobile.hyoukalibrary.base.BaseFragment;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.ArrayList;

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
    private ArrayList<String> starts = new ArrayList<>();
    private AddPopWindow addPopWindow;

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_index;
    }

    @Override
    public void finishCreateView(Bundle state) {
        unbinder = ButterKnife.bind(this, parentView);
    }


    public static IndexFragment newInstance() {
        return new IndexFragment();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.arl_circle)
    public void onClick() {
        IndexDialog indexDialog = new IndexDialog(mContext) {

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
            public void endCloseClick() {

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
        starts.clear();
        starts.add("CGO");
        starts.add("HFE");
        starts.add("HGH");
        starts.add("NGB");
        starts.add("PVG");
        starts.add("SHA");
        addPopWindow = new AddPopWindow((Activity) mContext, 672);
    }
}
