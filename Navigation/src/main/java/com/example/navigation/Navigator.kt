package com.example.navigation

import androidx.navigation.NavController

class Navigator {
    var navController: NavController? = null

    fun navigateToFlow(navigationFlow: NavigationFlow) = when (navigationFlow) {
        NavigationFlow.AuthFlow -> navController?.navigate(R.id.auth_flow)
        NavigationFlow.DashboardFlow -> navController?.navigate(R.id.dashboard_flow)
    }
}