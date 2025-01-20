@file:Suppress("ConstPropertyName")

package co.cuvva.presentation.design.system.icons.data

import co.cuvva.presentation.design.system.icons.model.IconState
import co.sottti.roller.coasters.presentation.design.system.icons.R

object Icons {
    object Home {
        val Filled = IconState(
            resId = R.drawable.ic_home_rounded_filled,
            descriptionResId = R.string.home_icon_description
        )
        val Outlined = IconState(
            resId = R.drawable.ic_home_rounded_outlined,
            descriptionResId = R.string.home_icon_description
        )
    }

    object Star {
        val Filled = IconState(
            resId = R.drawable.ic_star_rounded_filled,
            descriptionResId = R.string.star_icon_description
        )
        val Outlined = IconState(
            resId = R.drawable.ic_star_rounded_outlined,
            descriptionResId = R.string.star_icon_description
        )
    }

    object AccountCircle {
        val Filled = IconState(
            resId = R.drawable.ic_account_circle_rounded_filled,
            descriptionResId = R.string.account_circle_icon_description
        )
        val Outlined = IconState(
            resId = R.drawable.ic_account_circle_rounded_outlined,
            descriptionResId = R.string.account_circle_icon_description
        )
    }

    object Settings {
        val Outlined = IconState(
            resId = R.drawable.ic_settings_rounded_outlined,
            descriptionResId = R.string.settings_icon_description
        )
    }

    object ArrowBack {
        val Rounded = IconState(
            resId = R.drawable.ic_arrow_back_rounded,
            descriptionResId = R.string.arrow_back_icon_description
        )
    }

    object Palette {
        val Outlined = IconState(
            resId = R.drawable.ic_palette_rounded_outline,
            descriptionResId = R.string.palette_icon_description
        )
    }

    object Check {
        val Rounded = IconState(
            resId = R.drawable.ic_check_rounded,
            descriptionResId = R.string.check_icon_description
        )
    }
}