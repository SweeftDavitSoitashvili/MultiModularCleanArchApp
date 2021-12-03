package com.example.dashboard.data.mappers.resource

import com.example.dashboard.data.models.ResourceDto
import com.example.dashboard.domain.models.Resource


class ResourceMapper {
    fun mapToResource(resource : ResourceDto) : Resource {
        with(resource) {
            return Resource(
                id = id,
                name = name,
                color = color,
                pantoneValue = pantoneValue,
                year = year
            )
        }
    }

    fun mapToResourceList(resources : List<ResourceDto>) : List<Resource>{
        val res = mutableListOf<Resource>()
        resources.forEach {
            res.add(mapToResource(it))
        }
        return res.toList()
    }
}