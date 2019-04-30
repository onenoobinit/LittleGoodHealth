package com.youyijia.goodhealth.observer

import com.tencent.mm.opensdk.modelbase.BaseResp
import java.util.*

/**
 * Created by wangqiang on 2019/4/25.
 */
class WXLoginObserver : Observable() {

    companion object {
        val INSTANCE = WXLoginObserver()
    }


    fun resultCode(res: BaseResp) {
        setChanged()
        notifyObservers(res)
    }

}
