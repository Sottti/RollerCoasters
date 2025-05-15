package com.roller.coasters.presentation.design.system.images.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

public data class ImageState(
    @DrawableRes val resId: Int,
    @StringRes val descriptionResId: Int,
)