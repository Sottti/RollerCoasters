package com.sottti.roller.coasters.presentation.roller.coaster.details.data

import com.sottti.roller.coasters.domain.roller.coasters.model.RollerCoasterId
import com.sottti.roller.coasters.presentation.format.DateFormatter
import com.sottti.roller.coasters.presentation.format.DisplayUnitFormatter
import com.sottti.roller.coasters.domain.roller.coasters.usecase.ObserveIsFavouriteRollerCoaster
import com.sottti.roller.coasters.domain.roller.coasters.usecase.ObserveRollerCoaster
import com.sottti.roller.coasters.domain.roller.coasters.usecase.ToggleFavouriteRollerCoaster
import com.sottti.roller.coasters.domain.settings.usecase.language.ObserveAppLanguage
import com.sottti.roller.coasters.domain.settings.usecase.locale.ObserveSystemLocale
import io.mockk.mockk

internal fun viewModel(
    dateFormatter: DateFormatter = mockk(),
    displayUnitFormatter: DisplayUnitFormatter = mockk(),
    observeAppLanguage: ObserveAppLanguage = mockk(),
    observeIsFavouriteRollerCoaster: ObserveIsFavouriteRollerCoaster = mockk(),
    observeRollerCoaster: ObserveRollerCoaster = mockk(),
    observeSystemLocale: ObserveSystemLocale = mockk(),
    rollerCoasterId: RollerCoasterId,
    toggleFavouriteRollerCoaster: ToggleFavouriteRollerCoaster = mockk(),
): RollerCoasterDetailsViewModel =
    RollerCoasterDetailsViewModel(
        dateFormatter = dateFormatter,
        displayUnitFormatter = displayUnitFormatter,
        observeAppLanguage = observeAppLanguage,
        observeIsFavouriteRollerCoaster = observeIsFavouriteRollerCoaster,
        observeRollerCoaster = observeRollerCoaster,
        observeSystemLocale = observeSystemLocale,
        rollerCoasterId = rollerCoasterId,
        toggleFavouriteRollerCoaster = toggleFavouriteRollerCoaster,
    )

