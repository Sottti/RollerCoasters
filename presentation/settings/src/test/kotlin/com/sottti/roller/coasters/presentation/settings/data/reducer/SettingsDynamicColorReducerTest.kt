package com.sottti.roller.coasters.presentation.settings.data.reducer

import com.google.common.truth.Truth.assertThat
import com.sottti.roller.coasters.domain.settings.model.dynamicColor.AppDynamicColor
import com.sottti.roller.coasters.presentation.settings.data.loadedStateWithDynamicColorLoading
import com.sottti.roller.coasters.presentation.settings.data.loadedStateWithNullDynamicColor
import com.sottti.roller.coasters.presentation.settings.model.DynamicColorCheckedState
import org.junit.Test

internal class SettingsDynamicColorReducerTest {

    @Test
    fun `sets dynamic color to enabled`() {
        val result = loadedStateWithDynamicColorLoading()
            .updateDynamicColor(AppDynamicColor.Enabled)
            .dynamicColor?.checkedState

        assertThat(result).isEqualTo(DynamicColorCheckedState.Loaded(true))
    }

    @Test
    fun `sets dynamic color to disabled`() {
        val result = loadedStateWithDynamicColorLoading()
            .updateDynamicColor(AppDynamicColor.Disabled)
            .dynamicColor?.checkedState

        assertThat(result).isEqualTo(DynamicColorCheckedState.Loaded(false))
    }

    @Test
    fun `does not crash when dynamic color is null`() {
        val result = loadedStateWithNullDynamicColor()
            .updateDynamicColor(AppDynamicColor.Enabled)
            .dynamicColor

        assertThat(result).isNull()
    }
}
