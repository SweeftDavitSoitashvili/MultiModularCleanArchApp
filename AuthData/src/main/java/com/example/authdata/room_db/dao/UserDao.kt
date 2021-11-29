package com.example.authdata.room_db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.authdata.room_db.entity.UserEntity

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveUser(user : UserEntity)

    @Query("select * from userentity where email == :email")
    suspend fun getUserByEmail(email : String) : UserEntity

    @Query("SELECT EXISTS(SELECT * FROM userentity WHERE email = :email)")
    suspend fun isUserExist(email : String) : Boolean

}