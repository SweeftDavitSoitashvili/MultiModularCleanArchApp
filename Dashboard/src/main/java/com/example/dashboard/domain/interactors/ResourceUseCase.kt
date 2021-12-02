package com.example.dashboarddomain.interactors

import com.example.dashboarddomain.repository.ResourceRepository

class ResourceUseCase(
    private val resourceRepository: ResourceRepository
) {
    suspend fun getResource() = resourceRepository.getResources()
}