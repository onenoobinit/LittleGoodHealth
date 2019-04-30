package com.youyijia.goodhealth.updatebyrx2;

/**
 * 描述: 事件
 * --------------------------------------------
 * 工程:
 * #0000     mwy     创建日期: 2017-07-06 14:24
 */

public class RxEvent {
    private int code;
    private Object object;
    public RxEvent(int code, Object object){
        this.code=code;
        this.object=object;
    }
    public RxEvent(){}

    public int getCode() {
        return code;
    }

    public Object getObject() {
        return object;
    }
}
