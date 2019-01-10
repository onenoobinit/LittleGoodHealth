package com.mobile.hyoukalibrary.base;

import java.io.Serializable;

/**
 * 描述: 基础的实体类
 * --------------------------------------------
 * 工程:
 * #0000     mwy     创建日期: 2017-06-06 14:14
 */

public class BaseEntity implements Serializable {

    private String generalErrMsg;
    private String errMsg;
    private String success;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getGeneralErrMsg() {
        return generalErrMsg;
    }

    public void setGeneralErrMsg(String generalErrMsg) {
        this.generalErrMsg = generalErrMsg;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
                "generalErrMsg='" + generalErrMsg + '\'' +
                ", errMsg='" + errMsg + '\'' +
                ", success='" + success + '\'' +
                '}';
    }
}
