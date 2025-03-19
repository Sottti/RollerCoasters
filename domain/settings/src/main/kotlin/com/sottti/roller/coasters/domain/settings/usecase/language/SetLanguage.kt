package com.sottti.roller.coasters.domain.settings.usecase.language

import com.sottti.roller.coasters.domain.settings.model.Language
import com.sottti.roller.coasters.domain.settings.repository.SettingsRepository
import javax.inject.Inject

public class SetLanguage @Inject constructor(
    private val settingsRepository: SettingsRepository,
) {
    public operator fun invoke(language: Language) {
        settingsRepository.setLanguage(language)
    }
}