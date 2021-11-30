package com.example.dashboard.ui.vm

import androidx.lifecycle.ViewModel
import com.example.dashboarddomain.interactors.ResourceUseCase
import com.example.dashboarddomain.repository.ResourceRepository
import org.koin.java.KoinJavaComponent.inject

class DashboardVm(
    private val resourceUseCase : ResourceUseCase
) : ViewModel() {
    suspend fun getResource() = resourceUseCase.getResource()
}