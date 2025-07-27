package com.sottti.roller.coasters.domain.settings.mapper

import com.sottti.roller.coasters.domain.settings.model.colorContrast.AppColorContrast
import com.sottti.roller.coasters.domain.settings.model.colorContrast.ResolvedColorContrast
import com.sottti.roller.coasters.domain.settings.model.colorContrast.SystemColorContrast
import org.junit.Assert
import org.junit.Test
import kotlin.test.assertFailsWith

internal class ResolvedColorContrastMappersTest {

    @Test
    fun `app high contrast maps to resolved high contrast`() {
        val result = AppColorContrast.HighContrast.toResolvedColorContrast()
        Assert.assertEquals(ResolvedColorContrast.HighContrast, result)
    }

    @Test
    fun `app medium contrast maps to resolved medium contrast`() {
        val result = AppColorContrast.MediumContrast.toResolvedColorContrast()
        Assert.assertEquals(ResolvedColorContrast.MediumContrast, result)
    }

    @Test
    fun `app standard contrast maps to resolved standard contrast`() {
        val result = AppColorContrast.StandardContrast.toResolvedColorContrast()
        Assert.assertEquals(ResolvedColorContrast.StandardContrast, result)
    }

    @Test
    fun `app system contrast throws exception for unresolved state`() {
        assertFailsWith<IllegalStateException> { AppColorContrast.System.toResolvedColorContrast() }
    }

    @Test
    fun `app system contrast throws exception with specific message`() {
        val exception = assertFailsWith<IllegalStateException> {
            AppColorContrast.System.toResolvedColorContrast()
        }
        Assert.assertEquals(SYSTEM_CONTRAST_UNRESOLVED_MESSAGE, exception.message)
    }

    @Test
    fun `system high contrast maps to resolved high contrast`() {
        val result = SystemColorContrast.HighContrast.toResolvedColorContrast()
        Assert.assertEquals(ResolvedColorContrast.HighContrast, result)
    }

    @Test
    fun `system low contrast maps to resolved low contrast`() {
        val result = SystemColorContrast.LowContrast.toResolvedColorContrast()
        Assert.assertEquals(ResolvedColorContrast.LowContrast, result)
    }

    @Test
    fun `system medium contrast maps to resolved medium contrast`() {
        val result = SystemColorContrast.MediumContrast.toResolvedColorContrast()
        Assert.assertEquals(ResolvedColorContrast.MediumContrast, result)
    }

    @Test
    fun `system standard contrast maps to resolved standard contrast`() {
        val result = SystemColorContrast.StandardContrast.toResolvedColorContrast()
        Assert.assertEquals(ResolvedColorContrast.StandardContrast, result)
    }
}
