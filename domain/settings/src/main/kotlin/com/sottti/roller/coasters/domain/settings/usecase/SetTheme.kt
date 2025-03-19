package com.sottti.roller.coasters.domain.settings.usecase

import com.sottti.roller.coasters.domain.settings.model.Theme
import com.sottti.roller.coasters.domain.settings.repository.SettingsRepository
import javax.inject.Inject

public class SetTheme @Inject constructor(
    private val settingsRepository: SettingsRepository,
) {
    public suspend operator fun invoke(theme: Theme) {
        settingsRepository.setTheme(theme)
    }
}