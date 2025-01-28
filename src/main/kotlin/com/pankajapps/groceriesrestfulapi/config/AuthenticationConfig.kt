package com.pankajapps.groceriesrestfulapi.config

import com.pankajapps.groceriesrestfulapi.user.repository.AppUserRepository
import io.jsonwebtoken.security.Password
import org.springframework.context.annotation.Bean
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

class AuthenticationConfig(private val repository: AppUserRepository) {

    @Bean
    fun getUsername() =
        UserDetailsService{ username ->
            repository.findByAppUsername(username) ?: throw UsernameNotFoundException("User not found")
        }

    @Bean
    fun getPasswordEncoder(): PasswordEncoder = BCryptPasswordEncoder()

    @Bean
    fun getAuthenticationProvider(): AuthenticationProvider {
        return DaoAuthenticationProvider().apply {
            this.setUserDetailsService(getUsername())
            this.setPasswordEncoder(getPasswordEncoder())
        }
    }

    @Bean
    fun getauthenticationManager(authConfig: AuthenticationConfiguration) = authConfig.authenticationManager
}