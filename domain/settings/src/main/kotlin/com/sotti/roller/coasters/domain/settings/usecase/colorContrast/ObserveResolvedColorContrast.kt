package com.sotti.roller.coasters.domain.settings.usecase.colorContrast

import com.sotti.roller.coasters.domain.settings.mapper.toResolvedColorContrast
import com.sotti.roller.coasters.domain.settings.model.colorContrast.AppColorContrast
import com.sotti.roller.coasters.domain.settings.model.colorContrast.ResolvedColorContrast
import com.sotti.roller.coasters.domain.settings.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

public class ObserveResolvedColorContrast @Inject constructor(
    private val settingsRepository: SettingsRepository,
) {
    public operator fun invoke(): Flow<ResolvedColorContrast> =
        settingsRepository
            .observeAppColorContrast()
            .map { appColorContrast -> appColorContrast.resolve() }

    private fun AppColorContrast.resolve(): ResolvedColorContrast =
        when (this) {
            AppColorContrast.HighContrast,
            AppColorContrast.MediumContrast,
            AppColorContrast.StandardContrast,
                -> toResolvedColorContrast()

            AppColorContrast.System ->
                settingsRepository
                    .getSystemColorContrast()
                    .toResolvedColorContrast()
        }
}
