package com.mobile.hyoukalibrary.base;

import java.io.Serializable;

/**
 * 描述: 基础的实体类
 * --------------------------------------------
 * 工程:
 * #0000     mwy     创建日期: 2017-06-06 14:14
 */

public class BaseEntity<T> implements Serializable{
    private Class<T> type;
    private T data;
    private int status;
    private int error_code;
    private String message;
    public BaseEntity() {
        this.type = (Class<T>) getClass();
    }

    public void setType(Class<T> type) {
        this.type = type;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public int getError() {
        return error_code;
    }
    public void setError(int error) {
        this.error_code = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Class<T> getType() {
        return type;
    }
}
