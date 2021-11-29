package com.example.authdata.data_source.user

import com.example.authdata.room_db.AppDatabase
import com.example.authdata.room_db.entity.UserEntity

class UserDataSourceImpl(private val appDatabase: AppDatabase) : UserDataSource {

    override suspend fun getUserByEmail(email: String): UserEntity =
        appDatabase.getUserDao().getUserByEmail(email)

    override suspend fun saveUser(user: UserEntity) {
        appDatabase.getUserDao().saveUser(user)
    }

    override suspend fun isUserExist(email: String): Boolean = appDatabase.getUserDao().isUserExist(email)

}