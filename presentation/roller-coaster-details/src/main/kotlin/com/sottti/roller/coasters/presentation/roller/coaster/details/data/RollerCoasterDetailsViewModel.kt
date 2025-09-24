package com.sottti.roller.coasters.presentation.roller.coaster.details.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sottti.roller.coasters.domain.roller.coasters.model.RollerCoasterId
import com.sottti.roller.coasters.domain.roller.coasters.usecase.ObserveIsFavouriteRollerCoaster
import com.sottti.roller.coasters.domain.roller.coasters.usecase.ObserveRollerCoaster
import com.sottti.roller.coasters.domain.roller.coasters.usecase.ToggleFavouriteRollerCoaster
import com.sottti.roller.coasters.domain.settings.usecase.language.ObserveAppLanguage
import com.sottti.roller.coasters.domain.settings.usecase.locale.ObserveSystemLocale
import com.sottti.roller.coasters.presentation.format.DateFormatter
import com.sottti.roller.coasters.presentation.format.DisplayUnitFormatter
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsAction
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsAction.ToggleFavourite
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.scan
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class RollerCoasterDetailsViewModel @Inject constructor(
    observeAppLanguage: ObserveAppLanguage,
    observeIsFavouriteRollerCoaster: ObserveIsFavouriteRollerCoaster,
    observeRollerCoaster: ObserveRollerCoaster,
    observeSystemLocale: ObserveSystemLocale,
    private val dateFormatter: DateFormatter,
    private val displayUnitFormatter: DisplayUnitFormatter,
    private val rollerCoasterId: RollerCoasterId,
    private val toggleFavouriteRollerCoaster: ToggleFavouriteRollerCoaster,
) : ViewModel() {

    internal val state: StateFlow<RollerCoasterDetailsState> =
        combine(
            flow = observeAppLanguage(),
            flow2 = observeSystemLocale(),
            flow3 = observeRollerCoaster(rollerCoasterId),
            flow4 = observeIsFavouriteRollerCoaster(rollerCoasterId),
        ) { appLang, systemLocale, coaster, isFavourite ->
            { previous: RollerCoasterDetailsState ->
                previous
                    .updateRollerCoaster(
                        appLanguage = appLang,
                        dateFormatter = dateFormatter,
                        displayUnitFormatter = displayUnitFormatter,
                        isFavourite = isFavourite,
                        rollerCoaster = coaster,
                        systemLocale = systemLocale,
                    )
            }
        }
            .scan(initialState()) { previous, reduce -> reduce(previous) }
            .stateIn(
                scope = viewModelScope,
                started = WhileSubscribed(5_000),
                initialValue = initialState(),
            )

    internal val onAction: (RollerCoasterDetailsAction) -> Unit = { action ->
        when (action) {
            ToggleFavourite -> viewModelScope.launch {
                toggleFavouriteRollerCoaster(rollerCoasterId)
            }
        }
    }
}
