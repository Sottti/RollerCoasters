@file:Suppress("ConstPropertyName")

package co.cuvva.presentation.design.system.icons.data

import co.cuvva.presentation.design.system.icons.model.IconState
import co.sottti.roller.coasters.presentation.design.system.icons.R

public object Icons {
    public object Home {
        public val Filled: IconState = IconState(
            resId = R.drawable.ic_home_rounded_filled,
            descriptionResId = R.string.home_icon_description
        )
        public val Outlined: IconState = IconState(
            resId = R.drawable.ic_home_rounded_outlined,
            descriptionResId = R.string.home_icon_description
        )
    }

    public object Star {
        public val Filled: IconState = IconState(
            resId = R.drawable.ic_star_rounded_filled,
            descriptionResId = R.string.star_icon_description
        )
        public val Outlined: IconState = IconState(
            resId = R.drawable.ic_star_rounded_outlined,
            descriptionResId = R.string.star_icon_description
        )
    }

    public object AccountCircle {
        public val Filled: IconState = IconState(
            resId = R.drawable.ic_account_circle_rounded_filled,
            descriptionResId = R.string.account_circle_icon_description
        )
        public val Outlined: IconState = IconState(
            resId = R.drawable.ic_account_circle_rounded_outlined,
            descriptionResId = R.string.account_circle_icon_description
        )
    }

    public object Settings {
        public val Outlined: IconState = IconState(
            resId = R.drawable.ic_settings_rounded_outlined,
            descriptionResId = R.string.settings_icon_description
        )
    }

    public object ArrowBack {
        public val Rounded: IconState = IconState(
            resId = R.drawable.ic_arrow_back_rounded,
            descriptionResId = R.string.arrow_back_icon_description
        )
    }

    public object Palette {
        public val Outlined: IconState = IconState(
            resId = R.drawable.ic_palette_rounded_outline,
            descriptionResId = R.string.palette_icon_description
        )
    }

    public object Colors {
        public val Rounded: IconState = IconState(
            resId = R.drawable.ic_colors_rounded,
            descriptionResId = R.string.colors_icon_description
        )
    }
}