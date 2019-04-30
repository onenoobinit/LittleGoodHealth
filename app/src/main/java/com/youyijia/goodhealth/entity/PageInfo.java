package com.youyijia.goodhealth.entity;

/**
 * Created by wangqiang on 2019/4/12.
 */
public class PageInfo {
    /**
     * offset : 0
     * limit : 10
     * totalCount : 10
     * totalPage : 1
     * pageNum : 1
     */

    private int offset;
    private int limit;
    private int totalCount;
    private int totalPage;
    private int pageNum;

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }
}
