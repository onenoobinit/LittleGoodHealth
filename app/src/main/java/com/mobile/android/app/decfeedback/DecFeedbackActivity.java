package com.mobile.android.app.decfeedback;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.jaeger.library.StatusBarUtil;
import com.mobile.android.R;
import com.mobile.android.SupervisorApp;
import com.mobile.android.widgets.dialog.ListSelectDialog;
import com.mobile.android.widgets.dialog.LoadingDialog;
import com.mobile.android.widgets.pop.AddPopWindow;
import com.mobile.hyoukalibrary.base.BaseActivity;
import com.mobile.hyoukalibrary.utils.L;
import com.mobile.hyoukalibrary.utils.ToastUtil;
import com.zhy.autolayout.AutoFrameLayout;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 装修反馈页面
 * Created by wangqiang on 2018/5/21.
 */
public class DecFeedbackActivity extends BaseActivity implements View.OnClickListener {


    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_dec_submit)
    TextView tvDecSubmit;
    @BindView(R.id.tb_dec_feedback)
    Toolbar tbDecFeedback;
    @BindView(R.id.tv_dec_type)
    TextView tvDecType;
    @BindView(R.id.arl_dec_selector)
    AutoRelativeLayout arlDecSelector;
    @BindView(R.id.et_dec)
    EditText etDec;
    @BindView(R.id.iv_dec_search)
    ImageView ivDecSearch;
    @BindView(R.id.fl_content)
    AutoFrameLayout flContent;
    private ArrayList<String> sList;
    private AddPopWindow addPopWindow;
    private int dType;
    private View normalView;
    private EditText edit_feedback_content;
    private TextView tv_content_count;
    private View siginView;
    private AutoLinearLayout all_pay_type;
    private AutoLinearLayout all_tuoguo_type;
    private AutoLinearLayout all_tg_time;
    private ArrayList<String> mpayTypes;
    private ListSelectDialog mSelectDialog;
    private TextView tv_pay_type;
    private TextView tv_tg_type;
    private TextView tv_tg_time;
    private ArrayList<String> mtgType;
    private CheckBox ifpay;
    private CheckBox getdoor;
    private CheckBox getsign;
    private CheckBox gettg;
    private View mesureView;
    private AutoLinearLayout all_addView;
    private AutoLinearLayout all_addesigin_company;
    private int baoming_id;
    private LoadingDialog loadingDialog;
    private String text;
    private AutoLinearLayout all_if_pay;
    private AutoLinearLayout all_if_sigin;
    private AutoLinearLayout all_if_tuoguo;
    private int is_sm = 0;
    private int is_qy = 0;
    private int is_tg = 0;
    private EditText et_describe;
    private EditText et_sign_getmoney;
    private EditText et_sign_time;
    private EditText et_sign_company;
    private EditText et_tg_money;
    private EditText et_sign_getsign;
    private int pay_type;
    private int tg_type;
    private AutoLinearLayout all_mesure_whole;
    private AutoLinearLayout all_lf_time;
    private CheckBox changejj;
    private int is_only_jiaju = 0;
    private CheckBox centerkt;
    private CheckBox wholedz;
    private CheckBox yangtai;
    private CheckBox mumen;
    private CheckBox dinuan;
    private EditText et_lf_beizhu;
    private TextView tv_lf_time;
    private RadioGroup rg_area;
    private RadioButton over80;
    private RadioButton little80;
    private int area = 1;
    private RadioGroup rg_cbstyle;
    private RadioButton wholebao;
    private RadioButton halfbao;
    private int cbtype = 1;
    private RadioGroup rg_dgtime;
    private RadioButton threein;
    private RadioButton threeout;
    private RadioButton notsure;
    private int dgtime = 1;
    private RadioGroup rg_wx;
    private RadioButton wxyes;
    private RadioButton wxno;
    private int wx = 0;
    private RadioGroup rg_company;
    private RadioButton hadsend;
    private RadioButton nosend;
    private int cp_service = 1;
    private RadioGroup rg_zxstyle;
    private RadioButton wholez;
    private RadioButton partz;
    private int z_type = 1;
    private String json;
    List<Map<String, Object>> jsondatas = new ArrayList<>();
    private ArrayList<Integer> integers = new ArrayList<>();
    private ArrayList<String> arr = new ArrayList<>();
    private String is_need_jiaju;
    private ArrayList<String> selectList = new ArrayList<>();
    private int checkedRadioButtonId;
    private int checkedRadioButtonId2;
    private int checkedRadioButtonId3;
    private int checkedRadioButtonId5;
    private int checkedRadioButtonId4;
    private int checkedRadioButtonId1;
    private AutoLinearLayout all_sign_time;
    private TextView tv_sign_time;


    @Override
    protected void initViews(Bundle savedInstanceState) {
        StatusBarUtil.setColor(this, Color.parseColor("#ffffff"), 0);
        ButterKnife.bind(this);
        sList = new ArrayList<>();
        sList.add("正常反馈");
        sList.add("正常陪签");
        sList.add("陪签改约");
        sList.add("正常量房");
        sList.add("量房改约");
        sList.add("取消量房");
        tbDecFeedback.setNavigationOnClickListener(view -> {
            finish();
        });
        addPopWindow = new AddPopWindow(DecFeedbackActivity.this, 400);
        addPopWindow.setOnPopupItemClickListener(new AddPopWindow.PopupItemClickListener() {
            @Override
            public void popupItemClick(String sid) {
                flContent.removeAllViews();
                tvDecType.setText(sid);
                if ("正常反馈".equals(sid)) {
                    dType = 4;
                } else if ("正常量房".equals(sid)) {
                    dType = 1;
                } else if ("正常陪签".equals(sid)) {
                    dType = 6;
                } else if ("陪签改约".equals(sid)) {
                    dType = 5;
                } else if ("量房改约".equals(sid)) {
                    dType = 3;
                } else if ("取消量房".equals(sid)) {
                    dType = 2;
                } else {
                    dType = 0;
                }
            }
        });
    }


    //取消量房
    private void initCancelView() {
        flContent.removeAllViews();
        normalView = LayoutInflater.from(this).inflate(R.layout.dec_view_feedback, null);
        edit_feedback_content = normalView.findViewById(R.id.edit_feedback_content);
        edit_feedback_content.setHint("请输入取消量房原因！");
        tv_content_count = normalView.findViewById(R.id.tv_content_count);
        flContent.addView(normalView);
        initListener();
    }

    //量房改约
    private void initDateView() {
        flContent.removeAllViews();
        normalView = LayoutInflater.from(this).inflate(R.layout.dec_view_feedback, null);
        edit_feedback_content = normalView.findViewById(R.id.edit_feedback_content);
        edit_feedback_content.setHint("请输入量房改约内容！");
        tv_content_count = normalView.findViewById(R.id.tv_content_count);
        flContent.addView(normalView);
        initListener();
    }

    //正常量房
    private void initMesureView() {
        flContent.removeAllViews();
        mesureView = LayoutInflater.from(this).inflate(R.layout.dec_view_mesure, null);
        all_addView = mesureView.findViewById(R.id.all_addView);
        all_mesure_whole = mesureView.findViewById(R.id.all_mesure_whole);
        all_lf_time = mesureView.findViewById(R.id.all_lf_time);
        tv_lf_time = mesureView.findViewById(R.id.tv_lf_time);
        changejj = mesureView.findViewById(R.id.changejj);
        changejj.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                is_only_jiaju = 1;
                all_mesure_whole.setVisibility(View.GONE);
                all_lf_time.setVisibility(View.GONE);
            } else {
                is_only_jiaju = 0;
                all_lf_time.setVisibility(View.VISIBLE);
                all_mesure_whole.setVisibility(View.VISIBLE);
            }
        });
        all_lf_time.setOnClickListener(this);
        centerkt = mesureView.findViewById(R.id.centerkt);
        wholedz = mesureView.findViewById(R.id.wholedz);
        yangtai = mesureView.findViewById(R.id.yangtai);
        mumen = mesureView.findViewById(R.id.mumen);
        dinuan = mesureView.findViewById(R.id.dinuan);
        et_lf_beizhu = mesureView.findViewById(R.id.et_lf_beizhu);
        rg_area = mesureView.findViewById(R.id.rg_area);
        over80 = mesureView.findViewById(R.id.over80);
        little80 = mesureView.findViewById(R.id.little80);
        rg_cbstyle = mesureView.findViewById(R.id.rg_cbstyle);
        wholebao = mesureView.findViewById(R.id.wholebao);
        halfbao = mesureView.findViewById(R.id.halfbao);
        rg_dgtime = mesureView.findViewById(R.id.rg_dgtime);
        threein = mesureView.findViewById(R.id.threein);
        threeout = mesureView.findViewById(R.id.threeout);
        notsure = mesureView.findViewById(R.id.notsure);
        rg_wx = mesureView.findViewById(R.id.rg_wx);
        wxyes = mesureView.findViewById(R.id.wxyes);
        wxno = mesureView.findViewById(R.id.wxno);
        rg_company = mesureView.findViewById(R.id.rg_company);
        hadsend = mesureView.findViewById(R.id.hadsend);
        nosend = mesureView.findViewById(R.id.nosend);
        rg_zxstyle = mesureView.findViewById(R.id.rg_zxstyle);
        wholez = mesureView.findViewById(R.id.wholez);
        partz = mesureView.findViewById(R.id.partz);
        all_addesigin_company = mesureView.findViewById(R.id.all_addesigin_company);
        addViewItem(null);
        flContent.addView(mesureView);
        all_addesigin_company.setOnClickListener(this);
        integers.clear();
        centerkt.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                integers.add(1);
            } else {
                integers.remove((Integer) 1);
            }
        });
        wholedz.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                integers.add(2);
            } else {
                integers.remove((Integer) 2);
            }
        });
        yangtai.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                integers.add(4);
            } else {
                integers.remove((Integer) 4);
            }
        });
        mumen.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                integers.add(3);
            } else {
                integers.remove((Integer) 3);
            }
        });
        dinuan.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                integers.add(5);
            } else {
                integers.remove((Integer) 5);
            }
        });
    }


    //陪签改签
    private void initChangeView() {
        flContent.removeAllViews();
        siginView = LayoutInflater.from(this).inflate(R.layout.dec_view_sigin, null);
        all_pay_type = siginView.findViewById(R.id.all_pay_type);
        all_tuoguo_type = siginView.findViewById(R.id.all_tuoguo_type);
        all_tg_time = siginView.findViewById(R.id.all_tg_time);
        tv_pay_type = siginView.findViewById(R.id.tv_pay_type);
        tv_tg_type = siginView.findViewById(R.id.tv_tg_type);
        tv_tg_time = siginView.findViewById(R.id.tv_tg_time);
        ifpay = siginView.findViewById(R.id.ifpay);
        getdoor = siginView.findViewById(R.id.getdoor);
        getsign = siginView.findViewById(R.id.getsign);
        gettg = siginView.findViewById(R.id.gettg);
        all_if_pay = siginView.findViewById(R.id.all_if_pay);
        all_if_sigin = siginView.findViewById(R.id.all_if_sigin);
        all_if_tuoguo = siginView.findViewById(R.id.all_if_tuoguo);
        et_describe = siginView.findViewById(R.id.et_describe);
        et_sign_getmoney = siginView.findViewById(R.id.et_sign_getmoney);
        et_sign_company = siginView.findViewById(R.id.et_sign_company);
        et_tg_money = siginView.findViewById(R.id.et_tg_money);
        et_sign_getsign = siginView.findViewById(R.id.et_sign_getsign);
        all_sign_time = siginView.findViewById(R.id.all_sign_time);
        tv_sign_time = siginView.findViewById(R.id.tv_sign_time);
        flContent.addView(siginView);
        all_pay_type.setOnClickListener(this);
        all_tuoguo_type.setOnClickListener(this);
        all_tg_time.setOnClickListener(this);
        all_sign_time.setOnClickListener(this);
        ifpay.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                all_if_pay.setVisibility(View.VISIBLE);
            } else {
                all_if_pay.setVisibility(View.GONE);
            }
        });

        getdoor.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                is_sm = 1;
            } else {
                is_sm = 0;
            }
        });

        getsign.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                all_if_sigin.setVisibility(View.VISIBLE);
                is_qy = 1;
            } else {
                is_qy = 0;
                all_if_sigin.setVisibility(View.GONE);
            }
        });

        gettg.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                all_if_tuoguo.setVisibility(View.VISIBLE);
                is_tg = 1;
            } else {
                all_if_tuoguo.setVisibility(View.GONE);
                is_tg = 0;
            }
        });
    }

    //正常陪签
    private void initSiginView() {
        flContent.removeAllViews();
        siginView = LayoutInflater.from(this).inflate(R.layout.dec_view_sigin, null);
        all_pay_type = siginView.findViewById(R.id.all_pay_type);
        all_tuoguo_type = siginView.findViewById(R.id.all_tuoguo_type);
        all_tg_time = siginView.findViewById(R.id.all_tg_time);
        tv_pay_type = siginView.findViewById(R.id.tv_pay_type);
        tv_tg_type = siginView.findViewById(R.id.tv_tg_type);
        tv_tg_time = siginView.findViewById(R.id.tv_tg_time);
        ifpay = siginView.findViewById(R.id.ifpay);
        getdoor = siginView.findViewById(R.id.getdoor);
        getsign = siginView.findViewById(R.id.getsign);
        gettg = siginView.findViewById(R.id.gettg);
        all_if_pay = siginView.findViewById(R.id.all_if_pay);
        all_if_sigin = siginView.findViewById(R.id.all_if_sigin);
        all_if_tuoguo = siginView.findViewById(R.id.all_if_tuoguo);
        et_describe = siginView.findViewById(R.id.et_describe);
        et_sign_getmoney = siginView.findViewById(R.id.et_sign_getmoney);
        et_sign_company = siginView.findViewById(R.id.et_sign_company);
        et_tg_money = siginView.findViewById(R.id.et_tg_money);
        et_sign_getsign = siginView.findViewById(R.id.et_sign_getsign);
        all_sign_time = siginView.findViewById(R.id.all_sign_time);
        tv_sign_time = siginView.findViewById(R.id.tv_sign_time);
        flContent.addView(siginView);
        all_pay_type.setOnClickListener(this);
        all_tuoguo_type.setOnClickListener(this);
        all_tg_time.setOnClickListener(this);
        all_sign_time.setOnClickListener(this);
        ifpay.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                all_if_pay.setVisibility(View.VISIBLE);
            } else {
                all_if_pay.setVisibility(View.GONE);
            }
        });

        getdoor.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                is_sm = 1;
            } else {
                is_sm = 0;
            }
        });

        getsign.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                all_if_sigin.setVisibility(View.VISIBLE);
                is_qy = 1;
            } else {
                is_qy = 0;
                all_if_sigin.setVisibility(View.GONE);
            }
        });

        gettg.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                all_if_tuoguo.setVisibility(View.VISIBLE);
                is_tg = 1;
            } else {
                all_if_tuoguo.setVisibility(View.GONE);
                is_tg = 0;
            }
        });
    }

    //正常反馈
    private void initNormalView() {
        flContent.removeAllViews();
        normalView = LayoutInflater.from(this).inflate(R.layout.dec_view_feedback, null);
        edit_feedback_content = normalView.findViewById(R.id.edit_feedback_content);
        edit_feedback_content.setHint("请输入您的反馈内容！");
        tv_content_count = normalView.findViewById(R.id.tv_content_count);
        flContent.addView(normalView);
        initListener();
    }


    private void initListener() {
        edit_feedback_content.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                text = edit_feedback_content.getText().toString().trim();
                tv_content_count.setText(text.trim().length() + "");
            }
        });
    }

    //添加ViewItem
    private void addViewItem(View view) {
        if (all_addView.getChildCount() == 0) {//如果一个都没有，就添加一个
            View hotelEvaluateView = View.inflate(this, R.layout.item_addview, null);
            all_addView.addView(hotelEvaluateView);
            sortHotelViewItem();
        } else if (((String) view.getTag()).equals("add")) {//如果有一个以上的Item,点击为添加的Item则添加
            View hotelEvaluateView = View.inflate(this, R.layout.item_addview, null);
            all_addView.addView(hotelEvaluateView);
            sortHotelViewItem();
        }
    }


    /**
     * Item排序
     */
    private void sortHotelViewItem() {
        //获取LinearLayout里面所有的view
        for (int i = 0; i < all_addView.getChildCount(); i++) {
            final View childAt = all_addView.getChildAt(i);
            final TextView btn_remove = childAt.findViewById(R.id.tv_item_delete);
            if (i == 0) {
                btn_remove.setVisibility(View.GONE);
            } else {
                btn_remove.setVisibility(View.VISIBLE);
                btn_remove.setTag("remove");//设置删除标记
                btn_remove.setOnClickListener(view -> {
                    AlertDialog.Builder builder = new AlertDialog.Builder(DecFeedbackActivity.this);
                    builder.setMessage("是否要删除该设计公司？");
                    builder.setPositiveButton("确定", (dialog, which) -> {
                        all_addView.removeView(childAt);
                    });
                    //    设置一个NegativeButton
                    builder.setNegativeButton("取消", (dialog, which) -> {
                    });
                    builder.show();
                });
            }
        }

    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_dec_feedback;
    }

    @Override
    public void initToolBar() {

    }

    @OnClick({R.id.iv_dec_search, R.id.arl_dec_selector, R.id.tv_dec_submit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_dec_search:
                if (TextUtils.isEmpty(etDec.getText().toString().trim())) {
                    ToastUtil.show(DecFeedbackActivity.this, "请输入业主Id");
                } else {
                    baoming_id = Integer.parseInt(etDec.getText().toString().trim());
                    if (dType == 0) {
                        ToastUtil.show(DecFeedbackActivity.this, "请选择反馈类型！");
                    } else if (dType == 4) {
                        initNormalView();
                    } else if (dType == 1) {
                        initMesureView();
                    } else if (dType == 6) {
                        initSiginView();
                    } else if (dType == 5) {
                        initChangeView();
                    } else if (dType == 3) {
                        initDateView();
                    } else if (dType == 2) {
                        initCancelView();
                    }
                }
                break;
            case R.id.arl_dec_selector://反馈类型
//                addPopWindow.showPopupWindow(arlDecSelector, sList);
                break;

            case R.id.tv_dec_submit://提交
                if (flContent.getChildCount() == 0) {
                    ToastUtil.show(DecFeedbackActivity.this, "请您先选择需要提交的内容");
                    return;
                }

                if (dType == 4 || dType == 3 || dType == 2) {
                    if (TextUtils.isEmpty(edit_feedback_content.getText().toString().trim())) {
                        ToastUtil.show(DecFeedbackActivity.this, "请输入反馈内容!");
                        return;
                    } else {
                        if (loadingDialog == null) {
                            loadingDialog = new LoadingDialog(DecFeedbackActivity.this);
                        }
                        loadingDialog.show();
                        getnormalBack();
                    }
                }
                if (dType == 5 || dType == 6) {
                    if (TextUtils.isEmpty(et_describe.getText().toString().trim())) {
                        ToastUtil.show(DecFeedbackActivity.this, "请填写备注!");
                        return;
                    } else if (TextUtils.isEmpty(et_sign_company.getText().toString().trim())) {
                        ToastUtil.show(DecFeedbackActivity.this, "请输入装修公司!");
                        return;
                    } else if (TextUtils.isEmpty(et_sign_getmoney.getText().toString().trim())) {
                        ToastUtil.show(DecFeedbackActivity.this, "请输入实收金额数!");
                        return;
                    } else if ("请选择".equals(tv_pay_type.getText().toString().trim())) {
                        ToastUtil.show(DecFeedbackActivity.this, "请选择支付方式!");
                        return;
                    } else if (TextUtils.isEmpty(et_sign_getsign.getText().toString().trim()) && is_qy == 1) {
                        ToastUtil.show(DecFeedbackActivity.this, "您的合同金额未填写!");
                        return;
                    } else if ("请选择".equals(tv_tg_type.getText().toString().trim()) && is_tg == 1) {
                        ToastUtil.show(DecFeedbackActivity.this, "您的托管方式未选择!");
                        return;
                    } else if (TextUtils.isEmpty(et_tg_money.getText().toString().trim()) && is_tg == 1) {
                        ToastUtil.show(DecFeedbackActivity.this, "您的托管金额未填写!");
                        return;
                    } else if ("请选择".equals(tv_tg_time.getText().toString().trim()) && is_tg == 1) {
                        ToastUtil.show(DecFeedbackActivity.this, "您的托管日期未选择!");
                        return;
                    } else if ("请选择".equals(tv_sign_time.getText().toString().trim())) {
                        new android.support.v7.app.AlertDialog.Builder(this)
                                .setMessage("您的陪签时间未选择，确认提交？")
                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        if (loadingDialog == null) {
                                            loadingDialog = new LoadingDialog(DecFeedbackActivity.this);
                                        }
                                        loadingDialog.show();
                                        getsiginData();
                                    }
                                }).show();
                    } else {
                        if (loadingDialog == null) {
                            loadingDialog = new LoadingDialog(DecFeedbackActivity.this);
                        }
                        loadingDialog.show();
                        getsiginData();
                    }
                }
                if (dType == 1) {
                    checkedRadioButtonId = rg_area.getCheckedRadioButtonId();
                    checkedRadioButtonId1 = rg_cbstyle.getCheckedRadioButtonId();
                    checkedRadioButtonId2 = rg_dgtime.getCheckedRadioButtonId();
                    checkedRadioButtonId3 = rg_wx.getCheckedRadioButtonId();
                    checkedRadioButtonId5 = rg_company.getCheckedRadioButtonId();
                    checkedRadioButtonId4 = rg_zxstyle.getCheckedRadioButtonId();
                    if (is_only_jiaju == 1) {//仅修改家居需求
                        if (loadingDialog == null) {
                            loadingDialog = new LoadingDialog(DecFeedbackActivity.this);
                        }
                        loadingDialog.show();
                        Collections.sort(integers);
                        arr.clear();
                        for (int i = 0; i < integers.size(); i++) {
                            arr.add(integers.get(i) + "");
                        }
                        getonlyjiaju();
                    } else if (is_only_jiaju == 0) {
                        jsondatas.clear();
                        filterItem(all_addView.getChildCount());
                        json = gson.toJson(jsondatas);
                        if (TextUtils.isEmpty(et_lf_beizhu.getText().toString().trim())) {
                            ToastUtil.show(DecFeedbackActivity.this, "请你输入备注内容!");
                            return;
                        } else if ("请选择实际量房时间".equals(tv_lf_time.getText().toString().trim())) {
                            ToastUtil.show(DecFeedbackActivity.this, "请选择实际量房时间");
                            return;
                        } else if (checkedRadioButtonId != over80.getId() && checkedRadioButtonId != little80.getId()) {
                            ToastUtil.show(DecFeedbackActivity.this, "请选择面积大小！");
                        } else if (checkedRadioButtonId1 != wholebao.getId() && checkedRadioButtonId1 != halfbao.getId()) {
                            ToastUtil.show(DecFeedbackActivity.this, "请选择承包方式！");
                        } else if (checkedRadioButtonId2 != threein.getId() && checkedRadioButtonId2 != threeout.getId() && checkedRadioButtonId2 != notsure.getId()) {
                            ToastUtil.show(DecFeedbackActivity.this, "请选择动工时间！");
                        } else if (checkedRadioButtonId3 != wxyes.getId() && checkedRadioButtonId3 != wxno.getId()) {
                            ToastUtil.show(DecFeedbackActivity.this, "请选择微信添加！");
                        } else if (checkedRadioButtonId5 != hadsend.getId() && checkedRadioButtonId5 != nosend.getId()) {
                            ToastUtil.show(DecFeedbackActivity.this, "请选择公司服务！");
                        } else if (checkedRadioButtonId4 != wholez.getId() && checkedRadioButtonId4 != partz.getId()) {
                            ToastUtil.show(DecFeedbackActivity.this, "请选择装修类型！");
                        } else if (filterItem(all_addView.getChildCount())) {
                            initradioListiner();
                            getmesuerdata();
                        }
                    }
                }
                break;
            case R.id.all_pay_type://正常陪签支付方式
                if (mpayTypes == null) {
                    mpayTypes = new ArrayList<>();
                }
                mpayTypes.clear();
                mpayTypes.add("支付宝");
                mpayTypes.add("POS");
                mpayTypes.add("银行卡");
                mpayTypes.add("现金");
                showSelcetorDialog(tv_pay_type, mpayTypes);
                break;
            case R.id.all_tuoguo_type://正常陪签托管方式
                if (mtgType == null) {
                    mtgType = new ArrayList<>();
                }
                mtgType.clear();
                mtgType.add("平台分配");
                mtgType.add("商家自有");
                mtgType.add("开放式托管");
                showSelcetorDialog(tv_tg_type, mtgType);
                break;
            case R.id.all_tg_time://正常陪签托管日期
                showTimeDialog(tv_tg_time);
                break;
            case R.id.all_addesigin_company:
                all_addesigin_company.setTag("add");
                if (all_addView.getChildCount() > 4 && ((String) view.getTag()).equals("add")) {
                    ToastUtil.show(DecFeedbackActivity.this, "不好意思，数量已达上限！");
                } else {
                    addViewItem(view);
                }
                break;
            case R.id.all_lf_time:
                showTimeDialog(tv_lf_time);
                break;
            case R.id.all_sign_time:
                showTimeDialog(tv_sign_time);
                break;
        }
    }

    private void initradioListiner() {

        if (checkedRadioButtonId == over80.getId()) {
            area = 1;
        } else if (checkedRadioButtonId == little80.getId()) {
            area = 2;
        }

        if (checkedRadioButtonId1 == wholebao.getId()) {
            cbtype = 1;
        } else if (checkedRadioButtonId1 == halfbao.getId()) {
            cbtype = 2;
        }
        if (checkedRadioButtonId2 == threein.getId()) {
            dgtime = 1;
        } else if (checkedRadioButtonId2 == threeout.getId()) {
            dgtime = 2;
        } else if (checkedRadioButtonId2 == notsure.getId()) {
            dgtime = 3;
        }

        if (checkedRadioButtonId3 == wxyes.getId()) {
            wx = 1;
        } else if (checkedRadioButtonId3 == wxno.getId()) {
            wx = 0;
        }

        if (checkedRadioButtonId5 == hadsend.getId()) {
            cp_service = 1;
        } else if (checkedRadioButtonId5 == nosend.getId()) {
            cp_service = 2;
        }
        if (checkedRadioButtonId4 == wholez.getId()) {
            z_type = 1;
        } else if (checkedRadioButtonId4 == partz.getId()) {
            z_type = 2;
        }

        Collections.sort(integers);
        arr.clear();
        for (int i = 0; i < integers.size(); i++) {
            arr.add(integers.get(i) + "");
        }
    }

    // 获取添加的设计师公司信息
    private boolean filterItem(int childCount) {
        L.i("设计师item", childCount + "");
        for (int i = 0; i < childCount; i++) {
            AtomicInteger designer_service = new AtomicInteger(1);
            AtomicInteger is_chidao = new AtomicInteger(1);
            Map<String, Object> map = new HashMap<>();
            final View childAt = all_addView.getChildAt(i);
            final EditText et_fb_company = childAt.findViewById(R.id.et_fb_company);
            final RadioGroup attitude = childAt.findViewById(R.id.attitude);
            final RadioButton nice = childAt.findViewById(R.id.nice);
            final RadioButton just_so_so = childAt.findViewById(R.id.just_so_so);
            final RadioButton bad = childAt.findViewById(R.id.bad);
            final EditText et_bad_content = childAt.findViewById(R.id.et_bad_content);
            final RadioGroup rg_late = childAt.findViewById(R.id.rg_late);
            final RadioButton ontime = childAt.findViewById(R.id.ontime);
            final RadioButton comelate = childAt.findViewById(R.id.comelate);
            final RadioButton notcome = childAt.findViewById(R.id.notcome);
            final EditText et_late_content = childAt.findViewById(R.id.et_late_content);
            int checkedRadioButtonId = attitude.getCheckedRadioButtonId();
            int checkedRadioButtonId1 = rg_late.getCheckedRadioButtonId();
            if (TextUtils.isEmpty(et_fb_company.getText().toString().trim())) {
                ToastUtil.show(DecFeedbackActivity.this, "请输入设计师公司!");
                return false;
            } else if (checkedRadioButtonId != nice.getId() && checkedRadioButtonId != just_so_so.getId() && checkedRadioButtonId != bad.getId()) {
                ToastUtil.show(DecFeedbackActivity.this, "请选择设计师态度！");
                return false;
            } else if (checkedRadioButtonId1 != ontime.getId() && checkedRadioButtonId1 != comelate.getId() && checkedRadioButtonId1 != notcome.getId()) {
                ToastUtil.show(DecFeedbackActivity.this, "请选择迟到情况！");
                return false;
            }
            if (checkedRadioButtonId == nice.getId()) {
                designer_service.set(1);
            } else if (checkedRadioButtonId == just_so_so.getId()) {
                designer_service.set(0);
            } else if (checkedRadioButtonId == bad.getId()) {
                designer_service.set(2);
                if (TextUtils.isEmpty(et_bad_content.getText().toString().trim())) {
                    ToastUtil.show(DecFeedbackActivity.this, "请输入设计师态度详情!");
                    return false;
                }
            }
            if (checkedRadioButtonId1 == ontime.getId()) {
                is_chidao.set(1);
            } else if (checkedRadioButtonId1 == comelate.getId()) {
                is_chidao.set(2);
                if (TextUtils.isEmpty(et_late_content.getText().toString().trim())) {
                    ToastUtil.show(DecFeedbackActivity.this, "请输入迟到时间!");
                    return false;
                }
            } else if (checkedRadioButtonId1 == notcome.getId()) {
                is_chidao.set(3);
            }

            map.put("designer_company", et_fb_company.getText().toString().trim());
            map.put("is_chidao", is_chidao);
            map.put("designer_service", designer_service);
            map.put("designer_remark", et_bad_content.getText().toString().trim());
            map.put("chi_num", et_late_content.getText().toString().trim());
            L.i("设计师item", map + "");
            jsondatas.add(map);
        }
        return true;
    }


    /**
     * 量房接口
     */
    private void getmesuerdata() {
        if (loadingDialog == null) {
            loadingDialog = new LoadingDialog(DecFeedbackActivity.this);
        }
        loadingDialog.show();

        is_need_jiaju = ArrayList2String(arr);
        params.clear();
        params.put("token", SupervisorApp.getUser().getToken());
//        params.put("uid", SupervisorApp.getUser().getUid());
        params.put("baoming_id", baoming_id);
        params.put("type", dType);
        params.put("text", et_lf_beizhu.getText().toString().trim());
        params.put("is_only_jiaju", is_only_jiaju);
        params.put("lf_time", tv_lf_time.getText().toString().trim());
        params.put("is_need_jiaju", is_need_jiaju);
        params.put("area", area);
        params.put("contract_type", cbtype);
        params.put("catch_time", dgtime);
        params.put("is_add_weixin", wx);
        params.put("company_service", cp_service);
        params.put("zx_type", z_type);
        params.put("add_designer", json);
        L.i("量房参数", params + "");
        /*RetrofitManager.getInstance().create(CommonService.class)
                .getsigin(params)
                .throttleFirst(1, TimeUnit.SECONDS)
                .retryWhen(new RetryWhenNetworkException(2, 500, 500))
                .compose(RxSchedulers.<BaseEntity<String>>io_main())
                .subscribe(new BaseObserver<String>() {

                    @Override
                    protected void onHandleSuccess(String string) {
                        loadingDialog.dismiss();
                        if ("操作成功！".equals(string)) {
                            showPromptDialog();
                        }
                    }

                    @Override
                    protected void onFinally() {
                        super.onFinally();
                        loadingDialog.dismiss();
                    }
                });*/
    }


    // ArrayList类型转成String类型
    public String ArrayList2String(ArrayList<String> arrayList) {
        String result = "";
        if (arrayList != null && arrayList.size() > 0) {
            for (String item : arrayList) {
                // 把列表中的每条数据用逗号分割开来，然后拼接成字符串
                result += item + ",";
            }
            // 去掉最后一个逗号
            result = result.substring(0, result.length() - 1);
        }
        return result;
    }

    /**
     * 仅修改家居需求
     */
    private void getonlyjiaju() {
        is_need_jiaju = ArrayList2String(arr);
        params.clear();
        params.put("token", SupervisorApp.getUser().getToken());
//        params.put("uid", SupervisorApp.getUser().getUid());
        params.put("baoming_id", baoming_id);
        params.put("type", dType);
        params.put("is_only_jiaju", is_only_jiaju);
        params.put("is_need_jiaju", is_need_jiaju);
        L.i("仅家具需求参数", params + "");
       /* RetrofitManager.getInstance().create(CommonService.class)
                .getsigin(params)
                .throttleFirst(1, TimeUnit.SECONDS)
                .retryWhen(new RetryWhenNetworkException(2, 500, 500))
                .compose(RxSchedulers.<BaseEntity<String>>io_main())
                .subscribe(new BaseObserver<String>() {

                    @Override
                    protected void onHandleSuccess(String string) {
                        loadingDialog.dismiss();
                        if ("操作成功！".equals(string)) {
                            showPromptDialog();
                        }
                    }

                    @Override
                    protected void onFinally() {
                        super.onFinally();
                        loadingDialog.dismiss();
                    }
                });*/
    }

    /**
     * 显示时间选择框
     *
     * @param time
     */
    private void showTimeDialog(TextView time) {
        Calendar selectedDate = Calendar.getInstance();
        Calendar startDate = Calendar.getInstance();
        startDate.set(2013, 1, 1);//设置起始年份
        Calendar endDate = Calendar.getInstance();
        endDate.set(2030, 1, 1);//设置结束年份
        TimePickerView pvTime = new TimePickerBuilder(DecFeedbackActivity.this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {

                time.setText(getTime(date));
            }
        })
                .setRangDate(startDate, endDate)
                .setTitleText("请选择日期")
                .setDate(selectedDate)
                .setTitleColor(ContextCompat.getColor(DecFeedbackActivity.this, R.color.c_85b175))
                .setCancelColor(ContextCompat.getColor(DecFeedbackActivity.this, R.color.c_85b175))
                .setSubmitColor(ContextCompat.getColor(DecFeedbackActivity.this, R.color.c_85b175))
                //默认设置为年月日时分秒
                .setLabel("年", "月", "日", "时", "分", "秒")
                // 默认全部显示
                .setType(new boolean[]{true, true, true, true, true, false})
                .build();
        pvTime.show();
    }

    private String getTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        return format.format(date);
    }

    /**
     * 正常陪签，陪签改约
     */
    private void getsiginData() {
        params.clear();
        params.put("token", SupervisorApp.getUser().getToken());
//        params.put("uid", SupervisorApp.getUser().getUid());
        params.put("baoming_id", baoming_id);
        params.put("type", dType);//3.正常陪签4.陪签改约
        params.put("text", et_describe.getText().toString().trim());
        if ("请选择".equals(tv_sign_time.getText().toString().trim())) {
            params.put("date", "");
        }else {
            params.put("date", tv_sign_time.getText().toString().trim());
        }
        params.put("firm_name", et_sign_company.getText().toString().trim());
        params.put("jl_money", et_sign_getmoney.getText().toString().trim());
        if ("支付宝".equals(tv_pay_type.getText().toString().trim())) {
            pay_type = 1;
        } else if ("POS".equals(tv_pay_type.getText().toString().trim())) {
            pay_type = 2;
        } else if ("银行卡".equals(tv_pay_type.getText().toString().trim())) {
            pay_type = 3;
        } else if ("现金".equals(tv_pay_type.getText().toString().trim())) {
            pay_type = 4;
        }
        params.put("jl_pay_type", pay_type);
        params.put("is_sm", is_sm);
        params.put("is_qy", is_qy);
        params.put("is_tg", is_tg);
        if (!TextUtils.isEmpty(et_sign_getsign.getText().toString().trim())) {
            params.put("ht_money", et_sign_getsign.getText().toString().trim());
        }
        if (!"请选择".equals(tv_tg_type.getText().toString().trim())) {
            if ("平台分配".equals(tv_tg_type.getText().toString().trim())) {
                tg_type = 1;
            } else if ("商家自有".equals(tv_tg_type.getText().toString().trim())) {
                tg_type = 2;
            } else if ("开放式托管".equals(tv_tg_type.getText().toString().trim())) {
                tg_type = 3;
            }
            params.put("new_tg_type", tg_type);
        }
        if (!TextUtils.isEmpty(et_tg_money.getText().toString().trim())) {
            params.put("tg_fee", et_tg_money.getText().toString().trim());
        }
        if (!"请选择".equals(tv_tg_time.getText().toString().trim())) {
            params.put("tg_time", tv_tg_time.getText().toString().trim());
        }
        L.i("设计师参数", params + "");
       /* RetrofitManager.getInstance().create(CommonService.class)
                .getsigin(params)
                .throttleFirst(1, TimeUnit.SECONDS)
                .retryWhen(new RetryWhenNetworkException(2, 500, 500))
                .compose(RxSchedulers.<BaseEntity<String>>io_main())
                .subscribe(new BaseObserver<String>() {

                    @Override
                    protected void onHandleSuccess(String string) {
                        loadingDialog.dismiss();
                        if ("操作成功！".equals(string)) {
                            showPromptDialog();
                        }
                    }

                    @Override
                    protected void onFinally() {
                        super.onFinally();
                        loadingDialog.dismiss();
                    }
                });*/
    }

    /**
     * 正常反馈，量房改约，取消量房
     */
    private void getnormalBack() {
        params.clear();
        params.put("token", SupervisorApp.getUser().getToken());
//        params.put("uid", SupervisorApp.getUser().getUid());
        params.put("baoming_id", baoming_id);
        params.put("type", dType);
        params.put("text", text);
        /*RetrofitManager.getInstance().create(CommonService.class)
                .getnormalBack(params)
                .throttleFirst(1, TimeUnit.SECONDS)
                .retryWhen(new RetryWhenNetworkException(2, 500, 500))
                .compose(RxSchedulers.<BaseEntity<String>>io_main())
                .subscribe(new BaseObserver<String>() {

                    @Override
                    protected void onHandleSuccess(String string) {
                        loadingDialog.dismiss();
                        if ("操作成功！".equals(string)) {
                            showPromptDialog();
                        }
                    }

                    @Override
                    protected void onFinally() {
                        super.onFinally();
                        loadingDialog.dismiss();
                    }
                });*/
    }

    private void showPromptDialog() {
        new android.support.v7.app.AlertDialog.Builder(this)
                .setMessage("您的操作成功！")
                .setPositiveButton("我知道了", null).show();
        flContent.removeAllViews();
        etDec.setText("");
        if (dType == 1) {
            is_only_jiaju = 0;
        }
    }

    //选择dialog
    private void showSelcetorDialog(TextView tv, ArrayList<String> datas) {
        mSelectDialog = new ListSelectDialog(this, datas) {
            @Override
            public void itemClick(int position) {
                tv.setText(datas.get(position));
            }
        };
        mSelectDialog.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (loadingDialog != null) {
            loadingDialog.dismiss();
        }
    }
}
