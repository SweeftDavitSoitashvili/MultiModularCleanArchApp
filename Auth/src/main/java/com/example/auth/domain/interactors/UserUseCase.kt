package com.example.auth.domain.interactors

import com.example.authdomain.models.User
import com.example.authdomain.repository.UserRepository

class UserUseCase(private val userRepository: UserRepository) {
    suspend fun getUserByEmail(email : String) = userRepository.getUserByEmail(email)

    suspend fun isUserEmailExists(email: String) = userRepository.isUserEmailExist(email)

    suspend fun isUserExist(email : String, password : String) = userRepository.isUserExist(email,password)

    suspend fun saveUser(user : User) = userRepository.saveUser(user)

}