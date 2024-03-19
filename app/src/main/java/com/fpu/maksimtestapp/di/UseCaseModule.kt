package com.fpu.maksimtestapp.di

import com.fpu.maksimtestapp.domain.repository.ChallengesRepository
import com.fpu.maksimtestapp.domain.usecase.GetCompletedChallengesUseCase
import com.fpu.maksimtestapp.domain.usecase.GetCompletedChallengesUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class UseCaseModule {
    @Provides
    fun provideGetCompletedChallengesUseCase(repository: ChallengesRepository): GetCompletedChallengesUseCase =
        GetCompletedChallengesUseCaseImpl(repository)
}