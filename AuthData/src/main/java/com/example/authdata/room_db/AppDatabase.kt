package com.example.authdata.room_db

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.authdata.room_db.dao.UserDao
import com.example.authdata.room_db.entity.UserEntity

import androidx.room.Room

@Database(entities = [UserEntity::class], exportSchema = false, version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getUserDao(): UserDao

    companion object {
        private const val DB_NAME = "appdatabase.db"

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DB_NAME
                )
                    .build()

                INSTANCE = instance

                instance
            }
        }
    }
}