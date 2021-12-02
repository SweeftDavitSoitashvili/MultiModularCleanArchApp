package com.example.dashboard.data.remote_service

import com.example.dashboarddata.models.Response
import retrofit2.http.GET

interface RemoteApiService {
    @GET(".")
    suspend fun getResources() : Response
}