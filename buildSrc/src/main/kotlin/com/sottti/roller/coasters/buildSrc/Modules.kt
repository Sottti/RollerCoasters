@file:Suppress("ClassName", "ConstPropertyName")

package com.sottti.roller.coasters.buildSrc

object module {
    object presentation {
        const val aboutMe = ":presentation:about-me"
        const val explore = ":presentation:explore"
        const val favourites = ":presentation:favourites"
        const val home = ":presentation:home"
        const val settings = ":presentation:settings"

        object designSystem {
            const val colors = ":presentation:design-system:colors"
            const val dialogs = ":presentation:design-system:dialogs"
            const val dimensions = ":presentation:design-system:dimensions"
            const val icons = ":presentation:design-system:icons"
            const val playground = ":presentation:design-system:playground"
            const val switch = ":presentation:design-system:switch"
            const val text = ":presentation:design-system:text"
            const val themes = ":presentation:design-system:themes"
            const val typography = ":presentation:design-system:typography"
        }
    }

    object domain {
        const val settings = ":domain:settings"
    }

    object data {
        const val settings = ":data:settings"
    }

    object utils {
        const val device = ":utils:device"
    }
}