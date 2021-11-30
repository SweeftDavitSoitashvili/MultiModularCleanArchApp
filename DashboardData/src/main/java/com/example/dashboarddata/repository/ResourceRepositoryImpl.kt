package com.example.dashboarddata.repository

import android.util.Log.d
import com.example.dashboarddata.data_sources.resource.ResourceDataSource
import com.example.dashboarddata.mappers.resource.ResourceMapper
import com.example.dashboarddomain.models.Resource
import com.example.dashboarddomain.repository.ResourceRepository

class ResourceRepositoryImpl(
    private val resourceDataSource: ResourceDataSource,
    private val resourceMapper: ResourceMapper

) : ResourceRepository {
    override suspend fun getResources(): List<Resource> {
        return resourceMapper.mapToResourceList(resources = resourceDataSource.getResource().resources)
    }
}