package com.fpu.maksimtestapp.data

import com.fpu.maksimtestapp.data.mapper.toChallengeDomain
import com.fpu.maksimtestapp.data.network.CompletedApi
import com.fpu.maksimtestapp.domain.model.ChallengeDomain
import com.fpu.maksimtestapp.domain.repository.ChallengesRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class ChallengesRepositoryImpl @Inject constructor(
    private val completedApi: CompletedApi
) : ChallengesRepository {

    override suspend fun getCompletedChallenges(
        user: String,
        page: String
    ): List<ChallengeDomain> {
        return completedApi.getCompletedChallenges(
            user = user,
            page = page
        ).body()?.challenges?.map { it.toChallengeDomain() }.orEmpty()
    }
}