package com.mobile.android.widgets.pop;


import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.mobile.android.R;
import com.mobile.android.widgets.GridviewAdapter;

import java.util.ArrayList;

/**
 * 效果图弹出popupwindow
 * Created by wangqiang on 2017/4/11.
 */

public class AddPopWindow extends PopupWindow implements AdapterView.OnItemClickListener {
    private final Activity context;
    private final ListView lv_item;
    //        private final MyFlowLayout mFlowLayout;
    private View conentView;
    private final LayoutInflater inflater;
    private ArrayList<String> list = new ArrayList<>();
    private View parent;
    private boolean isTrue = false;
    Handler handler = new Handler();
    private final GridviewAdapter gridviewAdapter;


    public AddPopWindow(final Activity context, int i) {
        this.context = context;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        conentView = inflater.inflate(R.layout.add_popup_dialog, null);
        int h = context.getWindowManager().getDefaultDisplay().getHeight();
        int w = context.getWindowManager().getDefaultDisplay().getWidth();
        // 设置SelectPicPopupWindow的View
        this.setContentView(conentView);
        // 设置SelectPicPopupWindow弹出窗体的宽
       /* view.post(new Runnable() {
            @Override
            public void run() {
                setWidth(view.getWidth());
            }
        });*/
        setWidth(i);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(500);
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setOutsideTouchable(true);
        // 刷新状态
        this.update();
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0000000000);
        // 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
        this.setBackgroundDrawable(dw);
        lv_item = (ListView) conentView.findViewById(R.id.lv_item);
        gridviewAdapter = new GridviewAdapter(context, list);
        lv_item.setAdapter(gridviewAdapter);
        //grideview的点击事件
        lv_item.setOnItemClickListener(this);
        this.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getRawX() > parent.getX() && event.getRawX() < parent.getX() + parent.getMeasuredWidth()) {
                    parent.setEnabled(false);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            parent.setEnabled(true);
                        }
                    }, 200);
                    setFocusable(true);
                } else {
                    setFocusable(false);
                }
                return false;
            }
        });

    }


    /**
     * 显示popupWindow
     *
     * @param parent
     * @param listData
     */
    public void showPopupWindow(final View parent, ArrayList<String> listData) {
        this.parent = parent;
        if (!this.isShowing()) {
            // 以下拉方式显示popupwindow
//            this.showAsDropDown(parent, parent.getLayoutParams().width / 2, 18);
            this.showAsDropDown(parent, 0, 2);
            list.clear();
            for (int i = 0; i < listData.size(); i++) {
                list.add(listData.get(i));
            }

        } else {
            this.dismiss();
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (popupItemClickListener != null) {
            popupItemClickListener.popupItemClick(list.get(position), position);
            gridviewAdapter.selectIndex = position;
            gridviewAdapter.notifyDataSetChanged();
        }
        if (popupItemClickListener2 != null) {
            popupItemClickListener2.popupItemClick(position);
            gridviewAdapter.selectIndex = position;
            gridviewAdapter.notifyDataSetChanged();
        }
        dismiss();

    }

    //设置回调方法，在popupwindow条目点击事件中调用，传递值给activity刷新数据
    public interface PopupItemClickListener {
        void popupItemClick(String sid, int postion);
    }

    //设置回调方法，在popupwindow条目点击事件中调用，传递值给activity刷新数据
    public interface PopupItemClickListener2 {
        void popupItemClick(int position);
    }

    private PopupItemClickListener popupItemClickListener;
    private PopupItemClickListener2 popupItemClickListener2;

    public void setOnPopupItemClickListener(PopupItemClickListener popupItemClickListener) {
        this.popupItemClickListener = popupItemClickListener;
    }

    public void setPopupItemClickListener2(PopupItemClickListener2 popupItemClickListener2) {
        this.popupItemClickListener2 = popupItemClickListener2;
    }
}


