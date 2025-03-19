package com.sottti.roller.coasters.domain.settings.mappers

import com.sottti.roller.coasters.domain.settings.model.colorContrast.AppColorContrast
import com.sottti.roller.coasters.domain.settings.model.colorContrast.ResolvedColorContrast
import com.sottti.roller.coasters.domain.settings.model.colorContrast.SystemColorContrast
import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.test.assertFailsWith

class ColorContrastMappersTest {

    @Test
    fun `app high contrast maps to resolved high contrast`() {
        val result = AppColorContrast.HighContrast.toResolvedColorContrast()
        assertEquals(ResolvedColorContrast.HighContrast, result)
    }

    @Test
    fun `app medium contrast maps to resolved medium contrast`() {
        val result = AppColorContrast.MediumContrast.toResolvedColorContrast()
        assertEquals(ResolvedColorContrast.MediumContrast, result)
    }

    @Test
    fun `app standard contrast maps to resolved standard contrast`() {
        val result = AppColorContrast.StandardContrast.toResolvedColorContrast()
        assertEquals(ResolvedColorContrast.StandardContrast, result)
    }

    @Test
    fun `app system contrast throws exception for unresolved state`() {
        assertFailsWith<IllegalStateException> { AppColorContrast.SystemContrast.toResolvedColorContrast() }
    }

    @Test
    fun `system high contrast maps to resolved high contrast`() {
        val result = SystemColorContrast.HighContrast.toResolvedColorContrast()
        assertEquals(ResolvedColorContrast.HighContrast, result)
    }

    @Test
    fun `system low contrast maps to resolved low contrast`() {
        val result = SystemColorContrast.LowContrast.toResolvedColorContrast()
        assertEquals(ResolvedColorContrast.LowContrast, result)
    }

    @Test
    fun `system medium contrast maps to resolved medium contrast`() {
        val result = SystemColorContrast.MediumContrast.toResolvedColorContrast()
        assertEquals(ResolvedColorContrast.MediumContrast, result)
    }

    @Test
    fun `system standard contrast maps to resolved standard contrast`() {
        val result = SystemColorContrast.StandardContrast.toResolvedColorContrast()
        assertEquals(ResolvedColorContrast.StandardContrast, result)
    }
}