package com.fpu.maksimtestapp.data.mapper

import com.fpu.maksimtestapp.data.network.model.response.Challenge
import com.fpu.maksimtestapp.domain.model.ChallengeDomain
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun Challenge.toChallengeDomain() = ChallengeDomain(
    id = id.orEmpty(),
    name = name.orEmpty(),
    slug = slug.orEmpty(),
    completedLanguages = completedLanguages ?: emptyList(),
    completedAt = LocalDateTime.parse(completedAt, DateTimeFormatter.ISO_DATE_TIME)
)
