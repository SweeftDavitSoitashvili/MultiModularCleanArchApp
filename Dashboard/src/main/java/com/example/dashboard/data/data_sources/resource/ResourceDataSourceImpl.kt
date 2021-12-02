package com.example.dashboard.data.data_sources.resource

import com.example.dashboard.data.remote_service.RemoteApiService

class ResourceDataSourceImpl(
    private val resourceApiService: RemoteApiService
) : ResourceDataSource {
    override suspend fun getResource() = resourceApiService.getResources()
}