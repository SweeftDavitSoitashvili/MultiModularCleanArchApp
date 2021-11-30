package com.example.dashboard.ui.vm

import androidx.lifecycle.ViewModel
import com.example.dashboarddomain.interactors.ResourceUseCase
import com.example.dashboarddomain.repository.ResourceRepository
import org.koin.java.KoinJavaComponent.inject

class DashboardVm : ViewModel() {
    private val resourceRepository by inject<ResourceRepository>(ResourceRepository::class.java)
    private val resourceUseCase by lazy {
        ResourceUseCase(resourceRepository)
    }

    suspend fun getResource() = resourceUseCase.getResource()
}