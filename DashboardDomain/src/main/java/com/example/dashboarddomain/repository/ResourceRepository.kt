package com.example.dashboarddomain.repository

import com.example.dashboarddomain.models.Resource

interface ResourceRepository {
    suspend fun getResources() : List<Resource>
}