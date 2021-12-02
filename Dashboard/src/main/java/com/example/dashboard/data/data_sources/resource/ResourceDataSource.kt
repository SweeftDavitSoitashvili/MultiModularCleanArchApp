package com.example.dashboard.data.data_sources.resource

import com.example.dashboard.data.models.Response

interface ResourceDataSource {
    suspend fun getResource() : Response
}