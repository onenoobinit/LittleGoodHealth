package com.youyijia.goodhealth.entity;

import java.util.List;

/**
 * Created by wangqiang on 2019/4/28.
 */
public class ShopCartPricePost {
    private PostInfoBean postInfoBean;

    public PostInfoBean getPostInfoBean() {
        return postInfoBean;
    }

    public void setPostInfoBean(PostInfoBean postInfoBean) {
        this.postInfoBean = postInfoBean;
    }

    public class PostInfoBean {
        public List<Integer> getItemIds() {
            return itemIds;
        }

        public void setItemIds(List<Integer> itemIds) {
            this.itemIds = itemIds;
        }

        private List<Integer> itemIds;

       /* public class ItemIdsBean {

        }
*/
    }
}
