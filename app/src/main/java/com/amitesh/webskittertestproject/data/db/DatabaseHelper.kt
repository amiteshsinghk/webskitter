package com.amitesh.webskittertestproject.data.db

import com.amitesh.webskittertestproject.data.entities.User

interface DatabaseHelper {

    suspend fun getUsers(): List<User>

    suspend fun insertAll(users: List<User>)

    suspend fun insert(user: User)

    suspend fun update(user: User)

    suspend fun delete(user: User)

}