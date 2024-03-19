package com.fpu.maksimtestapp.domain.repository

import com.fpu.maksimtestapp.domain.model.ChallengeDomain

interface ChallengesRepository {

    suspend fun getCompletedChallenges(user: String, page: String) : List<ChallengeDomain>

}