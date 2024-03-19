package com.fpu.maksimtestapp.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

@Immutable
data class Colors(
    val primary: Color,
    val secondary: Color,
)

val black = Color.Black
val transparent = Color.Transparent
val green00AC4F = Color(0xFF00AC4F)


val white = Color.White
val white20 = Color(0x33FFFFFF)

val blue500 = Color(0xFF1460F3)

val neutral100 = Color(0xFFF0F1F3)
val neutral200 = Color(0xFFE7E9EB)
val neutral400 = Color(0xFFB3B6BE)
val neutral500 = Color(0xFF7E8493)
val neutral800 = Color(0xFF2B2C39)
val neutral900 = Color(0xFF21222E)

val androidx.compose.material.Colors.shimmer1: Color
    @Composable get() = if (isLight) neutral100 else neutral800

val androidx.compose.material.Colors.shimmer2: Color
    @Composable get() = if (isLight) neutral200 else neutral500

val androidx.compose.material.Colors.cardBorderColor: Color
    @Composable get() = if (isLight) neutral100 else Color.Transparent

val lightColors = Colors(
    primary = black,
    secondary = white,
)