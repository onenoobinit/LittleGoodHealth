package com.mobile.android.entity;

import java.io.Serializable;

/**
 * @author chenliangzhi
 * @date 2018/2/24
 * @describe 用户信息
 */

public class User implements Serializable {
    /**
     * token : 718D3E327ECD7958248F8B19A934670609D18960D600A771
     * msg : ok
     */

    private String token;
    private String msg;
    private Boolean isLogined;

    public Boolean isLogined() {
        if (isLogined == null) {
            return false;
        }
        return isLogined;
    }

    public void setLogined(Boolean logined) {
        isLogined = logined;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


    /* *//**
     * uid : 1582
     * realname : 夏天
     * token : EBsjdEVRtH-MzJqi9ScSr-VpTQUMDdQu-oUuV3NYNdG-1527492518
     *//*

    private String uid;
    private String realname;
//    private String token;
    private String account;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    *//*public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }*//*

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }*/
}
