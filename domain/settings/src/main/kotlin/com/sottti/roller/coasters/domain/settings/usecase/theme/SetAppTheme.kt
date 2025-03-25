package com.sottti.roller.coasters.domain.settings.usecase.theme

import com.sottti.roller.coasters.domain.settings.model.theme.AppTheme
import com.sottti.roller.coasters.domain.settings.repository.SettingsRepository
import javax.inject.Inject

public class SetAppTheme @Inject constructor(
    private val settingsRepository: SettingsRepository,
) {
    public suspend operator fun invoke(appTheme: AppTheme) {
        settingsRepository.setAppTheme(appTheme)
    }
}