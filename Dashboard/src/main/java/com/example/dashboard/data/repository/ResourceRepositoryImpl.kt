package com.example.dashboard.data.repository

import com.example.dashboard.data.data_sources.resource.ResourceDataSource
import com.example.dashboard.data.mappers.resource.ResourceMapper
import com.example.dashboard.domain.models.Resource
import com.example.dashboarddomain.repository.ResourceRepository

class ResourceRepositoryImpl(
    private val resourceDataSource: ResourceDataSource,
    private val resourceMapper: ResourceMapper

) : ResourceRepository {
    override suspend fun getResources(): List<Resource> {
        return resourceMapper.mapToResourceList(resources = resourceDataSource.getResource().resources)
    }
}