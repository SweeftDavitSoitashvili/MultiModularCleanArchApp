package com.example.authdomain.repository

import com.example.authdomain.models.User

interface UserRepository {
    suspend fun getUserByEmail(email: String) : User
    suspend fun saveUser(user: User)
    suspend fun isUserEmailExist(email: String) : Boolean
    suspend fun isUserExist(email: String, password: String) :Boolean
}