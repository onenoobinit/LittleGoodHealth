package com.mobile.android.entity;

/**
 * Created by wangqiang on 2019/1/25.
 */
public class LoginStautsInfo {
    /**
     * nickname :
     * loginType : 0
     * username :
     * headPortrait :
     * userType :
     */

    private String nickname;
    private String loginType;
    private String username;
    private String headPortrait;
    private String userType;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
