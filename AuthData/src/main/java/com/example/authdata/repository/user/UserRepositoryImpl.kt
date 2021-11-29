package com.example.authdata.repository.user

import com.example.authdata.data_source.user.UserDataSource
import com.example.authdata.mappers.UserMapper
import com.example.authdomain.models.User
import com.example.authdomain.repository.UserRepository

class UserRepositoryImpl(
    private val userDataSource: UserDataSource,
    private val userMapper: UserMapper
) : UserRepository {
    override suspend fun getUserByEmail(email: String) =
        userMapper.mapToUser(userDataSource.getUserByEmail(email))


    override suspend fun saveUser(user: User) {
        userDataSource.saveUser(userMapper.mapToUserEntity(user))
    }

    override suspend fun isUserExist(email: String) = userDataSource.isUserExist(email)

}