package com.fpu.maksimtestapp.presentation.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.fpu.maksimtestapp.R

enum class TopLevelDestination(
    @DrawableRes val icon: Int,
    @StringRes val titleId: Int,
) {
    HOME(
        icon = R.drawable.ic_home,
        titleId = R.string.home_tab_title,
    ),
}
