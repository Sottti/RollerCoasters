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
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class RollerCoasterDetailsViewModel @Inject constructor(
    observeAppLanguage: ObserveAppLanguage,
    observeIsFavouriteRollerCoaster: ObserveIsFavouriteRollerCoaster,
    observeRollerCoaster: ObserveRollerCoaster,
    observeSystemLocale: ObserveSystemLocale,
    private val dateFormatter: DateFormatter,
    private val rollerCoasterId: RollerCoasterId,
    private val toggleFavouriteRollerCoaster: ToggleFavouriteRollerCoaster,
    private val displayUnitFormatter: DisplayUnitFormatter,
) : ViewModel() {

    private val _state = MutableStateFlow(initialState())
    internal val state: StateFlow<RollerCoasterDetailsState> = _state.asStateFlow()

    init {
        combine(
            observeAppLanguage(),
            observeSystemLocale(),
            observeRollerCoaster(rollerCoasterId),
        ) { appLang, systemLocale, coaster ->
            Triple(appLang, systemLocale, coaster)
        }.onEach { (appLang, systemLocale, coaster) ->
            _state.updateRollerCoaster(
                appLanguage = appLang,
                rollerCoaster = coaster,
                systemLocale = systemLocale,
                displayUnitFormatter = displayUnitFormatter,
                dateFormatter = dateFormatter,
            )
        }.launchIn(viewModelScope)

        observeIsFavouriteRollerCoaster(rollerCoasterId)
            .onEach { _state.updateIsFavouriteRollerCoaster(it) }
            .launchIn(viewModelScope)
    }

    internal val onAction: (RollerCoasterDetailsAction) -> Unit =
        { action -> processAction(action) }

    private fun processAction(action: RollerCoasterDetailsAction) {
        when (action) {
            RollerCoasterDetailsAction.ToggleFavourite -> {
                viewModelScope.launch {
                    toggleFavouriteRollerCoaster(
                        rollerCoasterId = rollerCoasterId,
                    )
                }
            }
        }
    }
}