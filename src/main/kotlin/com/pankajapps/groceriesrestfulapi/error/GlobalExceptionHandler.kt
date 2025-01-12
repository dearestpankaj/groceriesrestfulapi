package com.pankajapps.groceriesrestfulapi.error

import org.apache.logging.log4j.message.Message
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler: ResponseEntityExceptionHandler() {

    private fun buildResponseEntity(status: HttpStatus, message: String): ResponseEntity<ApiError> {
        val error = ApiError(message = message, status = status)
        return  ResponseEntity(error, status)
    }
}

class BadRequestException(message: String): RuntimeException(message)

class SignUpException(message: String): RuntimeException(message)

class JwtAuthenticationException(message: String, cause: Throwable? = null): RuntimeException(message, cause)

class UserNotFoundException(message: String): RuntimeException(message)

class PasswordMismatchException(message: String): RuntimeException(message)

class UsernamePasswordMismatchException(message: String): RuntimeException(message)

class AccountVerificationException(message: String): RuntimeException(message)

class TokenExpiredException(message: String): RuntimeException(message)

class JwtKeyException(message: String): IllegalStateException(message)

class ShoppingListNotFoundException(message: String): RuntimeException(message)

class ShoppingListItemNotFoundException(message: String): RuntimeException(message)

class SupermarketException(message: String): RuntimeException(message)
