package com.sottti.roller.coasters.domain.settings.usecase.theme

import com.sottti.roller.coasters.domain.settings.model.theme.AppTheme
import com.sottti.roller.coasters.domain.settings.model.theme.ResolvedTheme
import com.sottti.roller.coasters.domain.settings.model.theme.SystemTheme
import com.sottti.roller.coasters.domain.settings.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import javax.inject.Inject

public class ObserveResolvedTheme @Inject constructor(
    private val settingsRepository: SettingsRepository,
) {
    public operator fun invoke(): Flow<ResolvedTheme> =
        combine(
            flow = settingsRepository.observeAppTheme(),
            flow2 = settingsRepository.observeSystemTheme(),
        ) { appTheme, systemTheme ->
            when (appTheme) {
                AppTheme.LightAppTheme -> ResolvedTheme.LightResolvedTheme
                AppTheme.DarkAppTheme -> ResolvedTheme.DarkResolvedTheme
                AppTheme.System -> when (systemTheme) {
                    SystemTheme.LightSystemTheme -> ResolvedTheme.LightResolvedTheme
                    SystemTheme.DarkSystemTheme -> ResolvedTheme.DarkResolvedTheme
                }
            }
        }
            .distinctUntilChanged()
}
