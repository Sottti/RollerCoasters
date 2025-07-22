package com.sottti.roller.coasters.presentation.explore.data

import com.sottti.roller.coasters.domain.roller.coasters.usecase.ObserveRollerCoasters
import com.sottti.roller.coasters.domain.settings.usecase.language.ObserveAppLanguage
import com.sottti.roller.coasters.domain.settings.usecase.locale.ObserveSystemLocale
import com.sottti.roller.coasters.presentation.explore.model.AllFilter
import com.sottti.roller.coasters.presentation.explore.model.AlphabeticalFilter
import com.sottti.roller.coasters.presentation.explore.model.DropFilter
import com.sottti.roller.coasters.presentation.explore.model.ExploreAction.PrimaryFilterAction.HideSortFilters
import com.sottti.roller.coasters.presentation.explore.model.ExploreAction.PrimaryFilterAction.HideTypeFilters
import com.sottti.roller.coasters.presentation.explore.model.ExploreAction.PrimaryFilterAction.ShowSortFilters
import com.sottti.roller.coasters.presentation.explore.model.ExploreAction.PrimaryFilterAction.ShowTypeFilters
import com.sottti.roller.coasters.presentation.explore.model.GForceFilter
import com.sottti.roller.coasters.presentation.explore.model.HeightFilter
import com.sottti.roller.coasters.presentation.explore.model.InversionsFilter
import com.sottti.roller.coasters.presentation.explore.model.LengthFilter
import com.sottti.roller.coasters.presentation.explore.model.MaxVerticalFilter
import com.sottti.roller.coasters.presentation.explore.model.SortByPrimaryFilter
import com.sottti.roller.coasters.presentation.explore.model.SpeedFilter
import com.sottti.roller.coasters.presentation.explore.model.SteelFilter
import com.sottti.roller.coasters.presentation.explore.model.TypePrimaryFilter
import com.sottti.roller.coasters.presentation.explore.model.WoodFilter
import com.sottti.roller.coasters.presentation.format.DisplayUnitFormatter
import com.sottti.roller.coasters.presentation.string.provider.StringProvider
import io.mockk.mockk

internal fun filtersWithExpansion(
    sortByExpanded: Boolean = false,
    typeExpanded: Boolean = false,
    sortBySecondaryVisible: Boolean = false,
    typeSecondaryVisible: Boolean = false,
) = filtersInitialState().copy(
    primary = filtersInitialState().primary.map {
        when (it) {
            is SortByPrimaryFilter -> it.copy(
                expanded = sortByExpanded,
                action = if (sortByExpanded) HideSortFilters else ShowSortFilters,
            )

            is TypePrimaryFilter -> it.copy(
                expanded = typeExpanded,
                action = if (typeExpanded) HideTypeFilters else ShowTypeFilters,
            )
        }
    },
    secondary = filtersInitialState().secondary.map {
        when (it) {
            is AlphabeticalFilter -> it.copy(visible = sortBySecondaryVisible)
            is DropFilter -> it.copy(visible = sortBySecondaryVisible)
            is GForceFilter -> it.copy(visible = sortBySecondaryVisible)
            is HeightFilter -> it.copy(visible = sortBySecondaryVisible)
            is InversionsFilter -> it.copy(visible = sortBySecondaryVisible)
            is LengthFilter -> it.copy(visible = sortBySecondaryVisible)
            is MaxVerticalFilter -> it.copy(visible = sortBySecondaryVisible)
            is SpeedFilter -> it.copy(visible = sortBySecondaryVisible)
            is AllFilter -> it.copy(visible = typeSecondaryVisible)
            is SteelFilter -> it.copy(visible = typeSecondaryVisible)
            is WoodFilter -> it.copy(visible = typeSecondaryVisible)
        }
    },
)


internal fun viewModel(
    displayUnitFormatter: DisplayUnitFormatter = mockk(),
    observeAppLanguage: ObserveAppLanguage = mockk(),
    observeRollerCoasters: ObserveRollerCoasters = mockk(),
    observeSystemLocale: ObserveSystemLocale = mockk(),
    stringProvider: StringProvider = mockk(),
): ExploreViewModel = ExploreViewModel(
    displayUnitFormatter = displayUnitFormatter,
    observeAppLanguage = observeAppLanguage,
    observeRollerCoasters = observeRollerCoasters,
    observeSystemLocale = observeSystemLocale,
    stringProvider = stringProvider,
)