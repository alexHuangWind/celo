package com.changyu.celo.service.entity

class HttpResultEntity<T> : BaseEntity<T>() {
    val isSuccess: Boolean
        get() = errorCode == 0
}
