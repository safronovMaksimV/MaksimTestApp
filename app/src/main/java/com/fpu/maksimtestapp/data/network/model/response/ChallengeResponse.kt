package com.fpu.maksimtestapp.data.network.model.response

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class ChallengeResponse(
    @SerializedName("totalPages")
    val totalPages: Long?,
    @SerializedName("totalItems")
    val totalItems: Long?,
    @SerializedName("data")
    val commonChallenges: List<CommonChallenge>?
)

@Keep
data class CommonChallenge(
    @SerializedName("id")
    val id: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("slug")
    val slug: String?,
    @SerializedName("completedLanguages")
    val completedLanguages: List<String>?,
    @SerializedName("completedAt")
    val completedAt: String?,
)
