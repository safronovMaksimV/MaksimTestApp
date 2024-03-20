package com.fpu.maksimtestapp.domain.model

data class Rank(
    val id: Long,
    val name: String,
    val color: String
)

data class CreatedApprovedBy(
    val username: String,
    val url: String
)
