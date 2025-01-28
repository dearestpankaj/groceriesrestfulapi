package com.pankajapps.groceriesrestfulapi.user.service

import com.pankajapps.groceriesrestfulapi.dto.UserInfoResponse
import com.pankajapps.groceriesrestfulapi.dto.UserInfoUpdateRequest
import com.pankajapps.groceriesrestfulapi.dto.UserPasswordUpdateRequest

interface AppUserService {
    fun  changeEmail(request: Map<String, String>): UserInfoResponse
    fun changePassword(request: UserPasswordUpdateRequest)
    fun changeInfo(request: UserInfoUpdateRequest): UserInfoResponse
    fun fetchInfo(): UserInfoResponse
}