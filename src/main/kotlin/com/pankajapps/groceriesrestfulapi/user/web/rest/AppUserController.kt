package com.pankajapps.groceriesrestfulapi.user.web.rest

import com.pankajapps.groceriesrestfulapi.api.UserResource
import com.pankajapps.groceriesrestfulapi.dto.UserInfoResponse
import com.pankajapps.groceriesrestfulapi.dto.UserInfoUpdateRequest
import com.pankajapps.groceriesrestfulapi.dto.UserPasswordUpdateRequest
import com.pankajapps.groceriesrestfulapi.user.service.AppUserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class AppUserController(private val service: AppUserService): UserResource {
    override fun changeEmail(requestBody: Map<String, String>): ResponseEntity<UserInfoResponse> = ResponseEntity.ok(service.changeEmail(requestBody))

    override fun changeInfo(userInfoUpdateRequest: UserInfoUpdateRequest): ResponseEntity<UserInfoResponse> =
        ResponseEntity.ok(service.changeInfo(userInfoUpdateRequest))

    override fun changePassword(
        userPasswordUpdateRequest: UserPasswordUpdateRequest) = ResponseEntity.ok(service.changePassword(userPasswordUpdateRequest))

    override fun fetchInfo(): ResponseEntity<UserInfoResponse> = ResponseEntity.ok(service.fetchInfo())

}