package com.mobile.android.retrofit.api;


import com.mobile.hyoukalibrary.OkhttpCacheInterceptor.Header.CacheHeaders;
import com.mobile.hyoukalibrary.base.BaseEntity;
import com.mobile.android.entity.DesiginerSearchInfo;
import com.mobile.android.entity.ContactOwerBean;
import com.mobile.android.entity.JcFeedBackType;
import com.mobile.android.entity.JcOwnerOrderInfo;
import com.mobile.android.entity.MemoRandomDetailBean;
import com.mobile.android.entity.MemoRandomListBean;
import com.mobile.android.entity.MissedCallsBean;
import com.mobile.android.entity.SmsContentBean;
import com.mobile.android.entity.SmsSendCodeBean;
import com.mobile.android.entity.TodayBwBean;
import com.mobile.android.entity.UpdateInfoBean;
import com.mobile.android.entity.User;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

/**
 * 描述: 一个公用的Service
 * --------------------------------------------
 * 工程:
 * #0000     mwy     创建日期: 2016-09-12 17:16
 */
public interface CommonService {


    @FormUrlEncoded
    @POST("designer_interface")
    Observable<BaseEntity<String>> getData(@Field("json_msg") String json_msg);

    /**
     * 登录
     */
    @FormUrlEncoded
    @POST("login")
    Observable<BaseEntity<User>> login(@FieldMap Map<String, Object> params);

    /**
     * 退出登录
     */
    @FormUrlEncoded
    @POST("login_out")
    Observable<BaseEntity<List>> loginOut(@FieldMap Map<String, Object> params);


    /**
     * 设计师查询
     */
    @FormUrlEncoded
    @POST("get_des_show_number_list")
    Observable<BaseEntity<List<DesiginerSearchInfo>>> getdesiginerinfo(@FieldMap Map<String, Object> params);


    /**
     * 业主查询
     */
    @FormUrlEncoded
    @POST("search_user_message")
    Observable<BaseEntity<List<ContactOwerBean>>> getOwnerInfo(@FieldMap Map<String, Object> params);

    /**
     * 拨号接口
     */
    @FormUrlEncoded
    @POST("callout_phone")
    Observable<BaseEntity<List>> callOutPhone(@FieldMap Map<String, Object> params);

    /**
     * 未接来电列表
     */
    @FormUrlEncoded
    @POST("missed_calls")
    Observable<BaseEntity<List<MissedCallsBean>>> missedCalls(@FieldMap Map<String, Object> params);

    /**
     * 获取短信发送节点
     */
    @FormUrlEncoded
    @POST("message_node")
    Observable<BaseEntity<SmsSendCodeBean>> getSmsNode(@FieldMap Map<String, Object> params);

    /**
     * 获取短信发送模板
     */
    @FormUrlEncoded
    @POST("get_node_message")
    Observable<BaseEntity<SmsContentBean>> getSmsContent(@FieldMap Map<String, Object> params);

    /**
     * 发送短信
     */
    @FormUrlEncoded
    @POST("send_message")
    Observable<BaseEntity<List>> sendMsg(@FieldMap Map<String, Object> params);

    /**
     * 装修反馈：正常反馈、两房改约、取消量房
     */
    @FormUrlEncoded
    @POST("set_custom_service_notes_info")
    Observable<BaseEntity<String>> getnormalBack(@FieldMap Map<String, Object> params);

    /**
     * 获取建材反馈类型
     */
    @FormUrlEncoded
    @POST("get_jc_config")
    Observable<BaseEntity<JcFeedBackType>> getJcConfig(@FieldMap Map<String, Object> params);

    /**
     * 获取建材反馈业主订单
     */
    @FormUrlEncoded
    @POST("get_jc_allot_baomings_info")
    Observable<BaseEntity<JcOwnerOrderInfo>> getJcOwnerOrderInfo(@FieldMap Map<String, Object> params);

    /**
     * 建材反馈提交
     */
    @FormUrlEncoded
    @POST("set_fk_info")
    Observable<BaseEntity<List>> jcFkSubmit(@FieldMap Map<String, Object> params);

    /**
     * 获取今日备忘
     */
    @FormUrlEncoded
    @POST("get_today_remark")
    Observable<BaseEntity<TodayBwBean>> getTodayBw(@FieldMap Map<String, Object> params);

    /**
     * 备忘录列表
     */
    @FormUrlEncoded
    @POST("memorandum_management")
    Observable<BaseEntity<List<MemoRandomListBean>>> getBwList(@FieldMap Map<String, Object> params);

    /**
     * 备忘录详情
     */
    @FormUrlEncoded
    @POST("see_memorandum")
    Observable<BaseEntity<MemoRandomDetailBean>> getBwDetail(@FieldMap Map<String, Object> params);

    /**
     * 增加备忘
     */
    @FormUrlEncoded
    @POST("add_memorandum")
    Observable<BaseEntity<List>> addBw(@FieldMap Map<String, Object> params);

    /**
     * 编辑备忘
     */
    @FormUrlEncoded
    @POST("edit_memorandum")
    Observable<BaseEntity<List>> editBw(@FieldMap Map<String, Object> params);

    /**
     * 删除备忘
     */
    @FormUrlEncoded
    @POST("delete_memorandum")
    Observable<BaseEntity<List>> deleteBw(@FieldMap Map<String, Object> params);

    /**
     * 装修反馈接口
     */
    @FormUrlEncoded
    @POST("set_custom_service_notes_info")//接口名重复
    Observable<BaseEntity<String>> getsigin(@FieldMap Map<String, Object> params);

    /**
     * 检查更新
     */
    @FormUrlEncoded
    @POST("get_new_code_url")
    Observable<BaseEntity<UpdateInfoBean>> checkUpdate(@FieldMap Map<String, Object> params);

    @FormUrlEncoded
    @POST("http://preapi.youjuke.com/Designer/designer_interface")
    Observable<BaseEntity<String>> getData1(@Field("json_msg") String json_msg);

    @Multipart
    @POST("designer_interface")
    Observable<BaseEntity<String>> upLoad(@Part("json_msg") RequestBody json_msg, @PartMap Map<String, RequestBody> fileList);

    @FormUrlEncoded
    @POST("designer_interface")
    Observable<BaseEntity<String>> upLoad(@Field("json_msg") String json_msg);

    @Headers(CacheHeaders.MAX_AGE)
    @FormUrlEncoded
    @POST("designer_interface")
    Observable<BaseEntity<String>> getDataToCache(@Field("json_msg") String json_msg);

    @Headers(CacheHeaders.MAX_AGE)
    @FormUrlEncoded
    @POST("http://preapi.youjuke.com/Designer/designer_interface")
    Observable<BaseEntity<String>> getData1ToCache(@Field("json_msg") String json_msg);

}
