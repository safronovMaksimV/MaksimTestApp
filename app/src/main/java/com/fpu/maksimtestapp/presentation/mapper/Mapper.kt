package com.fpu.maksimtestapp.presentation.mapper

import com.fpu.maksimtestapp.domain.model.ChallengeDetailsDomain
import com.fpu.maksimtestapp.domain.model.ChallengeDomain
import com.fpu.maksimtestapp.domain.model.CreatedApprovedBy
import com.fpu.maksimtestapp.domain.model.Rank
import com.fpu.maksimtestapp.presentation.model.UIChallenge
import com.fpu.maksimtestapp.presentation.model.UIChallengeDetails
import com.fpu.maksimtestapp.presentation.model.UICreatedApprovedBy
import com.fpu.maksimtestapp.presentation.model.UIRank
import java.time.format.DateTimeFormatter

fun ChallengeDomain.toUIChallenge() = UIChallenge(
    id = id,
    name = name,
    completedLanguages = completedLanguages,
    completedAt = completedAt.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm"))
)

fun ChallengeDetailsDomain.toUIChallengeDetails() = UIChallengeDetails(
    id = id,
    name = name,
    category = category,
    description = description,
    tags = tags,
    languages = languages,
    rank = rank.toUIRank(),
    createdBy = createdBy.toUICreatedApprovedBy(),
    approvedBy = approvedBy.toUICreatedApprovedBy(),
    totalAttempts = totalAttempts,
    totalCompleted = totalCompleted,
    totalStars = totalStars,
    voteScore = voteScore,
    publishedAt = publishedAt,
    approvedAt = approvedAt
)

fun Rank.toUIRank() = UIRank(
    name = name,
    color = color
)

fun CreatedApprovedBy.toUICreatedApprovedBy() = UICreatedApprovedBy(
    username = username,
    url = url
)