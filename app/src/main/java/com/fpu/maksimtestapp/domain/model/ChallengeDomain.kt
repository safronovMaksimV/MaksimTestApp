package com.fpu.maksimtestapp.domain.model

import java.time.LocalDateTime

data class ChallengeDomain(
    val id: String,
    val name: String,
    val slug: String,
    val completedLanguages: List<String>,
    val completedAt: LocalDateTime,
)