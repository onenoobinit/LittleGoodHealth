package com.mobile.android.app.order;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.androidkun.xtablayout.XTabLayout;
import com.jaeger.library.StatusBarUtil;
import com.mobile.android.R;
import com.mobile.android.app.order.fragment.CommentFragment;
import com.mobile.android.app.order.fragment.ExmineFragment;
import com.mobile.android.app.order.fragment.OperationFragment;
import com.mobile.android.app.order.fragment.ToPayFragment;
import com.mobile.android.app.order.fragment.WholeFragment;
import com.mobile.hyoukalibrary.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderActivity extends BaseActivity {

    @BindView(R.id.logi_tool_bar)
    Toolbar logiToolBar;
    @BindView(R.id.tablayout)
    XTabLayout tablayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    private List<Fragment> list;
    private String[] titles = {"全部", "待审核", "待操作", "待付款", "待评论"};

    @Override
    protected void initViews(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        StatusBarUtil.setColor(this, Color.parseColor("#F5A623"), 0);
        int number = getIntent().getIntExtra("number", 1);

        list = new ArrayList<>();
        list.add(WholeFragment.newInstance());
        list.add(ExmineFragment.newInstance());
        list.add(OperationFragment.newInstance());
        list.add(ToPayFragment.newInstance());
        list.add(CommentFragment.newInstance());
        MyAdapter myAdapter = new MyAdapter(getSupportFragmentManager());
        viewpager.setAdapter(myAdapter);
        tablayout.setupWithViewPager(viewpager);
        viewpager.setCurrentItem(number);
        logiToolBar.setNavigationOnClickListener(view -> finish());

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_order;
    }

    @Override
    public void initToolBar() {

    }

    class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        //重写这个方法，将设置每个Tab的标题
        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }
}
