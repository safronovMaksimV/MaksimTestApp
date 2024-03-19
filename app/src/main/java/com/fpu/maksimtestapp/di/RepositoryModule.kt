package com.fpu.maksimtestapp.di

import com.fpu.maksimtestapp.data.ChallengesRepositoryImpl
import com.fpu.maksimtestapp.data.network.CompletedApi
import com.fpu.maksimtestapp.domain.repository.ChallengesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {
    @Provides
    fun provideChallengesRepository(completedApi: CompletedApi): ChallengesRepository =
        ChallengesRepositoryImpl(completedApi)
}