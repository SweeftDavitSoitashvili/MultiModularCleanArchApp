package com.example.dashboarddomain.repository

import com.example.dashboard.domain.models.Resource

interface ResourceRepository {
    suspend fun getResources() : List<Resource>
}