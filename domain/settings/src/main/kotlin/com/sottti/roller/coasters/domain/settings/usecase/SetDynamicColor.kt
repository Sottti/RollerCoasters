package com.sottti.roller.coasters.domain.settings.usecase

import com.sottti.roller.coasters.domain.settings.repository.SettingsRepository
import javax.inject.Inject

public class SetDynamicColor @Inject constructor(
    private val settingsRepository: SettingsRepository,
) {
    public suspend operator fun invoke(dynamicColor: Boolean) {
        settingsRepository.setDynamicColor(dynamicColor)
    }
}