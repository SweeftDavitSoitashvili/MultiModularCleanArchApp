package com.example.multimoduleapp.navigation

import androidx.navigation.NavController
import com.example.multimoduleapp.R

class NavigationImpl(private val navController: NavController) :Navigation {
    override fun navigateToSignIn() {
        navController.navigate(R.id.signInFragment)
    }

    override fun navigateToSignUp() {
        navController.navigate(R.id.signUpFragment2)
    }

    override fun navigateToDashboard() {
        navController.navigate(R.id.dashboardFragment)
    }
}