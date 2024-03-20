package com.fpu.maksimtestapp.data.mapper

import com.fpu.maksimtestapp.data.network.model.response.ChallengeDetailsResponse
import com.fpu.maksimtestapp.data.network.model.response.CommonChallenge
import com.fpu.maksimtestapp.data.network.model.response.CreatedApprovedBy
import com.fpu.maksimtestapp.data.network.model.response.Rank
import com.fpu.maksimtestapp.domain.model.ChallengeDetailsDomain
import com.fpu.maksimtestapp.domain.model.ChallengeDomain
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

fun CommonChallenge.toChallengeDomain() = ChallengeDomain(
    id = id.orEmpty(),
    name = name.orEmpty(),
    slug = slug.orEmpty(),
    completedLanguages = completedLanguages ?: emptyList(),
    completedAt = LocalDateTime.parse(completedAt, DateTimeFormatter.ISO_DATE_TIME)
)

fun ChallengeDetailsResponse.toChallengeDomain() = ChallengeDetailsDomain(
    id = id.orEmpty(),
    name = name.orEmpty(),
    slug = slug.orEmpty(),
    category = category.orEmpty(),
    description = description.orEmpty(),
    tags = tags.orEmpty(),
    languages = languages.orEmpty(),
    rank = rank?.toDomainRank() ?: com.fpu.maksimtestapp.domain.model.Rank(0L, "", ""),
    createdBy = createdBy?.toCreatedApprovedByDomain()
        ?: com.fpu.maksimtestapp.domain.model.CreatedApprovedBy("", ""),
    approvedBy = approvedBy?.toCreatedApprovedByDomain()
        ?: com.fpu.maksimtestapp.domain.model.CreatedApprovedBy("", ""),
    totalAttempts = totalAttempts ?: 0L,
    totalCompleted = totalCompleted ?: 0L,
    totalStars = totalStars ?: 0L,
    voteScore = voteScore ?: 0L,
    publishedAt = publishedAt?.let {
        ZonedDateTime.parse(it, DateTimeFormatter.ISO_INSTANT.withZone(
            ZoneId.systemDefault())).toLocalDateTime()
    } ?: LocalDateTime.now(),
    approvedAt = approvedAt?.let {
        ZonedDateTime.parse(it, DateTimeFormatter.ISO_INSTANT.withZone(
            ZoneId.systemDefault())).toLocalDateTime()
    } ?: LocalDateTime.now()
)

fun Rank.toDomainRank() = com.fpu.maksimtestapp.domain.model.Rank(
    id = id ?: 0L,
    name = name.orEmpty(),
    color = color.orEmpty()
)

fun CreatedApprovedBy.toCreatedApprovedByDomain() =
    com.fpu.maksimtestapp.domain.model.CreatedApprovedBy(
        username = username.orEmpty(),
        url = url.orEmpty()
    )
