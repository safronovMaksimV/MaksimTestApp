package com.fpu.maksimtestapp.data.network

import com.fpu.maksimtestapp.data.network.model.response.ChallengeDetailsResponse
import com.fpu.maksimtestapp.data.network.model.response.ChallengeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ChallengesApi {

    @GET("users/{user}/code-challenges/completed")
    suspend fun getCompletedChallenges(
        @Path("user") user: String,
        @Query("page") page: String
    ): Response<ChallengeResponse>

    @GET("code-challenges/{challenge}")
    suspend fun getChallengeDetails(
        @Path("challenge") challengeId: String,
    ): Response<ChallengeDetailsResponse>

}