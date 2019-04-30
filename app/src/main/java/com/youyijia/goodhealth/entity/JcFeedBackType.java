package com.youyijia.goodhealth.entity;

import java.util.List;

/**
 * @author chenliangzhi
 * @date 2018/5/29
 * @describe
 */

public class JcFeedBackType {

    private List<FkTypeBean> fk_type;

    public List<FkTypeBean> getFk_type() {
        return fk_type;
    }

    public void setFk_type(List<FkTypeBean> fk_type) {
        this.fk_type = fk_type;
    }

    public static class FkTypeBean {
        /**
         * id : 1
         * type : 量房
         */

        private int id;
        private String type;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
