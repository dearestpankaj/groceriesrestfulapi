package com.habibicoding.groceriesrestfulapi.user.repository

import com.pankajapps.groceriesrestfulapi.user.entity.AppUser
import com.pankajapps.groceriesrestfulapi.user.repository.AppUserRepository
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager

@DataJpaTest
class AppUserRepositoryIT @Autowired constructor(
    val entityManager: TestEntityManager,
    val objectUnderTest: AppUserRepository
) {

    private val userEmail = "faris.diab@example.com"
    private val userName = "faris-diab"
    private val user = AppUser(
        firstName = "Faris",
        lastName = "Diab",
        email = userEmail,
        appUsername = userName,
        userPassword = "SecurePassword1234!",
        isVerified = true
    )

    @BeforeEach
    fun setup() {
        entityManager.persist(user)
    }

    @AfterEach
    fun tearDown() {
        entityManager.clear()
    }

    @Test
    fun `when new user gets persisted then check if email can be found`() {
        // assign
        // act
        val actualUser: AppUser? = objectUnderTest.findByEmail(userEmail)

        // assert
        assertNotNull(actualUser)
        assertEquals(userEmail, actualUser?.email)
    }
}