package com.example.dashboarddata.data_sources.resource

import com.example.dashboarddata.models.Response

interface ResourceDataSource {
    suspend fun getResource() : Response
}