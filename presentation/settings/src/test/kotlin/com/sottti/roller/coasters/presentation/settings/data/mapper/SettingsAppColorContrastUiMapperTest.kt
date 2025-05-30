package com.sottti.roller.coasters.presentation.settings.data.mapper

import com.google.common.truth.Truth.assertThat
import com.sottti.roller.coasters.domain.settings.model.colorContrast.AppColorContrast
import com.sottti.roller.coasters.presentation.design.system.dialogs.radioButtons.DialogRadioButtonOption
import org.junit.Test

internal class SettingsAppColorContrastUiMapperTest {

    @Test
    fun `domain to UI and back to domain remains consistent`() {
        AppColorContrast.entries.forEach {
            val ui = it.toPresentationModel(true)
            assertThat(ui.toDomain()).isEqualTo(it)
        }
    }

    @Test
    fun `each app color contrast maps to correct UI model with selection`() {
        AppColorContrast.entries.forEach {
            val expected = it.toPresentationModel(true)
            val actual = it.toPresentationModel(true)
            assertThat(actual.selected).isTrue()
            assertThat(actual).isEqualTo(expected)
        }
    }

    @Test
    fun `UI model maps back to correct app color contrast`() {
        val ui = AppColorContrast.HighContrast.toPresentationModel(selected = false)
        val domain = ui.toDomain()
        assertThat(domain).isEqualTo(AppColorContrast.HighContrast)
    }

    @Test
    fun `UI model maps to radio button option correctly`() {
        val ui = AppColorContrast.StandardContrast.toPresentationModel(selected = true)
        val option = ui.toRadioButtonOption()
        assertThat(option.text).isEqualTo(ui.text)
        assertThat(option.icon).isEqualTo(ui.icon)
        assertThat(option.selected).isEqualTo(ui.selected)
    }

    @Test
    fun `radio button option finds matching UI model`() {
        val list = AppColorContrast.entries.map { it.toPresentationModel(false) }
        val option = list[2].toRadioButtonOption()
        val matched = option.toAppColorContrastUi(list)
        assertThat(matched).isEqualTo(list[2])
    }

    @Test
    fun `radio button option falls back to first UI model if no match found`() {
        val list = AppColorContrast.entries.map { it.toPresentationModel(false) }
        val unmatched = DialogRadioButtonOption(
            text = -999,
            icon = list.first().icon,
            selected = false,
        )
        val fallback = unmatched.toAppColorContrastUi(list)
        assertThat(fallback).isEqualTo(list.first())
    }

    @Test(expected = NoSuchElementException::class)
    fun `radio button option fallback fails with empty UI model list`() {
        val option = DialogRadioButtonOption(
            text = -1,
            icon = com.sottti.roller.coasters.presentation.design.system.icons.data.Icons.BrightnessAuto.outlined,
            selected = false
        )
        option.toAppColorContrastUi(emptyList())
    }

    @Test
    fun `icon changes based on selection state`() {
        val contrast = AppColorContrast.MediumContrast
        val selectedIcon = contrast.toPresentationModel(true).icon
        val unselectedIcon = contrast.toPresentationModel(false).icon
        assertThat(selectedIcon).isNotEqualTo(unselectedIcon)
    }

    @Test
    fun `each app color contrast maps to unique resource id`() {
        val ids = AppColorContrast.entries.map { it.toPresentationModel(false).text }
        assertThat(ids.toSet().size).isEqualTo(ids.size)
    }
}