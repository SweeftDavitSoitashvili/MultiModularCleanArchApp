package com.example.auth.data.data_source.user

import com.example.auth.data.data_source.user.UserDataSource
import com.example.authdata.room_db.AppDatabase
import com.example.authdata.room_db.entity.UserEntity

class UserDataSourceImpl(private val appDatabase: AppDatabase) : UserDataSource {

    override suspend fun getUserByEmail(email: String): UserEntity =
        appDatabase.getUserDao().getUserByEmail(email)

    override suspend fun saveUser(user: UserEntity) {
        appDatabase.getUserDao().saveUser(user)
    }

    override suspend fun isUserEmailExist(email: String): Boolean = appDatabase.getUserDao().isUserEmailExist(email)

    override suspend fun isUserExist(email: String, password: String) = appDatabase.getUserDao().isUserExist(email, password)

}