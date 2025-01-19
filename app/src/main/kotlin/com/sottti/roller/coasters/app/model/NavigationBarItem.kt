package com.sottti.roller.coasters.app.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import com.sottti.roller.coasters.app.navigation.NavigationBarDestination

@Immutable
internal data class NavigationBarItem(
    @DrawableRes val iconResId: Int,
    @StringRes val iconDescriptionResId: Int,
    @StringRes val labelResId: Int,
    val destination: NavigationBarDestination,
)