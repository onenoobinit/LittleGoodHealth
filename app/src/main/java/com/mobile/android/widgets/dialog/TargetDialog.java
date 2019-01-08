package com.mobile.android.widgets.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.mobile.android.R;
import com.mobile.hyoukalibrary.base.BaseActivity;

/**
 * Created by wangqiang on 2019/1/8.
 */
public class TargetDialog {

    private final Context context;
    private View inflate;
    private Dialog dialog;

    public TargetDialog(Context context) {
        this.context = context;
        initView();

    }

    private void initView() {
        inflate = LayoutInflater.from(context).inflate(R.layout.dialog_target, null);
//        ListView listView = (ListView) inflate.findViewById(R.id.listView);
        /*listView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return datas.length;
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView textView = new TextView(context);
                textView.setText(datas[position]);
                textView.setTextColor(Color.parseColor("#333333"));
                textView.setTextSize(22);
                textView.setGravity(Gravity.CENTER);
                textView.setPadding(0, 10, 0, 10);
                return textView;
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dismiss();
                getInfo(datas[position]);
            }
        });*/
        dialog = new Dialog(context, R.style.Sweet_Alert_Dialog);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setContentView(inflate);
        Window dialogWindow = dialog.getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
        //设置dialog弹出时的动画效果，从屏幕底部向上弹出
        dialogWindow.setWindowAnimations(R.style.dialogStyle);
       /* dialogWindow.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        //设置窗口宽度为充满全屏
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        //设置窗口高度为包裹内容
        lp.height = 500;
        dialogWindow.setAttributes(lp);*/

        //设置弹框的高为屏幕的一半宽是屏幕的宽
        WindowManager windowManager = ((BaseActivity) context).getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        lp.width = (int) (display.getWidth()); //设置宽度
        lp.height = (int) (display.getHeight() * 0.5); //设置宽度
        dialog.getWindow().setAttributes(lp);
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
}
