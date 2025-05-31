package com.sottti.roller.coasters.presentation.settings.data.reducer

import com.google.common.truth.Truth.assertThat
import com.sottti.roller.coasters.domain.settings.model.theme.AppTheme
import com.sottti.roller.coasters.presentation.settings.data.loadedState
import com.sottti.roller.coasters.presentation.settings.data.mapper.toDomain
import com.sottti.roller.coasters.presentation.settings.data.mapper.toPresentationModel
import com.sottti.roller.coasters.presentation.settings.model.SelectedAppThemeState.Loaded
import org.junit.Test

internal class SettingsAppThemeReducerTest {

    @Test
    fun `updates app theme`() {
        val initialState = loadedState()
        val result = initialState.updateAppTheme(AppTheme.DarkAppTheme)
        val selected = result.appTheme.listItem.selectedAppTheme
        val isSelected = (selected as? Loaded)?.appTheme?.selected
        val isCorrect = (selected as? Loaded)?.appTheme?.toDomain() == AppTheme.DarkAppTheme
        assertThat(isSelected).isTrue()
        assertThat(isCorrect).isTrue()
    }

    @Test
    fun `shows app theme picker`() {
        val initialState = loadedState()
        val selected = AppTheme.System.toPresentationModel(selected = true)
        val result = initialState.showAppThemePicker(
            lightDarkAppThemingAvailable = true,
            selectedAppTheme = selected,
        )
        val picker = result.appTheme.picker
        assertThat(picker).isNotNull()
        assertThat(picker?.appThemes?.any {
            it.selected && it.toDomain() == AppTheme.System
        }).isTrue()
    }

    @Test
    fun `updates app theme picker`() {
        val initialState = loadedState()
        val selected = AppTheme.LightAppTheme.toPresentationModel(selected = true)
        val result = initialState.updateAppThemePicker(
            selectedAppTheme = selected,
        )
        val picker = result.appTheme.picker
        assertThat(picker).isNotNull()
        assertThat(picker?.appThemes?.any {
            it.selected && it.toDomain() == AppTheme.LightAppTheme
        }).isTrue()
    }

    @Test
    fun `hides app theme picker`() {
        val selected = AppTheme.System.toPresentationModel(selected = true)
        val state = loadedState().showAppThemePicker(
            lightDarkAppThemingAvailable = true,
            selectedAppTheme = selected,
        )
        val result = state.hideAppThemePicker()
        assertThat(result.appTheme.picker).isNull()
    }
}