package com.sottti.roller.coasters.app.model

import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import co.cuvva.presentation.design.system.icons.model.IconState
import com.sottti.roller.coasters.app.navigation.NavigationBarDestination

@Immutable
internal data class NavigationBarItem(
    @StringRes val labelResId: Int,
    val destination: NavigationBarDestination,
    val icon: IconState,
)