package com.example.dashboard.data.remote_service

import com.example.dashboard.data.models.Response
import retrofit2.http.GET

interface RemoteApiService {
    @GET(".")
    suspend fun getResources() : Response
}