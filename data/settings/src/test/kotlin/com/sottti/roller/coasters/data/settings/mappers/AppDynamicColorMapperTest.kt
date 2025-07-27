package com.sottti.roller.coasters.data.settings.mappers

import com.google.common.truth.Truth.assertThat
import com.sottti.roller.coasters.data.settings.mapper.toAppDynamicColor
import com.sottti.roller.coasters.data.settings.mapper.toBoolean
import com.sottti.roller.coasters.domain.settings.model.dynamicColor.AppDynamicColor
import org.junit.Test

internal class AppDynamicColorMapperTest {

    @Test
    fun `to app dynamic color maps true to enabled`() {
        val result = true.toAppDynamicColor()
        assertThat(result).isEqualTo(AppDynamicColor.Enabled)
    }

    @Test
    fun `to app dynamic color maps false to disabled`() {
        val result = false.toAppDynamicColor()
        assertThat(result).isEqualTo(AppDynamicColor.Disabled)
    }

    @Test
    fun `to boolean maps enabled to true`() {
        val result = AppDynamicColor.Enabled.toBoolean()
        assertThat(result).isTrue()
    }

    @Test
    fun `to boolean maps disabled to false`() {
        val result = AppDynamicColor.Disabled.toBoolean()
        assertThat(result).isFalse()
    }

    @Test
    fun `round trip conversion from boolean to app dynamic color and back preserves value`() {
        listOf(true, false).forEach { boolean ->
            val result = boolean.toAppDynamicColor().toBoolean()
            assertThat(result).isEqualTo(boolean)
        }
    }

    @Test
    fun `round trip conversion from app dynamic color to boolean and back preserves value`() {
        listOf(AppDynamicColor.Enabled, AppDynamicColor.Disabled).forEach { appDynamicColor ->
            val result = appDynamicColor.toBoolean().toAppDynamicColor()
            assertThat(result).isEqualTo(appDynamicColor)
        }
    }
}
