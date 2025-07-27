package com.sottti.roller.coasters.presentation.settings.data.reducer

import com.google.common.truth.Truth.assertThat
import com.sottti.roller.coasters.domain.settings.model.language.AppLanguage
import com.sottti.roller.coasters.presentation.settings.data.loadedState
import com.sottti.roller.coasters.presentation.settings.data.mapper.toDomain
import com.sottti.roller.coasters.presentation.settings.data.mapper.toPresentationModel
import com.sottti.roller.coasters.presentation.settings.model.AppSelectedLanguageState.Loaded
import org.junit.Test

internal class SettingsAppLanguageReducerTest {

    @Test
    fun `updates app language`() {
        val initialState = loadedState()
        val result = initialState.updateAppLanguage(AppLanguage.SpanishSpain)
        val selectedAppLanguage = result.appLanguage.listItem.selectedAppLanguage
        val isSelected = (selectedAppLanguage as? Loaded)?.appLanguage?.selected
        val isCorrectLanguage =
            (selectedAppLanguage as? Loaded)?.appLanguage?.toDomain() == AppLanguage.SpanishSpain
        assertThat(isSelected).isTrue()
        assertThat(isCorrectLanguage).isTrue()
    }

    @Test
    fun `updates app language picker`() {
        val initialState = loadedState()
        val selectedLanguage = AppLanguage.EnglishGb.toPresentationModel(selected = true)
        val result = initialState.updateAppLanguagePicker(selectedLanguage)
        val picker = result.appLanguage.picker
        assertThat(picker?.appLanguages).isNotEmpty()
        assertThat(picker?.appLanguages?.any {
            it.selected && it.toDomain() == AppLanguage.EnglishGb
        }).isTrue()
    }

    @Test
    fun `shows app language picker`() {
        val initialState = loadedState()
        val result = initialState.showAppLanguagePicker(AppLanguage.EnglishGb)
        val picker = result.appLanguage.picker
        assertThat(picker).isNotNull()
        assertThat(picker?.appLanguages?.any {
            it.selected && it.toDomain() == AppLanguage.EnglishGb
        }).isTrue()
    }

    @Test
    fun `hides app language picker`() {
        val state = loadedState().showAppLanguagePicker(AppLanguage.EnglishGb)
        val result = state.hideAppLanguagePicker()
        assertThat(result.appLanguage.picker).isNull()
    }
}
