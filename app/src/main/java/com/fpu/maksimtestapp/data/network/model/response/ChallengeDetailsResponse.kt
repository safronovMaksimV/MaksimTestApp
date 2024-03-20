package com.fpu.maksimtestapp.data.network.model.response

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class ChallengeDetailsResponse(
    @SerializedName("id")
    val id: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("slug")
    val slug: String?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("category")
    val category: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("tags")
    val tags: List<String>?,
    @SerializedName("languages")
    val languages: List<String>?,
    @SerializedName("rank")
    val rank: Rank?,
    @SerializedName("createdBy")
    val createdBy: CreatedApprovedBy?,
    @SerializedName("approvedBy")
    val approvedBy: CreatedApprovedBy?,
    @SerializedName("totalAttempts")
    val totalAttempts: Long?,
    @SerializedName("totalCompleted")
    val totalCompleted: Long?,
    @SerializedName("totalStars")
    val totalStars: Long?,
    @SerializedName("voteScore")
    val voteScore: Long?,
    @SerializedName("publishedAt")
    val publishedAt: String?,
    @SerializedName("approvedAt")
    val approvedAt: String?,
)

@Keep
data class Rank(
    @SerializedName("id")
    val id: Long?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("color")
    val color: String?
)

@Keep
data class CreatedApprovedBy(
    @SerializedName("username")
    val username: String?,
    @SerializedName("url")
    val url: String?
)