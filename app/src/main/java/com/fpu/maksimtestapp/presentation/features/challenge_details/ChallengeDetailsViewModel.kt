package com.fpu.maksimtestapp.presentation.features.challenge_details

import com.fpu.maksimtestapp.base.BaseViewModel
import com.fpu.maksimtestapp.domain.usecase.GetChallengeDetailsUseCase
import com.fpu.maksimtestapp.presentation.mapper.toUIChallengeDetails
import com.fpu.maksimtestapp.utils.subscribe
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class ChallengeDetailsViewModel @Inject constructor(
    private val getChallengeDetailsUseCase: GetChallengeDetailsUseCase
) : BaseViewModel<ChallengeDetailsScreenContract.State, ChallengeDetailsScreenContract.Event, ChallengeDetailsScreenContract.Effect>() {

    override fun createInitialState() = ChallengeDetailsScreenContract.State.initial()

    override fun handleEvent(event: ChallengeDetailsScreenContract.Event) {
        when (event) {
            is ChallengeDetailsScreenContract.Event.FetchChallengeInfoById -> fetchChallengeInfoById(
                event.id
            )

            is ChallengeDetailsScreenContract.Event.SetRefreshing -> setState {
                copy(isLoading = event.isRefreshing)
            }
        }
    }

    private fun fetchChallengeInfoById(id: String) {
        launch {
            setState { copy(isLoading = true) }
            getChallengeDetailsUseCase(id).subscribe(
                this,
                success = { challenge ->
                    setState {
                        copy(
                            challenge = challenge.toUIChallengeDetails(),
                        )
                    }
                },
                error = {
                    it.printStackTrace()
                },
                complete = {
                    setState { copy(isLoading = false) }
                }
            )
        }
    }
}
