package com.youyijia.goodhealth.widgets.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.youyijia.goodhealth.R;
import com.youyijia.goodhealth.app.web.GuigeAdapter;
import com.youyijia.goodhealth.entity.ShopDetailInfo;
import com.youyijia.hyoukalibrary.base.BaseActivity;

import java.util.List;

/**
 * Created by wangqiang on 2019/4/24.
 */
public abstract class GuigeDialog {
    private final Context context;
    private final ShopDetailInfo.SpecificationsBeanX datas;
    public static int portLog = 0;
    private View inflate;
    private Dialog dialog;
    private ImageView iv_gg_icon;
    private TextView tv_gg_price;
    private TextView tv_gg_select;
    private LinearLayout all_addView;
    private LinearLayout ll_shoucang;
    private LinearLayout ll_shopcart;
    private TextView tv_add_shopcar;
    private TextView tv_buy;
    private RecyclerView rv_gg;

    public GuigeDialog(Context context, ShopDetailInfo.SpecificationsBeanX data) {
        this.context = context;
        this.datas = data;
        initView();

    }

    private void initView() {
        inflate = LayoutInflater.from(context).inflate(R.layout.dialog_guige, null);
        dialog = new Dialog(context, R.style.Sweet_Alert_Dialog);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setContentView(inflate);
        Window dialogWindow = dialog.getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
        //设置dialog弹出时的动画效果，从屏幕底部向上弹出
        dialogWindow.setWindowAnimations(R.style.dialogStyle);
        //设置弹框的高为屏幕的一半宽是屏幕的宽
        WindowManager windowManager = ((BaseActivity) context).getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        lp.width = (int) (display.getWidth()); //设置宽度
        lp.height = (int) (display.getHeight() * 0.7); //设置宽度
        dialog.getWindow().setAttributes(lp);

        iv_gg_icon = inflate.findViewById(R.id.iv_gg_icon);
        tv_gg_price = inflate.findViewById(R.id.tv_gg_price);
        tv_gg_select = inflate.findViewById(R.id.tv_gg_select);
//        all_addView = inflate.findViewById(R.id.all_addView);
        ll_shoucang = inflate.findViewById(R.id.ll_shoucang);
        ll_shopcart = inflate.findViewById(R.id.ll_shopcart);
        tv_add_shopcar = inflate.findViewById(R.id.tv_add_shopcar);
        tv_buy = inflate.findViewById(R.id.tv_buy);
        rv_gg = inflate.findViewById(R.id.rv_gg);
        LinearLayoutManager weightmanager = new LinearLayoutManager(context);
        weightmanager.setOrientation(LinearLayoutManager.VERTICAL);
        rv_gg.setLayoutManager(weightmanager);
        List<ShopDetailInfo.SpecificationsBeanX.SpecificationsBean> specifications = datas.getSpecifications();

        GuigeAdapter endSeclectAdapter = new GuigeAdapter(context, specifications) {
            @Override
            public void setOnFristClick(int position, int secPostion) {
                setOnSize(datas.getSpecifications().get(position).getAttrname(), datas.getSpecifications().get(position).getAttrvalue().get(secPostion).getInfoName());
            }
        };
        rv_gg.setAdapter(endSeclectAdapter);


        ll_shoucang.setOnClickListener(v -> showPromptDialog("功能开发中..."));
        ll_shopcart.setOnClickListener(v -> {

        });
    }


    public void show() {
        if (dialog != null) {
            dialog.show();
        }
    }

    public void dismiss() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
            dialog.cancel();
            dialog = null;
        }
    }

    private void showPromptDialog(String s) {
        new AlertDialog.Builder(context)
                .setMessage(s)
                .setPositiveButton("确定", null).show();
    }

    public abstract void setOnSize(String ggtype, String typename);
}
