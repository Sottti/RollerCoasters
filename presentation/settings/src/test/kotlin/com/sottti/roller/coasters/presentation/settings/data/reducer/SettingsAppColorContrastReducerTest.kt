package com.sottti.roller.coasters.presentation.settings.data.reducer

import com.google.common.truth.Truth.assertThat
import com.sottti.roller.coasters.domain.settings.model.colorContrast.AppColorContrast.HighContrast
import com.sottti.roller.coasters.domain.settings.model.colorContrast.AppColorContrast.StandardContrast
import com.sottti.roller.coasters.domain.settings.model.dynamicColor.AppDynamicColor
import com.sottti.roller.coasters.presentation.settings.data.loadedState
import com.sottti.roller.coasters.presentation.settings.data.mapper.toPresentationModel
import com.sottti.roller.coasters.presentation.settings.model.SelectedAppColorContrastState.Loaded
import org.junit.Test

internal class SettingsAppColorContrastReducerTest {

    @Test
    fun `updates app color contrast`() {
        val initialState = loadedState()
        val result = initialState.updateAppColorContrast(HighContrast)
        val selectedAppContrast = result.appColorContrast.listItem.selectedAppColorContrast
        val isSelected = (selectedAppContrast as? Loaded)?.appColorContrast?.selected
        assertThat(isSelected).isTrue()
    }

    @Test
    fun `updates app color contrast picker`() {
        val initialState = loadedState()
        val selectedAppContrast = StandardContrast.toPresentationModel(selected = true)
        val result = initialState.updateAppColorContrastPicker(
            appColorContrastAvailable = true,
            selectedAppColorContrast = selectedAppContrast,
        )
        assertThat(result.appColorContrast.picker?.appColorContrasts).isNotEmpty()
    }

    @Test
    fun `shows app color contrast picker when dynamic color is off`() {
        val initialState = loadedState(dynamicColorState = AppDynamicColor.Disabled)
        val selectedAppColorContrast = StandardContrast
        val result = initialState.showAppColorContrastPicker(
            selectedAppColorContrast = selectedAppColorContrast,
            appColorContrastAvailable = true,
        )
        assertThat(result.appColorContrast.picker).isNotNull()
        assertThat(result.appColorContrast.notAvailableMessage).isNull()
    }

    @Test
    fun `shows app color contrast not available message when dynamic color is on`() {
        val initialState = loadedState()
        val result = initialState.showAppColorContrastPicker(
            selectedAppColorContrast = StandardContrast,
            appColorContrastAvailable = true,
        )
        assertThat(result.appColorContrast.picker).isNull()
        assertThat(result.appColorContrast.notAvailableMessage).isNotNull()
    }

    @Test
    fun `hides app color contrast picker`() {
        val initialState = loadedState().showAppColorContrastPicker(StandardContrast, true)
        val result = initialState.hideAppColorContrastPicker()
        assertThat(result.appColorContrast.picker).isNull()
    }

    @Test
    fun `hides app color contrast not available message`() {
        val initialState = loadedState().showAppColorContrastPicker(StandardContrast, true)
        val result = initialState.hideAppColorContrastNotAvailableMessage()
        assertThat(result.appColorContrast.notAvailableMessage).isNull()
    }
}
