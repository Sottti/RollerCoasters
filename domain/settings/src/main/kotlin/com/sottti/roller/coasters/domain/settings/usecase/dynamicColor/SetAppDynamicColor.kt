package com.sottti.roller.coasters.domain.settings.usecase.dynamicColor

import com.sottti.roller.coasters.domain.settings.model.dynamicColor.AppDynamicColor
import com.sottti.roller.coasters.domain.settings.repository.SettingsRepository
import javax.inject.Inject

public class SetAppDynamicColor @Inject constructor(
    private val settingsRepository: SettingsRepository,
) {
    public suspend operator fun invoke(appDynamicColor: AppDynamicColor) {
        settingsRepository.setAppDynamicColor(appDynamicColor)
    }
}
