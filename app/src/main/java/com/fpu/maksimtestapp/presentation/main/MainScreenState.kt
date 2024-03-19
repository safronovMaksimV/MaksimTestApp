package com.fpu.maksimtestapp.presentation.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.fpu.maksimtestapp.presentation.navigation.TopLevelDestination

@Stable
class MainScreenState(
    val navController: NavHostController
) {

    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val topLevelDestinations: List<TopLevelDestination> = TopLevelDestination.entries
}

@Composable
fun rememberMainScreenState(
    navController: NavHostController = rememberNavController(),
): MainScreenState {
    return remember(
        navController
    ) {
        MainScreenState(
            navController = navController
        )
    }
}