package com.sottti.roller.coasters.presentation.roller.coaster.details.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sottti.roller.coasters.domain.roller.coasters.model.RollerCoasterId
import com.sottti.roller.coasters.domain.roller.coasters.usecase.ObserveRollerCoaster
import com.sottti.roller.coasters.domain.settings.usecase.language.ObserveAppLanguage
import com.sottti.roller.coasters.domain.settings.usecase.locale.ObserveSystemLocale
import com.sottti.roller.coasters.presentation.format.DateFormatter
import com.sottti.roller.coasters.presentation.format.UnitDisplayFormatter
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
internal class RollerCoasterDetailsViewModel @Inject constructor(
    private val dateFormatter: DateFormatter,
    private val observeAppLanguage: ObserveAppLanguage,
    private val observeRollerCoaster: ObserveRollerCoaster,
    private val observeSystemLocale: ObserveSystemLocale,
    private val rollerCoasterId: RollerCoasterId,
    private val unitDisplayFormatter: UnitDisplayFormatter,

    ) : ViewModel() {

    private val _state = MutableStateFlow(initialState())
    internal val state: StateFlow<RollerCoasterDetailsViewState> = _state.asStateFlow()

    init {
        combine(
            observeAppLanguage(),
            observeSystemLocale(),
            observeRollerCoaster(rollerCoasterId)
        ) { appLang, systemLocale, coaster ->
            Triple(appLang, systemLocale, coaster)
        }.onEach { (appLang, systemLocale, coaster) ->
            _state.updateRollerCoaster(
                appLanguage = appLang,
                rollerCoaster = coaster,
                systemLocale = systemLocale,
                unitDisplayFormatter = unitDisplayFormatter,
                dateFormatter = dateFormatter,
            )
        }.launchIn(viewModelScope)
    }
}