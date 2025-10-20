package com.sottti.roller.coasters.domain.settings.usecase.theme

import com.sottti.roller.coasters.domain.settings.mapper.toResolvedTheme
import com.sottti.roller.coasters.domain.settings.model.theme.AppTheme
import com.sottti.roller.coasters.domain.settings.model.theme.ResolvedTheme
import com.sottti.roller.coasters.domain.settings.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

public class ObserveResolvedTheme @Inject constructor(
    private val settingsRepository: SettingsRepository,
) {
    public operator fun invoke(): Flow<ResolvedTheme> =
        settingsRepository
            .observeAppTheme()
            .map { appTheme -> appTheme.resolve() }

    private suspend fun AppTheme.resolve(): ResolvedTheme =
        when (this) {
            AppTheme.LightAppTheme -> ResolvedTheme.LightAppTheme
            AppTheme.DarkAppTheme -> ResolvedTheme.DarkAppTheme
            AppTheme.System -> settingsRepository
                .getSystemTheme()
                .toResolvedTheme()
        }
}
