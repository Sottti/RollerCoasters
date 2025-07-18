package com.sottti.roller.coasters.presentation.navigation

import androidx.compose.runtime.saveable.Saver
import kotlinx.serialization.Serializable


public sealed interface NavigationDestination {
    @Serializable
    public data object AboutMe : NavigationDestination

    @Serializable
    public data object Explore : NavigationDestination

    @Serializable
    public data object Favourites : NavigationDestination

    @Serializable
    public data object Home : NavigationDestination

    @Serializable
    public data object Search : NavigationDestination

    @Serializable
    public data object Settings : NavigationDestination

    @Serializable
    public data class RollerCoasterDetails(val rollerCoasterId: Int) : NavigationDestination {
        public companion object {
            public const val KEY_ROLLER_COASTER_ID: String = "rollerCoasterId"
        }
    }

    public companion object {
        public val saver: Saver<NavigationDestination, String> =
            Saver(
                save = { it::class.simpleName ?: AboutMe::class.simpleName },
                restore = { name ->
                    when (name) {
                        AboutMe::class.simpleName -> AboutMe
                        Explore::class.simpleName -> Explore
                        Favourites::class.simpleName -> Favourites
                        Home::class.simpleName -> Home
                        Search::class.simpleName -> Search
                        Settings::class.simpleName -> Settings
                        else -> Explore
                    }
                }
            )
    }
}