package com.fpu.maksimtestapp.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Immutable
data class Typography(
    val bodyLarge: TextStyle,
    val bodyMedium: TextStyle,
    val bodySmall: TextStyle,
    val labelLarge: TextStyle,
    val labelMedium: TextStyle,
    val labelSmall: TextStyle,
    val titleLarge: TextStyle,
    val titleMedium: TextStyle,
    val titleSmall: TextStyle,
    val headlineLarge: TextStyle,
    val headlineMedium: TextStyle,
    val headlineSmall: TextStyle,
    val navTapLabel: TextStyle,
)

val navTapLabel = TextStyle(
    fontSize = 10.sp,
    lineHeight = 16.sp,
    fontFamily = sfProFamily,
    fontWeight = FontWeight.W400,
)

val bodyLarge = TextStyle(
    color = black,
    fontSize = 17.sp,
    lineHeight = 22.sp,
    fontFamily = sfProFamily,
    fontWeight = FontWeight.W400,
)

val bodyMedium = TextStyle(
    color = black,
    fontSize = 13.sp,
    lineHeight = 16.sp,
    fontFamily = sfProFamily,
    fontWeight = FontWeight.W400,
)

val bodySmall = TextStyle(
    color = black,
    fontSize = 12.sp,
    lineHeight = 16.sp,
    fontFamily = sfProFamily,
    fontWeight = FontWeight.W400,
)

val labelLarge = TextStyle(
    color = black,
    fontSize = 15.sp,
    lineHeight = 20.sp,
    fontFamily = sfProFamily,
    fontWeight = FontWeight.W600,
)

val labelMedium = TextStyle(
    color = black,
    fontSize = 13.sp,
    lineHeight = 18.sp,
    fontFamily = sfProFamily,
    fontWeight = FontWeight.W600,
)

val labelSmall = TextStyle(
    color = black,
    fontSize = 12.sp,
    lineHeight = 16.sp,
    fontFamily = sfProFamily,
    fontWeight = FontWeight.W500,
)

val titleLarge = TextStyle(
    color = black,
    fontSize = 20.sp,
    lineHeight = 25.sp,
    fontFamily = sfProFamily,
    fontWeight = FontWeight.W600,
)

val titleMedium = TextStyle(
    color = black,
    fontSize = 16.sp,
    lineHeight = 20.sp,
    fontFamily = sfProFamily,
    fontWeight = FontWeight.W600,
)
val titleSmall = TextStyle(
    color = black,
    fontSize = 15.sp,
    lineHeight = 20.sp,
    fontFamily = sfProFamily,
    fontWeight = FontWeight.W400,
)

val headlineLarge = TextStyle(
    color = black,
    fontSize = 34.sp,
    lineHeight = 41.sp,
    fontFamily = sfProFamily,
    fontWeight = FontWeight.W700,
)

val headlineMedium = TextStyle(
    color = black,
    fontSize = 28.sp,
    lineHeight = 34.sp,
    fontFamily = sfProFamily,
    fontWeight = FontWeight.W700,
)

val headlineSmall = TextStyle(
    color = black,
    fontSize = 17.sp,
    lineHeight = 22.sp,
    fontFamily = sfProFamily,
    fontWeight = FontWeight.W600,
)

val typography = Typography(
    bodyLarge = bodyLarge,
    bodyMedium = bodyMedium,
    bodySmall = bodySmall,
    labelLarge = labelLarge,
    labelMedium = labelMedium,
    labelSmall = labelSmall,
    titleLarge = titleLarge,
    titleMedium = titleMedium,
    titleSmall = titleSmall,
    headlineLarge = headlineLarge,
    headlineMedium = headlineMedium,
    headlineSmall = headlineSmall,
    navTapLabel = navTapLabel
)