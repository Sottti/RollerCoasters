package com.sottti.roller.coasters.presentation.settings.model

import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import co.cuvva.presentation.design.system.icons.model.IconState

@Immutable
internal data class AppMeasurementSystemState(
    val listItem: AppMeasurementSystemListItemState,
    val picker: AppMeasurementSystemPickerState?,
)

@Immutable
internal data class AppMeasurementSystemListItemState(
    @StringRes val headline: Int,
    @StringRes val supporting: Int,
    val selectedAppMeasurementSystem: SelectedAppMeasurementSystemState,
    val icon: IconState,
)

@Immutable
internal sealed class SelectedAppMeasurementSystemState {
    data object Loading : SelectedAppMeasurementSystemState()
    data class Loaded(
        val appMeasurementSystem: AppMeasurementSystemUi,
    ) : SelectedAppMeasurementSystemState()
}

@Immutable
internal data class AppMeasurementSystemPickerState(
    @StringRes val confirm: Int,
    @StringRes val dismiss: Int,
    @StringRes val title: Int,
    val appMeasurementSystems: List<AppMeasurementSystemUi>,
)

@Immutable
internal sealed class AppMeasurementSystemUi(
    @StringRes val text: Int,
    val icon: IconState,
    val selected: Boolean,
) {
    class Metric(
        @StringRes text: Int,
        icon: IconState,
        selected: Boolean,
    ) : AppMeasurementSystemUi(text, icon, selected)

    class ImperialUS(
        @StringRes text: Int,
        icon: IconState,
        selected: Boolean,
    ) : AppMeasurementSystemUi(text, icon, selected)

    class ImperialUK(
        @StringRes text: Int,
        icon: IconState,
        selected: Boolean,
    ) : AppMeasurementSystemUi(text, icon, selected)

    class SystemApp(
        @StringRes text: Int,
        icon: IconState,
        selected: Boolean,
    ) : AppMeasurementSystemUi(text, icon, selected)
}