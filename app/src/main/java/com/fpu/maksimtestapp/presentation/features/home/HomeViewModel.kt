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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCompletedChallengesUseCase: GetCompletedChallengesUseCase,
) : BaseViewModel<HomeScreenContract.State, HomeScreenContract.Event, HomeScreenContract.Effect>() {

    private val mutableRefreshState = MutableStateFlow(true)
    val refreshState = mutableRefreshState.asStateFlow()

    override fun createInitialState() = HomeScreenContract.State.initial()

    override fun handleEvent(event: HomeScreenContract.Event) {
        when (event) {
            is HomeScreenContract.Event.RefreshScreen -> refreshScreen()
            is HomeScreenContract.Event.SetRefreshing -> setRefreshing(event.isRefreshing)
        }
    }

    init {
        initScreen()
    }

    private fun initScreen() {
        setRefreshing(true)
        setState { copy(isLoading = true) }
        val pager = Pager(PagingConfig(pageSize = MifareUltralight.PAGE_SIZE)) {
            CompletedChallengesDataSource(
                "Voile",
                getCompletedChallengesUseCase
            )
        }
        val challenges = pager.flow.cachedIn(viewModelScope)

        setState { copy(challengeList = challenges, isLoading = false) }
        setRefreshing(false)
    }

    private fun refreshScreen() {
        initScreen()
    }

    private fun setRefreshing(refresh: Boolean) {
        launch {
            mutableRefreshState.emit(refresh)
        }
    }
}
