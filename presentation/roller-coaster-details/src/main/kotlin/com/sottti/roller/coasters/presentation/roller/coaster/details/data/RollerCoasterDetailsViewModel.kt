package com.sottti.roller.coasters.presentation.roller.coaster.details.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sottti.roller.coasters.domain.roller.coasters.model.RollerCoaster
import com.sottti.roller.coasters.domain.roller.coasters.model.RollerCoasterId
import com.sottti.roller.coasters.domain.roller.coasters.usecase.ObserveIsFavouriteRollerCoaster
import com.sottti.roller.coasters.domain.roller.coasters.usecase.ObserveRollerCoaster
import com.sottti.roller.coasters.domain.roller.coasters.usecase.ToggleFavouriteRollerCoaster
import com.sottti.roller.coasters.domain.settings.model.language.AppLanguage
import com.sottti.roller.coasters.domain.settings.usecase.language.ObserveAppLanguage
import com.sottti.roller.coasters.domain.settings.usecase.locale.ObserveSystemLocale
import com.sottti.roller.coasters.presentation.format.DateFormatter
import com.sottti.roller.coasters.presentation.format.DisplayUnitFormatter
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsAction
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsAction.ToggleFavourite
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsState
import com.sottti.roller.coasters.presentation.utils.stateInWhileSubscribed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.scan
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.Locale
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
            flow2 = observeRollerCoaster(rollerCoasterId),
            flow3 = observeIsFavouriteRollerCoaster(rollerCoasterId),
            flow4 = observeSystemLocale(),
        ) { appLang, coaster, isFavourite, systemLocale ->
            reducer(appLang, coaster, isFavourite, systemLocale)
        }
            .scan(initialState) { previous, reduce -> reduce(previous) }
            .drop(1)
            .distinctUntilChanged()
            .stateInWhileSubscribed(initialValue = initialState)
            .stateIn(
                scope = viewModelScope,
                started = WhileSubscribed(stopTimeoutMillis = 5_000),
                initialValue = initialState,
            )

    private val reducer: (
        appLang: AppLanguage,
        coaster: RollerCoaster,
        isFavourite: Boolean,
        systemLocale: Locale,
    ) -> (RollerCoasterDetailsState) -> RollerCoasterDetailsState =
        { appLang, coaster, isFavourite, systemLocale ->
            { previous: RollerCoasterDetailsState ->
                previous.updateRollerCoaster(
                    appLanguage = appLang,
                    dateFormatter = dateFormatter,
                    displayUnitFormatter = displayUnitFormatter,
                    isFavourite = isFavourite,
                    rollerCoaster = coaster,
                    systemLocale = systemLocale,
                )
            }
        }

    internal val onAction: (RollerCoasterDetailsAction) -> Unit = ::processAction
    private fun processAction(action: RollerCoasterDetailsAction) {
        when (action) {
            ToggleFavourite -> viewModelScope.launch {
                toggleFavouriteRollerCoaster(rollerCoasterId)
            }
        }
    }
}
