package com.sotti.roller.coasters.presentation.settings.data.reducer

import com.google.common.truth.Truth.assertThat
import com.sotti.roller.coasters.domain.settings.model.measurementSystem.AppMeasurementSystem.ImperialUk
import com.sotti.roller.coasters.domain.settings.model.measurementSystem.AppMeasurementSystem.ImperialUs
import com.sotti.roller.coasters.domain.settings.model.measurementSystem.AppMeasurementSystem.Metric
import com.sotti.roller.coasters.domain.settings.model.measurementSystem.AppMeasurementSystem.System
import com.sotti.roller.coasters.presentation.settings.data.loadedState
import com.sotti.roller.coasters.presentation.settings.data.mapper.toDomain
import com.sotti.roller.coasters.presentation.settings.data.mapper.toPresentationModel
import com.sotti.roller.coasters.presentation.settings.model.SelectedAppMeasurementSystemState.Loaded
import org.junit.Test

internal class SettingsAppMeasurementSystemReducerTest {

    @Test
    fun `updates app measurement system`() {
        val initialState = loadedState()
        val result = initialState.updateAppMeasurementSystem(ImperialUs)
        val selected = result.appMeasurementSystem.listItem.selectedAppMeasurementSystem
        val isSelected = (selected as? Loaded)?.appMeasurementSystem?.selected
        val isCorrect = (selected as? Loaded)?.appMeasurementSystem?.toDomain() == ImperialUs
        assertThat(isSelected).isTrue()
        assertThat(isCorrect).isTrue()
    }

    @Test
    fun `shows app measurement system picker`() {
        val initialState = loadedState()
        val result = initialState.showAppMeasurementSystemPicker(ImperialUk)
        val picker = result.appMeasurementSystem.picker
        assertThat(picker).isNotNull()
        assertThat(picker?.appMeasurementSystems?.any {
            it.selected && it.toDomain() == ImperialUk
        }).isTrue()
    }

    @Test
    fun `updates app measurement system picker`() {
        val initialState = loadedState()
        val selected = Metric.toPresentationModel(selected = true)
        val result = initialState.updateAppMeasurementSystemPicker(selected)
        val picker = result.appMeasurementSystem.picker
        assertThat(picker).isNotNull()
        assertThat(picker?.appMeasurementSystems?.any {
            it.selected && it.toDomain() == Metric
        }).isTrue()
    }

    @Test
    fun `hides app measurement system picker`() {
        val initialState = loadedState().showAppMeasurementSystemPicker(System)
        val result = initialState.hideAppMeasurementSystemPicker()
        assertThat(result.appMeasurementSystem.picker).isNull()
    }
}
