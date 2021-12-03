package com.example.dashboard.domain.interactors

import com.example.dashboard.domain.repository.ResourceRepository


class ResourceUseCase(
    private val resourceRepository: ResourceRepository
) {
    suspend fun getResource() = resourceRepository.getResources()
}