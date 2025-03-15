package com.sottti.roller.coasters.presentation.settings.model

import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import co.cuvva.presentation.design.system.icons.model.IconState

@Immutable
internal data class MeasurementSystemState(
    val listItem: MeasurementSystemListItemState,
    val picker: MeasurementSystemPickerState?,
)

@Immutable
internal data class MeasurementSystemListItemState(
    @StringRes val headline: Int,
    @StringRes val supporting: Int,
    val selectedMeasurementSystem: SelectedMeasurementSystemState,
    val icon: IconState,
)

@Immutable
internal sealed class SelectedMeasurementSystemState {
    data object Loading : SelectedMeasurementSystemState()
    data class Loaded(val measurementSystem: MeasurementSystemUi) : SelectedMeasurementSystemState()
}

@Immutable
internal data class MeasurementSystemPickerState(
    @StringRes val confirm: Int,
    @StringRes val dismiss: Int,
    @StringRes val title: Int,
    val measurementSystems: List<MeasurementSystemUi>,
)

@Immutable
internal sealed class MeasurementSystemUi(
    @StringRes val text: Int,
    val icon: IconState,
    val selected: Boolean,
) {
    class Metric(
        @StringRes text: Int,
        icon: IconState,
        selected: Boolean,
    ) : MeasurementSystemUi(text, icon, selected)

    class ImperialUS(
        @StringRes text: Int,
        icon: IconState,
        selected: Boolean,
    ) : MeasurementSystemUi(text, icon, selected)

    class ImperialUK(
        @StringRes text: Int,
        icon: IconState,
        selected: Boolean,
    ) : MeasurementSystemUi(text, icon, selected)

    class System(
        @StringRes text: Int,
        icon: IconState,
        selected: Boolean,
    ) : MeasurementSystemUi(text, icon, selected)
}