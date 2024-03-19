package com.fpu.maksimtestapp.presentation.features.challenge_details

import com.fpu.maksimtestapp.base.UiEffect
import com.fpu.maksimtestapp.base.UiEvent
import com.fpu.maksimtestapp.base.UiState

class ChallengeDetailsScreenContract {

    data class State(
        val isLoading: Boolean,
    ) : UiState {

        companion object {
            fun initial() = State(
                isLoading = false,
            )
        }
    }

    sealed interface Event : UiEvent {
        data class FetchChallengeInfoById(val id: String) : Event
    }

    sealed interface Effect : UiEffect

}
