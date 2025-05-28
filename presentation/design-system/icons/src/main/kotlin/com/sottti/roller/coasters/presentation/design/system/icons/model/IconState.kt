package com.sottti.roller.coasters.presentation.design.system.icons.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable

@Immutable
public data class IconState(
    @DrawableRes val resId: Int,
    @StringRes val descriptionResId: Int,
)