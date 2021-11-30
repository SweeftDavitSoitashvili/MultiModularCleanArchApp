package com.example.dashboarddata.remote_service

import com.example.dashboarddata.models.Response
import retrofit2.http.GET

interface RemoteApiService {
    @GET("unknown")
    suspend fun getResources() : Response
}