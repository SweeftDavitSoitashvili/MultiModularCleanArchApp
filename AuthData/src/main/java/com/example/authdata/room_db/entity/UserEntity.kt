package com.example.authdata.room_db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity(
    @PrimaryKey
    val id : Int,
    val email : String,
    val password : String
)