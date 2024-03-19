package com.fpu.maksimtestapp.presentation.mapper

import com.fpu.maksimtestapp.domain.model.ChallengeDomain
import com.fpu.maksimtestapp.presentation.model.UIChallenge
import java.time.format.DateTimeFormatter

fun ChallengeDomain.toUIChallenge() = UIChallenge(
    id = id,
    name = name,
    completedLanguages = completedLanguages,
    completedAt = completedAt.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm"))
)