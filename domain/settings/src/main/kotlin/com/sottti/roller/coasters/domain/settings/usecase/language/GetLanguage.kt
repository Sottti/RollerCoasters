package com.sottti.roller.coasters.domain.settings.usecase.language

import com.sottti.roller.coasters.domain.settings.model.language.AppLanguage
import com.sottti.roller.coasters.domain.settings.repository.SettingsRepository
import javax.inject.Inject

public class GetLanguage @Inject constructor(
    private val settingsRepository: SettingsRepository,
) {
    public suspend operator fun invoke(): AppLanguage {
        return settingsRepository.getLanguage()
    }
}