package com.amitesh.webskittertestproject.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.amitesh.webskittertestproject.data.entities.User

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}