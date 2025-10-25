package com.sotti.roller.coasters.presentation.explore.data

import com.sotti.roller.coasters.domain.roller.coasters.usecase.ObserveRollerCoasters
import com.sotti.roller.coasters.domain.settings.usecase.language.ObserveAppLanguage
import com.sotti.roller.coasters.domain.settings.usecase.locale.ObserveSystemLocale
import com.sotti.roller.coasters.presentation.explore.model.AllFilter
import com.sotti.roller.coasters.presentation.explore.model.AlphabeticalFilter
import com.sotti.roller.coasters.presentation.explore.model.DropFilter
import com.sotti.roller.coasters.presentation.explore.model.ExploreAction.PrimaryFilterAction.HideSortFilters
import com.sotti.roller.coasters.presentation.explore.model.ExploreAction.PrimaryFilterAction.HideTypeFilters
import com.sotti.roller.coasters.presentation.explore.model.ExploreAction.PrimaryFilterAction.ShowSortFilters
import com.sotti.roller.coasters.presentation.explore.model.ExploreAction.PrimaryFilterAction.ShowTypeFilters
import com.sotti.roller.coasters.presentation.explore.model.GForceFilter
import com.sotti.roller.coasters.presentation.explore.model.HeightFilter
import com.sotti.roller.coasters.presentation.explore.model.InversionsFilter
import com.sotti.roller.coasters.presentation.explore.model.LengthFilter
import com.sotti.roller.coasters.presentation.explore.model.MaxVerticalFilter
import com.sotti.roller.coasters.presentation.explore.model.SortByPrimaryFilter
import com.sotti.roller.coasters.presentation.explore.model.SpeedFilter
import com.sotti.roller.coasters.presentation.explore.model.SteelFilter
import com.sotti.roller.coasters.presentation.explore.model.TypePrimaryFilter
import com.sotti.roller.coasters.presentation.explore.model.WoodFilter
import com.sotti.roller.coasters.presentation.format.DisplayUnitFormatter
import com.sotti.roller.coasters.presentation.string.provider.StringProvider
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
