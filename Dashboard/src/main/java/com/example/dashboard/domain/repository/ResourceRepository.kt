package com.example.dashboard.domain.repository

import com.example.dashboard.domain.models.Resource

interface ResourceRepository {
    suspend fun getResources() : List<Resource>
}