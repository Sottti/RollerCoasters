package com.sottti.roller.coasters.domain.settings.usecase

import com.sottti.roller.coasters.domain.model.Language
import com.sottti.roller.coasters.domain.settings.repository.SettingsRepository
import javax.inject.Inject

public class GetLanguage @Inject constructor(
    private val settingsRepository: SettingsRepository,
) {
    public suspend operator fun invoke(): Language {
        return settingsRepository.getLanguage()
    }
}