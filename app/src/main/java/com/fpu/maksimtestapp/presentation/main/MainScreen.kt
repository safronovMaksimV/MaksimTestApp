package com.fpu.maksimtestapp.presentation.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.fpu.maksimtestapp.presentation.navigation.MainNavHost
import com.fpu.maksimtestapp.ui.theme.neutral100

@Composable
fun MainScreen(
    mainScreenState: MainScreenState
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(neutral100)
                .padding(padding)
        ) {
            MainNavHost(mainScreenState = mainScreenState)
        }
    }
}

