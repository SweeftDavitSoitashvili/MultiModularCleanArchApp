package com.example.dashboarddata.data_sources.resource

import com.example.dashboarddata.remote_service.RemoteApiService

class ResourceDataSourceImpl(
    private val resourceApiService: RemoteApiService
) : ResourceDataSource {
    override suspend fun getResource() = resourceApiService.getResources()
}