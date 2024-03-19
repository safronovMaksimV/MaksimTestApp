package com.fpu.maksimtestapp.presentation.features.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.fpu.maksimtestapp.presentation.features.home.HomeRoute

const val HOME_ROUTE = "home_route"

fun NavController.navigateToHomeScreen(navOptions: NavOptions) = navigate(HOME_ROUTE, navOptions)

fun NavGraphBuilder.homeScreen(
    onNavigateToChallengeDetails: (String) -> Unit
) {
    composable(route = HOME_ROUTE) {
        HomeRoute(
            onNavigateToChallengeDetails = onNavigateToChallengeDetails
        )
    }
}
