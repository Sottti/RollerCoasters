package com.sottti.roller.coasters.presentation.settings.model

import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import com.sottti.roller.coasters.presentation.design.system.icons.model.IconState

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
internal sealed interface SelectedAppMeasurementSystemState {
    @Immutable
    data object Loading : SelectedAppMeasurementSystemState

    @Immutable
    data class Loaded(
        val appMeasurementSystem: AppMeasurementSystemUi,
    ) : SelectedAppMeasurementSystemState
}

@Immutable
internal data class AppMeasurementSystemPickerState(
    @StringRes val confirm: Int,
    @StringRes val dismiss: Int,
    @StringRes val title: Int,
    val appMeasurementSystems: List<AppMeasurementSystemUi>,
)

@Immutable
internal sealed interface AppMeasurementSystemUi {
    @get:StringRes
    val text: Int
    val icon: IconState
    val selected: Boolean
}

@Immutable
internal data class Metric(
    @StringRes override val text: Int,
    override val icon: IconState,
    override val selected: Boolean,
) : AppMeasurementSystemUi

@Immutable
internal data class ImperialUs(
    @StringRes override val text: Int,
    override val icon: IconState,
    override val selected: Boolean,
) : AppMeasurementSystemUi

@Immutable
internal data class ImperialUk(
    @StringRes override val text: Int,
    override val icon: IconState,
    override val selected: Boolean,
) : AppMeasurementSystemUi

@Immutable
internal data class SystemApp(
    @StringRes override val text: Int,
    override val icon: IconState,
    override val selected: Boolean,
) : AppMeasurementSystemUi
