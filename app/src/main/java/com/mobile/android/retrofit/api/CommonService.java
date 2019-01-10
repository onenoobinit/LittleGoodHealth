package com.mobile.android.retrofit.api;


import com.mobile.hyoukalibrary.OkhttpCacheInterceptor.Header.CacheHeaders;
import com.mobile.hyoukalibrary.base.BaseEntity;

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
    Observable<BaseEntity> getData(@Field("json_msg") String json_msg);

    /**
     * 登录
     */
    @FormUrlEncoded
    @POST("personalCenterHandler/common/PersonalCenterCommonHandler.ashx")
    Observable<BaseEntity> login(@FieldMap Map<String, Object> params);

    /**
     * 退出登录
     */
    @FormUrlEncoded
    @POST("login_out")
    Observable<BaseEntity> loginOut(@FieldMap Map<String, Object> params);


    /**
     * 设计师查询
     */
    @FormUrlEncoded
    @POST("get_des_show_number_list")
    Observable<BaseEntity> getdesiginerinfo(@FieldMap Map<String, Object> params);


    /**
     * 业主查询
     */
    @FormUrlEncoded
    @POST("search_user_message")
    Observable<BaseEntity> getOwnerInfo(@FieldMap Map<String, Object> params);

    /**
     * 拨号接口
     */
    @FormUrlEncoded
    @POST("callout_phone")
    Observable<BaseEntity> callOutPhone(@FieldMap Map<String, Object> params);

    /**
     * 未接来电列表
     */
    @FormUrlEncoded
    @POST("missed_calls")
    Observable<BaseEntity> missedCalls(@FieldMap Map<String, Object> params);

    /**
     * 获取短信发送节点
     */
    @FormUrlEncoded
    @POST("message_node")
    Observable<BaseEntity> getSmsNode(@FieldMap Map<String, Object> params);

    /**
     * 获取短信发送模板
     */
    @FormUrlEncoded
    @POST("get_node_message")
    Observable<BaseEntity> getSmsContent(@FieldMap Map<String, Object> params);

    /**
     * 发送短信
     */
    @FormUrlEncoded
    @POST("send_message")
    Observable<BaseEntity> sendMsg(@FieldMap Map<String, Object> params);

    /**
     * 装修反馈：正常反馈、两房改约、取消量房
     */
    @FormUrlEncoded
    @POST("set_custom_service_notes_info")
    Observable<BaseEntity> getnormalBack(@FieldMap Map<String, Object> params);

    /**
     * 获取建材反馈类型
     */
    @FormUrlEncoded
    @POST("get_jc_config")
    Observable<BaseEntity> getJcConfig(@FieldMap Map<String, Object> params);

    /**
     * 获取建材反馈业主订单
     */
    @FormUrlEncoded
    @POST("get_jc_allot_baomings_info")
    Observable<BaseEntity> getJcOwnerOrderInfo(@FieldMap Map<String, Object> params);

    /**
     * 建材反馈提交
     */
    @FormUrlEncoded
    @POST("set_fk_info")
    Observable<BaseEntity> jcFkSubmit(@FieldMap Map<String, Object> params);

    /**
     * 获取今日备忘
     */
    @FormUrlEncoded
    @POST("get_today_remark")
    Observable<BaseEntity> getTodayBw(@FieldMap Map<String, Object> params);

    /**
     * 备忘录列表
     */
    @FormUrlEncoded
    @POST("memorandum_management")
    Observable<BaseEntity> getBwList(@FieldMap Map<String, Object> params);

    /**
     * 备忘录详情
     */
    @FormUrlEncoded
    @POST("see_memorandum")
    Observable<BaseEntity> getBwDetail(@FieldMap Map<String, Object> params);

    /**
     * 增加备忘
     */
    @FormUrlEncoded
    @POST("add_memorandum")
    Observable<BaseEntity> addBw(@FieldMap Map<String, Object> params);

    /**
     * 编辑备忘
     */
    @FormUrlEncoded
    @POST("edit_memorandum")
    Observable<BaseEntity> editBw(@FieldMap Map<String, Object> params);

    /**
     * 删除备忘
     */
    @FormUrlEncoded
    @POST("delete_memorandum")
    Observable<BaseEntity> deleteBw(@FieldMap Map<String, Object> params);

    /**
     * 装修反馈接口
     */
    @FormUrlEncoded
    @POST("set_custom_service_notes_info")//接口名重复
    Observable<BaseEntity> getsigin(@FieldMap Map<String, Object> params);

    /**
     * 检查更新
     */
    @FormUrlEncoded
    @POST("get_new_code_url")
    Observable<BaseEntity> checkUpdate(@FieldMap Map<String, Object> params);

    @FormUrlEncoded
    @POST("http://preapi.youjuke.com/Designer/designer_interface")
    Observable<BaseEntity> getData1(@Field("json_msg") String json_msg);

    @Multipart
    @POST("designer_interface")
    Observable<BaseEntity> upLoad(@Part("json_msg") RequestBody json_msg, @PartMap Map<String, RequestBody> fileList);

    @FormUrlEncoded
    @POST("designer_interface")
    Observable<BaseEntity> upLoad(@Field("json_msg") String json_msg);

    @Headers(CacheHeaders.MAX_AGE)
    @FormUrlEncoded
    @POST("designer_interface")
    Observable<BaseEntity> getDataToCache(@Field("json_msg") String json_msg);

    @Headers(CacheHeaders.MAX_AGE)
    @FormUrlEncoded
    @POST("http://preapi.youjuke.com/Designer/designer_interface")
    Observable<BaseEntity> getData1ToCache(@Field("json_msg") String json_msg);

}
