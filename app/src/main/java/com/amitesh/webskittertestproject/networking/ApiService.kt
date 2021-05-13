package com.amitesh.webskittertestproject.networking

import com.amitesh.webskittertestproject.data.models.Album
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("/photos")
    suspend fun getProducts(): Response<Album>
}