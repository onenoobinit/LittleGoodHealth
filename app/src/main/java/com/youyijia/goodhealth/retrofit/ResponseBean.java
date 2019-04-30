package com.youyijia.goodhealth.retrofit;


/**
 * 描述: 响应实体类
 * --------------------------------------------
 * 工程:
 * #0000     mwy     创建日期: 2016-09-12 15:52
 */
public class ResponseBean {

    private String status;
    private String error;
    private String message;
    private String data;

    @Override
    public String toString() {
        return "ResponseBean{" +
                "error='" + error + '\'' +
                ", status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", data='" + data + '\'' +
                '}';
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
