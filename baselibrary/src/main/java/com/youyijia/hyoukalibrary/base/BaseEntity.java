package com.youyijia.hyoukalibrary.base;

import java.io.Serializable;

/**
 * 描述: 基础的实体类
 * --------------------------------------------
 * 工程:
 * #0000     wq     创建日期: 2017-06-06 14:14
 */

public class BaseEntity implements Serializable {

    private String message;
    private String codeText;
    private String code;
    private String success;
    private String data;
    private String pageInfo;

    public String getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(String pageInfo) {
        this.pageInfo = pageInfo;
    }


    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCodeText() {
        return codeText;
    }

    public void setCodeText(String codeText) {
        this.codeText = codeText;
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
                "success='" + success + '\'' +
                ",message='" + message + '\'' +
                ",data='" + data + '\'' +
                ", codeText='" + codeText + '\'' +
                ", code='" + code + '\'' +
                ", pageInfo='" + pageInfo + '\'' +
                '}';
    }
}
