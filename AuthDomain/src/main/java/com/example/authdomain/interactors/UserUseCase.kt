package com.example.authdomain.interactors

import com.example.authdomain.models.User
import com.example.authdomain.repository.UserRepository

class UserUseCase(private val userRepository: UserRepository) {
    suspend fun getUserByEmail(email : String) = userRepository.getUserByEmail(email)

    suspend fun isUserExists(email: String) = userRepository.isUserExist(email)

    suspend fun saveUser(user : User) = userRepository.saveUser(user)

}