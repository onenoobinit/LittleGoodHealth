package com.mobile.android.retrofit.api;


import com.mobile.hyoukalibrary.OkhttpCacheInterceptor.Header.CacheHeaders;
import com.mobile.hyoukalibrary.base.BaseEntity;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
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
     * 个人中心
     */
    @FormUrlEncoded
    @POST("personalCenterHandler/common/PersonalCenterCommonHandler.ashx")
    Observable<BaseEntity> me(@Header("token") String token, @FieldMap Map<String, Object> params);

    /**
     * 订单角标
     */
    @FormUrlEncoded
    @POST("PersonalCenterMobile/CargoQuery/ExportBusiness/ExportMainHandler.ashx")
    Observable<BaseEntity> meMarker(@Header("token") String token, @FieldMap Map<String, Object> params);

    /**
     * 订单列表
     */
    @FormUrlEncoded
    @POST("PersonalCenterMobile/CargoQuery/ExportBusiness/ExportMainHandler.ashx")
    Observable<BaseEntity> getOrder(@Header("token") String token, @FieldMap Map<String, Object> params);

    /**
     * 设置
     */
    @FormUrlEncoded
    @POST("personalCenterHandler/personalInfo/PersonalInfoHandler.ashx")
    Observable<BaseEntity> getSet(@Header("token") String token, @FieldMap Map<String, Object> params);

    /**
     * 退出
     */
    @FormUrlEncoded
    @POST("personalCenterHandler/common/PersonalCenterCommonHandler.ashx")
    Observable<BaseEntity> out(@Header("token") String token, @FieldMap Map<String, Object> params);

    /**
     * 绑定手机、邮箱获取验证码
     */
    @FormUrlEncoded
    @POST("personalCenterHandler/common/PersonalCenterCommonHandler.ashx")
    Observable<BaseEntity> getCode(@Header("token") String token, @FieldMap Map<String, Object> params);

    /**
     * 验证手机、邮箱验证码
     */
    @FormUrlEncoded
    @POST("personalCenterHandler/common/PersonalCenterCommonHandler.ashx")
    Observable<BaseEntity> checkCode(@Header("token") String token, @FieldMap Map<String, Object> params);

    /**
     * 意见反馈
     */
    @FormUrlEncoded
    @POST("personalCenterHandler/common/PersonalCenterCommonHandler.ashx")
    Observable<BaseEntity> feedBack(@Header("token") String token, @FieldMap Map<String, Object> params);

    /**
     * 修改密码
     */
    @FormUrlEncoded
    @POST("personalCenterHandler/accountCenter/SecurityCenterHandler.ashx")
    Observable<BaseEntity> setPassWord(@Header("token") String token, @FieldMap Map<String, Object> params);

    /**
     * 注册
     */
    @FormUrlEncoded
    @POST("personalCenterHandler/common/PersonalCenterCommonHandler.ashx")
    Observable<BaseEntity> register(@FieldMap Map<String, Object> params);

    /**
     * 始发港
     */
    @FormUrlEncoded
    @POST("SmartProductsHandler/Common/CommonHandler.ashx")
    Observable<BaseEntity> getPort(@Header("token") String token, @FieldMap Map<String, Object> params);

    /**
     * 目的地
     */
    @FormUrlEncoded
    @POST("SmartProductsHandler/Common/CommonHandler.ashx")
    Observable<BaseEntity> getStart(@Header("token") String token, @FieldMap Map<String, Object> params);

    /**
     * 方案选择1
     */
    @FormUrlEncoded
    @POST("SmartProductsHandler/ProductSearch/ProductQueryV2Handler.ashx")
    Observable<BaseEntity> getSelect(@Header("token") String token, @FieldMap Map<String, Object> params);

    /**
     * 产品详情
     */
    @FormUrlEncoded
    @POST("SmartProductsHandler/ProductSearch/ProductResultHandler.ashx")
    Observable<BaseEntity> getProdetail(@Header("token") String token, @FieldMap Map<String, Object> params);

    /**
     * 产品详情日期
     */
    @FormUrlEncoded
    @POST("SmartProductsHandler/ProductSearch/ProductResultHandler.ashx")
    Observable<BaseEntity> getDateinfo(@Header("token") String token, @FieldMap Map<String, Object> params);

    /**
     * 方案选择提交
     */
    @FormUrlEncoded
    @POST("SmartProductsHandler/SubmitOrders/SubmitOrdersHandler.ashx")
    Observable<BaseEntity> getSubmitData(@Header("token") String token, @FieldMap Map<String, Object> params);

    /**
     * 托书提交
     */
    @FormUrlEncoded
    @POST("SmartProductsHandler/SubmitOrders/SubmitOrdersHandler.ashx")
    Observable<BaseEntity> getSubmitNext(@Header("token") String token, @FieldMap Map<String, Object> params);


























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
