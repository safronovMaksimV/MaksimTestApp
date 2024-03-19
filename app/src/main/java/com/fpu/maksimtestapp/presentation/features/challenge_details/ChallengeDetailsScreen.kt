package com.fpu.maksimtestapp.presentation.features.challenge_details

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun ChallengeDetailsRoute(
    onBackClick: () -> Unit,
    challengeId: String,
    viewModel: ChallengeDetailsViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

}

