package com.fpu.maksimtestapp.domain.usecase

import com.fpu.maksimtestapp.domain.model.ChallengeDomain
import com.fpu.maksimtestapp.domain.repository.ChallengesRepository
import javax.inject.Inject

interface GetCompletedChallengesUseCase {
    suspend fun getCompletedChallenges(
        user: String,
        page: String
    ): List<ChallengeDomain>
}

class GetCompletedChallengesUseCaseImpl @Inject constructor(
    private val challengesRepository: ChallengesRepository
) : GetCompletedChallengesUseCase {

    override suspend fun getCompletedChallenges(
        user: String,
        page: String
    ) = challengesRepository.getCompletedChallenges(user, page)
}