package com.changyu.celo.service.subscriber

import timber.log.Timber

abstract class DefaultHttpResultSubscriber<T> : HttpResultSubscriber<T>() {
    override fun _onError(status: Int, msg: String?) {
        
    }

    override fun onCompleted() {
        Timber.d(" --> onCompleted")
    }
}
