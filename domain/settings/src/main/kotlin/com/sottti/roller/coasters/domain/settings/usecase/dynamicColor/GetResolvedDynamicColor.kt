package com.sottti.roller.coasters.domain.settings.usecase.dynamicColor

import com.sottti.roller.coasters.domain.system.features.SystemFeatures
import com.sottti.roller.coasters.domain.settings.mapper.toResolvedDynamicColor
import com.sottti.roller.coasters.domain.settings.model.dynamicColor.ResolvedDynamicColor
import com.sottti.roller.coasters.domain.settings.repository.SettingsRepository
import javax.inject.Inject

public class GetResolvedDynamicColor @Inject constructor(
    private val systemFeatures: SystemFeatures,
    private val settingsRepository: SettingsRepository,
) {
    public suspend operator fun invoke(): ResolvedDynamicColor =
        when {
            systemFeatures.systemDynamicColorAvailable() ->
                settingsRepository
                    .getAppDynamicColor()
                    .toResolvedDynamicColor()

            else -> ResolvedDynamicColor(enabled = false)
        }
}
