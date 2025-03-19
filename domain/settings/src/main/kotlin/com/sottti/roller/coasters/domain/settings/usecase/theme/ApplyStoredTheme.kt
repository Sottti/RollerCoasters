package com.sottti.roller.coasters.domain.settings.usecase.theme

import com.sottti.roller.coasters.domain.settings.repository.SettingsRepository
import javax.inject.Inject

public class ApplyStoredTheme @Inject constructor(
    private val settingsRepository: SettingsRepository,
) {
    public suspend operator fun invoke() {
        settingsRepository.applyStoredTheme()
    }
}