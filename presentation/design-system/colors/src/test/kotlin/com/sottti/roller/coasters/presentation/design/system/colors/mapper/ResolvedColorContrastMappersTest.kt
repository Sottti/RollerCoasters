package com.sottti.roller.coasters.presentation.design.system.colors.mapper

import androidx.compose.material3.ColorScheme
import com.google.common.truth.Truth
import com.sottti.roller.coasters.domain.settings.model.colorContrast.ResolvedColorContrast
import com.sottti.roller.coasters.presentation.design.system.colors.color.ColorSchemes
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import org.junit.Before
import org.junit.Test

internal class ResolvedColorContrastMappersTest {

    private lateinit var darkHighContrastScheme: ColorScheme
    private lateinit var darkMediumContrastScheme: ColorScheme
    private lateinit var darkStandardContrastScheme: ColorScheme
    private lateinit var lightHighContrastScheme: ColorScheme
    private lateinit var lightMediumContrastScheme: ColorScheme
    private lateinit var lightStandardContrastScheme: ColorScheme

    @Before
    fun setUp() {
        darkHighContrastScheme = mockk()
        darkMediumContrastScheme = mockk()
        darkStandardContrastScheme = mockk()
        lightHighContrastScheme = mockk()
        lightMediumContrastScheme = mockk()
        lightStandardContrastScheme = mockk()

        mockkObject(ColorSchemes.Dark)
        mockkObject(ColorSchemes.Light)

        every { ColorSchemes.Dark.highContrast } returns darkHighContrastScheme
        every { ColorSchemes.Dark.mediumContrast } returns darkMediumContrastScheme
        every { ColorSchemes.Dark.standardContrast } returns darkStandardContrastScheme

        every { ColorSchemes.Light.highContrast } returns lightHighContrastScheme
        every { ColorSchemes.Light.mediumContrast } returns lightMediumContrastScheme
        every { ColorSchemes.Light.standardContrast } returns lightStandardContrastScheme
    }

    @Test
    fun `high contrast maps to dark high contrast scheme`() {
        val result = ResolvedColorContrast.HighContrast.darkColorScheme()
        Truth.assertThat(result).isEqualTo(darkHighContrastScheme)
    }

    @Test
    fun `medium contrast maps to dark medium contrast scheme`() {
        val result = ResolvedColorContrast.MediumContrast.darkColorScheme()
        Truth.assertThat(result).isEqualTo(darkMediumContrastScheme)
    }

    @Test
    fun `standard contrast maps to dark standard contrast scheme`() {
        val result = ResolvedColorContrast.StandardContrast.darkColorScheme()
        Truth.assertThat(result).isEqualTo(darkStandardContrastScheme)
    }

    @Test
    fun `low contrast maps to dark standard contrast scheme`() {
        val result = ResolvedColorContrast.LowContrast.darkColorScheme()
        Truth.assertThat(result).isEqualTo(darkStandardContrastScheme)
    }

    @Test
    fun `high contrast maps to light high contrast scheme`() {
        val result = ResolvedColorContrast.HighContrast.lightColorScheme()
        Truth.assertThat(result).isEqualTo(lightHighContrastScheme)
    }

    @Test
    fun `medium contrast maps to light medium contrast scheme`() {
        val result = ResolvedColorContrast.MediumContrast.lightColorScheme()
        Truth.assertThat(result).isEqualTo(lightMediumContrastScheme)
    }

    @Test
    fun `standard contrast maps to light standard contrast scheme`() {
        val result = ResolvedColorContrast.StandardContrast.lightColorScheme()
        Truth.assertThat(result).isEqualTo(lightStandardContrastScheme)
    }

    @Test
    fun `low contrast maps to light standard contrast scheme`() {
        val result = ResolvedColorContrast.LowContrast.lightColorScheme()
        Truth.assertThat(result).isEqualTo(lightStandardContrastScheme)
    }
}
