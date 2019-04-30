package com.youyijia.goodhealth.entity;

import java.util.List;

/**
 * Created by wangqiang on 2019/4/29.
 */
public class ShopdeletePost {
    private PostInfoBean postInfoBean;

    public PostInfoBean getPostInfoBean() {
        return postInfoBean;
    }

    public void setPostInfoBean(PostInfoBean postInfoBean) {
        this.postInfoBean = postInfoBean;
    }

    public class PostInfoBean {
        public List<Integer> getIds() {
            return ids;
        }

        public void setIds(List<Integer> ids) {
            this.ids = ids;
        }

        private List<Integer> ids;
    }
}
