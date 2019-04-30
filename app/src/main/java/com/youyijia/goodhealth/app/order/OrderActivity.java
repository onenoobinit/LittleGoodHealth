package com.youyijia.goodhealth.app.order;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.androidkun.xtablayout.XTabLayout;
import com.youyijia.goodhealth.R;
import com.youyijia.goodhealth.app.order.fragment.CommentFragment;
import com.youyijia.goodhealth.app.order.fragment.ExmineFragment;
import com.youyijia.goodhealth.app.order.fragment.OperationFragment;
import com.youyijia.goodhealth.app.order.fragment.ToPayFragment;
import com.youyijia.goodhealth.app.order.fragment.WholeFragment;
import com.youyijia.hyoukalibrary.base.BaseActivity;
import com.youyijia.hyoukalibrary.utils.StatusBarCompat;

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
    private String[] titles = {"全部", "待支付", "待发货", "待收货", "待评价"};

    @Override
    protected void initViews(Bundle savedInstanceState) {
        StatusBarCompat.setTranslucentForImageView(this, 0, null);
        ButterKnife.bind(this);
        int number = getIntent().getIntExtra("number", 1);

        list = new ArrayList<>();
        list.add(WholeFragment.newInstance());//全部
        list.add(ExmineFragment.newInstance());//待支付
        list.add(OperationFragment.newInstance());//待发货
        list.add(ToPayFragment.newInstance());//待收货
        list.add(CommentFragment.newInstance());//待评价
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
