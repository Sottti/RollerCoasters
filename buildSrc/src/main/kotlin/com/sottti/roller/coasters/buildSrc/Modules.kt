@file:Suppress("ClassName", "ConstPropertyName")

package com.sottti.roller.coasters.buildSrc

object module {
    object presentation {
        const val aboutMe = ":presentation:about-me"
        const val empty = ":presentation:empty"
        const val error = ":presentation:error"
        const val explore = ":presentation:explore"
        const val favourites = ":presentation:favourites"
        const val fixtures = ":presentation:fixtures"
        const val format = ":presentation:format"
        const val home = ":presentation:home"
        const val imageLoading = ":presentation:image-loading"
        const val informative = ":presentation:informative"
        const val navigation = ":presentation:navigation"
        const val previews = ":presentation:previews"
        const val rollerCoasterDetails = ":presentation:roller-coaster-details"
        const val settings = ":presentation:settings"
        const val stringProvider = ":presentation:string-provider"
        const val topBars = ":presentation:top-bars"

        object designSystem {
            const val chip = ":presentation:design-system:chip"
            const val colors = ":presentation:design-system:colors"
            const val dialogs = ":presentation:design-system:dialogs"
            const val dimensions = ":presentation:design-system:dimensions"
            const val icons = ":presentation:design-system:icons"
            const val illustrations = ":presentation:design-system:illustrations"
            const val map = ":presentation:design-system:map"
            const val playground = ":presentation:design-system:playground"
            const val progressIndicators = ":presentation:design-system:progress-indicators"
            const val rollerCoasterCard = ":presentation:design-system:roller-coaster-card"
            const val switch = ":presentation:design-system:switch"
            const val text = ":presentation:design-system:text"
            const val themes = ":presentation:design-system:themes"
            const val typography = ":presentation:design-system:typography"
        }
    }

    object domain {
        const val features = ":domain:features"
        const val fixtures = ":domain:fixtures"
        const val model = ":domain:model"
        const val rollerCoasters = ":domain:roller-coasters"
        const val settings = ":domain:settings"
    }

    object data {
        const val features = ":data:features"
        const val network = ":data:network"
        const val rollerCoasters = ":data:roller-coasters"
        const val settings = ":data:settings"
    }

    object utils {
        const val timeDates = ":utils:time-dates"
    }

    const val di = ":di"
}