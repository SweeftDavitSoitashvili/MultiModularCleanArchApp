package com.example.dashboard.data.mappers.resource

import com.example.dashboarddomain.models.Resource

class ResourceMapper {
    fun mapToResource(resource : Resource) : com.example.dashboarddomain.models.Resource {
        with(resource) {
            return com.example.dashboarddomain.models.Resource(
                id = id,
                name = name,
                color = color,
                pantoneValue = pantoneValue,
                year = year
            )
        }
    }

    fun mapToResourceList(resources : List<Resource>) : List<com.example.dashboarddomain.models.Resource>{
        val res = mutableListOf<com.example.dashboarddomain.models.Resource>()
        resources.forEach {
            res.add(mapToResource(it))
        }
        return res.toList()
    }
}