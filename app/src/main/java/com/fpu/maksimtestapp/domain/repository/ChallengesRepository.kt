package com.fpu.maksimtestapp.domain.repository

import com.fpu.maksimtestapp.domain.model.ChallengeDetailsDomain
import com.fpu.maksimtestapp.domain.model.ChallengeDomain
import kotlinx.coroutines.flow.Flow

interface ChallengesRepository {

    suspend fun getCompletedChallenges(user: String, page: String) : List<ChallengeDomain>

    suspend fun getChallengeDetailsById(id: String) : Flow<ChallengeDetailsDomain>

}