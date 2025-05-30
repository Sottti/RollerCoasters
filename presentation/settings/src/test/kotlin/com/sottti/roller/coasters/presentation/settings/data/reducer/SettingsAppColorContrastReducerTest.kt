package com.sottti.roller.coasters.presentation.settings.data.reducer

import com.google.common.truth.Truth.assertThat
import com.sottti.roller.coasters.domain.settings.model.colorContrast.AppColorContrast
import com.sottti.roller.coasters.domain.settings.model.dynamicColor.AppDynamicColor
import com.sottti.roller.coasters.presentation.settings.data.loadedState
import com.sottti.roller.coasters.presentation.settings.data.mapper.toPresentationModel
import com.sottti.roller.coasters.presentation.settings.model.SelectedAppColorContrastState.Loaded
import org.junit.Test

internal class SettingsAppColorContrastReducerTest {

    @Test
    fun `updates app color contrast`() {
        val state = loadedState()
        val result = state.updateAppColorContrast(AppColorContrast.HighContrast)
        val selectedAppContrast = result.appColorContrast.listItem.selectedAppColorContrast
        val isSelected = (selectedAppContrast as? Loaded)?.appColorContrast?.selected
        assertThat(isSelected).isTrue()
    }

    @Test
    fun `updates app color contrast picker`() {
        val newSelectedAppColorContrast =
            AppColorContrast.StandardContrast.toPresentationModel(selected = true)
        val result = loadedState().updateAppColorContrastPicker(
            appColorContrastAvailable = true,
            selectedAppColorContrast = newSelectedAppColorContrast
        )
        assertThat(result.appColorContrast.picker?.appColorContrasts).isNotEmpty()
    }

    @Test
    fun `shows app color contrast picker when dynamic color is off`() {
        val state = loadedState(dynamicColorState = AppDynamicColor.Disabled)
        val result = state.showAppColorContrastPicker(
            AppColorContrast.StandardContrast,
            appColorContrastAvailable = true,
        )
        assertThat(result.appColorContrast.picker).isNotNull()
        assertThat(result.appColorContrast.notAvailableMessage).isNull()
    }

    @Test
    fun `shows app color contrast not available message when dynamic color is on`() {
        val state = loadedState()
        val result = state.showAppColorContrastPicker(
            AppColorContrast.StandardContrast,
            appColorContrastAvailable = true,
        )
        assertThat(result.appColorContrast.picker).isNull()
        assertThat(result.appColorContrast.notAvailableMessage).isNotNull()
    }

    @Test
    fun `hides app color contrast picker`() {
        val state = loadedState()
        val shown = state.showAppColorContrastPicker(AppColorContrast.StandardContrast, true)
        val result = shown.hideAppColorContrastPicker()
        assertThat(result.appColorContrast.picker).isNull()
    }

    @Test
    fun `hides app color contrast not available message`() {
        val state = loadedState()
        val shown = state.showAppColorContrastPicker(AppColorContrast.StandardContrast, true)
        val result = shown.hideAppColorContrastNotAvailableMessage()
        assertThat(result.appColorContrast.notAvailableMessage).isNull()
    }
}