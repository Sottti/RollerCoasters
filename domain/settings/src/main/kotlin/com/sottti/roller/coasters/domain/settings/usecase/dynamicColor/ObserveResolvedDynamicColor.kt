package com.sottti.roller.coasters.domain.settings.usecase.dynamicColor

import com.sottti.roller.coasters.domain.features.Features
import com.sottti.roller.coasters.domain.settings.model.dynamicColor.ResolvedDynamicColor
import com.sottti.roller.coasters.domain.settings.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import javax.inject.Inject

public class ObserveResolvedDynamicColor @Inject constructor(
    private val features: Features,
    private val settingsRepository: SettingsRepository,
) {
    public operator fun invoke(): Flow<ResolvedDynamicColor> =
        when {
            features.systemDynamicColorAvailable() ->
                settingsRepository
                    .observeAppDynamicColor()
                    .map { appDynamicColor -> ResolvedDynamicColor(appDynamicColor.enabled) }

            else -> flowOf(ResolvedDynamicColor(false))
        }
}