package com.fpu.maksimtestapp.presentation.features.home

import android.nfc.tech.MifareUltralight
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.fpu.maksimtestapp.base.BaseViewModel
import com.fpu.maksimtestapp.data.network.CompletedChallengesDataSource
import com.fpu.maksimtestapp.domain.usecase.GetCompletedChallengesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCompletedChallengesUseCase: GetCompletedChallengesUseCase,
) : BaseViewModel<HomeScreenContract.State, HomeScreenContract.Event, HomeScreenContract.Effect>() {

    override fun createInitialState() = HomeScreenContract.State.initial()

    override fun handleEvent(event: HomeScreenContract.Event) {
        when (event) {
            is HomeScreenContract.Event.RefreshScreen -> refreshScreen()
            is HomeScreenContract.Event.SetRefreshing -> setState {
                copy(isLoading = event.isRefreshing)
            }
        }
    }

    init {
        initScreen()
    }

    private fun initScreen() {
        setState { copy(isLoading = true) }
        val pager = Pager(PagingConfig(pageSize = MifareUltralight.PAGE_SIZE)) {
            CompletedChallengesDataSource(
                "Voile",
                getCompletedChallengesUseCase
            )
        }
        val challenges = pager.flow.cachedIn(viewModelScope)

        setState { copy(challengeList = challenges, isLoading = false) }
    }

    private fun refreshScreen() {
        initScreen()
    }
}
