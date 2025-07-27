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
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsAction.LoadUi
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsAction.ToggleFavourite
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
    private val dateFormatter: DateFormatter,
    private val displayUnitFormatter: DisplayUnitFormatter,
    private val observeAppLanguage: ObserveAppLanguage,
    private val observeIsFavouriteRollerCoaster: ObserveIsFavouriteRollerCoaster,
    private val observeRollerCoaster: ObserveRollerCoaster,
    private val observeSystemLocale: ObserveSystemLocale,
    private val rollerCoasterId: RollerCoasterId,
    private val toggleFavouriteRollerCoaster: ToggleFavouriteRollerCoaster,
) : ViewModel() {

    private val _state = MutableStateFlow(initialState())
    internal val state: StateFlow<RollerCoasterDetailsState> = _state.asStateFlow()

    internal val onAction: (RollerCoasterDetailsAction) -> Unit =
        { action -> processAction(action) }

    private fun processAction(action: RollerCoasterDetailsAction) {
        when (action) {
            LoadUi -> {
                observeState()
                observeIsFavourite()
            }

            ToggleFavourite -> viewModelScope.launch {
                toggleFavouriteRollerCoaster(rollerCoasterId = rollerCoasterId)
            }
        }
    }

    private fun observeState() {
        combine(
            flow = observeAppLanguage(),
            flow2 = observeSystemLocale(),
            flow3 = observeRollerCoaster(rollerCoasterId),
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
    }

    private fun observeIsFavourite() {
        observeIsFavouriteRollerCoaster(rollerCoasterId)
            .onEach { _state.updateIsFavouriteRollerCoaster(it) }
            .launchIn(viewModelScope)
    }
}
