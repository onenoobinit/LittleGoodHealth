package com.mobile.android.entity;

import java.util.List;

/**
 * Created by wangqiang on 2019/1/14.
 */
public class StartInfo {
    private List<StartPortBean> startPort;

    public List<StartPortBean> getStartPort() {
        return startPort;
    }

    public void setStartPort(List<StartPortBean> startPort) {
        this.startPort = startPort;
    }

    public static class StartPortBean {
        /**
         * nameC : 郑州市
         * port : CGO
         */

        private String nameC;
        private String port;

        public String getNameC() {
            return nameC;
        }

        public void setNameC(String nameC) {
            this.nameC = nameC;
        }

        public String getPort() {
            return port;
        }

        public void setPort(String port) {
            this.port = port;
        }
    }
}
