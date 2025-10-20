package com.sottti.roller.coasters.presentation.navigation

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.saveable.Saver
import kotlinx.serialization.Serializable


@Immutable
public sealed interface NavigationDestination {
    @Serializable
    @Immutable
    public data object AboutMe : NavigationDestination

    @Serializable
    @Immutable
    public data object Explore : NavigationDestination

    @Serializable
    @Immutable
    public data object Favourites : NavigationDestination

    @Immutable
    @Serializable
    public data object Home : NavigationDestination

    @Immutable
    @Serializable
    public data object Search : NavigationDestination

    @Immutable
    @Serializable
    public data object Settings : NavigationDestination

    @Immutable
    @Serializable
    public data class RollerCoasterDetails(val rollerCoasterId: Int) : NavigationDestination {
        public companion object {
            public const val KEY_ROLLER_COASTER_ID: String = "rollerCoasterId"
        }
    }

    public companion object {
        public val saver: Saver<NavigationDestination, String> = Saver(
            save = { navigationDestination ->
                navigationDestination::class.qualifiedName ?: Explore::class.qualifiedName
            },
            restore = { name -> name.toNavigationDestination() }
        )
    }
}
