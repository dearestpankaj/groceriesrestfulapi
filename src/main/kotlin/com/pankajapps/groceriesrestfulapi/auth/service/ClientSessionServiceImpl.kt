package com.pankajapps.groceriesrestfulapi.auth.service

import com.pankajapps.groceriesrestfulapi.user.entity.AppUser
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service

@Service
class ClientSessionServiceImpl : ClientSessionService {
    override fun retrieveAuthentication(): Authentication? {
        TODO("Not yet implemented")
    }

    override fun findCurrentSessionUser(): AppUser {
        TODO("Not yet implemented")
    }

    override fun getAuthenticationUser(): AppUser {
        TODO("Not yet implemented")
    }
}