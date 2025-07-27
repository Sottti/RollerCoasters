package com.sottti.roller.coasters.presentation.settings.data.mapper

import com.google.common.truth.Truth.assertThat
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.AppMeasurementSystem
import com.sottti.roller.coasters.presentation.design.system.dialogs.radioButtons.DialogRadioButtonOption
import com.sottti.roller.coasters.presentation.design.system.icons.data.Icons
import org.junit.Test

internal class SettingsAppMeasurementSystemUiMapperTest {

    @Test
    fun `each app measurement system maps to correct UI model with selection`() {
        AppMeasurementSystem.entries.forEach {
            val expected = it.toPresentationModel(true)
            val actual = it.toPresentationModel(true)
            assertThat(actual).isEqualTo(expected)
        }
    }

    @Test
    fun `domain to UI and back to domain remains consistent`() {
        AppMeasurementSystem.entries.forEach {
            val ui = it.toPresentationModel(true)
            val domain = ui.toDomain()
            assertThat(domain).isEqualTo(it)
        }
    }

    @Test
    fun `icon changes based on selection state`() {
        val system = AppMeasurementSystem.System
        val selectedIcon = system.toPresentationModel(true).icon
        val unselectedIcon = system.toPresentationModel(false).icon
        assertThat(selectedIcon).isNotEqualTo(unselectedIcon)
    }

    @Test
    fun `each app measurement system maps to unique resource id`() {
        val ids = AppMeasurementSystem.entries.map { it.toPresentationModel(false).text }
        assertThat(ids.toSet().size).isEqualTo(ids.size)
    }

    @Test
    fun `UI model maps to radio button option correctly`() {
        val ui = AppMeasurementSystem.ImperialUk.toPresentationModel(true)
        val option = ui.toRadioButtonOption()
        assertThat(option.text).isEqualTo(ui.text)
        assertThat(option.icon).isEqualTo(ui.icon)
        assertThat(option.selected).isEqualTo(ui.selected)
    }

    @Test
    fun `radio button option finds matching UI model`() {
        val list = AppMeasurementSystem.entries.map { it.toPresentationModel(false) }
        val option = list[1].toRadioButtonOption()
        val matched = option.toAppMeasurementSystemUi(list)
        assertThat(matched).isEqualTo(list[1])
    }

    @Test
    fun `radio button option falls back to first UI model if no match found`() {
        val list = AppMeasurementSystem.entries.map { it.toPresentationModel(false) }
        val unmatched = DialogRadioButtonOption(
            text = -999,
            icon = Icons.Straighten.outlined,
            selected = false,
        )
        val fallback = unmatched.toAppMeasurementSystemUi(list)
        assertThat(fallback).isEqualTo(list.first())
    }

    @Test(expected = NoSuchElementException::class)
    fun `radio button option fallback fails with empty UI model list`() {
        val option = DialogRadioButtonOption(
            text = -1,
            icon = Icons.Straighten.outlined,
            selected = false,
        )
        option.toAppMeasurementSystemUi(emptyList())
    }
}
