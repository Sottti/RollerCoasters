package com.sottti.roller.coasters.domain.settings.usecase

import com.sottti.roller.coasters.domain.model.ColorContrast
import com.sottti.roller.coasters.domain.settings.repository.SettingsRepository
import javax.inject.Inject

public class SetColorContrast @Inject constructor(
    private val settingsRepository: SettingsRepository,
) {
    public suspend operator fun invoke(colorContrast: ColorContrast) {
        settingsRepository.setColorContrast(colorContrast)
    }
}