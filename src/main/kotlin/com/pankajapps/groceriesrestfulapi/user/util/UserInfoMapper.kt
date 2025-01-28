package com.pankajapps.groceriesrestfulapi.user.util

import com.pankajapps.groceriesrestfulapi.dto.UserInfoResponse
import com.pankajapps.groceriesrestfulapi.user.entity.AppUser
import jakarta.persistence.Entity
import org.springframework.stereotype.Component

@Component
class UserInfoMapper {
    fun toDto(entity: AppUser) = UserInfoResponse(
        firstName = entity.firstName,
        lastName = entity.lastName,
        email = entity.email,
        username = entity.appUsername
    )
}