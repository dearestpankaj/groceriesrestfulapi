package com.pankajapps.groceriesrestfulapi.user.repository

import com.pankajapps.groceriesrestfulapi.user.entity.AppUser
import jakarta.validation.constraints.Email
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface AppUserRepository: JpaRepository<AppUser, Long> {
    @Query("select u from AppUser where u.email = ?1")
    fun findByEmail(email: String): AppUser?

    @Query("select u from AppUser where u.username = ?1")
    fun  findByAppUsername(username: String): AppUser?
}