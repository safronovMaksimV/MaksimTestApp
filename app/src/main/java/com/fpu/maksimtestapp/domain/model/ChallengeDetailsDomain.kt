package com.fpu.maksimtestapp.domain.model

import java.time.LocalDateTime

data class ChallengeDetailsDomain(
    val id: String,
    val name: String,
    val slug: String,
    val category: String,
    val description: String,
    val tags: List<String>,
    val languages: List<String>,
    val rank: Rank,
    val createdBy: CreatedApprovedBy,
    val approvedBy: CreatedApprovedBy,
    val totalAttempts: Long,
    val totalCompleted: Long,
    val totalStars: Long,
    val voteScore: Long,
    val publishedAt: LocalDateTime,
    val approvedAt: LocalDateTime,
)