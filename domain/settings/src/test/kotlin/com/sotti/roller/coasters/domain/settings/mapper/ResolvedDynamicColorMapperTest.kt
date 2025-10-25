package com.sotti.roller.coasters.domain.settings.mapper

import com.sotti.roller.coasters.domain.settings.model.dynamicColor.AppDynamicColor
import com.sotti.roller.coasters.domain.settings.model.dynamicColor.ResolvedDynamicColor
import org.junit.Assert
import org.junit.Test

internal class ResolvedDynamicColorMapperTest {

    @Test
    fun `app dynamic color enabled maps to resolved dynamic color with enabled true`() {
        val result = AppDynamicColor.Enabled.toResolvedDynamicColor()
        Assert.assertEquals(ResolvedDynamicColor(enabled = true), result)
    }

    @Test
    fun `app dynamic color disabled maps to resolved dynamic color with enabled false`() {
        val result = AppDynamicColor.Disabled.toResolvedDynamicColor()
        Assert.assertEquals(ResolvedDynamicColor(enabled = false), result)
    }
}
