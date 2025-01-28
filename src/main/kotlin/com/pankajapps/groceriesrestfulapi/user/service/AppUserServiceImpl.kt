package com.pankajapps.groceriesrestfulapi.user.service

import com.pankajapps.groceriesrestfulapi.auth.service.ClientSessionService
import com.pankajapps.groceriesrestfulapi.dto.UserInfoResponse
import com.pankajapps.groceriesrestfulapi.dto.UserInfoUpdateRequest
import com.pankajapps.groceriesrestfulapi.dto.UserPasswordUpdateRequest
import com.pankajapps.groceriesrestfulapi.error.BadRequestException
import com.pankajapps.groceriesrestfulapi.error.PasswordMismatchException
import com.pankajapps.groceriesrestfulapi.user.entity.AppUser
import com.pankajapps.groceriesrestfulapi.user.repository.AppUserRepository
import com.pankajapps.groceriesrestfulapi.user.util.UserInfoMapper
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AppUserServiceImpl(
    private  val passwordEncoder: PasswordEncoder,
    private  val repository: AppUserRepository,
    private val mapper: UserInfoMapper,
    private val service: ClientSessionService
) : AppUserService {


    override fun changeEmail(request: Map<String, String>): UserInfoResponse {
        val currentUser: AppUser = service.findCurrentSessionUser()
        val newEmail = request["email"] ?: throw BadRequestException("The new email is missing in the request")
        validateEmail(newEmail, currentUser.id)

        currentUser.email = newEmail
        val updatedUser = repository.save(currentUser)

        return  mapper.toDto(updatedUser)
    }

    private fun validateEmail(email: String, currentUserId: Long) {
        val emailRegex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\\\.[A-Z]{2,6}\$"
        if (!email.matches(emailRegex.toRegex())) {
            throw BadRequestException("Invalid Email Request")
        }
        repository.findByEmail(email)?.let { user ->
            if (user.id != currentUserId) {
                throw BadRequestException("Email is already used by another user")
            }
        }
    }

    override fun changePassword(request: UserPasswordUpdateRequest) {
        val user: AppUser = service.findCurrentSessionUser()

        if (!passwordEncoder.matches(request.currentPassword, user.password)) {
            throw PasswordMismatchException("the current password is wrong!")
        }
        if (request.newPassword != request.newPasswordConfirmation) {
            throw PasswordMismatchException("Your new password does not match with the password confirmation!")
        }

        user.userPassword = passwordEncoder.encode(request.newPassword)
        repository.save(user)
    }

    override fun changeInfo(request: UserInfoUpdateRequest): UserInfoResponse {
        val user: AppUser = service.findCurrentSessionUser()
        user.apply {
            this.firstName = request.firstName ?: firstName
            this.lastName = request.lastName ?: lastName
        }
        val savedUser: AppUser = repository.save(user)
        return mapper.toDto(savedUser)
    }

    override fun fetchInfo(): UserInfoResponse {
        val user: AppUser = service.findCurrentSessionUser()
        return mapper.toDto(user)
    }
}