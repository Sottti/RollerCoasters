package com.sotti.roller.coasters.presentation.roller.coaster.details.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sotti.roller.coasters.domain.roller.coasters.model.RollerCoaster
import com.sotti.roller.coasters.domain.roller.coasters.model.RollerCoasterId
import com.sotti.roller.coasters.domain.roller.coasters.usecase.ObserveIsFavouriteRollerCoaster
import com.sotti.roller.coasters.domain.roller.coasters.usecase.ObserveRollerCoaster
import com.sotti.roller.coasters.domain.roller.coasters.usecase.ToggleFavouriteRollerCoaster
import com.sotti.roller.coasters.domain.settings.model.language.AppLanguage
import com.sotti.roller.coasters.domain.settings.usecase.language.ObserveAppLanguage
import com.sotti.roller.coasters.domain.settings.usecase.locale.ObserveSystemLocale
import com.sotti.roller.coasters.presentation.format.DateFormatter
import com.sotti.roller.coasters.presentation.format.DisplayUnitFormatter
import com.sotti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsAction
import com.sotti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsAction.ToggleFavourite
import com.sotti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsState
import com.sotti.roller.coasters.presentation.utils.stateInWhileSubscribed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.scan
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
            .stateInWhileSubscribed(initialState)

    private val reducer: (
        appLang: AppLanguage,
        coaster: RollerCoaster,
        isFavourite: Boolean,
        systemLocale: Locale,
    ) -> (RollerCoasterDetailsState) -> RollerCoasterDetailsState =
        { appLang, coaster, isFavourite, systemLocale ->
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

    internal val onAction: (RollerCoasterDetailsAction) -> Unit = ::processAction
    private fun processAction(action: RollerCoasterDetailsAction) {
        when (action) {
            ToggleFavourite -> viewModelScope.launch {
                toggleFavouriteRollerCoaster(rollerCoasterId)
            }
        }
    }
}
