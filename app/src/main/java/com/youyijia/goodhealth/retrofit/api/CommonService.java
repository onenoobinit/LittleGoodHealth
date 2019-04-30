package com.youyijia.goodhealth.retrofit.api;


import com.youyijia.hyoukalibrary.OkhttpCacheInterceptor.Header.CacheHeaders;
import com.youyijia.hyoukalibrary.base.BaseEntity;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

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
     * 个人中心
     */
    @FormUrlEncoded
    @POST("personalCenterHandler/common/PersonalCenterCommonHandler.ashx")
    Observable<BaseEntity> me(@Header("token") String token, @FieldMap Map<String, Object> params);

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
     * 托书提交手机号
     */
    @FormUrlEncoded
    @POST("personalCenterHandler/operationCenter/cargoQuery/exportBusiness/ChangeDateHandler.ashx")
    Observable<BaseEntity> getSubmitPhone(@Header("token") String token, @FieldMap Map<String, Object> params);


    /**
     * 检测是否登录
     */
    @FormUrlEncoded
    @POST("personalCenterHandler/common/PersonalCenterCommonHandler.ashx")
    Observable<BaseEntity> getLoginStauts(@Header("token") String token, @FieldMap Map<String, Object> params);

    /**
     * 个人中心订单入库、订单、运费
     */
    @FormUrlEncoded
    @POST("personalCenterHandler/operationCenter/cargoQuery/exportBusiness/DataToConfirmHandler.ashx")
    Observable<BaseEntity> getOrderThree(@Header("token") String token, @FieldMap Map<String, Object> params);


    /**
     * 登录
     */
    @POST("api/common/customer/auth/login")
    Observable<BaseEntity> login(@Body RequestBody body);

    /**
     * 优医佳动态
     */
    @GET("api/article/health/dynamic")
    Observable<BaseEntity> getRollMessage();

    /**
     * 优医佳动态
     */
    @GET("api/common/banner")
    Observable<BaseEntity> getBanner(@QueryMap Map<String, Object> map);

    /**
     * 首页列表
     */
    @GET("api/article/health/direction")
    Observable<BaseEntity> getRecycle(@QueryMap Map<String, Object> map);

    /**
     * 微生态中心
     */
    @POST("api/advisory/channel/detail")
    @Headers({"Content-Type:application/json;charset=UTF-8"})
    Observable<BaseEntity> getmicenter(@Body Object json);

    /**
     * 微生态医生详情
     */
    @GET("api/advisory/channel/doctor/detail/{id}")
    Observable<BaseEntity> getDoctor(@Path("id") String id);

    /**
     * 权威频道详情
     */
    @GET("api/advisory/channel/doctor/lecture/list")
    Observable<BaseEntity> getDetailVideo(@QueryMap Map<String, Object> map);

    /**
     * 查询全部讲座
     */
    @GET("api/advisory/channel/doctor/lecture/all")
    Observable<BaseEntity> getAllVideo(@QueryMap Map<String, Object> map);


    /**
     * 查询其他讲座
     */
    @GET("api/advisory/channel/doctor/lecture/others")
    Observable<BaseEntity> getOtherVideo(@QueryMap Map<String, Object> map);

    /**
     * 商城列表
     */
    @GET("api/store/commodity?")
    Observable<BaseEntity> getShopData(@QueryMap Map<String, Object> map);

    /**
     * 商品详情
     */
    @GET("api/store/commodity/{commodityId}")
    Observable<BaseEntity> getShopDetail(@Path("commodityId") String commodityId);

    /**
     * 健康风向详情
     */
    @GET("api/article/health/direction/{directionId}")
    Observable<BaseEntity> getWindDetail(@Path("directionId") String directionId);

    /**
     * 心理咨询医生列表
     */
    @GET("api/advisory/doctor")
    Observable<BaseEntity> getXldoctor(@QueryMap Map<String, Object> map);

    /**
     * 绿通简介
     */
    @GET("api/green/channel/information")
    Observable<BaseEntity> getLvInstrodution();

    /**
     * 绿通城市列表
     */
    @GET("api/green/channel/medicalCity/list")
    Observable<BaseEntity> getCity();

    /**
     * 绿通服务列表
     */
    @GET("api/green/channel/medicalCity/{id}")
    Observable<BaseEntity> getService(@Path("id") String id);

    /**
     * 绿通医院列表
     */
    @GET("api/green/channel/cityService/hospital")
    Observable<BaseEntity> getHospital(@QueryMap Map<String, Object> map);

    /**
     * 绿通就医人列表
     */
    @GET("api/green/channel/medicalPerson/list")
    Observable<BaseEntity> getJiuyi();

    /**
     * 添加就医人
     */
    @Headers({"Content-Type: application/json"})
    @PUT("api/green/channel/medicalPerson")
    Observable<BaseEntity> getAddjiuyi(@Body RequestBody body);

    /**
     * 添加就医人
     */
    @Headers({"Content-Type: application/json"})
    @POST("api/green/channel/medicalPerson")
    Observable<BaseEntity> getChangejiuyi(@Body RequestBody body);

    /**
     * 意见反馈
     */
    @Headers({"Content-Type: application/json"})
    @POST("api/common/app/comment/save")
    Observable<BaseEntity> feedBack(@Body RequestBody body);

    /**
     * 获取就医人价格
     */
    @GET("api/green/channel/order/price")
    Observable<BaseEntity> getGreenPrice(@QueryMap Map<String, Object> map);

    /**
     * 健康资讯多条目
     */
    @GET("api/article/health/information")
    Observable<BaseEntity> getjkNews(@QueryMap Map<String, Object> map);

    /**
     * 验证手机号是否被注册
     */
    @Headers({"Content-Type:application/json;charset=UTF-8"})
    @POST("api/common/customer/auth/authcode/phone/check")
    Observable<BaseEntity> getPhoneCheck(@Body RequestBody body);

    /**
     * 获取验证码
     */
    @GET("api/common/customer/auth/register/authcode/{phoneNumber}")
    Observable<BaseEntity> getPhoneCode(@Path("phoneNumber") String phoneNumber);

    /**
     * 忘记密码获取验证码
     */
    @GET("api/common/customer/auth/password/authcode/{phoneNumber}")
    Observable<BaseEntity> getForgetCode(@Path("phoneNumber") String phoneNumber);

    /**
     * 个人注册
     */
    @Headers({"Content-Type:application/json;charset=UTF-8"})
    @POST("api/common/customer/auth/register/oneself")
    Observable<BaseEntity> getSelfRegister(@Body RequestBody body);

    /**
     * 企业注册
     */
    @Headers({"Content-Type:application/json;charset=UTF-8"})
    @POST("api/common/customer/auth/register/company")
    Observable<BaseEntity> getCompanyRegister(@Body RequestBody body);

    /**
     * 评论列表
     */
    @GET("api/article/comment")
    Observable<BaseEntity> getCommentList(@QueryMap Map<String, Object> map);

    /**
     * 评论点赞
     */
    @Headers({"Content-Type:application/json;charset=UTF-8"})
    @POST("api/article/comment/like")
    Observable<BaseEntity> getCommentZan(@Body RequestBody body);

    /**
     * 健康资讯详情
     */
    @GET("api/article/health/information/{directionId}")
    Observable<BaseEntity> getJKDetail(@Path("directionId") String directionId);

    /**
     * 获取当前绑定手机号
     */
    @GET("api/common/customer/auth/phone/present")
    Observable<BaseEntity> getPhone();

    /**
     * 修改密码
     */
    @Headers({"Content-Type:application/json;charset=UTF-8"})
    @POST("api/common/customer/auth/password/reset")
    Observable<BaseEntity> setPassWord(@Body RequestBody body);

    /**
     * 忘记密码
     */
    @Headers({"Content-Type:application/json;charset=UTF-8"})
    @POST("api/common/customer/auth/password/forget")
    Observable<BaseEntity> setForget(@Body RequestBody body);

    /**
     * 修改个人信息
     */
    @Headers({"Content-Type:application/json;charset=UTF-8"})
    @POST("api/common/customer")
    Observable<BaseEntity> setUserInfo(@Body RequestBody body);

    /**
     * 更换手机号获取验证码
     */
    @GET("api/common/customer/auth/phone/authcode/{phoneNumber}")
    Observable<BaseEntity> getChangeCode(@Path("phoneNumber") String phoneNumber);

    /**
     * 更换手机号
     */
    @Headers({"Content-Type:application/json;charset=UTF-8"})
    @POST("api/common/customer/auth/phone")
    Observable<BaseEntity> setChangePhone(@Body RequestBody body);

    /**
     * 获取当前用户所有收货地址
     */
    @GET("api/common/receiveAddress/list")
    Observable<BaseEntity> getAddressList();

    /**
     * 设置默认地址
     */
    @Headers({"Content-Type:application/json;charset=UTF-8"})
    @POST("api/common/receiveAddress/default")
    Observable<BaseEntity> getAddressMr(@Body RequestBody body);

    /**
     * 删除地址
     */
    @Headers("Content-Type:application/json;charset=UTF-8")
    @HTTP(method = "DELETE", path = "api/common/receiveAddress", hasBody = true)
    Observable<BaseEntity> getAddressRemove(@Body RequestBody body);

    /**
     * 修改头像
     */
    @Multipart
    @POST("api/file/aliyun/oss/upload")
    Observable<BaseEntity> upLoad(@Part MultipartBody.Part file);

    /**
     * 升级企业用户
     */
    @Headers({"Content-Type:application/json;charset=UTF-8"})
    @POST("api/common/company/code/bind")
    Observable<BaseEntity> getQyBind(@Body RequestBody body);

    /**
     * 添加就医人
     */
    @Headers({"Content-Type: application/json"})
    @PUT("api/article/comment")
    Observable<BaseEntity> putComment(@Body RequestBody body);

    /**
     * 提交绿通订单
     */
    @Headers({"Content-Type: application/json"})
    @PUT("api/green/channel/order")
    Observable<BaseEntity> putGreenOrder(@Body RequestBody body);

    /**
     * 订单角标
     */
    @GET("api/order/order/pay/message")
    Observable<BaseEntity> meMarker();

    /**
     * 添加到购物车
     */
    @Headers({"Content-Type: application/json"})
    @PUT("api/store/shopping/cart")
    Observable<BaseEntity> addToCart(@Body RequestBody body);

    /**
     * 购物车列表
     */
    @GET("api/store/shopping/cart")
    Observable<BaseEntity> getShopCartData();

    /**
     * 添加地址
     */
    @Headers({"Content-Type: application/json"})
    @PUT("api/common/receiveAddress")
    Observable<BaseEntity> addArea(@Body RequestBody body);

    /**
     * 修改收货地址
     */
    @Headers({"Content-Type:application/json;charset=UTF-8"})
    @POST("api/common/receiveAddress")
    Observable<BaseEntity> changArea(@Body RequestBody body);

    /**
     * 订单列表
     */
    @GET("api/order/order")
    Observable<BaseEntity> getOrder(@QueryMap Map<String, Object> params);

    /**
     * 修改购物车数量
     */
    @Headers({"Content-Type:application/json;charset=UTF-8"})
    @POST("api/store/shopping/cart")
    Observable<BaseEntity> changeSize(@Body RequestBody body);

    /**
     * 购买立即结算
     */
    @Headers({"Content-Type:application/json;charset=UTF-8"})
    @POST("api/store/commodity/settlement")
    Observable<BaseEntity> getShopPrice(@Body RequestBody body);

    /**
     * 查询健康小屋
     */
    @GET("api/advisory/health/cabin/detail")
    Observable<BaseEntity> getHouse();

    /**
     * 商品结算立即购买
     */
    @Headers({"Content-Type:application/json;charset=UTF-8"})
    @POST("api/store/commodity/settlement")
    Observable<BaseEntity> getSetlement(@Body RequestBody body);

    /**
     * 支付宝获取签名后的订单信息
     */
    @GET("api/order/payment/alipay/{orderId}")
    Observable<BaseEntity> getOrderInfo(@Path("orderId") String orderId);

    /**
     * 微信支付获取订单信息
     */
    @Headers({"Content-Type:application/json;charset=UTF-8"})
    @POST("api/order/payment/wechat")
    Observable<BaseEntity> wxpayInfo(@Body RequestBody body);

    /**
     * 修改购物车数量
     */
    @Headers({"Content-Type:application/json;charset=UTF-8"})
    @POST("api/common/customer/auth/wx/login")
    Observable<BaseEntity> wxLogin(@Body RequestBody body);

    /**
     * 获取当前微信绑定状态
     */
    @GET("api/common/customer/auth/wx/bind/status")
    Observable<BaseEntity> getwxStatus();

    /**
     * 解除微信绑定
     */
    @Headers("Content-Type:application/json;charset=UTF-8")
    @HTTP(method = "DELETE", path = "api/common/customer/auth/wx/bind", hasBody = true)
    Observable<BaseEntity> wxUnbing();

    /**
     * 绑定微信
     */
    @Headers({"Content-Type:application/json;charset=UTF-8"})
    @POST("api/common/customer/auth/wx/bind")
    Observable<BaseEntity> wxBing(@Body RequestBody body);

    /**
     * 取消订单
     */
    @Headers({"Content-Type:application/json;charset=UTF-8"})
    @POST("api/order/order/cancellation/{orderId}")
    Observable<BaseEntity> cancelOrder(@Path("orderId") String orderId);

    /**
     * 订单评论
     */
    @Headers({"Content-Type:application/json;charset=UTF-8"})
    @POST("api/order/order")
    Observable<BaseEntity> getComment(@Body RequestBody body);

    /**
     * 查询物流信息
     */
    @GET("api/order/logistics/traces")
    Observable<BaseEntity> myTrace(@QueryMap Map<String, Object> params);

    /**
     * 商品订单下单
     */
    @Headers({"Content-Type: application/json"})
    @PUT("api/order/order")
    Observable<BaseEntity> getOrderId(@Body RequestBody body);

    /**
     * 订单评论
     */
    @Headers({"Content-Type:application/json;charset=UTF-8"})
    @POST("api/store/shopping/cart/settlement")
    Observable<BaseEntity> getCartPrice(@Body RequestBody body);

    /**
     * 获取订单详情
     */
    @GET("api/order/order/{orderId}")
    Observable<BaseEntity> getMyOrderDetail(@Path("orderId") String orderId);

    /**
     * 删除地址
     */
    @Headers("Content-Type:application/json;charset=UTF-8")
    @HTTP(method = "DELETE", path = "api/store/shopping/cart", hasBody = true)
    Observable<BaseEntity> deleteShopItems(@Body RequestBody body);




















    /**
     * 检查更新
     */
    @FormUrlEncoded
    @POST("get_new_code_url")
    Observable<BaseEntity> checkUpdate(@FieldMap Map<String, Object> params);

    @FormUrlEncoded
    @POST("http://preapi.youjuke.com/Designer/designer_interface")
    Observable<BaseEntity> getData1(@Field("json_msg") String json_msg);


    @Headers(CacheHeaders.MAX_AGE)
    @FormUrlEncoded
    @POST("designer_interface")
    Observable<BaseEntity> getDataToCache(@Field("json_msg") String json_msg);

    @Headers(CacheHeaders.MAX_AGE)
    @FormUrlEncoded
    @POST("http://preapi.youjuke.com/Designer/designer_interface")
    Observable<BaseEntity> getData1ToCache(@Field("json_msg") String json_msg);

}
