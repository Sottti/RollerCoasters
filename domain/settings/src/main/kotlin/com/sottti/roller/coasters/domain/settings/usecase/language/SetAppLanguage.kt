package com.sottti.roller.coasters.domain.settings.usecase.language

import com.sottti.roller.coasters.domain.settings.model.language.AppLanguage
import com.sottti.roller.coasters.domain.settings.repository.SettingsRepository
import javax.inject.Inject

public class SetAppLanguage @Inject constructor(
    private val settingsRepository: SettingsRepository,
) {
    public operator fun invoke(appLanguage: AppLanguage) {
        settingsRepository.setLanguage(appLanguage)
    }
}