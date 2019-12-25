package com.changyu.celo.userList.model

import com.changyu.celo.userList.service.UserInfoService
import com.changyu.celo.service.ServiceFactory
import com.changyu.celo.userList.model.bean.UserInfoItem

import rx.Observable

private const val endPoint = "https://randomapi.com"

fun fetchUserInfo(): Observable<UserInfoItem> {
    return ServiceFactory
            .createRxRetrofitService(UserInfoService::class.java, endPoint)
            .getUserInfo()
}

