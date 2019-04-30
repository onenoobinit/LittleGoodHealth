package com.youyijia.goodhealth.widgets.dialog;

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

import com.youyijia.goodhealth.R;
import com.youyijia.goodhealth.app.program.adapter.EndSeclectAdapter;
import com.youyijia.goodhealth.app.program.adapter.GridEndAdapter;
import com.youyijia.goodhealth.entity.ProductDetailInfo;
import com.youyijia.hyoukalibrary.base.BaseActivity;

import java.util.List;

/**
 * Created by wangqiang on 2019/1/8.
 */
public abstract class TargetDialog {

    private final Context context;
    private final List<ProductDetailInfo.PortDataBean.DestinationBean> datas;
    public static int portLog = 0;
    private final String mudi;
    private View inflate;
    private Dialog dialog;
    private RecyclerView rv_end;

    public TargetDialog(Context context, List<ProductDetailInfo.PortDataBean.DestinationBean> destinationList, String mudi) {
        this.context = context;
        this.datas = destinationList;
        this.mudi = mudi;
        initView();

    }

    private void initView() {
        inflate = LayoutInflater.from(context).inflate(R.layout.dialog_target, null);
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
        lp.height = (int) (display.getHeight() * 0.5); //设置宽度
        dialog.getWindow().setAttributes(lp);

        rv_end = inflate.findViewById(R.id.rv_end);
        LinearLayoutManager weightmanager = new LinearLayoutManager(context);
        weightmanager.setOrientation(LinearLayoutManager.VERTICAL);
        rv_end.setLayoutManager(weightmanager);

        if (portLog == 0) {
            for (int i = 0; i < datas.size(); i++) {
                for (int i1 = 0; i1 < datas.get(i).getPortList().size(); i1++) {
                    if (mudi.equals(datas.get(i).getPortList().get(i1).getPort())) {
                        EndSeclectAdapter.selectPostion = i;
                        GridEndAdapter.selectPostion = i1;
                    }
                }
            }
        }

        EndSeclectAdapter endSeclectAdapter = new EndSeclectAdapter(context, datas) {
            @Override
            public void setOnFristClick(int position, int secPostion) {
                setOnTargetClick(datas.get(position).getPortList().get(secPostion).getPort(), datas.get(position).getPortList().get(secPostion).getName());
            }
        };
        rv_end.setAdapter(endSeclectAdapter);
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

    public abstract void setOnTargetClick(String target, String hy);
}
