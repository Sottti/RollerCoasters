@file:Suppress("ClassName", "ConstPropertyName")

package com.sottti.roller.coasters.buildSrc

object module {
    object presentation {
        const val aboutMe = ":presentation:about-me"
        const val explore = ":presentation:explore"
        const val favourites = ":presentation:favourites"
        const val home = ":presentation:home"
        const val imageLoading = ":presentation:image-loading"
        const val navigation = ":presentation:navigation"
        const val previews = ":presentation:previews"
        const val settings = ":presentation:settings"
        const val topBars = ":presentation:top-bars"
        const val utils = ":presentation:utils"

        object designSystem {
            const val chip = ":presentation:design-system:chip"
            const val colors = ":presentation:design-system:colors"
            const val dialogs = ":presentation:design-system:dialogs"
            const val dimensions = ":presentation:design-system:dimensions"
            const val icons = ":presentation:design-system:icons"
            const val loading = ":presentation:design-system:loading"
            const val playground = ":presentation:design-system:playground"
            const val rollerCoasterCard = ":presentation:design-system:roller-coaster-card"
            const val switch = ":presentation:design-system:switch"
            const val text = ":presentation:design-system:text"
            const val themes = ":presentation:design-system:themes"
            const val typography = ":presentation:design-system:typography"
        }
    }

    object domain {
        const val model = ":domain:model"
    }

    object data {
        const val network = ":data:network"
        const val rollerCoasters = ":data:roller-coasters"
        const val settings = ":data:settings"
        const val workManager = ":data:work-manager"
    }

    object utils {
        const val dates = ":utils:dates"
        const val device = ":utils:device"
    }
}