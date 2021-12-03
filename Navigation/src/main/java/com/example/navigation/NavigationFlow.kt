package com.example.navigation

sealed class NavigationFlow {
    object AuthFlow : NavigationFlow()
    object DashboardFlow : NavigationFlow()
}