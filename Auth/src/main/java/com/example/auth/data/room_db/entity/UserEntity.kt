package com.example.authdata.room_db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val email : String,
    val password : String
)