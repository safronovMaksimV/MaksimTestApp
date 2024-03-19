package com.fpu.maksimtestapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.fpu.maksimtestapp.presentation.features.challenge_details.navigation.challengeDetailsScreen
import com.fpu.maksimtestapp.presentation.features.challenge_details.navigation.navigateToChallengeDetailsScreen
import com.fpu.maksimtestapp.presentation.features.home.navigation.HOME_ROUTE
import com.fpu.maksimtestapp.presentation.features.home.navigation.homeScreen
import com.fpu.maksimtestapp.presentation.main.MainScreenState

@Composable
fun MainNavHost(
    mainScreenState: MainScreenState,
    modifier: Modifier = Modifier,
    startDestination: String = HOME_ROUTE,
) {
    val navController = mainScreenState.navController
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        homeScreen(
            onNavigateToChallengeDetails = navController::navigateToChallengeDetailsScreen
        )
        challengeDetailsScreen(
            onBackClick = navController::popBackStack
        )
    }
}
