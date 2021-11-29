package com.example.authdata.mappers

import com.example.authdata.room_db.entity.UserEntity
import com.example.authdomain.models.User

class UserMapper {
    fun mapToUserEntity(user : User) = UserEntity(user.id, user.email, user.password)

    fun mapToUser(userEntity: UserEntity) = User(userEntity.id, userEntity.email, userEntity.password)
}