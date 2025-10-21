package com.sottti.roller.coasters.domain.settings.usecase.theme

import com.sottti.roller.coasters.domain.settings.model.theme.SystemTheme
import com.sottti.roller.coasters.domain.settings.repository.SettingsRepository
import javax.inject.Inject

public class GetSystemTheme @Inject constructor(
    private val settingsRepository: SettingsRepository,
) {
    public operator fun invoke(): SystemTheme =
        settingsRepository.getSystemTheme()
}
