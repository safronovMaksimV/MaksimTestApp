package com.fpu.maksimtestapp.domain.usecase

import com.fpu.maksimtestapp.domain.model.ChallengeDetailsDomain
import com.fpu.maksimtestapp.domain.repository.ChallengesRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

interface GetChallengeDetailsUseCase {
    suspend operator fun invoke(id: String): Flow<ChallengeDetailsDomain>
}

class GetChallengeDetailsUseCaseImpl @Inject constructor(
    private val cardsRepository: ChallengesRepository
) : GetChallengeDetailsUseCase {
    override suspend fun invoke(id: String): Flow<ChallengeDetailsDomain> {
        return cardsRepository.getChallengeDetailsById(id)
    }

}
