package com.amitesh.webskittertestproject.data.db

import com.amitesh.webskittertestproject.data.entities.User

class DatabaseHelperImpl(private val appDatabase: AppDatabase) : DatabaseHelper {

    override suspend fun getUsers(): List<User> = appDatabase.userDao().getAll()

    override suspend fun insertAll(users: List<User>) = appDatabase.userDao().insertAll(users)

    override suspend fun insert(user: User) = appDatabase.userDao().insert(user)

    override suspend fun update(user: User) = appDatabase.userDao().update(user)

    override suspend fun delete(user: User) = appDatabase.userDao().delete(user)

}