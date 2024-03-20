package com.fpu.maksimtestapp.presentation.features.challenge_details

import com.fpu.maksimtestapp.base.UiEffect
import com.fpu.maksimtestapp.base.UiEvent
import com.fpu.maksimtestapp.base.UiState
import com.fpu.maksimtestapp.presentation.model.UIChallengeDetails

class ChallengeDetailsScreenContract {

    data class State(
        val isLoading: Boolean,
        val challenge: UIChallengeDetails?,
    ) : UiState {

        companion object {
            fun initial() = State(
                isLoading = false,
                challenge = null
            )
        }
    }

    sealed interface Event : UiEvent {
        data class FetchChallengeInfoById(val id: String) : Event

        data class SetRefreshing(val isRefreshing: Boolean) : Event
    }

    sealed interface Effect : UiEffect

}
