package com.mobile.android.app.submit;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobile.android.R;
import com.mobile.android.SupervisorApp;
import com.mobile.android.entity.SubmitInfo;
import com.mobile.android.entity.SubmitSuccessInfo;
import com.mobile.android.retrofit.RetrofitManager;
import com.mobile.android.retrofit.RetryWhenNetworkException;
import com.mobile.android.retrofit.RxSchedulers;
import com.mobile.android.retrofit.api.CommonService;
import com.mobile.android.widgets.dialog.MyDialog;
import com.mobile.android.widgets.dialog.SubmitShowDialog;
import com.mobile.android.widgets.dialog.SubmitSuccessDialog;
import com.mobile.android.widgets.dialog.SubmitTipsDialog;
import com.mobile.hyoukalibrary.base.BaseActivity;
import com.mobile.hyoukalibrary.base.BaseEntity;
import com.mobile.hyoukalibrary.base.BaseObserver;
import com.mobile.hyoukalibrary.utils.L;
import com.mobile.hyoukalibrary.utils.SPUtil;
import com.mobile.hyoukalibrary.utils.ToastUtil;
import com.zhy.autolayout.AutoLinearLayout;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by wangqiang on 2019/1/19.
 */
public class SubmitActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.logi_tool_bar)
    Toolbar logiToolBar;
    @BindView(R.id.iv_submit_jie)
    ImageView ivSubmitJie;
    @BindView(R.id.tv_base_hb)
    TextView tvBaseHb;
    @BindView(R.id.tv_base_cpmc)
    TextView tvBaseCpmc;
    @BindView(R.id.tv_base_sf)
    TextView tvBaseSf;
    @BindView(R.id.tv_base_md)
    TextView tvBaseMd;
    @BindView(R.id.tv_base_js)
    TextView tvBaseJs;
    @BindView(R.id.tv_base_zl)
    TextView tvBaseZl;
    @BindView(R.id.tv_base_tj)
    TextView tvBaseTj;
    @BindView(R.id.tv_base_sjbz)
    TextView tvBaseSjbz;
    @BindView(R.id.iv_coast)
    TextView ivCoast;
    @BindView(R.id.tv_coast_yfdj)
    TextView tvCoastYfdj;
    @BindView(R.id.tv_coast_fpbl)
    TextView tvCoastFpbl;
    @BindView(R.id.tv_coast_jfzl)
    TextView tvCoastJfzl;
    @BindView(R.id.tv_coast_zyf)
    TextView tvCoastZyf;
    @BindView(R.id.tv_coast_jfbz)
    TextView tvCoastJfbz;
    @BindView(R.id.iv_insureance_content)
    TextView ivInsureanceContent;
    @BindView(R.id.cb_hy)
    CheckBox cbHy;
    @BindView(R.id.tv_hy)
    TextView tvHy;
    @BindView(R.id.tv_coast_name)
    TextView tvCoastName;
    @BindView(R.id.tv_coast_code)
    TextView tvCoastCode;
    @BindView(R.id.cb_bzsj)
    CheckBox cbBzsj;
    @BindView(R.id.tv_contact_history)
    TextView tvContactHistory;
    @BindView(R.id.et_contact_name)
    EditText etContactName;
    @BindView(R.id.et_contact_phone)
    EditText etContactPhone;
    @BindView(R.id.et_contact_qq)
    EditText etContactQq;
    @BindView(R.id.et_contact_mail)
    EditText etContactMail;
    @BindView(R.id.et_contact_content)
    EditText etContactContent;
    @BindView(R.id.all_addView)
    AutoLinearLayout allAddView;
    @BindView(R.id.tv_tips_one)
    TextView tvTipsOne;
    @BindView(R.id.tv_tips_mobile)
    TextView tvTipsMobile;
    @BindView(R.id.tv_tips_jf)
    TextView tvTipsJf;
    @BindView(R.id.tv_tips_bx)
    TextView tvTipsBx;
    @BindView(R.id.tv_submit_money)
    TextView tvSubmitMoney;
    @BindView(R.id.tv_submit_sure)
    TextView tvSubmitSure;
    @BindView(R.id.tv_bzsj)
    TextView tvBzsj;

    @BindView(R.id.tv_trans_type)
    TextView tvTransType;
    @BindView(R.id.iv_hyx)
    ImageView ivHyx;
    @BindView(R.id.iv_bzsj)
    ImageView ivBzsj;

    @BindView(R.id.tv_bzsj_content)
    TextView tvBzsjContent;
    @BindView(R.id.rv_show_tips)
    RecyclerView rvShowTips;
    @BindView(R.id.rv_fj)
    RecyclerView rvFj;
    @BindView(R.id.rv_show)
    RecyclerView rvShow;
    private ExpertAskBannerOneAdapter mBannerOneAdapter;
    private String TOKEN;
    private String productNo;
    private String startPort;
    private String productDate;
    private String destination;
    private String goodNumber;
    private String goodWeight;
    private String goodVolume;
    private String bookingPosition;
    private String packageWay;
    private String quotedPriceId;
    private String priceCheckId;
    private MyDialog myDialog;
    private SubmitInfo submitInfo;
    private String proportion;
    private SubmitInfo.GoodsExtensionInsuranceBean.PlanListBean planList;
    private SubmitInfo.GoodsExtensionInsuranceBean.PlanListBean.Plan1Bean plan1;
    private SubmitInfo.GoodsExtensionInsuranceBean.PlanListBean.Plan2Bean plan2;
    private SubmitInfo.GoodsExtensionInsuranceBean.PlanListBean.Plan3Bean plan3;
    private String validHyx;
    private List<String> productTips;
    private List<SubmitInfo.OrderBillBean.ContactInfoBean> contactInfo;
    private String realTotal;
    private List<SubmitInfo.AttachServiceBean> attachService = new ArrayList<>();
    private boolean isFjChek = false;
    private String fJid;
    private boolean isHyxCheck = false;
    private boolean isShbzCheck = false;
    private int planselect = 2;
    private BigDecimal insureance1;
    private BigDecimal insureance2;
    private BigDecimal insureance3;
    private String validFj;
    private String validSj;
    private String sizeNoteType;
    List<Map<String, Object>> jsondatas = new ArrayList<>();
    List<String> fjList = new ArrayList<>();
    private String cCjson;
    private Map<String, Object> mapContact;
    private String contactJson;
    private String fjJson;
    private String productName;
    private String goodsExtensionInsurance;
    private String protectBusiness;
    private String bookingDate;
    private BigDecimal realTotalMoney;
    private BigDecimal fjNunmber;
    private BigDecimal bigDecimal;
    private String mChage = "0";
    private int lastItemPosition;
    private int firstItemPosition;
    private SubmitTipsDialog submitTipsDialog;
    private SubmitShowDialog submitShowDialog;
    private SubmitSuccessInfo submitSuccessInfo;
    private SubmitSuccessDialog submitSuccessDialog;
    protected Map<String, Object> mParams = new HashMap<String, Object>();
    private String airline;
    private SubmitInfo.TipsBean tips;
    private String shownumber;
    private Handler handler;
    private ShowAdapter showAdapter;

    @Override
    protected void initViews(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        TOKEN = SupervisorApp.getUser().getToken();

        productNo = getIntent().getStringExtra("productNo");
        startPort = getIntent().getStringExtra("startPort");
        productDate = getIntent().getStringExtra("productDate");
        destination = getIntent().getStringExtra("destination");
        goodNumber = getIntent().getStringExtra("goodNumber");
        goodWeight = getIntent().getStringExtra("goodWeight");
        goodVolume = getIntent().getStringExtra("goodVolume");
        bookingPosition = getIntent().getStringExtra("bookingPosition");
        packageWay = getIntent().getStringExtra("packageWay");
        quotedPriceId = getIntent().getStringExtra("quotedPriceId");
        priceCheckId = getIntent().getStringExtra("priceCheckId");
        proportion = getIntent().getStringExtra("proportion");
        realTotal = getIntent().getStringExtra("realTotal");
        airline = getIntent().getStringExtra("airline");

        realTotalMoney = new BigDecimal(realTotal);
        getSubmit();
        initCheckboxListener();
        //默认加载第一个
        addViewItem(null);
    }

    private void getSubmit() {
        if (myDialog == null) {
            myDialog = new MyDialog(this);
        }
        myDialog.showDialog();
        params.clear();
        params.put("act", "getSingles2019Data");
        params.put("productNo", productNo);
        params.put("quotedPriceId", quotedPriceId);
        params.put("startPort", startPort);
        params.put("productDate", productDate);
        params.put("destination", destination);
        params.put("goodNumber", goodNumber);
        params.put("goodWeight", goodWeight);
        params.put("goodVolume", goodVolume);
        params.put("bookingPosition", bookingPosition);
        params.put("packageWay", packageWay);
        params.put("priceCheckId", priceCheckId);

        RetrofitManager.getInstance().create(CommonService.class)
                .getSubmitData(TOKEN, params)
                .throttleFirst(1, TimeUnit.SECONDS)
                .retryWhen(new RetryWhenNetworkException(2, 500, 500))
                .compose(RxSchedulers.io_main())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onHandleSuccess(BaseEntity baseEntity) {

                        myDialog.dismissDialog();
                        if (!TextUtils.isEmpty(baseEntity.getErrMsg())) {
                            ToastUtil.show(SubmitActivity.this, baseEntity.getErrMsg());
                            return;
                        } else {
                            submitInfo = gson.fromJson(baseEntity.getSuccess(), SubmitInfo.class);
                            initData();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        if (myDialog != null) {
                            myDialog.dismissDialog();
                        }

                    }
                });
    }

    private void initData() {
        sizeNoteType = submitInfo.getOrderDetail().getSizeNoteType();
        productName = submitInfo.getOrderDetail().getProductName();
        //基础信息
        tvBaseHb.setText(productDate);
        tvBaseCpmc.setText(submitInfo.getOrderDetail().getProductName());
        tvBaseSf.setText(startPort);
        tvBaseMd.setText(destination);
        tvBaseJs.setText(goodNumber);
        tvBaseZl.setText(goodWeight);
        tvBaseTj.setText(goodVolume);
        tvBaseSjbz.setText(proportion);

        //费用信息
        tvCoastYfdj.setText(submitInfo.getOrderDetail().getUnitPrice());
        tvCoastFpbl.setText(submitInfo.getOrderDetail().getBubbleRatio().getTxt());
        tvCoastJfzl.setText(submitInfo.getOrderDetail().getChargeableWeight());
        if ("0".equals(submitInfo.getOrderBill().getTransferPriceType())) {
            tvTransType.setText("转运费");
        } else if ("1".equals(submitInfo.getOrderBill().getTransferPriceType())) {
            tvTransType.setText("转运费补差");
        }
        tvCoastZyf.setText(submitInfo.getOrderBill().getTransferPrice());
        tvCoastJfbz.setText("1:" + submitInfo.getOrderDetail().getProportion());

        //附加信息
        attachService = submitInfo.getAttachService();
        fjNunmber = new BigDecimal(attachService.get(0).getPrice());
        validFj = attachService.get(0).getValid();
        fJid = attachService.get(0).getId();
        String selected = attachService.get(0).getSelected();
        if ("0".equals(selected)) {
            isFjChek = false;
        } else if ("1".equals(selected)) {
            isFjChek = true;
        }
        LinearLayoutManager manager2 = new LinearLayoutManager(this);
        manager2.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvFj.setLayoutManager(manager2);
        FjAdapter fjAdapter = new FjAdapter(this, attachService) {
            @Override
            public void setOnCheckClik(boolean isCheck, String checkId) {
                BigDecimal chebox1 = new BigDecimal(tvSubmitMoney.getText().toString().trim());
                if (isCheck == false) {
                    isFjChek = false;
                    tvSubmitMoney.setText(chebox1.subtract(fjNunmber) + "");
                } else if (isCheck == true) {
                    isFjChek = true;
                    tvSubmitMoney.setText(chebox1.add(fjNunmber) + "");
                }
            }
        };
        rvFj.setAdapter(fjAdapter);


        //货延险
        validHyx = submitInfo.getGoodsExtensionInsurance().getValid();
        if ("1".equals(submitInfo.getGoodsExtensionInsurance().getValid())) {
            ivHyx.setVisibility(View.GONE);
            cbHy.setVisibility(View.VISIBLE);
        } else if ("0".equals(submitInfo.getGoodsExtensionInsurance().getValid())) {
            ivHyx.setVisibility(View.VISIBLE);
            cbHy.setVisibility(View.GONE);
        }
        tvCoastName.setText("公司名称：" + submitInfo.getGoodsExtensionInsurance().getUserInfo().getCompanyName());
        tvCoastCode.setText("信用代码：" + submitInfo.getGoodsExtensionInsurance().getUserInfo().getTaxId());
        insureance1 = new BigDecimal(submitInfo.getGoodsExtensionInsurance().getPlanList().getPlan1().getInsuranceFee());
        insureance2 = new BigDecimal(submitInfo.getGoodsExtensionInsurance().getPlanList().getPlan2().getInsuranceFee());
        insureance3 = new BigDecimal(submitInfo.getGoodsExtensionInsurance().getPlanList().getPlan3().getInsuranceFee());
        planList = submitInfo.getGoodsExtensionInsurance().getPlanList();
        plan1 = planList.getPlan1();
        plan2 = planList.getPlan2();
        plan3 = planList.getPlan3();
        initShows();

        //商家保障
        validSj = submitInfo.getProtectBusiness().getValid();
        String selected1 = submitInfo.getProtectBusiness().getSelected();
        if ("0".equals(selected1)) {
            isShbzCheck = false;
        } else if ("1".equals(selected1)) {
            isShbzCheck = true;
        }

        if ("1".equals(validSj)) {
            ivBzsj.setVisibility(View.GONE);
            cbBzsj.setVisibility(View.VISIBLE);
//            tvBzsjContent.setText(submitInfo.getProtectBusiness().getContent());
            if ("1".equals(submitInfo.getProtectBusiness().getSelected())) {
                cbBzsj.setChecked(true);
            } else {
                cbBzsj.setChecked(false);
            }
        } else if ("0".equals(validSj)) {
            ivBzsj.setVisibility(View.VISIBLE);
            cbBzsj.setVisibility(View.GONE);
        }

        //尺寸提示
        productTips = submitInfo.getOrderBill().getProductTips();
        LinearLayoutManager manager3 = new LinearLayoutManager(this);
        manager3.setOrientation(LinearLayoutManager.VERTICAL);
        rvShowTips.setLayoutManager(manager3);
        ShowTipsAdapter showTipsAdapter = new ShowTipsAdapter(this, productTips);
        rvShowTips.setAdapter(showTipsAdapter);

        //联系人
        contactInfo = submitInfo.getOrderBill().getContactInfo();
        SPUtil.setObject(SubmitActivity.this, "contactList", contactInfo);

        //初始化总金额
        if (isFjChek == true && isShbzCheck == true && isHyxCheck == true) {
            BigDecimal add = realTotalMoney.add(fjNunmber).add(new BigDecimal("1"));
            if (planselect == 0) {
                tvSubmitMoney.setText(realTotalMoney.add(fjNunmber).add(new BigDecimal("1")).add(insureance1) + "");
            } else if (planselect == 1) {
                tvSubmitMoney.setText(realTotalMoney.add(fjNunmber).add(new BigDecimal("1")).add(insureance2) + "");
            } else if (planselect == 2) {
                tvSubmitMoney.setText(realTotalMoney.add(fjNunmber).add(new BigDecimal("1")).add(insureance3) + "");
            }
        } else if (isFjChek == false && isHyxCheck == true && isShbzCheck == true) {
            if (planselect == 0) {
                tvSubmitMoney.setText(realTotalMoney.add(new BigDecimal("1").add(insureance1)) + "");
            } else if (planselect == 1) {
                tvSubmitMoney.setText(realTotalMoney.add(new BigDecimal("1").add(insureance2)) + "");
            } else if (planselect == 2) {
                tvSubmitMoney.setText(realTotalMoney.add(new BigDecimal("1").add(insureance3)) + "");
            }

        } else if (isFjChek == true && isHyxCheck == false && isShbzCheck == true) {
            tvSubmitMoney.setText(realTotalMoney.add(fjNunmber).add(new BigDecimal("1")) + "");
        } else if (isFjChek == true && isHyxCheck == true && isShbzCheck == false) {
            if (planselect == 0) {
                tvSubmitMoney.setText(realTotalMoney.add(fjNunmber).add(insureance1) + "");
            } else if (planselect == 1) {
                tvSubmitMoney.setText(realTotalMoney.add(fjNunmber).add(insureance2) + "");
            } else if (planselect == 2) {
                tvSubmitMoney.setText(realTotalMoney.add(fjNunmber).add(insureance3) + "");
            }

        } else if (isFjChek == false && isHyxCheck == false && isShbzCheck == true) {
            tvSubmitMoney.setText(realTotalMoney.add(new BigDecimal("1")) + "");
        } else if (isFjChek == false && isHyxCheck == true && isShbzCheck == false) {
            if (planselect == 0) {
                tvSubmitMoney.setText(realTotalMoney.add(insureance1) + "");
            } else if (planselect == 1) {
                tvSubmitMoney.setText(realTotalMoney.add(insureance2) + "");
            } else if (planselect == 2) {
                tvSubmitMoney.setText(realTotalMoney.add(insureance3) + "");
            }
        } else if (isFjChek == true && isHyxCheck == false && isShbzCheck == false) {
            tvSubmitMoney.setText(realTotalMoney.add(fjNunmber) + "");
        } else if (isFjChek == false && isHyxCheck == false && isShbzCheck == false) {
            tvSubmitMoney.setText(realTotalMoney + "");
        }

    }

    private void initShows() {

        LinearLayoutManager manager3 = new LinearLayoutManager(this);
        manager3.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvShow.setLayoutManager(manager3);
        showAdapter = new ShowAdapter(this, plan1, plan2, plan3, validHyx) {
            @Override
            public void onItemClick(int position) {
                if (isHyxCheck == false) {
                    bigDecimal = new BigDecimal(tvSubmitMoney.getText().toString().trim());
                }
                ShowAdapter.selectPostion = position;
                planselect = position;
                cbHy.setChecked(true);
                if (position == 0) {
                    tvSubmitMoney.setText(bigDecimal.add(insureance1) + "");
                } else if (position == 1) {
                    tvSubmitMoney.setText(bigDecimal.add(insureance2) + "");
                } else if (position == 2) {
                    tvSubmitMoney.setText(bigDecimal.add(insureance3) + "");
                }
                notifyDataSetChanged();
            }
        };
        rvShow.setAdapter(showAdapter);
        WindowManager wm = (WindowManager) this
                .getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();

        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                rvShow.smoothScrollBy((width - 400) / 2, 0);
            }
        }, 500);
    }


    private void initCheckboxListener() {
        cbBzsj.setOnCheckedChangeListener((button, isCheck) -> {
            BigDecimal chebox3 = new BigDecimal(tvSubmitMoney.getText().toString().trim());
            if (isCheck) {
                isShbzCheck = true;
                tvBzsj.setBackgroundResource(R.drawable.bg_rb_select);
                tvBzsj.setTextColor(Color.parseColor("#ffffff"));
                tvSubmitMoney.setText(chebox3.add(new BigDecimal("1")) + "");
            } else {
                isShbzCheck = false;
                tvBzsj.setBackgroundResource(R.drawable.bg_tv_cb);
                tvBzsj.setTextColor(Color.parseColor("#575757"));
                tvSubmitMoney.setText(chebox3.subtract(new BigDecimal("1")) + "");
            }
        });

        cbHy.setOnCheckedChangeListener((button, isCheck) -> {
            BigDecimal chebox2 = new BigDecimal(tvSubmitMoney.getText().toString().trim());
            if (isCheck) {
                isHyxCheck = true;
                mChage = "1";
                ShowAdapter.selectPostion = 2;
                showAdapter.notifyDataSetChanged();
                tvHy.setBackgroundResource(R.drawable.bg_rb_select);
                tvHy.setTextColor(Color.parseColor("#ffffff"));
                tvSubmitMoney.setText(chebox2.add(insureance3) + "");
            } else {
                isHyxCheck = false;
                mChage = "0";
                ShowAdapter.selectPostion = 3;
                showAdapter.notifyDataSetChanged();
                tvHy.setBackgroundResource(R.drawable.bg_tv_cb);
                tvHy.setTextColor(Color.parseColor("#575757"));
                if (planselect == 0) {
                    tvSubmitMoney.setText(chebox2.subtract(insureance1) + "");
                } else if (planselect == 1) {
                    tvSubmitMoney.setText(chebox2.subtract(insureance2) + "");
                } else if (planselect == 2) {
                    tvSubmitMoney.setText(chebox2.subtract(insureance3) + "");
                }
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_submit;
    }

    @Override
    public void initToolBar() {
        logiToolBar.setNavigationOnClickListener(view -> finish());
    }

    @OnClick({R.id.iv_submit_jie, R.id.tv_contact_history, R.id.tv_tips_mobile, R.id.tv_tips_jf, R.id.tv_tips_bx, R.id.tv_submit_sure, R.id.iv_coast, R.id.iv_insureance_content})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_submit_jie:
                break;
            case R.id.tv_contact_history://历史联系人
                startActivityForResult(new Intent(this, ContactHistoryActivity.class), 1111);
                break;
            case R.id.tv_tips_mobile:
                Intent telIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:400-728-5256"));
                startActivity(telIntent);
                break;
            case R.id.tv_tips_jf://计费方式
                startActivity(new Intent(this, PayStyleActivity.class));
                break;
            case R.id.tv_tips_bx://保险
                startActivity(new Intent(this, InsureanceActivity.class));
                break;
            case R.id.tv_submit_sure:
                nextSure();
                break;
            case R.id.iv_insureance_content:
                startActivity(new Intent(this, InsureanceActivity.class));
                break;
            case R.id.iv_coast:
                startActivity(new Intent(this, PayStyleActivity.class));
                break;
            default:
                break;
        }
    }

    private void nextSure() {
        jsondatas.clear();
        fjList.clear();
        if (mapContact == null) {
            mapContact = new HashMap<>();
        }
        mapContact.clear();
        if (TextUtils.isEmpty(etContactName.getText().toString().trim())) {
            showPromptDialog("联系人信息为空！");
            return;
        } else if (TextUtils.isEmpty(etContactPhone.getText().toString().trim())) {
            showPromptDialog("手机号为空！");
            return;
        } else if (TextUtils.isEmpty(etContactQq.getText().toString())) {
            showPromptDialog("请填写QQ!");
            return;
        } else if (filterItem(allAddView.getChildCount())) {
            cCjson = gson.toJson(jsondatas);
            mapContact.put("name", etContactName.getText().toString().trim());
            mapContact.put("tel", etContactPhone.getText().toString().trim());
            mapContact.put("qq", etContactQq.getText().toString().trim());
            mapContact.put("email", etContactMail.getText().toString().trim());
            mapContact.put("remark", etContactContent.getText().toString().trim());
            contactJson = gson.toJson(mapContact);

            if (isFjChek == false) {
                fjList.clear();
            } else if (isFjChek == true) {
                fjList.add(fJid);
            }

            fjJson = gson.toJson(fjList);
            if (isHyxCheck == false) {
                goodsExtensionInsurance = "0";
            } else {
                if (planselect == 0) {
                    goodsExtensionInsurance = "1";
                } else if (planselect == 1) {
                    goodsExtensionInsurance = "2";
                } else if (planselect == 2) {
                    goodsExtensionInsurance = "3";
                }

            }

            if (isShbzCheck == true) {
                protectBusiness = "1";
            } else {
                protectBusiness = "0";
            }
            showFristDialog();
        }

    }

    private void showFristDialog() {
        if (submitShowDialog == null) {
            submitShowDialog = new SubmitShowDialog(SubmitActivity.this) {
                @Override
                public void setOnSureClick() {
                    tips = submitInfo.getTips();
                    shownumber = "1";
                    showTipsDialog();
                    dismiss();
                }
            };
        }
        submitShowDialog.show();
    }

    private void showTipsDialog() {

        if (submitTipsDialog == null) {
            submitTipsDialog = new SubmitTipsDialog(SubmitActivity.this, tips, shownumber) {
                @Override
                public void setOnSureClick(String s1, String s2) {
                    if ("1".equals(s1) && "1".equals(s2)) {
                        shownumber = "2";
                        dismiss();
                        showTipsDialog();
                    } else {
                        getSureNext();
                        dismiss();
                    }
                }
            };
        }
        submitTipsDialog.show();
    }

    private void getSureNext() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// HH:mm:ss
        bookingDate = simpleDateFormat.format(new Date(System.currentTimeMillis()));
        if (myDialog == null) {
            myDialog = new MyDialog(this);
        }
        myDialog.showDialog();
        params.clear();
        params.put("act", "postSingles2019Data");
        params.put("productNo", productNo);
        params.put("airline", airline);
        params.put("quotedPriceId", quotedPriceId);
        params.put("priceCheckId", priceCheckId);
        params.put("productDate", productDate);
        params.put("startPort", startPort);
        params.put("destination", destination);
        params.put("goodNumber", goodNumber);
        params.put("goodWeight", goodWeight);
        params.put("goodVolume", goodVolume);
        params.put("bookingPosition", bookingPosition);
        params.put("packageWay", packageWay);
        params.put("bookingDate", bookingDate);
        params.put("goodsExtensionInsurance", goodsExtensionInsurance);
        params.put("bookingMode", "智能订舱");
        params.put("attachService", fjJson);
        params.put("protectBusiness", protectBusiness);
        params.put("protectBusinessPrice", "1");
        params.put("sizeNote", cCjson);
        params.put("contactInfo", contactJson);

        L.i("AAAAA", params.toString());
        RetrofitManager.getInstance().create(CommonService.class)
                .getSubmitNext(TOKEN, params)
                .throttleFirst(1, TimeUnit.SECONDS)
                .retryWhen(new RetryWhenNetworkException(2, 500, 500))
                .compose(RxSchedulers.io_main())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onHandleSuccess(BaseEntity baseEntity) {

                        myDialog.dismissDialog();
                        if (!TextUtils.isEmpty(baseEntity.getErrMsg())) {
                            ToastUtil.show(SubmitActivity.this, baseEntity.getErrMsg());
                            return;
                        } else {
                            submitSuccessInfo = gson.fromJson(baseEntity.getSuccess(), SubmitSuccessInfo.class);
                            initSuccess();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        if (myDialog != null) {
                            myDialog.dismissDialog();
                        }

                    }
                });
    }

    private void initSuccess() {
        if (submitSuccessDialog == null) {
            submitSuccessDialog = new SubmitSuccessDialog(SubmitActivity.this, submitSuccessInfo) {
                @Override
                public void setOnSureClick(String mobile) {
                    submitMobile(mobile);
                    dismiss();
                }
            };
        }
        submitSuccessDialog.show();
    }

    private void submitMobile(String mobile) {
        if (myDialog == null) {
            myDialog = new MyDialog(SubmitActivity.this);
        }
        myDialog.showDialog();
        mParams.clear();
        mParams.put("act", "postOrderBillReschedulingManualReviewData");
        mParams.put("orderBillCode", submitSuccessInfo.getOrderBillCode());
        mParams.put("mobile", mobile);

        RetrofitManager.getInstance().create(CommonService.class)
                .getSubmitPhone(TOKEN, mParams)
                .throttleFirst(1, TimeUnit.SECONDS)
                .retryWhen(new RetryWhenNetworkException(2, 500, 500))
                .compose(RxSchedulers.io_main())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onHandleSuccess(BaseEntity baseEntity) {
                        if (!TextUtils.isEmpty(baseEntity.getErrMsg())) {
                            ToastUtil.show(SubmitActivity.this, baseEntity.getErrMsg());
                            return;
                        } else {
                            startActivity(new Intent(SubmitActivity.this, SubmitSuccessActivity.class));
                            finish();
                        }
                    }

                    @Override
                    protected void onFinally() {
                        super.onFinally();
                        if (myDialog != null) {
                            myDialog.dismissDialog();
                        }
                    }
                });
    }


    // 获取添加的设计师公司信息
    private boolean filterItem(int childCount) {
        L.i("子条目item", childCount + "");
        for (int i = 0; i < childCount; i++) {
            Map<String, Object> map = new HashMap<>();
            final View childAt = allAddView.getChildAt(i);

            final EditText et_add_chang = childAt.findViewById(R.id.et_add_chang);
            final EditText et_add_kuan = childAt.findViewById(R.id.et_add_kuan);
            final EditText et_add_gao = childAt.findViewById(R.id.et_add_gao);
            final EditText et_add_number = childAt.findViewById(R.id.et_add_number);

            if ("1".equals(sizeNoteType)) {
                if (TextUtils.isEmpty(et_add_chang.getText().toString().trim())) {
                    ToastUtil.show(SubmitActivity.this, "请输入长!");
                    return false;
                } else if (TextUtils.isEmpty(et_add_kuan.getText().toString().trim())) {
                    ToastUtil.show(SubmitActivity.this, "请输入宽！");
                    return false;
                } else if (TextUtils.isEmpty(et_add_gao.getText().toString().trim())) {
                    ToastUtil.show(SubmitActivity.this, "请输入高！");
                    return false;
                } else if (TextUtils.isEmpty(et_add_number.getText().toString().trim())) {
                    ToastUtil.show(SubmitActivity.this, "请输入件数！");
                    return false;
                }
            }


            if (TextUtils.isEmpty(et_add_chang.getText().toString().trim()) && TextUtils.isEmpty(et_add_kuan.getText().toString().trim())
                    && TextUtils.isEmpty(et_add_gao.getText().toString().trim()) && TextUtils.isEmpty(et_add_number.getText().toString().trim())) {
            } else {
                map.put("length", et_add_chang.getText().toString().trim());
                map.put("width", et_add_kuan.getText().toString().trim());
                map.put("high", et_add_gao.getText().toString().trim());
                map.put("pieces", et_add_number.getText().toString().trim());
                jsondatas.add(map);
            }
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1111) {
            if (resultCode != -1) {
                etContactName.setText(contactInfo.get(resultCode).getName());
                etContactPhone.setText(contactInfo.get(resultCode).getTel());
                etContactQq.setText(contactInfo.get(resultCode).getQq());
                etContactMail.setText(contactInfo.get(resultCode).getEmail());
                etContactContent.setText(contactInfo.get(resultCode).getRemark());
            }
        }
    }

    private void showPromptDialog(String s) {
        new android.support.v7.app.AlertDialog.Builder(this)
                .setMessage(s)
                .setPositiveButton("确定", null).show();
    }

    //添加ViewItem
    private void addViewItem(View view) {
        if (allAddView.getChildCount() == 0) {//如果一个都没有，就添加一个
            View hotelEvaluateView = View.inflate(this, R.layout.item_addview_submit, null);
            allAddView.addView(hotelEvaluateView);
            sortHotelViewItem();
        } else if (((String) view.getTag()).equals("add")) {//如果有一个以上的Item,点击为添加的Item则添加
            View hotelEvaluateView = View.inflate(this, R.layout.item_addview_submit, null);
            allAddView.addView(hotelEvaluateView);
            sortHotelViewItem();
        }
    }

    /**
     * Item排序
     */
    private void sortHotelViewItem() {
        //获取LinearLayout里面所有的view
        for (int i = 0; i < allAddView.getChildCount(); i++) {
            final View childAt = allAddView.getChildAt(i);
            final ImageView iv_add = childAt.findViewById(R.id.iv_add);
            final ImageView iv_del = childAt.findViewById(R.id.iv_del);

            final EditText et_add_chang = childAt.findViewById(R.id.et_add_chang);
            final EditText et_add_kuan = childAt.findViewById(R.id.et_add_kuan);
            final EditText et_add_gao = childAt.findViewById(R.id.et_add_gao);
            final EditText et_add_number = childAt.findViewById(R.id.et_add_number);

            if (allAddView.getChildCount() == 1) {
                iv_del.setVisibility(View.GONE);
                iv_add.setVisibility(View.VISIBLE);

                iv_add.setOnClickListener(view -> {
                    if (TextUtils.isEmpty(et_add_chang.getText().toString().trim()) || TextUtils.isEmpty(et_add_kuan.getText().toString().trim())
                            || TextUtils.isEmpty(et_add_gao.getText().toString().trim()) || TextUtils.isEmpty(et_add_number.getText().toString().trim())) {
                        return;
                    } else {
                        iv_add.setTag("add");
                        addViewItem(view);
                    }
                });

            } else {
                if (i == (allAddView.getChildCount() - 1)) {
                    iv_del.setVisibility(View.GONE);
                    iv_add.setVisibility(View.VISIBLE);

                    iv_add.setOnClickListener(view -> {
                        if (TextUtils.isEmpty(et_add_chang.getText().toString().trim()) || TextUtils.isEmpty(et_add_kuan.getText().toString().trim())
                                || TextUtils.isEmpty(et_add_gao.getText().toString().trim()) || TextUtils.isEmpty(et_add_number.getText().toString().trim())) {
                            return;
                        } else {
                            iv_add.setTag("add");
                            addViewItem(view);
                        }
                    });

                } else {
                    iv_del.setVisibility(View.VISIBLE);
                    iv_add.setVisibility(View.GONE);
                    iv_del.setTag("remove");//设置删除标记
                    iv_del.setOnClickListener(view -> {
                        AlertDialog.Builder builder = new AlertDialog.Builder(SubmitActivity.this);
                        builder.setMessage("是否要删除该条信息？");
                        builder.setPositiveButton("确定", (dialog, which) -> {
                            allAddView.removeView(childAt);
                        });
                        //    设置一个NegativeButton
                        builder.setNegativeButton("取消", (dialog, which) -> {
                        });
                        builder.show();
                    });
                }

            }
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
