package com.changyu.celo.userList.service

import com.changyu.celo.userList.model.bean.UserInfoItem
import retrofit2.http.GET
import rx.Observable

/**
 * ClassName:   UserInfoService
 *
 * Author:      changyu
 * Date:        2019/12/14
 */
interface UserInfoService {
    @GET("/api/1e597e438ec60fea62ea22c5034954e9")
    fun getUserInfo(): Observable<UserInfoItem>

}