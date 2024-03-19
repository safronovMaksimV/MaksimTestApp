package com.fpu.maksimtestapp.presentation.model

data class UIChallenge(
    val id: String,
    val name: String,
    val completedLanguages: List<String>,
    val completedAt: String,
)