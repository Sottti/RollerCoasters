package com.sottti.roller.coasters.domain.settings.usecase.colorContrast

import com.sottti.roller.coasters.domain.settings.mapper.toResolvedColorContrast
import com.sottti.roller.coasters.domain.settings.model.colorContrast.AppColorContrast
import com.sottti.roller.coasters.domain.settings.model.colorContrast.ResolvedColorContrast
import com.sottti.roller.coasters.domain.settings.model.colorContrast.SystemColorContrast
import com.sottti.roller.coasters.domain.settings.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

public class ObserveResolvedColorContrast @Inject constructor(
    private val settingsRepository: SettingsRepository,
) {
    public operator fun invoke(): Flow<ResolvedColorContrast> =
        combine(
            settingsRepository.observeAppColorContrast(),
            settingsRepository.observeSystemColorContrast(),
        ) { appColorContrast, systemColorContrast ->
            appColorContrast.resolve(systemColorContrast)
        }

    private fun AppColorContrast.resolve(
        systemColorContrast: SystemColorContrast,
    ): ResolvedColorContrast = when (this) {
        AppColorContrast.HighContrast,
        AppColorContrast.MediumContrast,
        AppColorContrast.StandardContrast,
            -> toResolvedColorContrast()

        AppColorContrast.System -> systemColorContrast.toResolvedColorContrast()
    }
}
