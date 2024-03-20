package com.fpu.maksimtestapp.presentation.features.home

import androidx.paging.PagingData
import com.fpu.maksimtestapp.base.UiEffect
import com.fpu.maksimtestapp.base.UiEvent
import com.fpu.maksimtestapp.base.UiState
import com.fpu.maksimtestapp.presentation.model.UIChallenge
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class HomeScreenContract {

    data class State(
        val isLoading: Boolean,
        val challengeList: Flow<PagingData<UIChallenge>>
    ) : UiState {
        companion object {
            fun initial() = State(
                isLoading = false,
                challengeList = flowOf(),
            )
        }
    }

    sealed interface Event : UiEvent {
        data object RefreshScreen : Event

        data class SetRefreshing(val isRefreshing: Boolean) : Event
    }

    sealed interface Effect : UiEffect

}
