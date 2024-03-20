package com.fpu.maksimtestapp.data

import com.fpu.maksimtestapp.data.mapper.toChallengeDomain
import com.fpu.maksimtestapp.data.network.ChallengesApi
import com.fpu.maksimtestapp.domain.model.ChallengeDomain
import com.fpu.maksimtestapp.domain.repository.ChallengesRepository
import com.fpu.maksimtestapp.utils.NetworkException
import javax.inject.Inject
import kotlinx.coroutines.flow.flow

class ChallengesRepositoryImpl @Inject constructor(
    private val challengesApi: ChallengesApi
) : ChallengesRepository {

    override suspend fun getCompletedChallenges(
        user: String,
        page: String
    ): List<ChallengeDomain> {
        val response = challengesApi.getCompletedChallenges(
            user = user,
            page = page
        )
        return if (response.isSuccessful) {
            response.body()?.commonChallenges?.map { it.toChallengeDomain() }.orEmpty()
        } else {
            throw NetworkException(response.code())
        }
    }

    override suspend fun getChallengeDetailsById(id: String) = flow {
        val response = challengesApi.getChallengeDetails(
            challengeId = id,
        )
        if (response.isSuccessful) {
            emit(response.body()?.toChallengeDomain() ?: throw NetworkException(404))
        } else {
            throw NetworkException(response.code())
        }
    }
}