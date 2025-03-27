package com.sottti.roller.coasters.domain.settings.usecase.colorContrast

import com.sottti.roller.coasters.domain.settings.model.colorContrast.AppColorContrast
import com.sottti.roller.coasters.domain.settings.repository.SettingsRepository
import javax.inject.Inject

public class GetAppColorContrast @Inject constructor(
    private val settingsRepository: SettingsRepository,
) {
    public suspend operator fun invoke(): AppColorContrast =
        settingsRepository.getAppColorContrast()
}