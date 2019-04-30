package com.youyijia.goodhealth.entity;

import java.util.List;

/**
 * @author chenliangzhi
 * @date 2018/5/29
 * @describe
 */

public class SmsSendCodeBean {

    /**
     * name : chenhuijing-187
     * node : [{"message_id":"1","title":"业主未接电话"},{"message_id":"2","title":"陪签时间变动"},{"message_id":"3","title":"量房后给业主发个短信"}]
     */

    private String name;
    private List<NodeBean> node;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<NodeBean> getNode() {
        return node;
    }

    public void setNode(List<NodeBean> node) {
        this.node = node;
    }

    public static class NodeBean {
        /**
         * message_id : 1
         * title : 业主未接电话
         */

        private String message_id;
        private String title;

        public String getMessage_id() {
            return message_id;
        }

        public void setMessage_id(String message_id) {
            this.message_id = message_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
