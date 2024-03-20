package com.fpu.maksimtestapp.presentation.model

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter




data class UIChallengeDetails(
    val id: String,
    val name: String,
    val category: String,
    val description: String,
    val tags: List<String>,
    val languages: List<String>,
    val rank: UIRank,
    val createdBy: UICreatedApprovedBy,
    val approvedBy: UICreatedApprovedBy,
    val totalAttempts: Long,
    val totalCompleted: Long,
    val totalStars: Long,
    val voteScore: Long,
    val publishedAt: LocalDateTime,
    val approvedAt: LocalDateTime,
) {
    fun getRankString() = "Rank name: ${rank.name} \nRank color: ${rank.color}"
    fun getCreatedByString() =
        "Created username: ${createdBy.username} \nCreated url: ${createdBy.url}"

    fun getApprovedByString() =
        "Approved username: ${approvedBy.username} \nApproved url: ${approvedBy.url}"

    fun getStringDateTime(time: LocalDateTime): String? {
        val customFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

        return time.format(customFormatter)
    }
}

data class UIRank(
    val name: String,
    val color: String
)

data class UICreatedApprovedBy(
    val username: String,
    val url: String
)