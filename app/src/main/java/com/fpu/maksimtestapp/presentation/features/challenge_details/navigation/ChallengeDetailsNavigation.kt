package com.fpu.maksimtestapp.presentation.features.challenge_details.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.fpu.maksimtestapp.presentation.features.challenge_details.ChallengeDetailsRoute

const val CHALLENGE_ID_ARG = "challengeId"
const val CHALLENGE_DETAILS_BASE_ROUTE = "challenge_details_route"
const val CHALLENGE_DETAILS_ROUTE = "${CHALLENGE_DETAILS_BASE_ROUTE}/{$CHALLENGE_ID_ARG}"

fun NavController.navigateToChallengeDetailsScreen(
    challengeId: String,
    navOptions: NavOptions? = null
) {
    val route = "${CHALLENGE_DETAILS_BASE_ROUTE}/$challengeId"
    navigate(route, navOptions)
}

fun NavGraphBuilder.challengeDetailsScreen(
    onBackClick: () -> Unit,
) {
    composable(
        route = CHALLENGE_DETAILS_ROUTE,
        arguments = listOf(
            navArgument(CHALLENGE_ID_ARG) {
                defaultValue = ""
                nullable = true
                type = NavType.StringType
            }
        )
    ) {
        ChallengeDetailsRoute(
            onBackClick,
            challengeId = it.arguments?.getString(CHALLENGE_ID_ARG) ?: ""
        )
    }
}
