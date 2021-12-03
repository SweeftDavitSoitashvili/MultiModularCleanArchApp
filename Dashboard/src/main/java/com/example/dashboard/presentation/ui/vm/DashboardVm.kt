package com.example.dashboard.presentation.ui.vm

import androidx.lifecycle.ViewModel
import com.example.dashboard.domain.interactors.ResourceUseCase

class DashboardVm(
    private val resourceUseCase : ResourceUseCase
) : ViewModel() {
    suspend fun getResource() = resourceUseCase.getResource()
}