package com.youyijia.goodhealth.entity;

import java.util.List;

/**
 * Created by wangqiang on 2019/1/10.
 */
public class MeInfo {

    /**
     * accDetail : {"accPeriodExpiration":{"expirationDate":"--","expirationDetail":[],"expirationVotes":"0"},"accPeriodWarn":{"warnDate":"--","warnDetail":[],"warnPercentage":"0","warnVotes":"0"},"allQuota":"500000.00","overQuota":"157961.31"}
     * userDetail : {"address":"+989+6","birthday":"2014-09-02","couponCount":"0","email":"6*******6@qq.com","exportContractDate":"已过期","headPortrait":"10.png","importContractDate":"已过期","integral":"24","mobile":"139*****450","nickName":"测试账号wqwq","personalInfoPerfection":"100","phone":"6543","qq":"男男女女t","sex":"保密","userName":"wqwq","weChat":"3254"}
     */

    private AccDetailBean accDetail;
    private UserDetailBean userDetail;

    public AccDetailBean getAccDetail() {
        return accDetail;
    }

    public void setAccDetail(AccDetailBean accDetail) {
        this.accDetail = accDetail;
    }

    public UserDetailBean getUserDetail() {
        return userDetail;
    }

    public void setUserDetail(UserDetailBean userDetail) {
        this.userDetail = userDetail;
    }

    public static class AccDetailBean {
        /**
         * accPeriodExpiration : {"expirationDate":"--","expirationDetail":[],"expirationVotes":"0"}
         * accPeriodWarn : {"warnDate":"--","warnDetail":[],"warnPercentage":"0","warnVotes":"0"}
         * allQuota : 500000.00
         * overQuota : 157961.31
         */

        private AccPeriodExpirationBean accPeriodExpiration;
        private AccPeriodWarnBean accPeriodWarn;
        private String allQuota;
        private String overQuota;

        public AccPeriodExpirationBean getAccPeriodExpiration() {
            return accPeriodExpiration;
        }

        public void setAccPeriodExpiration(AccPeriodExpirationBean accPeriodExpiration) {
            this.accPeriodExpiration = accPeriodExpiration;
        }

        public AccPeriodWarnBean getAccPeriodWarn() {
            return accPeriodWarn;
        }

        public void setAccPeriodWarn(AccPeriodWarnBean accPeriodWarn) {
            this.accPeriodWarn = accPeriodWarn;
        }

        public String getAllQuota() {
            return allQuota;
        }

        public void setAllQuota(String allQuota) {
            this.allQuota = allQuota;
        }

        public String getOverQuota() {
            return overQuota;
        }

        public void setOverQuota(String overQuota) {
            this.overQuota = overQuota;
        }

        public static class AccPeriodExpirationBean {
            /**
             * expirationDate : --
             * expirationDetail : []
             * expirationVotes : 0
             */

            private String expirationDate;
            private String expirationVotes;
            private List<?> expirationDetail;

            public String getExpirationDate() {
                return expirationDate;
            }

            public void setExpirationDate(String expirationDate) {
                this.expirationDate = expirationDate;
            }

            public String getExpirationVotes() {
                return expirationVotes;
            }

            public void setExpirationVotes(String expirationVotes) {
                this.expirationVotes = expirationVotes;
            }

            public List<?> getExpirationDetail() {
                return expirationDetail;
            }

            public void setExpirationDetail(List<?> expirationDetail) {
                this.expirationDetail = expirationDetail;
            }
        }

        public static class AccPeriodWarnBean {
            /**
             * warnDate : --
             * warnDetail : []
             * warnPercentage : 0
             * warnVotes : 0
             */

            private String warnDate;
            private String warnPercentage;
            private String warnVotes;
            private List<?> warnDetail;

            public String getWarnDate() {
                return warnDate;
            }

            public void setWarnDate(String warnDate) {
                this.warnDate = warnDate;
            }

            public String getWarnPercentage() {
                return warnPercentage;
            }

            public void setWarnPercentage(String warnPercentage) {
                this.warnPercentage = warnPercentage;
            }

            public String getWarnVotes() {
                return warnVotes;
            }

            public void setWarnVotes(String warnVotes) {
                this.warnVotes = warnVotes;
            }

            public List<?> getWarnDetail() {
                return warnDetail;
            }

            public void setWarnDetail(List<?> warnDetail) {
                this.warnDetail = warnDetail;
            }
        }
    }

    public static class UserDetailBean {
        /**
         * address : +989+6
         * birthday : 2014-09-02
         * couponCount : 0
         * email : 6*******6@qq.com
         * exportContractDate : 已过期
         * headPortrait : 10.png
         * importContractDate : 已过期
         * integral : 24
         * mobile : 139*****450
         * nickName : 测试账号wqwq
         * personalInfoPerfection : 100
         * phone : 6543
         * qq : 男男女女t
         * sex : 保密
         * userName : wqwq
         * weChat : 3254
         */

        private String address;
        private String birthday;
        private String couponCount;
        private String email;
        private String exportContractDate;
        private String headPortrait;
        private String importContractDate;
        private String integral;
        private String mobile;
        private String nickName;
        private String personalInfoPerfection;
        private String phone;
        private String qq;
        private String sex;
        private String userName;
        private String weChat;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getCouponCount() {
            return couponCount;
        }

        public void setCouponCount(String couponCount) {
            this.couponCount = couponCount;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getExportContractDate() {
            return exportContractDate;
        }

        public void setExportContractDate(String exportContractDate) {
            this.exportContractDate = exportContractDate;
        }

        public String getHeadPortrait() {
            return headPortrait;
        }

        public void setHeadPortrait(String headPortrait) {
            this.headPortrait = headPortrait;
        }

        public String getImportContractDate() {
            return importContractDate;
        }

        public void setImportContractDate(String importContractDate) {
            this.importContractDate = importContractDate;
        }

        public String getIntegral() {
            return integral;
        }

        public void setIntegral(String integral) {
            this.integral = integral;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getPersonalInfoPerfection() {
            return personalInfoPerfection;
        }

        public void setPersonalInfoPerfection(String personalInfoPerfection) {
            this.personalInfoPerfection = personalInfoPerfection;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getQq() {
            return qq;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getWeChat() {
            return weChat;
        }

        public void setWeChat(String weChat) {
            this.weChat = weChat;
        }
    }
}
