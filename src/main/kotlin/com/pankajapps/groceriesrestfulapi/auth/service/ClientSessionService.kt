package com.pankajapps.groceriesrestfulapi.auth.service

import com.pankajapps.groceriesrestfulapi.user.entity.AppUser
import org.springframework.security.core.Authentication


interface ClientSessionService {
    fun retrieveAuthentication(): Authentication?

    fun findCurrentSessionUser(): AppUser

    fun  getAuthenticationUser(): AppUser
}