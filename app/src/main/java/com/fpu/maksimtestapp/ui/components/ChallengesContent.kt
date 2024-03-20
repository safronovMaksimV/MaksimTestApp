package com.fpu.maksimtestapp.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fpu.maksimtestapp.presentation.features.home.CHALLENGE_SHIMMER_REPEAT
import com.fpu.maksimtestapp.presentation.features.home.CheckInsListChallengeLoadingState
import com.fpu.maksimtestapp.presentation.features.home.ShimmerAnimation

@Composable
fun ChallengesLoadingContent(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .padding(horizontal = 10.dp)
            .navigationBarsPadding()
            .imePadding()
            .fillMaxSize(),
    ) {
        ShimmerAnimation { brush ->
            Spacer(modifier = Modifier.height(20.dp))
            repeat(CHALLENGE_SHIMMER_REPEAT * 6) {
                CheckInsListChallengeLoadingState(brush)
                Spacer(Modifier.height(30.dp))
            }
        }
    }
}