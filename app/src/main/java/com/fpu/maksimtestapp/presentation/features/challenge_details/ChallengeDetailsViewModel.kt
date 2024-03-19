package com.fpu.maksimtestapp.presentation.features.challenge_details

import com.fpu.maksimtestapp.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class ChallengeDetailsViewModel @Inject constructor(
) : BaseViewModel<ChallengeDetailsScreenContract.State, ChallengeDetailsScreenContract.Event, ChallengeDetailsScreenContract.Effect>() {

    override fun createInitialState() = ChallengeDetailsScreenContract.State.initial()

    override fun handleEvent(event: ChallengeDetailsScreenContract.Event) {
        when (event) {
            is ChallengeDetailsScreenContract.Event.FetchChallengeInfoById -> fetchChallengeInfoById(
                event.id
            )
        }
    }

    private fun fetchChallengeInfoById(id: String) {
        launch {
            setState { copy(isLoading = true) }
        }
    }
}
