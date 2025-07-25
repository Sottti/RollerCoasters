package com.sottti.roller.coasters.presentation.navigation

import androidx.compose.runtime.saveable.Saver
import com.sottti.roller.coasters.presentation.navigation.NavigationDestination.AboutMe
import com.sottti.roller.coasters.presentation.navigation.NavigationDestination.Explore
import com.sottti.roller.coasters.presentation.navigation.NavigationDestination.Favourites
import com.sottti.roller.coasters.presentation.navigation.NavigationDestination.Home
import com.sottti.roller.coasters.presentation.navigation.NavigationDestination.Search
import com.sottti.roller.coasters.presentation.navigation.NavigationDestination.Settings
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
        public val saver: Saver<NavigationDestination, String> = Saver(
            save = { navigationDestination ->
                navigationDestination::class.qualifiedName ?: Explore::class.qualifiedName
            },
            restore = { name -> name.toNavigationDestination() }
        )
    }
}

public fun String?.toNavigationDestination(): NavigationDestination =
    when (this) {
        AboutMe::class.qualifiedName -> AboutMe
        Explore::class.qualifiedName -> Explore
        Favourites::class.qualifiedName -> Favourites
        Home::class.qualifiedName -> Home
        Search::class.qualifiedName -> Search
        Settings::class.qualifiedName -> Settings
        else -> Explore
    }