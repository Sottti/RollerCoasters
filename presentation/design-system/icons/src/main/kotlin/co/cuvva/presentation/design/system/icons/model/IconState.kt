package co.cuvva.presentation.design.system.icons.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class IconState(
    @DrawableRes val resId: Int,
    @StringRes val descriptionResId: Int,
)