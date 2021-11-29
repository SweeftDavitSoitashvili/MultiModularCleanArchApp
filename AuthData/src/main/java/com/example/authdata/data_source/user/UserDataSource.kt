package com.example.authdata.data_source.user

import com.example.authdata.room_db.entity.UserEntity

interface UserDataSource {
    suspend fun getUserByEmail(email: String) : UserEntity
    suspend fun saveUser(user: UserEntity)
    suspend fun isUserExist(email: String) : Boolean
}